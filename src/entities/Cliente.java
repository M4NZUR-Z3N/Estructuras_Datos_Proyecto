package entities;
import lists.ListaMascotas;
import java.util.List;

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

    public String generarFacturaConRuta(List<Ubicacion> camino, int distanciaTotal) {
        StringBuilder factura = new StringBuilder();
        factura.append("======================================\n");
        factura.append("       FACTURA - FRIENDS FOR LIFE     \n");
        factura.append("======================================\n");
        factura.append("CLIENTE: ").append(nombre).append("\n");
        factura.append("CEDULA:  ").append(cedula).append("\n");
        factura.append("PRIORIDAD: ").append(prioridad);
        if (prioridad == 1) factura.append(" (Basico)");
        else if (prioridad == 2) factura.append(" (Afiliado)");
        else factura.append(" (Premium)");
        factura.append("\n");
        factura.append("UBICACION: ").append(ubicacion.getNombre()).append("\n");
        factura.append("--------------------------------------\n");
        factura.append("      PRODUCTOS EN CARRITO           \n");
        factura.append("--------------------------------------\n");
        
        String reporteCarrito = carrito.generarReporteCostos();
        factura.append(reporteCarrito);
        
        factura.append("--------------------------------------\n");
        factura.append("          RUTA DE ENTREGA            \n");
        factura.append("--------------------------------------\n");
        
        if (camino.isEmpty()) {
            factura.append("NO HAY RUTA DISPONIBLE\n");
        } else {
            factura.append("Distancia total: ").append(distanciaTotal).append(" km\n");
            factura.append("Ruta optima:\n");
            
            String rutaStr = "";
            for (int i = 0; i < camino.size(); i++) {
                rutaStr += camino.get(i).getNombre();
                if (i < camino.size() - 1) {
                    rutaStr += " -> ";
                }
            }
            
            factura.append(rutaStr).append("\n");
        }
        
        factura.append("======================================\n");
        
        return factura.toString();
    }

    @Override
    public String toString() {
        return String.format("Cliente: %s | Cedula: %s | Ubicacion: %s | Prioridad: %d | Productos: %d",
                nombre, cedula, ubicacion.getNombre(), prioridad, carrito.getTamaño());
    }
}