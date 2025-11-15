package main;

import entities.Cliente;
import entities.Mascota;
import lists.ArbolMascotas;  // 🔥 Árbol desde package lists
import lists.ListaMascotas;  // Lista para carritos
import java.util.Scanner;

public class Main {
    private static ArbolMascotas inventario = new ArbolMascotas(); // 🔥 ÁRBOL BINARIO
    private static Cliente[] colaClientes;
    private static int frente = 0;
    private static int fin = -1;
    private static int tamañoCola = 0;
    private static final int CAPACIDAD_COLA = 50;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        colaClientes = new Cliente[CAPACIDAD_COLA];

        System.out.println("🐾 BIENVENIDO A FRIENDS FOR LIFE 🐾");
        System.out.println("Sistema de Gestión de Mascotas\n");

        cargarDatosEjemplo();
        menuPrincipal();
    }

    private static void cargarDatosEjemplo() {
        // Agregar mascotas al ÁRBOL BINARIO
        inventario.insertar(new Mascota("Max", 150000, "Perro", "Labrador", 24));
        inventario.insertar(new Mascota("Luna", 120000, "Gato", "Siamés", 18));
        inventario.insertar(new Mascota("Rocky", 180000, "Perro", "Pastor Alemán", 36));
        inventario.insertar(new Mascota("Bobby", 130000, "Perro", "Beagle", 20));

        System.out.println("Inventario cargado con " + inventario.getTamaño() + " mascotas en ÁRBOL BINARIO.");
    }

    public static void menuPrincipal() {
        int opcion;

        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Gestión de Inventario (Árbol Binario)");
            System.out.println("2. Gestión de Clientes");
            System.out.println("3. Procesar Atención de Clientes");
            System.out.println("4. Ver Estado del Sistema");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    menuGestionInventario();
                    break;
                case 2:
                    menuGestionClientes();
                    break;
                case 3:
                    procesarAtencion();
                    break;
                case 4:
                    verEstadoSistema();
                    break;
                case 5:
                    System.out.println("¡Gracias por usar Friends for Life!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }

    private static void menuGestionInventario() {
        int opcion;

        do {
            System.out.println("\n=== GESTIÓN DE INVENTARIO (ÁRBOL BINARIO) ===");
            System.out.println("1. Agregar mascota al inventario");
            System.out.println("2. Buscar mascota en inventario");
            System.out.println("3. Mostrar inventario ordenado (In-Order)");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarMascotaInventario();
                    break;
                case 2:
                    buscarMascotaInventario();
                    break;
                case 3:
                    inventario.inOrder();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 4);
    }

    private static void agregarMascotaInventario() {
        System.out.println("\n--- Agregar Mascota al Inventario (Árbol Binario) ---");

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

        Mascota nuevaMascota = new Mascota(nombre, precio, especie, raza, edad);

        if (inventario.insertar(nuevaMascota)) {
            System.out.println("✅ Mascota agregada al ÁRBOL BINARIO exitosamente.");
        } else {
            System.out.println("❌ Error: Ya existe una mascota con ese nombre.");
        }
    }

    private static void buscarMascotaInventario() {
        System.out.println("\n--- Buscar Mascota en Inventario (Árbol Binario) ---");
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();

        Mascota mascota = inventario.buscar(nombre);
        if (mascota != null) {
            System.out.println("✅ Mascota encontrada:");
            System.out.println(mascota);
        } else {
            System.out.println("❌ No se encontró la mascota.");
        }
    }

    // Los métodos de clientes se mantienen igual...
    private static void menuGestionClientes() {
        int opcion;

        do {
            System.out.println("\n=== GESTIÓN DE CLIENTES ===");
            System.out.println("1. Agregar cliente a la cola");
            System.out.println("2. Ver siguiente cliente");
            System.out.println("3. Mostrar cola de clientes");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarClienteCola();
                    break;
                case 2:
                    verSiguienteCliente();
                    break;
                case 3:
                    mostrarColaClientes();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 4);
    }

    private static void agregarClienteCola() {
        if (tamañoCola >= CAPACIDAD_COLA) {
            System.out.println("❌ Error: La cola está llena.");
            return;
        }

        System.out.println("\n--- Agregar Cliente a la Cola ---");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Cédula: ");
        String cedula = scanner.nextLine();

        System.out.print("Prioridad (1=Básico, 2=Afiliado, 3=Premium): ");
        int prioridad = scanner.nextInt();
        scanner.nextLine();

        if (prioridad < 1 || prioridad > 3) {
            System.out.println("❌ Error: Prioridad debe ser 1, 2 o 3.");
            return;
        }

        Cliente nuevoCliente = new Cliente(nombre, cedula, prioridad);

        // Agregar productos al carrito desde el ÁRBOL
        System.out.println("¿Desea agregar mascotas al carrito? (s/n): ");
        String respuesta = scanner.nextLine();

        while (respuesta.equalsIgnoreCase("s")) {
            System.out.print("Nombre de la mascota: ");
            String nombreMascota = scanner.nextLine();

            Mascota mascota = inventario.buscar(nombreMascota);
            if (mascota != null) {
                Mascota copia = new Mascota(
                        mascota.getNombre(),
                        mascota.getPrecio(),
                        mascota.getEspecie(),
                        mascota.getRaza(),
                        mascota.getEdad()
                );
                nuevoCliente.getCarrito().insertarAlFinal(copia);
                System.out.println("✅ Mascota agregada al carrito.");
            } else {
                System.out.println("❌ Mascota no encontrada en inventario.");
            }

            System.out.println("¿Agregar otra mascota? (s/n): ");
            respuesta = scanner.nextLine();
        }

        // Insertar en cola
        fin = (fin + 1) % CAPACIDAD_COLA;
        colaClientes[fin] = nuevoCliente;
        tamañoCola++;

        System.out.println("✅ Cliente agregado a la cola exitosamente.");
    }

    private static void procesarAtencion() {
        if (tamañoCola == 0) {
            System.out.println("❌ No hay clientes en la cola.");
            return;
        }

        // Buscar cliente con mayor prioridad
        int maxPrioridad = -1;
        int posicionMax = -1;

        for (int i = 0; i < tamañoCola; i++) {
            int index = (frente + i) % CAPACIDAD_COLA;
            if (colaClientes[index].getPrioridad() > maxPrioridad) {
                maxPrioridad = colaClientes[index].getPrioridad();
                posicionMax = index;
            }
        }

        if (posicionMax != -1) {
            Cliente clienteAtendido = colaClientes[posicionMax];

            System.out.println("\n--- ATENDIENDO CLIENTE ---");
            System.out.println(clienteAtendido.generarFactura());

            // Reorganizar cola
            for (int i = posicionMax; i != fin; i = (i + 1) % CAPACIDAD_COLA) {
                int siguiente = (i + 1) % CAPACIDAD_COLA;
                colaClientes[i] = colaClientes[siguiente];
            }

            fin = (fin - 1 + CAPACIDAD_COLA) % CAPACIDAD_COLA;
            tamañoCola--;

            System.out.println("✅ Cliente atendido y removido de la cola.");
        }
    }

    private static void verSiguienteCliente() {
        if (tamañoCola == 0) {
            System.out.println("❌ No hay clientes en la cola.");
            return;
        }

        int maxPrioridad = -1;
        Cliente siguiente = null;

        for (int i = 0; i < tamañoCola; i++) {
            int index = (frente + i) % CAPACIDAD_COLA;
            if (colaClientes[index].getPrioridad() > maxPrioridad) {
                maxPrioridad = colaClientes[index].getPrioridad();
                siguiente = colaClientes[index];
            }
        }

        if (siguiente != null) {
            System.out.println("Siguiente cliente a atender:");
            System.out.println(siguiente);
        }
    }

    private static void mostrarColaClientes() {
        if (tamañoCola == 0) {
            System.out.println("La cola está vacía.");
            return;
        }

        System.out.println("\n=== COLA DE CLIENTES ===");
        for (int i = 0; i < tamañoCola; i++) {
            int index = (frente + i) % CAPACIDAD_COLA;
            System.out.println((i + 1) + ". " + colaClientes[index]);
        }
    }

    private static void verEstadoSistema() {
        System.out.println("\n=== ESTADO DEL SISTEMA ===");
        System.out.println("Mascotas en inventario (Árbol Binario): " + inventario.getTamaño());
        System.out.println("Clientes en cola: " + tamañoCola);

        System.out.println("\nInventario actual (ordenado por nombre):");
        inventario.inOrder();
    }
}