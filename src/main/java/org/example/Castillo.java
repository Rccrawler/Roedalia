package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Castillo implements Runnable{

    private SalaCastillo salaCastillo = new SalaCastillo();

    @Override
    public void run() {
        // dentro del mismo castillo barias conexiones Pues boy a necesitar salas

        Thread[] SalasCastillo = new Thread[30];
        ServerSocket skServidor = null;

        try {
            skServidor = new ServerSocket(5000); //puertos apartir del 5000 son reserbados a programadores
        } catch (IOException e){
            System.out.println("Puerta del castillo atascada: error en el socket al iniciar");
            System.out.println(e.getMessage());
        }

        while (true){
            try {
                for (int i = 0; i < SalasCastillo.length; i++) {
                    if (SalasCastillo[i] != null && !SalasCastillo[i].isAlive()) {
                        //System.out.println("Hilo " + i + " ha terminado. hilo liberado.");
                        SalasCastillo[i] = null;
                    }
                }

                int SalaDisponible = -1;
                for (int i = 0; i < SalasCastillo.length; i++) {
                    if (SalasCastillo[i] == null) {
                        SalaDisponible = i;
                        break;
                    }
                }

                if (SalaDisponible == -1) {
                    System.out.println("Castillo lleno (30/30 clientes). Esperando...");
                    Thread.sleep(1000);
                    continue;
                }

                Socket skCliente;
                skCliente = skServidor.accept();

                InstanciaClienteCastillo servidorHilo = new InstanciaClienteCastillo(skCliente, salaCastillo);
                SalasCastillo[SalaDisponible] = new Thread(servidorHilo);
                SalasCastillo[SalaDisponible].start();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
