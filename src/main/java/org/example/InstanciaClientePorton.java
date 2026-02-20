package org.example;

import java.io.*;
import java.net.Socket;

public class InstanciaClientePorton implements Runnable {

    private Socket socket;
    private SalaDelPortonNorte sala;

    public InstanciaClientePorton(Socket socket, SalaDelPortonNorte sala){
        this.socket = socket;
        this.sala = sala;
    }

    @Override
    public void run() {

        sala.lleganCarretas();

        try {
            InputStream get = this.socket.getInputStream(); // obtener mensajes
            DataInputStream flujo_entrada = new DataInputStream(get);
            OutputStream post = this.socket.getOutputStream(); // enviar mensajes
            DataOutputStream flujo_salida = new DataOutputStream(post);

            String quienEs = flujo_entrada.readUTF();

            if (quienEs.equals("Lance du Lac(Dion)")) {
                sala.notificarLlegada(flujo_salida, flujo_entrada);
            } else if (quienEs.equals("caballero vigilante")) {
                sala.hacerVigilancia(flujo_entrada, flujo_salida);
            } else if (quienEs.equals("caballero enmascarado")) {
                System.out.println("Ojo caballero enmascarado a entrado");
                sala.SalaEscondidaLance(flujo_salida, flujo_entrada);
            } else if (quienEs.equals("dama tapada")) {
                System.out.println("Ojo dama tapada a entrado");
                sala.SalaEscondidaEli(flujo_salida, flujo_entrada);
            } else {
                sala.esperarlanceDuLacDion(flujo_salida, flujo_entrada, quienEs);
            }

            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
