package lists;
import entities.Mascota;
import java.util.ArrayList;

public class ListaMascotas {
    private NodoLista primero;
    private int tamaño;

    // Nodo para lista enlazada simple
    private static class NodoLista {
        private Mascota mascota;
        private NodoLista siguiente;

        public NodoLista(Mascota mascota) {
            this.mascota = mascota;
            this.siguiente = null;
        }
    }

    public ListaMascotas() {
        this.primero = null;
        this.tamaño = 0;
    }

    // Insertar al final (para carrito)
    public void insertarAlFinal(Mascota mascota) {
        NodoLista nuevoNodo = new NodoLista(mascota);

        if (primero == null) {
            primero = nuevoNodo;
        } else {
            NodoLista actual = primero;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamaño++;
    }

    // Generar reporte de costos para factura
    public String generarReporteCostos() {
        StringBuilder reporte = new StringBuilder();

        if (primero == null) {
            return "El carrito está vacío.\n";
        }

        NodoLista actual = primero;
        double costoTotalAcumulado = 0;
        int item = 1;

        while (actual != null) {
            int cantidad = 1;
            double costoTotalMascota = actual.mascota.calcularCostoTotal(cantidad);

            reporte.append(String.format("%d. %s - ₡%.2f\n",
                    item, actual.mascota.getNombre(), costoTotalMascota));

            costoTotalAcumulado += costoTotalMascota;
            actual = actual.siguiente;
            item++;
        }

        reporte.append(String.format("TOTAL: ₡%.2f\n", costoTotalAcumulado));
        return reporte.toString();
    }

    // Getters
    public int getTamaño() { return tamaño; }
    public boolean estaVacia() { return primero == null; }
}