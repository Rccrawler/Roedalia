package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;

public class SalaDelPortonNorte {
    private boolean lanceDuLacDionPresente  = false;
    private String mensajeRumor = "";

    private ArrayList<Carreta> carretas = new ArrayList<>();

    private String[] productosNoPermitidos = {"Queso sin fermentar", "Leche cruda"};
    private String[] nombres = {
            "Juan", "Ana", "Carlos", "Lucía", "Pedro",
            "Marta", "Luis", "Sofía", "David", "Elena",
            "Jorge", "Paula", "Mario", "Laura", "Andrés",
            "Raúl", "Carmen", "Diego", "Sara", "Iván",
            "Patricia", "Alberto", "Noelia", "Hugo", "Valeria",
            "Adrián", "Claudia", "Samuel", "Nerea", "Rubén",
            "Alicia", "Daniel", "Natalia", "Óscar", "Julia",
            "Fernando", "Cristina", "Sergio", "Irene", "Álvaro",
            "Beatriz", "Manuel", "Rocío", "Gabriel", "Esther",
            "Miguel", "Carla", "Marcos", "Silvia", "Pablo",
            "Victoria", "Alejandro", "Eva", "Tomás", "Lidia",
            "Guillermo", "Inés", "Ismael", "Lorena", "Joel",
            "Marina", "Ricardo", "Aitana", "Bruno", "Candela",
            "Dani", "Elisa", "Fabián", "Gema", "Héctor",
            "Iker", "Jimena", "Kevin", "Lara", "Mateo",
            "Nicolás", "Olga", "Ramón", "Teresa", "Unai",
            "Vera", "Xavier", "Yolanda", "Zoe"
    };

    private String[] alimentos = {
            "Manzana", "Plátano", "Pera", "Fresa", "Uva",
            "Melón", "Sandía", "Mango", "Papaya", "Kiwi",
            "Zanahoria", "Lechuga", "Espinaca", "Brócoli", "Pepino",
            "Calabacín", "Calabaza", "Maíz", "Guisantes", "Judías verdes",
            "Tomate", "Pimiento", "Patata cocida", "Batata", "Remolacha",
            "Arroz", "Avena", "Trigo", "Cebada", "Mijo",
            "Quinoa", "Lentejas", "Garbanzos", "Alubias", "Soja",
            "Pan", "Pan negro", "Cereales", "Copos de maíz", "Pasta cocida",
            "Semillas de girasol", "Semillas de calabaza", "Semillas de lino", "Semillas de sésamo", "Semillas de chía",
            "Almendras", "Nueces", "Avellanas", "Anacardos", "Cacahuetes",
            "Huevo cocido", "Queso", "perdiz", "Pimenton", "Curriy",
            "pimienta", "gindilla", "Galletas simples", "Bizcocho", "Tortitas",
            "Pienso para roedores", "Mezcla de semillas", "Zecina", "Granola", "Avena",
            "Higos", "Dátiles", "Pasas", "Arándanos", "Frambuesas",
            "Mandarina", "Naranja", "Pomelo", "Coco", "Piña",
            "Champiñones", "Coliflor", "Repollo", "Apio", "Acelga",
            "Pipas", "Maíz inflado", "Pan duro", "Arroz inflado", "Harina",
            "Miel", "Azúcar", "Canela", "Carne cocida", "Pescado cocido"
    };



    public synchronized void esperarlanceDuLacDion(DataOutputStream salidaCaballero, DataInputStream entradaCaballero){
        try {
            if(this.lanceDuLacDionPresente){
                salidaCaballero.writeUTF("lo encontre");
                this.mensajeRumor = entradaCaballero.readUTF();
            }else {
                salidaCaballero.writeUTF("no lo encuentro en el porton");
                try { // esperamos un poco que las damas corren poco
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.err.println("El hilo fue interrumpido: " + e.getMessage());
                }

                // recordar el sistema de duelos con lanze y toda la movida

            }/*
            while (!this.elisabethaPresente){
                wait(); // que espere a elisabet la dama en concreto
            }*/
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void notificarLlegada(DataOutputStream salidaElisabetha, DataInputStream entradaElisabetha){

        // arreglar para pasarle a lance y a los caballeros los chismes cuando esten los personajes

        this.lanceDuLacDionPresente = true;
        notifyAll(); // despertamos a las damas revisar que hilos se despiertan esacta mente
        try{
            try { // esperamos un poco que las damas duerme mucho
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.err.println("El hilo fue interrumpido: " + e.getMessage());
            }
            salidaElisabetha.writeUTF(this.mensajeRumor);
            salidaElisabetha.writeUTF("Damas atendidas");
            this.lanceDuLacDionPresente = false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void lleganCarretas(){
        // que ballan llegando carretas desde que inicia el programa porque lla tiene que haber una fila antes de que llege el caballero
        Random random = new Random();

        // Seleccionamos 3 nombres aleatorios para crear las carretas
        while (carretas.size() < 3) {
            int idx = random.nextInt(nombres.length);
            String nombre = nombres[idx];
            boolean existe = false;
            for (Carreta c : carretas) {
                if (c.getNombre().equals(nombre)) {
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                carretas.add(new Carreta(nombre));
            }
        }

        // Asignar productos aleatorios a cada carreta
        for (Carreta c : carretas) {
            // Cantidad aleatoria de productos normales (1-5)
            int cantidad = 1 + random.nextInt(10);

            while (c.getProductos().size() < cantidad) {
                String producto = alimentos[random.nextInt(alimentos.length)];
                if (!c.getProductos().contains(producto)) {
                    c.addProducto(producto);
                }
            }

            // Probabilidad 30% de añadir un producto no permitido
            if (random.nextInt(100) < 30) {
                String prodNP = productosNoPermitidos[random.nextInt(productosNoPermitidos.length)];
                c.addProducto(prodNP);
            }
        }

        /*
        // Mostrar resultados
        for (Carreta c : carretas) {
            System.out.println(c.getNombre() + " tiene: " + c.getProductos());
        }*/
    }
    public synchronized void AnadirCarretasAFila(){
        Random random = new Random();
        int numCarretas = 1;

        if (random.nextInt(100) < 30) {
            numCarretas = 2;
        }

        for (int i = 0; i < numCarretas; i++) {
            String nombre;
            boolean existe;
            do {
                int idx = random.nextInt(nombres.length);
                nombre = nombres[idx];
                existe = false;
                for (Carreta c : carretas) {
                    if (c.getNombre().equals(nombre)) {
                        existe = true;
                        break;
                    }
                }
            } while (existe);

            carretas.add(new Carreta(nombre));
        }
    }
    public synchronized boolean eliminarCarretaPorNombre(String nombre){
        for (int i = 0; i < carretas.size(); i++) {
            if (carretas.get(i).getNombre().equals(nombre)) {
                carretas.remove(i);
                return true;
            }
        }
        return false;
    }


    public synchronized void hacerVigilancia(DataInputStream flujoEntrada, DataOutputStream flujoSalida) {
        String caballero;
        try {
            caballero = flujoEntrada.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        AnadirCarretasAFila();

        Carreta carreta = carretas.remove(0);
        String nombreCarreta = carreta.getNombre();
        boolean revisarProductos = new Random().nextInt(100) < 30;

        String detalle;
        String resultado;

        if (revisarProductos) {
            detalle = caballero + " revisa la carreta de " + nombreCarreta;
            String productoProhibido = null;
            for (String producto : carreta.getProductos()) {
                for (String prohibido : productosNoPermitidos) {
                    if (prohibido.equals(producto)) {
                        productoProhibido = prohibido;
                        break;
                    }
                }
                if (productoProhibido != null) {
                    break;
                }
            }

            if (productoProhibido != null) {
                resultado = caballero + ": no puedes pasar, producto no permitido (" + productoProhibido + "). Carreta retirada.";
            } else {
                resultado = caballero + ": puedes pasar, productos revisados sin problemas.";
            }
        } else {
            detalle = caballero + " reconoce a " + nombreCarreta + " como vecino de la ciudad";
            resultado = caballero + ": pasa sin inspeccion, bienvenido.";
        }

        if (carretas.isEmpty()) {
            AnadirCarretasAFila();
        }

        try {
            flujoSalida.writeUTF(detalle);
            flujoSalida.writeUTF(resultado);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
