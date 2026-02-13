package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ElMercado implements Runnable {

    // Servidor
    private LugarMercado lugarMercado = new LugarMercado();

    @Override
    public void run() {
        // dentro del mismo castillo barias conexiones Pues boy a necesitar salas

        Thread[] LugaresMercado = new Thread[90];
        ServerSocket skServidor = null;

        try {
            skServidor = new ServerSocket(5002); //puertos apartir del 5000 son reserbados a programadores
        } catch (IOException e){
            System.out.println("No hay quien entre al mercado");
            System.out.println(e.getMessage());
        }

        while (true){
            try {
                for (int i = 0; i < LugaresMercado.length; i++) {
                    if (LugaresMercado[i] != null && !LugaresMercado[i].isAlive()) {
                        //System.out.println("Hilo " + i + " ha terminado. hilo liberado.");
                        LugaresMercado[i] = null;
                    }
                }

                int SalaDisponible = -1;
                for (int i = 0; i < LugaresMercado.length; i++) {
                    if (LugaresMercado[i] == null) {
                        SalaDisponible = i;
                        break;
                    }
                }

                if (SalaDisponible == -1) {
                    System.out.println("Mercado lleno (90/90 clientes). Esperando...");
                    Thread.sleep(1000);
                    continue;
                }

                Socket skCliente;
                skCliente = skServidor.accept();

                InstanciaClienteMercado servidorHilo = new InstanciaClienteMercado(skCliente, lugarMercado);
                LugaresMercado[SalaDisponible] = new Thread(servidorHilo);
                LugaresMercado[SalaDisponible].start();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
