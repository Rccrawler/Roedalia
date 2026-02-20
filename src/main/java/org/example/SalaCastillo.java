package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SalaCastillo {
    private boolean elisabethaPresente = false;
    private boolean alquimistaPresente = false;
    private boolean lancePresente = false;

    private String conbersacionAl = "";

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

    public synchronized void esperarReunionUrguente(DataOutputStream salidaAl, DataInputStream entradaAl) { // esto es para los alquimistas
        this.alquimistaPresente = true;
        this.conbersacionAl = "";

        String mensaje = "";

        if (this.elisabethaPresente && this.lancePresente){
            mensaje = "Elisabetha y Lance";
        } else if (elisabethaPresente){
            mensaje = "Elisabetha";
        } else if (lancePresente){
            mensaje = "Lance";
        } else {
            mensaje = "Nadie";
        }

        try {
            salidaAl.writeUTF(mensaje);
            // Solo leer la respuesta si Lance está presente; si no, el cliente cierra el socket sin enviar nada
            if (mensaje.equals("Lance") || mensaje.equals("Elisabetha y Lance")) {
                this.conbersacionAl = entradaAl.readUTF();
                notifyAll(); // despertar a Lance para que recoja el mensaje
            }
            this.alquimistaPresente = false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // demas codigo para pasarle la pocion a eli
    }


    public synchronized void revisarAlquimista(DataOutputStream flujoSalidaLa, DataInputStream flujoEntradaLa) {
        String notificacion = "";
        lancePresente = true;
        notifyAll(); // avisar al alquimista de que Lance ha llegado

        try {
            wait(1800);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (this.conbersacionAl != "") {
            try {
                flujoSalidaLa.writeUTF(this.conbersacionAl);
                this.conbersacionAl = "";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            lancePresente = false;
        } else {
            try {
                flujoSalidaLa.writeUTF("Nade me espera");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            lancePresente = false;
        }

    }
}
