package org.example;

import java.util.Random;

public class Elisabetha implements Runnable {

    // hilo especial

    // hojo los alquimistas piden esto Elisabetha lo atenderá justo
    //después de terminar la acción que en ese momento esté realizando ya que es uno de
    //los ratones más importantes del reino.

    // por lo que eli despues de cada tarea se tendra que pasar al castillo por si a caso
    // pero que se pase por el castillo no quiere decir que balla a tender a los demas que no sean los Alquimistas
    // que si estan son los que tiene prioridad

    // hay que hacer que elisabet como se ase desconecte del Mercado lla que pone que hay que desconectarse cuando acabe de comprar

    private int NivelDeChispa; // cuidado no se prenda fuego
    private boolean ConocioLance = false;

    private boolean CierreProgrma = false; // para notificar al main el cierre del programa ojo peligrosisimo

    public boolean isCierreProgrma() {
        return CierreProgrma;
    }

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
