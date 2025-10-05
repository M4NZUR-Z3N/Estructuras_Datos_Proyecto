import entities.Mascota;
import java.util.Scanner;
import lists.ListaMascotas;
import view.PanelManager;

public class main {
    private static ListaMascotas listaMascotas = new ListaMascotas();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("🐾 BIENVENIDO A FRIENDS FOR LIFE 🐾");
        System.out.println("Sistema de Gestión de Mascotas\n");
        
        PanelManager.programa();
    }
    
    public static void menuPrincipal() {
        int opcion;
        
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Agregar mascota al inicio");
            System.out.println("2. Agregar mascota al final");
            System.out.println("3. Modificar mascota");
            System.out.println("4. Agregar imagen a mascota");
            System.out.println("5. Generar reporte de costos");
            System.out.println("6. Mostrar todas las mascotas");
            System.out.println("7. Buscar mascota por nombre");
            System.out.println("8. Eliminar mascota");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1:
                    agregarMascotaAlInicio();
                    break;
                case 2:
                    agregarMascotaAlFinal();
                    break;
                case 3:
                    modificarMascota();
                    break;
                case 4:
                    agregarImagenAMascota();
                    break;
                case 5:
                    listaMascotas.generarReporteCostos();
                    break;
                case 6:
                    listaMascotas.mostrarMascotas();
                    break;
                case 7:
                    buscarMascota();
                    break;
                case 8:
                    eliminarMascota();
                    break;
                case 9:
                    System.out.println("¡Gracias por usar Friends for Life!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 9);
    }
    
    private static void agregarMascotaAlInicio() {
        System.out.println("\n--- Agregar Mascota al Inicio ---");
        Mascota mascota = leerDatosMascota();
        listaMascotas.insertarAlInicio(mascota);
        System.out.println("Mascota agregada al inicio exitosamente.");
    }
    
    private static void agregarMascotaAlFinal() {
        System.out.println("\n--- Agregar Mascota al Final ---");
        Mascota mascota = leerDatosMascota();
        listaMascotas.insertarAlFinal(mascota);
        System.out.println("Mascota agregada al final exitosamente.");
    }
    
    private static Mascota leerDatosMascota() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Especie: ");
        String especie = scanner.nextLine();
        
        System.out.print("Raza: ");
        String raza = scanner.nextLine();
        
        System.out.print("Edad (meses): ");
        int edad = scanner.nextInt();
        scanner.nextLine();
        
        return new Mascota(nombre, precio, especie, raza, edad);
    }
    
    private static void modificarMascota() {
        System.out.println("\n--- Modificar Mascota ---");
        System.out.print("Ingrese el nombre de la mascota a modificar: ");
        String nombre = scanner.nextLine();
        
        Mascota mascotaExistente = listaMascotas.buscarMascota(nombre);
        if (mascotaExistente != null) {
            System.out.println("Ingrese los nuevos datos:");
            Mascota mascotaActualizada = leerDatosMascota();
            
            if (listaMascotas.modificarMascota(nombre, mascotaActualizada)) {
                System.out.println("Mascota modificada exitosamente.");
            } else {
                System.out.println("Error al modificar la mascota.");
            }
        } else {
            System.out.println("No se encontró una mascota con ese nombre.");
        }
    }
    
    private static void agregarImagenAMascota() {
        System.out.println("\n--- Agregar Imagen a Mascota ---");
        System.out.print("Ingrese el nombre de la mascota: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Ingrese la ruta de la imagen: ");
        String rutaImagen = scanner.nextLine();
        /*
        if (listaMascotas.agregarImagenAMascota(nombre, rutaImagen)) {
            System.out.println("Imagen agregada exitosamente.");
        } else {
            System.out.println("No se encontró una mascota con ese nombre.");
        }*/
    }
    
    private static void buscarMascota() {
        System.out.println("\n--- Buscar Mascota ---");
        System.out.print("Ingrese el nombre de la mascota: ");
        String nombre = scanner.nextLine();
        
        Mascota mascota = listaMascotas.buscarMascota(nombre);
        if (mascota != null) {
            System.out.println("Mascota encontrada:");
            System.out.println(mascota);
        } else {
            System.out.println("No se encontró una mascota con ese nombre.");
        }
    }
    
    private static void eliminarMascota() {
        System.out.println("\n--- Eliminar Mascota ---");
        System.out.print("Ingrese el nombre de la mascota a eliminar: ");
        String nombre = scanner.nextLine();
        
        if (listaMascotas.eliminarMascota(nombre)) {
            System.out.println("Mascota eliminada exitosamente.");
        } else {
            System.out.println("No se encontró una mascota con ese nombre.");
        }
    }
}