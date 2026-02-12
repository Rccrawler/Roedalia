package org.example;

public class AlacenaPociones {

    // Clase compartida de los malvados alquimistas

    private int pociones;

    public AlacenaPociones(){
        this.pociones = 1;
    }

    public synchronized int MirarPociones(){
        return pociones;
    }

    public synchronized void SumarPocion(){
        this.pociones = (this.pociones + 1);
    }

}

/*

public class Main {

    public static void main(String[] args) {

        Contador contador = new Contador();
        int numHilo = 1;

        for (int i = 1; i < 101; i++) {
            Hilo tread = new Hilo(contador, numHilo);
            tread.start();
            numHilo++;
        }

    }
}


public class Contador {
    private int cont;

    public Contador() {
        this.cont = 1;
    }

    public synchronized int MirarContador(){
        return cont;
    }

    public synchronized void PonerValorContador(int nuevoValor){
        this.cont = nuevoValor;
    }

}


public class Hilo extends Thread{
    private Contador contador;
    private int numHilo;
   // Hilo(int contador){
    //    this.cont = contador;
   // }
    Hilo(Contador contador, int numHilo){
        this.contador = contador;
        this.numHilo = numHilo;
    }

    public void run() {
        synchronized(contador) {
            while (numHilo != contador.MirarContador()) {
                //System.out.println("soy el hilo" + ", " + numHilo + " != " + contador.MirarContador());
                try {
                    contador.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return; // Termina el hilo si es interrumpido
                }
            }

            System.out.println(contador.MirarContador());
            contador.PonerValorContador(contador.MirarContador() + 1);
            //System.out.println("ahora el contador esta en:" + contador.MirarContador());

            contador.notifyAll(); // ← Despierta a TODOS los hilos esperando
        }
    }

}

 */