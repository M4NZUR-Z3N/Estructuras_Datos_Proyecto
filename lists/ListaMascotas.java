package lists;
import entities.Mascota;

public class ListaMascotas {
    private NodoMascota cabeza;
    private int tamaño;
    
    // Constructor
    public ListaMascotas() {
        this.cabeza = null;
        this.tamaño = 0;
    }
    
    // 1. Insertar al inicio
    public void insertarAlInicio(Mascota mascota) {
        NodoMascota nuevoNodo = new NodoMascota(mascota);
        nuevoNodo.setSiguiente(cabeza);
        cabeza = nuevoNodo;
        tamaño++;
    }
    
    // 2. Insertar al final
    public void insertarAlFinal(Mascota mascota) {
        NodoMascota nuevoNodo = new NodoMascota(mascota);
        
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoMascota actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        tamaño++;
    }
    
    // 3. Modificar mascota por nombre
    public boolean modificarMascota(String nombre, Mascota mascotaActualizada) {
        NodoMascota actual = cabeza;
        
        while (actual != null) {
            if (actual.getMascota().getNombre().equals(nombre)) {
                actual.setMascota(mascotaActualizada);
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false; // No se encontró la mascota
    }
    
    // 4. Agregar imagen a una mascota específica
    public boolean agregarImagenAMascota(String nombreMascota, String rutaImagen) {
        NodoMascota actual = cabeza;
        
        while (actual != null) {
            if (actual.getMascota().getNombre().equals(nombreMascota)) {
                actual.getMascota().agregarImagen(rutaImagen);
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false; // No se encontró la mascota
    }
    
    // 5. Método para generar reporte de costos
    public void generarReporteCostos() {
        if (cabeza == null) {
            System.out.println("La lista de mascotas está vacía.");
            return;
        }
        
        NodoMascota actual = cabeza;
        double costoTotalAcumulado = 0;
        
        System.out.println("=== REPORTE DE COSTOS DE MASCOTAS ===");
        
        while (actual != null) {
            Mascota mascota = actual.getMascota();
            // Para este ejemplo, usaremos cantidad = 1, pero puedes modificarlo
            int cantidad = 1;
            double costoTotalMascota = mascota.calcularCostoTotal(cantidad);
            
            System.out.printf("Mascota: %s | Precio unitario: ₡%.2f | Cantidad: %d | Costo total: ₡%.2f%n",
                    mascota.getNombre(), mascota.getPrecio(), cantidad, costoTotalMascota);
            
            costoTotalAcumulado += costoTotalMascota;
            actual = actual.getSiguiente();
        }
        
        System.out.printf("=== COSTO TOTAL ACUMULADO: ₡%.2f ===%n", costoTotalAcumulado);
    }
    
    // 6. Mostrar todas las mascotas
    public void mostrarMascotas() {
        if (cabeza == null) {
            System.out.println("La lista de mascotas está vacía.");
            return;
        }
        
        NodoMascota actual = cabeza;
        int contador = 1;
        
        System.out.println("=== LISTA DE MASCOTAS ===");
        while (actual != null) {
            System.out.println(contador + ". " + actual.getMascota());
            actual = actual.getSiguiente();
            contador++;
        }
    }
    
    // 7. Buscar mascota por nombre
    public Mascota buscarMascota(String nombre) {
        NodoMascota actual = cabeza;
        
        while (actual != null) {
            if (actual.getMascota().getNombre().equals(nombre)) {
                return actual.getMascota();
            }
            actual = actual.getSiguiente();
        }
        return null; // No se encontró
    }
    
    // 8. Eliminar mascota por nombre
    public boolean eliminarMascota(String nombre) {
        if (cabeza == null) {
            return false;
        }
        
        // Caso especial: eliminar la cabeza
        if (cabeza.getMascota().getNombre().equals(nombre)) {
            cabeza = cabeza.getSiguiente();
            tamaño--;
            return true;
        }
        
        // Buscar en el resto de la lista
        NodoMascota actual = cabeza;
        NodoMascota anterior = null;
        
        while (actual != null && !actual.getMascota().getNombre().equals(nombre)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }
        
        if (actual != null) {
            anterior.setSiguiente(actual.getSiguiente());
            tamaño--;
            return true;
        }
        
        return false; // No se encontró
    }
    
    // Getters
    public int getTamaño() {
        return tamaño;
    }
    
    public boolean estaVacia() {
        return cabeza == null;
    }
}