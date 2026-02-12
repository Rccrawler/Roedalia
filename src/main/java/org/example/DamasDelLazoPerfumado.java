package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class DamasDelLazoPerfumado implements Runnable {

    Socket skCliente = null;

    private String nombre;

    public DamasDelLazoPerfumado(String nom){
        this.nombre = nom;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (true){

            int result = random.nextInt(2);

            Socket skCliente = null;
            DataOutputStream flujo_salida = null;
            DataInputStream flujo_entrada = null;

            switch (result){
                case 0:
                    System.out.println(nombre + " Realizar labores propias de las Damas del Lazo");
                    result = random.nextInt(3);
                    try {
                        Thread.sleep(5000); // Pausa el hilo actual por 3 segundos
                    } catch (InterruptedException e) {
                        System.err.println(nombre + " El hilo fue interrumpido: " + e.getMessage());
                    }
                    switch (result){
                        case 0:
                            System.out.println(nombre + " monta a caballo");
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                System.err.println(nombre + "El hilo fue interrumpido: " + e.getMessage());
                            }
                            break;
                        case 1:
                            System.out.println(nombre + " practica esgrima");
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                System.err.println(nombre + "El hilo fue interrumpido: " + e.getMessage());
                            }
                            break;
                        case 2:
                            System.out.println(nombre + " enterarse de rumores");
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                System.err.println(nombre + "El hilo fue interrumpido: " + e.getMessage());
                            }
                            break;
                    }
                    break;

                case 1:

                    System.out.println(nombre + " ir a hablar con eli");

                    int esAttendida = 0;
                    int contConsexionIntent = 0;
                    int notificar = 1;
                    while (esAttendida == 0){ // esperamos a la conesion 20 segundos
                        try {

                            result = random.nextInt(2);
                            String mensaje = "";
                            if(result == 0){
                                mensaje = "confidencia";
                            }else {
                                mensaje = "rumor";
                            }

                            skCliente = new Socket("localhost", 5000);
                            flujo_salida = new DataOutputStream(skCliente.getOutputStream());
                            flujo_entrada = new DataInputStream(skCliente.getInputStream());

                            flujo_salida.writeUTF(nombre);

                            if (notificar == 1){
                                System.out.println(nombre + " Esta en la sala del castillo"); // que solo se inprima una vez
                                notificar = 0;
                            }

                            String esta = flujo_entrada.readUTF();

                            if(esta.equals("la encontre")){
                                System.out.println(nombre + "Boy a realizar confesiones a Elisabetha");
                                esAttendida = 1;
                                System.out.println(nombre + "esta la princesa en el castillo");
                                flujo_salida.writeUTF(mensaje);
                            }

                        } catch (IOException e){
                            //System.out.println(nombre + " error en el socket");
                            //System.out.println(e.getMessage());
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException o) {
                                System.err.println(nombre + "El hilo fue interrumpido: " + e.getMessage());
                            }
                            contConsexionIntent = (contConsexionIntent+1);
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            System.err.println(nombre + " El hilo fue interrumpido: " + e.getMessage());
                        }
                        contConsexionIntent = (contConsexionIntent+1);
                        if (contConsexionIntent == 20){
                            esAttendida = -1;
                            System.out.println(nombre + " Pues no viene eli me voy a otra cosa");
                        }
                    }
                    break;
                // segir conbersacion con castillo recordar que hay que pasar la variable mensaje
            }

        }

    }
}
