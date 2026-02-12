package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
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

            Socket skCliente = null;
            DataOutputStream flujo_salida = null;
            DataInputStream flujo_entrada = null;

            if (result <= 59) {
                System.out.println(nombre + "Estudiar ");
            } else if (result >= 60 || result <= 79) {

            } else if (result >= 80 || result <= 99) {

            }

        }
    }

}
