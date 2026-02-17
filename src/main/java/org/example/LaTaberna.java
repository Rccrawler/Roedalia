package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LaTaberna implements Runnable{

    // Servidor
    private String nombre = "El descanso del gerrero";

    private SalaTaberna salaTaberna = new SalaTaberna();

    @Override
    public void run() {

        Thread[] salasTaberna = new Thread[20];
        ServerSocket skServidor = null;

        try {
            skServidor = new ServerSocket(5003); //puertos apartir del 5000 son reserbados a programadores
        } catch (IOException e){
            System.out.println("Taberrna cerrada: error en el socket al iniciar");
            System.out.println(e.getMessage());
        }

        while (true){
            try {
                for (int i = 0; i < salasTaberna.length; i++) {
                    if (salasTaberna[i] != null && !salasTaberna[i].isAlive()) {
                        //System.out.println("Hilo " + i + " ha terminado. hilo liberado.");
                        salasTaberna[i] = null;
                    }
                }

                int SalaDisponible = -1;
                for (int i = 0; i < salasTaberna.length; i++) {
                    if (salasTaberna[i] == null) {
                        SalaDisponible = i;
                        break;
                    }
                }

                if (SalaDisponible == -1) {
                    System.out.println("Taberna llena (20/20 clientes). Esperando...");
                    Thread.sleep(1000);
                    continue;
                }

                Socket skCliente;
                skCliente = skServidor.accept();

                InstanciaClienteTaberna servidorHilo = new InstanciaClienteTaberna(skCliente, salaTaberna);
                salasTaberna[SalaDisponible] = new Thread(servidorHilo);
                salasTaberna[SalaDisponible].start();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
