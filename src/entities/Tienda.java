package entities;

import lists.ArbolMascotas;
import lists.ColaClientes;

public class Tienda {
    private String nombre;
    private ArbolMascotas inventario;
    private ColaClientes colaClientes;

    public Tienda(String nombre, int capacidadCola) {
        this.nombre = nombre;
        this.inventario = new ArbolMascotas();
        this.colaClientes = new ColaClientes(capacidadCola);
    }

    public ArbolMascotas getInventario() { return inventario; }
    public ColaClientes getColaClientes() { return colaClientes; }
    public String getNombre() { return nombre; }
}
