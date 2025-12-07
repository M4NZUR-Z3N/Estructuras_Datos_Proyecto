package entities;
import lists.ListaMascotas;

public class Cliente {
    private String nombre;
    private String cedula;
    private int prioridad;
    private Ubicacion ubicacion;
    private ListaMascotas carrito;

    public Cliente(String nombre, String cedula, int prioridad, Ubicacion ubicacion) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.prioridad = prioridad;
        this.ubicacion = ubicacion;
        this.carrito = new ListaMascotas();
    }

    public String getNombre() { 
        return nombre; 
    }
    
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public String getCedula() { 
        return cedula; 
    }
    
    public void setCedula(String cedula) { 
        this.cedula = cedula; 
    }

    public int getPrioridad() { 
        return prioridad; 
    }
    
    public void setPrioridad(int prioridad) { 
        this.prioridad = prioridad; 
    }

    public Ubicacion getUbicacion() { 
        return ubicacion; 
    }
    
    public void setUbicacion(Ubicacion ubicacion) { 
        this.ubicacion = ubicacion; 
    }

    public ListaMascotas getCarrito() { 
        return carrito; 
    }

    public String generarFactura() {
        StringBuilder factura = new StringBuilder();
        factura.append("=== FACTURA FRIENDS FOR LIFE ===\n");
        factura.append("Cliente: ").append(nombre).append("\n");
        factura.append("Cédula: ").append(cedula).append("\n");
        factura.append("Ubicación: ").append(ubicacion.getNombre()).append("\n");
        factura.append("Prioridad: ").append(prioridad).append("\n");
        factura.append("--- Productos en Carrito ---\n");
        
        String reporteCarrito = carrito.generarReporteCostos();
        factura.append(reporteCarrito);
        factura.append("================\n");
        
        return factura.toString();
    }

    @Override
    public String toString() {
        return String.format("Cliente: %s | Cédula: %s | Ubicación: %s | Prioridad: %d | Productos: %d",
                nombre, cedula, ubicacion.getNombre(), prioridad, carrito.getTamaño());
    }
}