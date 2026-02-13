package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class Alquimistas implements Runnable {

    //Threads de alta prioridad
    // investigar que es un Thread de alta prioridad

    // se conectaran al castillo para hablar con lanze y tendra que avisar a lanze

    private String nombre;
    private AlacenaPociones alacenaPociones;

    public Alquimistas(AlacenaPociones alaPo, String nom){
        this.nombre = nom;
        this.alacenaPociones = alaPo;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (true){

            int result = random.nextInt(100);

            int estanteEli = 0;
            int estanteLan = 0;

            String deciosion = "";

            //String mensaje;

            Socket skCliente = null;
            DataOutputStream flujo_salida = null;
            DataInputStream flujo_entrada = null;

            if (result <= 59) { // 60%
                System.out.println(nombre + " Estudiar en sus calderos pociones mágicas para dañar la chispa de Elisabetha y Lance");
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    System.err.println(nombre + " El hilo fue interrumpido: " + e.getMessage());
                }

                result = random.nextInt(100);

                if (result <= 29){ // 30%
                    System.out.println(nombre + " obtuve una poción para bajar la chispa de Elisabetha");
                    alacenaPociones.setPocionesElisabetha();
                } else if (result >= 30 && result <= 59) { // 30%
                    System.out.println(nombre + " obtube una poción que cree una excusa para citar a Lance");
                    alacenaPociones.setPocionesLance();
                } else if (result >= 60 && result <= 99) { // 40%
                    System.out.println(nombre + " @3#~$=~·# no logre la poción");
                }

            } else if (result >= 60 && result <= 79) { // 20%
                System.out.println(nombre + " Contactare con Elisabetha para proporcionarle una poción que haga descender su chispa");
                estanteEli = alacenaPociones.getPocionesElisabetha();
                if (estanteEli >= 1) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        System.err.println(nombre + " El hilo fue interrumpido: " + e.getMessage());
                    }
                    try{
                        skCliente = new Socket("localhost", 5000);
                        flujo_salida = new DataOutputStream(skCliente.getOutputStream());
                        flujo_entrada = new DataInputStream(skCliente.getInputStream());

                        flujo_salida.writeUTF(nombre);

                        flujo_entrada.readUTF();

                        result = random.nextInt(100);

                        if (result < 30) {
                            System.out.println(nombre + " Nota mental: consegi engañar a eli jaaaajjaja");
                            flujo_salida.writeUTF("Te engañe");
                        } else {
                            flujo_salida.writeUTF("no consegi engañarte");
                            System.out.println(nombre + " Nota mental: NO CONSEIGI ENGAÑARLA NOOO NOO LA @#~&% $$ #~$%@ $$ @##$$$@~&%");
                        }

                    } catch (IOException e){
                        System.out.println(nombre + " error en el socket");
                        System.out.println(e.getMessage());
                    }

                } else {
                    System.out.println(nombre + " Balla no puedo visitar a Elisabetha no me quedan pociones " + estanteEli);
                }
            } else if (result >= 80 && result <= 99) { // 20%
                System.out.println(nombre + "Contactar con Lance para llamarlo al orden y amenazarle con enviarlo al Frente Norte");
                estanteLan = alacenaPociones.getPocionesLance();
                if (estanteLan >= 1){
                    try {
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        System.err.println(nombre + " El hilo fue interrumpido: " + e.getMessage());
                    }

                    result = random.nextInt(100);

                    if (result >= 79){ //80%
                        deciosion = "engañar a Lance";
                        System.out.println(nombre + " Decicio " + deciosion);

                        try {
                            skCliente = new Socket("localhost", 5000);
                            flujo_salida = new DataOutputStream(skCliente.getOutputStream());
                            flujo_entrada = new DataInputStream(skCliente.getInputStream());

                            flujo_salida.writeUTF(nombre);

                            flujo_entrada.readUTF();

                            result = random.nextInt(100);

                            if (result >= 19) {// 20½
                                flujo_salida.writeUTF("Te engañe");
                                System.out.println(nombre + " Nota mental: consegi engañar a Lance jaaaajjaja");
                            } else { // resto
                                flujo_salida.writeUTF("no consegi engañarte");
                                System.out.println(nombre + " Nota mental: NO CONSEIGI ENGAÑARLO NOOO NOO LA @#~&% $$ #~$%@ $$ @##$$$@~&%");
                            }

                        } catch (IOException e){
                            System.out.println(nombre + " error en el socket");
                            System.out.println(e.getMessage());
                        }

                    } else if (result <= 80) { //20%
                        deciosion = "Amenazar a Lance";
                        System.out.println(nombre + " Decicio " + deciosion);
                        alacenaPociones.setPocionesLance();

                        try {
                            skCliente = new Socket("localhost", 5000);
                            flujo_salida = new DataOutputStream(skCliente.getOutputStream());
                            flujo_entrada = new DataInputStream(skCliente.getInputStream());

                            flujo_salida.writeUTF(nombre);

                            flujo_entrada.readUTF();

                            result = random.nextInt(100);

                            if (result >= 19) {// 20½
                                flujo_salida.writeUTF("vas a morir en el frente");
                                System.out.println(nombre + " Nota mental: consegi engañar a Lance cree que ira al frente");
                            } else { // resto
                                flujo_salida.writeUTF("no consegi engañarte");
                                System.out.println(nombre + " Nota mental: NO CONSEIGI ENGAÑARLO NOOO NOO LA @#~&% $$ #~$%@ $$ @##$$$@~&%");
                            }

                        } catch (IOException e){
                            System.out.println(nombre + " error en el socket");
                            System.out.println(e.getMessage());
                        }

                    }

                } else {
                    System.out.println(nombre + " Balla no puedo visitar a Lance no me quedan pociones " + estanteLan);
                }
            }

        }
    }
}
