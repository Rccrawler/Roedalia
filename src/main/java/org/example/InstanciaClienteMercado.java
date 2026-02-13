package org.example;

import java.io.*;
import java.net.Socket;

public class InstanciaClienteMercado implements Runnable{

    private Socket socket;
    private LugarMercado sala;

    public InstanciaClienteMercado(Socket socket, LugarMercado sala){
        this.socket = socket;
        this.sala = sala;
    }

    @Override
    public void run(){
        try{
            InputStream get = this.socket.getInputStream(); // obtener mensajes
            DataInputStream flujo_entrada = new DataInputStream(get);
            OutputStream post = this.socket.getOutputStream(); // enviar mensajes
            DataOutputStream flujo_salida = new DataOutputStream(post);

            String quienEs = flujo_entrada.readUTF();

            if (quienEs.equals("Elisabetha")) {
                sala.notificarLlegada(flujo_salida, flujo_entrada);
            }
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
