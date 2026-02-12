package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
                System.out.println(nombre + " Contactar con Elisabetha para proporcionarle una poción que haga descender su chispa");
                estanteEli = alacenaPociones.getPocionesElisabetha();
                if (estanteEli >= 1) {

                } else {
                    System.out.println(nombre + " Balla no puedo visitar a Elisabetha no me quedan pociones " + estanteEli);
                }
            } else if (result >= 80 && result <= 99) { // 20%
                System.out.println(nombre + "Contactar con Lance para llamarlo al orden y amenazarle con enviarlo al Frente Norte");
            }

        }
    }
}
