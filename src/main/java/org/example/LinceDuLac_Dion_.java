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
    private String nombre = "Lince Dulac dion";
    private boolean ofensaRecivida;
    private final Random random = new Random();
    private final List<String> companeros = Arrays.asList("Diego", "Aldric", "Cayo", "Faelan");
    private final String[] lugaresGuardia = {"Portón Norte", "El Descanso del Guerrero"};

    @Override
    public void run() {
        while (true) {
            int accion = random.nextInt(3); // 0 hablar, 1 duelo, 2 guardia
            switch (accion) { // accion
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
        Random random = new Random();
        int aleatorio = random.nextInt(lugaresGuardia.length);
        String lugar = lugaresGuardia[aleatorio];
        switch (lugar){

            case "Portón Norte":
                System.out.println(nombre + " vigila el Portón Norte");
                try {
                    Socket skCliente = null;
                    DataOutputStream flujo_salida = null;
                    DataInputStream flujo_entrada = null;

                    skCliente = new Socket("localhost", 5001);
                    flujo_salida = new DataOutputStream(skCliente.getOutputStream());
                    flujo_entrada = new DataInputStream(skCliente.getInputStream());

                    flujo_salida.writeUTF("caballero vigilante");
                    flujo_salida.writeUTF(nombre);
                    String detalle = flujo_entrada.readUTF();
                    String resultado = flujo_entrada.readUTF();

                    System.out.println(nombre + " " + detalle);
                    System.out.println(nombre + " " + resultado);

                } catch (IOException e) {
                    System.err.println(nombre + " No pude vigilar en el porton: " + e.getMessage());
                }
                try {
                    Thread.sleep(5000); // Pausa el hilo actual por 6 segundos
                } catch (InterruptedException e) {
                    System.err.println(nombre + " El hilo fue interrumpido: " + e.getMessage());
                }
                break;
            case "El Descanso del Guerrero":
                System.out.println(nombre + "El Descanso del Guerrero");
                try {
                    Socket skCliente = null;
                    DataOutputStream flujo_salida = null;
                    DataInputStream flujo_entrada = null;

                    skCliente = new Socket("localhost", 5003);
                    flujo_salida = new DataOutputStream(skCliente.getOutputStream());
                    flujo_entrada = new DataInputStream(skCliente.getInputStream());

                    flujo_salida.writeUTF(nombre);

                    // Espera en la taberna 8 segundos para dar tiempo a que Elisabetha se conecte
                    Thread.sleep(8000);

                    String estaEli = flujo_entrada.readUTF();

                    if (estaEli.equals("true")){

                    }else {
                        System.out.println(nombre + "Acabo mi turno de vigilar");
                    }

                } catch (UnknownHostException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                break;
        }
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
            this.NivelDeChispa = this.NivelDeChispa - 5; // se puede mejorar pero funciona
            if(this.NivelDeChispa < 0){ // se puede mejorar pero funciona
                this.NivelDeChispa = 0;
            }
            System.out.println(nombre + " la chispa actual es " + NivelDeChispa + "----------------------------------");
        } else {
            System.out.println("Lance du Lac(Dion) vence a " + rival + " sin herirlo en el duelo directo.");

            this.NivelDeChispa = this.NivelDeChispa + 7;

            if (ConocioElisabetha == false && NivelDeChispa > 50){ // si es true
                this.NivelDeChispa = 50;
            }

            System.out.println(nombre + " la chispa actual es " + NivelDeChispa + "----------------------------------");
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

        System.out.println(nombre + " hablare con " + elegido + "---------------------");

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
                this.NivelDeChispa = this.NivelDeChispa - 5; // se puede mejorar pero funciona
                if(this.NivelDeChispa < 0){ // se puede mejorar pero funciona
                    this.NivelDeChispa = 0;
                }
                System.out.println(nombre + " la chispa actual es " + NivelDeChispa + "----------------------------------");
            } else {
                System.out.println("Lance du Lac(Dion) vence a " + elegido + " sin herirlo.");

                this.NivelDeChispa = this.NivelDeChispa + 7;

                if (ConocioElisabetha == false && NivelDeChispa > 50){ // si es true
                    this.NivelDeChispa = 50;
                }

                System.out.println(nombre + " la chispa actual es " + NivelDeChispa + "----------------------------------");

            }
        } else {
            System.out.println("Lance du Lac(Dion) escucha a " + elegido + " y continúa su ronda.");
            System.out.println(nombre + " la chispa actual es " + NivelDeChispa + "----------------------------------");
        }

        CaballerosDelPortonNorte.registrarResultado(elegido, duelo, herido);
    }
}
