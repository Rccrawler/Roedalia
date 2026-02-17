package org.example;

import java.io.*;
import java.net.Socket;

public class InstanciaClienteTaberna implements Runnable{
    private Socket socket;
    private SalaTaberna sala;

    public InstanciaClienteTaberna(Socket socket, SalaTaberna sala){
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
                sala.esperarAelisabetha(flujo_salida, flujo_entrada);

            }else {
                sala.esperarLanze(flujo_salida, flujo_entrada);

            }

            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
