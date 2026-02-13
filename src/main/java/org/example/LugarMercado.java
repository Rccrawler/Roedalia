package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class LugarMercado {
    private boolean elisabethaPresente = false;

    private String producto = "";

    public synchronized void notificarLlegada(DataOutputStream salidaElisabetha, DataInputStream entradaElisabetha){

        this.elisabethaPresente = true;
        // darle a elegir 5 productos
        // queso, especias del lejano oriente, telas para vestidos, jugo de gosella, repelente de gatos, brillantes collares, cucharas

    }

}
