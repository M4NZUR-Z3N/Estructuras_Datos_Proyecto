package entities;
import lists.ListaMascotas;

public class Cliente {
    private String nombre;
    private String cedula;
    private int prioridad; // 1=básico, 2=afiliado, 3=premium
    private ListaMascotas carrito;

    public Cliente(String nombre, String cedula, int prioridad) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.prioridad = prioridad;
        this.carrito = new ListaMascotas();
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public int getPrioridad() { return prioridad; }
    public void setPrioridad(int prioridad) { this.prioridad = prioridad; }

    public ListaMascotas getCarrito() { return carrito; }

    // Método para generar factura
    public String generarFactura() {
        StringBuilder factura = new StringBuilder();
        factura.append("=== FACTURA ===\n");
        factura.append("Cliente: ").append(nombre).append("\n");
        factura.append("Cédula: ").append(cedula).append("\n");
        factura.append("Prioridad: ").append(prioridad).append("\n");
        factura.append("--- Productos en Carrito ---\n");

        // Usar el método de la lista para generar reporte
        String reporteCarrito = carrito.generarReporteCostos();
        factura.append(reporteCarrito);
        factura.append("================\n");

        return factura.toString();
    }

    @Override
    public String toString() {
        return String.format("Cliente: %s | Cédula: %s | Prioridad: %d | Productos en carrito: %d",
                nombre, cedula, prioridad, carrito.getTamaño());
    }
}