package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class CaballerosDelPortonNorte implements Runnable {

    private static final Map<String, String> mensajesPendientes = new HashMap<>();
    private static final Map<String, Resultado> resultados = new HashMap<>();

    private static class Resultado {
        final boolean duelo;
        final boolean herido;
        Resultado(boolean duelo, boolean herido) {
            this.duelo = duelo;
            this.herido = herido;
        }
    }

    public static synchronized String consumirMensaje(String nombre) {
        return mensajesPendientes.remove(nombre);
    }

    public static synchronized boolean hayMensaje(String nombre) {
        return mensajesPendientes.containsKey(nombre);
    }

    public static synchronized void registrarResultado(String nombre, boolean duelo, boolean herido) {
        resultados.put(nombre, new Resultado(duelo, herido));
        CaballerosDelPortonNorte.class.notifyAll();
    }

    Socket skCliente = null;

    private String nombre;
    public CaballerosDelPortonNorte(String nom){
        this.nombre = nom;
    }

    @Override
    public void run() {
        Random random = new Random();
        String lugar = "algun lugar";

        while (true){

            int labor = random.nextInt(2);

            Socket skCliente = null;
            DataOutputStream flujo_salida = null;
            DataInputStream flujo_entrada = null;

            switch (labor){
                case 0:
                    labor = random.nextInt(3);

                    if (labor == 0){
                        lugar = "el Portón Norte";// Kurmin Jatau
                        System.out.println(nombre + " vigilar " + lugar + "-------------------------------------------");

                        try {

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

                    } else if (labor == 1) {
                        lugar = "la Muralla";
                        System.out.println(nombre + " vigilar " + lugar);
                    } else if (labor == 2) {
                        lugar = "las Torres";
                        System.out.println(nombre + " vigilar " + lugar);
                    }

                    System.out.println(nombre + " Realizare labores de vigilancia en " + lugar + " de la ciudad");

                    try {
                        Thread.sleep(6000); // Pausa el hilo actual por 6 segundos
                    } catch (InterruptedException e) {
                        System.err.println(nombre + " El hilo fue interrumpido: " + e.getMessage());
                    }
                    break;

                case 1:
                    System.out.println(nombre + " Ire a hablar con Lance");

                    try {
                        int laborMensaje = random.nextInt(100); // del 0 al 99
                        String mensaje;
                        if (laborMensaje < 50) { // 50%
                            mensaje = "confidencia personal";
                        } else if (laborMensaje < 75) { // 25%
                            mensaje = "rumor infundado";
                        } else { // 25%
                            mensaje = "ofensa sobre Elisabetha";
                        }

                        synchronized (CaballerosDelPortonNorte.class) {
                            mensajesPendientes.put(nombre, mensaje);
                            CaballerosDelPortonNorte.class.notifyAll();
                        }
                        System.out.println(nombre + " espera a Lance con: " + mensaje);

                        Resultado resultado;
                        long limite = System.currentTimeMillis() + 25000; // espera max 25s
                        synchronized (CaballerosDelPortonNorte.class) {
                            while (!resultados.containsKey(nombre) && System.currentTimeMillis() < limite) {
                                CaballerosDelPortonNorte.class.wait(500);
                            }
                            resultado = resultados.remove(nombre);
                        }

                        if (resultado == null) {
                            System.out.println(nombre + " se cansa de esperar a Lance.");
                        } else if (resultado.duelo) {
                            if (resultado.herido) {
                                System.out.println(nombre + " ha quedado herido en el duelo contra Lance. Se recupera 30s.");
                                try {
                                    Thread.sleep(30000);
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                }
                            } else {
                                System.out.println(nombre + " perdió el duelo con Lance sin heridas graves.");
                            }
                        } else {
                            System.out.println(nombre + " fue escuchado por Lance.");
                        }

                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    break;
            }
        }

    }
}
