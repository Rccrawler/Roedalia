package org.example;

import java.util.ArrayList;

public class Carreta {

    private String nombre;
    private ArrayList<String> productos;

    // Constructor
    public Carreta(String nombre) {
        this.nombre = nombre;
        this.productos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<String> productos) {
        this.productos = productos;
    }

    public void addProducto(String producto) {
        this.productos.add(producto);
    }
}
