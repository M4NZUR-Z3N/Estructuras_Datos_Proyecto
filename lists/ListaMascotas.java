package lists;
import entities.Mascota;

public class ListaMascotas {
    private NodoMascota primero;
    private int tamaño;
    
    // Constructor
    public ListaMascotas() {
        this.primero = null;
        this.tamaño = 0;
    }
    
    // 1. Insertar al inicio
    public void insertarAlInicio(Mascota mascota) {
        NodoMascota nuevoNodo = new NodoMascota(mascota);
        nuevoNodo.setSiguiente(primero);
        primero = nuevoNodo;
        tamaño++;
    }
    
    // 2. Insertar al final
    public void insertarAlFinal(Mascota mascota) {
        NodoMascota nuevoNodo = new NodoMascota(mascota);
        
        if (primero == null) {
            primero = nuevoNodo;
        } else {
            NodoMascota actual = primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        tamaño++;
    }
    
    // 3. Modificar mascota por nombre
    public boolean modificarMascota(String nombre, Mascota mascotaActualizada) {
        NodoMascota actual = primero;
        
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
    public boolean agregarImagenAMascota(Mascota mascota, String rutaImagen) {
                mascota.agregarImagen(rutaImagen);
                return true;
    }
    
    // 5. Método para generar reporte de costos
    public void generarReporteCostos() {
        if (primero == null) {
            System.out.println("La lista de mascotas está vacía.");
            return;
        }
        
        NodoMascota actual = primero;
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
        if (primero == null) {
            System.out.println("La lista de mascotas está vacía.");
            return;
        }
        
        NodoMascota actual = primero;
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
        NodoMascota actual = primero;
        
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
        if (primero == null) {
            return false;
        }
        
        // Caso especial: eliminar la primero
        if (primero.getMascota().getNombre().equals(nombre)) {
            primero = primero.getSiguiente();
            tamaño--;
            return true;
        }
        
        // Buscar en el resto de la lista
        NodoMascota actual = primero;
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
        return primero == null;
    }
}