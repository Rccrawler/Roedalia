package org.example;

import java.io.*;
import java.net.Socket;

public class InstanciaClienteCastillo implements Runnable {

    private Socket socket;
    private SalaCastillo sala;

    public InstanciaClienteCastillo(Socket socket, SalaCastillo sala){
        this.socket = socket;
        this.sala = sala;
    }

    @Override
    public void run() {

        try {
            InputStream get = this.socket.getInputStream(); // obtener mensajes
            DataInputStream flujo_entrada = new DataInputStream(get);
            OutputStream post = this.socket.getOutputStream(); // enviar mensajes
            DataOutputStream flujo_salida = new DataOutputStream(post);

            String quienEs = flujo_entrada.readUTF();

            if (quienEs.equals("Elisabetha")) {
                sala.notificarLlegada(flujo_salida, flujo_entrada);

            }else if(quienEs.equals("Theobald") || quienEs.equals("Ignatius")) {
                sala.esperarReunionUrguente(flujo_salida, flujo_entrada);
            }else if(quienEs.equals("Lince Dulac dion")) {
                sala.revisarAlquimista(flujo_salida, flujo_entrada);
            } else {
                sala.esperarAelisabetha(flujo_salida, flujo_entrada);
            }

            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
