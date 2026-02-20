package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SalaTaberna {
    private boolean elisabethaPresente = false;
    private boolean lancePresente = false;

    private String mensaje = "";

    public synchronized void esperarAelisabetha(DataOutputStream salidaEli, DataInputStream entradaEli){


    }

    public synchronized void esperarLanze(DataOutputStream salidaLan, DataInputStream entradaLan){

        this.lancePresente = true;
        if (this.elisabethaPresente) {
            try {
                salidaLan.writeUTF("true");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                salidaLan.writeUTF("false");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.lancePresente = false;
    }


}
