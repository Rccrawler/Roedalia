package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SalaCastillo {
    private boolean elisabethaPresente = false;
    private boolean alquimistaPresente = false;

    private String mensajeRumor = "";

    public synchronized void esperarAelisabetha(DataOutputStream salidaDama, DataInputStream entradaDama){

        try {
            if(this.elisabethaPresente == true && this.elisabethaPresente == false) {
                salidaDama.writeUTF("la encontre");
                this.mensajeRumor = entradaDama.readUTF();

            } else if(this.elisabethaPresente == true){
                salidaDama.writeUTF("esta hablando con el Alquimista");
                try { // esperamos un poco que las damas corren poco
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.err.println("El hilo fue interrumpido: " + e.getMessage());
                }

            }else {
                salidaDama.writeUTF("no la encuentro en el castillo");
                try { // esperamos un poco que las damas corren poco
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.err.println("El hilo fue interrumpido: " + e.getMessage());
                }

            }/*
            while (!this.elisabethaPresente){
                wait(); // que espere a elisabet la dama en concreto
            }*/
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void notificarLlegada(DataOutputStream salidaElisabetha, DataInputStream entradaElisabetha){

        // arreglar para pasarle a elizabet los chismes cuando esten los personajes

        this.elisabethaPresente = true;
        notifyAll(); // despertamos a las damas revisar que hilos se despiertan esacta mente
        try{
            try { // esperamos un poco que las damas duerme mucho
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.err.println("El hilo fue interrumpido: " + e.getMessage());
            }
            salidaElisabetha.writeUTF(this.mensajeRumor);
            salidaElisabetha.writeUTF("Damas atendidas");
            this.elisabethaPresente = false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void esperarReunionUrguente() { // esto es para los alquimistas
        this.alquimistaPresente = true;

        // demas codigo para pasarle la pocion a eli

    }
}
