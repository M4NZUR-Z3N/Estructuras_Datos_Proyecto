package entities;

import lists.ArbolMascotas;
import lists.ColaClientes;
import lists.GrafoUbicaciones;

public class Tienda {
    private String nombre;
    private Ubicacion ubicacionTienda;
    private ArbolMascotas inventario;
    private ColaClientes colaClientes;
    private GrafoUbicaciones grafoUbicaciones;

    public Tienda(String nombre, Ubicacion ubicacionTienda, int capacidadCola) {
        this.nombre = nombre;
        this.ubicacionTienda = ubicacionTienda;
        this.inventario = new ArbolMascotas();
        this.grafoUbicaciones = new GrafoUbicaciones();
        this.grafoUbicaciones.agregarUbicacion(ubicacionTienda);
        this.colaClientes = new ColaClientes(capacidadCola, this.grafoUbicaciones);
    }

    public ArbolMascotas getInventario() { 
        return inventario; 
    }
    
    public ColaClientes getColaClientes() { 
        return colaClientes; 
    }
    
    public String getNombre() { 
        return nombre; 
    }
    
    public Ubicacion getUbicacionTienda() { 
        return ubicacionTienda; 
    }
    
    public GrafoUbicaciones getGrafoUbicaciones() { 
        return grafoUbicaciones; 
    }
}