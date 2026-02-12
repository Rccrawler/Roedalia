package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class CaballerosDelPortonNorte implements Runnable {

    Socket skCliente = null;

    private String nombre;
    public CaballerosDelPortonNorte(String nom){
        this.nombre = nom;
    }

    @Override
    public void run() {
        Random random = new Random();
        String lugar = "algun lugar";

        while (true){

            int labor = random.nextInt(2);

            Socket skCliente = null;
            DataOutputStream flujo_salida = null;
            DataInputStream flujo_entrada = null;

            switch (labor){
                case 0:
                    labor = random.nextInt(3);

                    if (labor == 0){
                        lugar = "el Portón Norte";// Kurmin Jatau
                    } else if (labor == 1) {
                        lugar = "la Muralla";
                    } else if (labor == 2) {
                        lugar = "las Torres";
                    }

                    System.out.println(nombre + " Realizare labores de vigilancia en " + lugar + " de la ciudad");

                    try {
                        Thread.sleep(6000); // Pausa el hilo actual por 3 segundos
                    } catch (InterruptedException e) {
                        System.err.println(nombre + " El hilo fue interrumpido: " + e.getMessage());
                    }
                    break;

                case 1:
                    System.out.println(nombre + " Ire a hablar con Lance");

                    int esAttendido = 0;
                    int contConsexionIntent = 0;
                    int notificar = 1;

                    while (esAttendido == 0) { // esperamos a la conesion 20 segundos
                        try {
                            labor = random.nextInt(100); //del  0 al 99
                            String mensaje = "";
                            if(labor < 37){
                                mensaje = "confidencia personal";
                            }else if (labor < 75){
                                mensaje = "rumor infundado";
                            } else { // del 75 al 99 un 25/100 de probabilidad
                                mensaje = "ofensa sobre Elisabetha";
                            }

                            skCliente = new Socket("localhost", 5000);
                            flujo_salida = new DataOutputStream(skCliente.getOutputStream());
                            flujo_entrada = new DataInputStream(skCliente.getInputStream());

                            flujo_salida.writeUTF(nombre);

                            if (notificar == 1){
                                System.out.println(nombre + " Esta en el porton norte"); // que solo se inprima una vez
                                notificar = 0;
                            }

                            String esta = flujo_entrada.readUTF();

                            if(esta.equals("la encontre")){
                                System.out.println(nombre + "Boy a hablar con Lince Du Lac Dion");
                                esAttendido = 1;
                                flujo_salida.writeUTF(mensaje);

                                // si es retado aun duelo
                                if(mensaje.equals("ofensa sobre Elisabetha")){
                                    String respuesta = flujo_entrada.readUTF(); // retado por lance
                                    System.out.println(nombre + " " + respuesta); // mensaje de que es retado
                                    int reflejos = random.nextInt(100); // quien mas reflejos tenga antes dara el golpe
                                    int reflejos_rival = flujo_entrada.readInt();
                                    flujo_salida.writeInt(reflejos);
                                    if (reflejos < reflejos_rival){
                                        System.out.println(nombre + " fue alcanzado por lanze con su lanza");
                                        try {
                                            Thread.sleep(30000); // 30 segundos = 30000 milisegundos
                                        } catch (InterruptedException e) {
                                            Thread.currentThread().interrupt();
                                        }
                                    } else {
                                        System.out.println(nombre + " alcanzo a lanze con su lanza");
                                    }
                                }
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
                        if (contConsexionIntent == 25){
                            esAttendido = -1;
                            System.out.println(nombre + " Pues no viene eli me voy a otra cosa");
                        }
                    }
                    break;
            }
        }

    }
}
