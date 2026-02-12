package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SalaDelPortonNorte {
    private boolean lanceDuLacDionPresente  = false;
    private String mensajeRumor = "";

    public synchronized void esperarlanceDuLacDion(DataOutputStream salidaCaballero, DataInputStream entradaCaballero){
        try {
            if(this.lanceDuLacDionPresente){
                salidaCaballero.writeUTF("lo encontre");
                this.mensajeRumor = entradaCaballero.readUTF();
            }else {
                salidaCaballero.writeUTF("no lo encuentro en el porton");
                try { // esperamos un poco que las damas corren poco
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.err.println("El hilo fue interrumpido: " + e.getMessage());
                }

                // recordar el sistema de duelos con lanze y toda la movida

            }/*
            while (!this.elisabethaPresente){
                wait(); // que espere a elisabet la dama en concreto
            }*/
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void notificarLlegada(DataOutputStream salidaElisabetha, DataInputStream entradaElisabetha){

        // arreglar para pasarle a lance y a los caballeros los chismes cuando esten los personajes

        this.lanceDuLacDionPresente = true;
        notifyAll(); // despertamos a las damas revisar que hilos se despiertan esacta mente
        try{
            try { // esperamos un poco que las damas duerme mucho
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.err.println("El hilo fue interrumpido: " + e.getMessage());
            }
            salidaElisabetha.writeUTF(this.mensajeRumor);
            salidaElisabetha.writeUTF("Damas atendidas");
            this.lanceDuLacDionPresente = false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
