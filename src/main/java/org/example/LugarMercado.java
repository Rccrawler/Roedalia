package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

public class LugarMercado {
    private boolean elisabethaPresente = false;

    private String producto = "";

    public synchronized void notificarLlegada(DataOutputStream salidaElisabetha, DataInputStream entradaElisabetha) throws IOException {

        Random random = new Random();
        String[] ProductosNonbres = {"Queso", "Especias del lejano oriente", "Telas para vestidos", "jugo de gosella", "repelente de gatos", "brillantes collares", "cucharas"};
        String[] ProductosSelecionados = new String[5];

        this.elisabethaPresente = true;
        // mezclar el array en lugar de usar lista
        for (int i = ProductosNonbres.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            String tmp = ProductosNonbres[i];
            ProductosNonbres[i] = ProductosNonbres[j];
            ProductosNonbres[j] = tmp;
        }
        for (int i = 0; i < ProductosSelecionados.length; i++) {
            ProductosSelecionados[i] = ProductosNonbres[i];
        }

        salidaElisabetha.writeUTF("Productos");

        int producto = random.nextInt(5);
        System.out.println("Elisabetha" + " comprara " + ProductosSelecionados[producto]);
        String respuesta = entradaElisabetha.readUTF();
        System.out.println("Elisabetha " + respuesta);
        System.out.println("Elisabetha se fue del mercado");

    }
}
