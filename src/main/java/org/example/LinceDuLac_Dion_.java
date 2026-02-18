package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LinceDuLac_Dion_ implements Runnable {

    // hilo especial

    // el caballero sienpre es dañado con 20 de hacerle daño nunca lanze sale dañado
    // hojo debe revisar el castillo por si el alquimista queiere contactar con el ya que seria urgente

    // hojo revisar que cuando tiene que vigilar el porton no entre con su nonbre sino con el testo: caballero vigilante si no podra trabajar

    private int NivelDeChispa; // cuidado no se prenda fuego
    private boolean ConocioElisabetha = false;

    private String nombre;

    private boolean ofensaRecivida;

    private final Random random = new Random();
    private final List<String> companeros = Arrays.asList("Diego", "Aldric", "Cayo", "Faelan");
    private final String[] lugaresGuardia = {"Portón Norte", "muralla", "torres"};

    @Override
    public void run() {
        while (true) {
            int accion = random.nextInt(3); // 0 hablar, 1 duelo, 2 guardia
            switch (accion) {
                case 0:
                    atenderCaballero();
                    break;
                case 1:
                    realizarDueloDirecto();
                    break;
                case 2:
                    realizarGuardia();
                    break;
                default:
                    break;
            }
            try {
                Thread.sleep(2000 + random.nextInt(2000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private void realizarGuardia() {

    }

    private void realizarDueloDirecto() {
        String rival = companeros.get(random.nextInt(companeros.size()));
        System.out.println("Lance du Lac(Dion) desafía a duelo a " + rival);
        try {
            Thread.sleep(5000); // duelo dura 5s
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        boolean herido = random.nextInt(100) < 20;
        if (herido) {
            System.out.println("Lance du Lac(Dion) hiere gravemente a " + rival + " en el duelo directo.");
        } else {
            System.out.println("Lance du Lac(Dion) vence a " + rival + " sin herirlo en el duelo directo.");
        }
        CaballerosDelPortonNorte.registrarResultado(rival, true, herido);
    }

    private void atenderCaballero() {
        Collections.shuffle(companeros, random);
        String elegido = null;
        String mensaje = null;

        for (String candidato : companeros) {
            if (CaballerosDelPortonNorte.hayMensaje(candidato)) {
                elegido = candidato;
                mensaje = CaballerosDelPortonNorte.consumirMensaje(candidato);
                break;
            }
        }

        if (elegido == null) {
            System.out.println("Lance du Lac(Dion) no encuentra compañeros con nada que contar.");
            return;
        }

        System.out.println("Lance du Lac(Dion) atiende a " + elegido + " con mensaje: " + mensaje);
        try {
            Thread.sleep(4000); // atender cuesta 4s
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        boolean duelo = mensaje.equals("ofensa sobre Elisabetha");
        boolean herido = false;

        if (duelo) {
            System.out.println("Lance du Lac(Dion) reta a duelo a " + elegido);
            try {
                Thread.sleep(5000); // duelo dura 5s
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            herido = random.nextInt(100) < 20;
            if (herido) {
                System.out.println("Lance du Lac(Dion) vence y hiere gravemente a " + elegido);
            } else {
                System.out.println("Lance du Lac(Dion) vence a " + elegido + " sin herirlo.");
            }
        } else {
            System.out.println("Lance du Lac(Dion) escucha a " + elegido + " y continúa su ronda.");
        }

        CaballerosDelPortonNorte.registrarResultado(elegido, duelo, herido);
    }
}
