/*
 * Copyright (c) 2026 Andrew.C
 * Autor: Andrew.C
 * Licencia: GNU GPL (General Public License)
 * OS: Linux Fedora
 * Descripción: Simulación completa de la famosa y antigua Leyenda de Roedalia que lla solo
 * parecía polvo en los pliegues de la historia / by D.G
 */

package org.example;

import java.util.Random;

public class CustodioDelFuegoSecreto {

    // orquestador Aspirante a Mago del Multiverso

    public static void main(String[] args) {

        //otros inprescindibles
        String[] DamasNonbres = { "Aitana", "Aurora", "Valentina", "Elena"};
        String[] alquimistasNonbres = { "Theobald", "Ignatius"};
        String[] CaballerosNonbres = { "Diego", "Aldric", "Cayo", "Faelan"};

        // sitios - servidores
        Castillo castillo = new Castillo(); // portal 5000
        Thread castilloThread = new Thread(castillo, "castilloThread");

        ElPortonNorte ElPortonNorte = new ElPortonNorte(); // portal 5001
        Thread ElPortonNorteThread = new Thread(ElPortonNorte, "ElPortonNorteThread");

        ElMercado ElMercado = new ElMercado(); // portal 5002
        Thread ElMercadoThread = new Thread(ElMercado, "ElMercadoThread");

        LaTaberna LaTaberna = new LaTaberna(); // portal 5003
        Thread LaTabernaThread = new Thread(LaTaberna, "LaTabernaThread");

        // clase comun
        AlacenaPociones alacenaPociones = new AlacenaPociones();

        // personajes
        Elisabetha elisabetha = new Elisabetha(); // hilo especial
        Thread elisabethaThread = new Thread(elisabetha, "elisabethaThread");

        LinceDuLac_Dion_ LinceDuLac_Dion_ = new LinceDuLac_Dion_(); // hilo especial
        Thread LinceDuLac_Dion_Thread = new Thread(LinceDuLac_Dion_, "LinceDuLac_Dion_Thread");

        Thread[] alquimistas = new Thread[2]; //  hilo alta prioridad

        Thread[] DamasDelLazoPerfumado = new Thread[4];

        Thread[] CaballerosDelPortonNorte = new Thread[4];

        titular();

        try {
            Thread.sleep(3000); // Pausa el hilo actual por 3 segundos
        } catch (InterruptedException e) {
            System.err.println("El hilo fue interrumpido: " + e.getMessage());
        }

        // iniciamos sitios

        castilloThread.start();
        ElMercadoThread.start();
        ElPortonNorteThread.start();
        LaTabernaThread.start();

        // iniciamos personajes

        for (int i = 0; i < alquimistas.length; i++) {
            Alquimistas al = new Alquimistas(alacenaPociones, alquimistasNonbres[i]);
            alquimistas[i] = new Thread(al);
            //alquimistas[i].setPriority(Thread.MAX_PRIORITY); lo de alta prioridad es de broma pero por si acaso lo comento
            alquimistas[i].start(); // ¿el castillo puede ser un lugar o no?
        }

        for (int i = 0; i < DamasDelLazoPerfumado.length; i++) {
            DamasDelLazoPerfumado da = new DamasDelLazoPerfumado(DamasNonbres[i]);
            DamasDelLazoPerfumado[i] = new Thread(da);
            DamasDelLazoPerfumado[i].start();
        }

        for (int i = 0; i < CaballerosDelPortonNorte.length; i++) {
            CaballerosDelPortonNorte ca = new CaballerosDelPortonNorte(CaballerosNonbres[i]);
            CaballerosDelPortonNorte[i] = new Thread(ca);
            CaballerosDelPortonNorte[i].start();
        }

        elisabethaThread.start();

    }

    public static void titular (){
        System.out.println("    ██████");
        System.out.println("   ███░░███");
        System.out.println("  ░███ ░░░  █████ ████  ██████   ███████  ██████ ");
        System.out.println(" ███████   ░░███ ░███  ███░░███ ███░░███ ███░░███");
        System.out.println("░░░███░     ░███ ░███ ░███████ ░███ ░███░███ ░███");
        System.out.println("  ░███      ░███ ░███ ░███░░░  ░███ ░███░███ ░███");
        System.out.println("  █████     ░░████████░░██████ ░░███████░░██████");
        System.out.println(" ░░░░░       ░░░░░░░░  ░░░░░░   ░░░░░███ ░░░░░░");
        System.out.println("                                ███ ░███");
        System.out.println("                               ░░██████");

        try {
            Thread.sleep(1000); // Pausa el hilo actual por 3 segundos
        } catch (InterruptedException e) {
            System.err.println("El hilo fue interrumpido: " + e.getMessage());
        }

        System.out.println();

        System.out.println("     █████             ████");
        System.out.println("   ░░███             ░░███");
        System.out.println(" ███████   ██████     ░███");
        System.out.println("███░░███  ███░░███    ░███  ░░░░░███");
        System.out.println("███ ░███ ░███████     ░███   ███████");
        System.out.println("███ ░███ ░███░░░      ░███  ███░░███");
        System.out.println("░████████░░██████     █████░░████████");

        try {
            Thread.sleep(1000); // Pausa el hilo actual por 3 segundos
        } catch (InterruptedException e) {
            System.err.println("El hilo fue interrumpido: " + e.getMessage());
        }

        System.out.println();

        System.out.println("         ███                             ████                      ███                     ");
        System.out.println(" █████  ████  █████████████   █████ ████ ░███   ██████    ██████  ████   ██████  ████████ ");
        System.out.println("███░░  ░░███ ░░███░░███░░███ ░░███ ░███  ░███  ░░░░░███  ███░░███░░███  ███░░███░░███░░███");
        System.out.println("░█████  ░███  ░███ ░███ ░███  ░███ ░███  ░███   ███████ ░███ ░░░  ░███ ░███ ░███ ░███ ░███");
        System.out.println("░░░░███ ░███  ░███ ░███ ░███  ░███ ░███  ░███  ███░░███ ░███  ███ ░███ ░███ ░███ ░███ ░███");
        System.out.println("██████  █████ █████░███ █████ ░░████████ █████░░████████░░██████  █████░░██████  ████ █████");

        try {
            Thread.sleep(1000); // Pausa el hilo actual por 3 segundos
        } catch (InterruptedException e) {
            System.err.println("El hilo fue interrumpido: " + e.getMessage());
        }

        System.out.println();

        System.out.println(" ███              ███            ███                █████         ");
        System.out.println("░░░              ░░░            ░░░                ░░███          ");
        System.out.println("████  ████████   ████   ██████  ████   ██████    ███████   ██████ ");
        System.out.println("░███ ░░███░░███ ░░███  ███░░███░░███  ░░░░░███  ███░░███  ███░░███  ██");
        System.out.println("░███  ░███ ░███  ░███ ░███ ░░░  ░███   ███████ ░███ ░███ ░███ ░███  ");
        System.out.println("░███  ░███ ░███  ░███ ░███  ███ ░███  ███░░███ ░███ ░███ ░███ ░███  ██");
        System.out.println("█████ ████ █████ █████░░██████  █████░░████████░░████████░░██████ ");

        try {
            Thread.sleep(1500); // Pausa el hilo actual por 3 segundos
        } catch (InterruptedException e) {
            System.err.println("El hilo fue interrumpido: " + e.getMessage());
        }

        clarCli();

        System.out.println();

        System.out.println("Y los Custodios del Fuego Secreto empezaron a");
        System.out.println("Hacer sonar unos misteriosos aparatos similares a las antiguas tablillas de epigrafía.");
        System.out.println("Cada pulsación producía un chasquido rítmico, como si diminutos martillos golpearan runas invisibles o fuegos fatuos.");
        System.out.println("Los símbolos grabados en aquellas tablillas respondían al toque,");
        System.out.println("y al ser presionados, enviaban órdenes arcanas a la gran Tablilla de Luz.");
        System.out.println("A un lado, un pequeño ídolo negro como el carbon de forma redondeada se deslizaba sobre la mesa,");
        System.out.println("obedeciendo los movimientos de la mano como si estuviera poseído por un Yōkai de aquellas historias japonesas.");
        System.out.println("Cuando el Custodio presionaba sus botones, el ídolo lanzaba señales al Tablilla de Luz,");
        System.out.println("haciendo que una punta de flecha luminosa danzara sobre la superficie brillante.");
        System.out.println();
        System.out.println("Y así como quedó escrito, el Compilador empezó a forjar los hilos del destino gracias al i9 13900KF con sus 32 hilos a 6GHz:");
        System.out.println();
    }
    public static void clarCli(){
        for (int i = 0; i < 20; i++) {
            System.out.println("");
        }
    }

}