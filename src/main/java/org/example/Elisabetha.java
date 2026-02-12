package org.example;

import java.util.Random;

public class Elisabetha implements Runnable {

    // hilo especial

    private int NivelDeChispa; // cuidado no se prenda fuego
    private boolean ConocioLance = false;

    public Elisabetha(){
    }

    @Override
    public void run() {
        Random random = new Random();
        int decision = random.nextInt(3);

        switch (decision){
            case 0:
                System.out.println("Elisabetha atendera a sus Damas del Lazo");
            case 1:
                System.out.println("Asistir a bailes de la Corte");
            case 2:
                System.out.println("Leer pergaminos en la biblioteca del castillo");
            case 3:
                System.out.println("Escaparse por la noche por los pasadizos");
        }

    }
}
