package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ElPortonNorte implements Runnable {

    // Servidor
    private SalaDelPortonNorte salaDelPortonNorte = new SalaDelPortonNorte();

    @Override
    public void run() {

        Thread[] SalasPortonNorte = new Thread[10];
        ServerSocket skServidor = null;

        try {
            skServidor = new ServerSocket(5001); //puertos apartir del 5000 son reserbados a programadores
        } catch (IOException e){
            System.out.println("Puerta del castillo atascada: error en el socket al iniciar");
            System.out.println(e.getMessage());
        }

        while (true){
            try {
                for (int i = 0; i < SalasPortonNorte.length; i++) {
                    if (SalasPortonNorte[i] != null && !SalasPortonNorte[i].isAlive()) {
                        //System.out.println("Hilo " + i + " ha terminado. hilo liberado.");
                        SalasPortonNorte[i] = null;
                    }
                }

                int SalaDisponible = -1;
                for (int i = 0; i < SalasPortonNorte.length; i++) {
                    if (SalasPortonNorte[i] == null) {
                        SalaDisponible = i;
                        break;
                    }
                }

                if (SalaDisponible == -1) {
                    System.out.println("porton norte lleno (30/30 clientes). Esperando...");
                    Thread.sleep(1000);
                    continue;
                }

                Socket skCliente;
                skCliente = skServidor.accept();

                InstanciaClientePorton servidorHilo = new InstanciaClientePorton(skCliente, salaDelPortonNorte);
                SalasPortonNorte[SalaDisponible] = new Thread(servidorHilo);
                SalasPortonNorte[SalaDisponible].start();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}