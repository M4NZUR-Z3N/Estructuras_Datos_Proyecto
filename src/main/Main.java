package main;

import entities.*;
import lists.ArbolMascotas;
import lists.ColaClientes;
import lists.GrafoUbicaciones;
import java.util.*;

public class Main {
    private static Tienda tienda;
    private static Scanner scanner = new Scanner(System.in);
    private static List<Ubicacion> ubicacionesDisponibles;

    public static void main(String[] args) {
        // Inicializar ubicaciones de Costa Rica
        inicializarUbicacionesCR();
        
        // Crear tienda con ubicación en San José
        Ubicacion ubicacionTienda = buscarUbicacionPorCodigo("SJ01");
        tienda = new Tienda("Friends for Life", ubicacionTienda, 50);
        
        // Inicializar grafo con conexiones de Costa Rica
        inicializarGrafoCostaRica();
        
        System.out.println("🐾 BIENVENIDO A FRIENDS FOR LIFE 🐾");
        System.out.println("Sistema de Gestión de Mascotas y Entregas\n");
        
        cargarDatosEjemplo();
        menuPrincipal();
    }

    private static void inicializarUbicacionesCR() {
        ubicacionesDisponibles = new ArrayList<>();
        
        // Provincias principales de Costa Rica
        ubicacionesDisponibles.add(new Ubicacion("San José Centro", "SJ01"));
        ubicacionesDisponibles.add(new Ubicacion("Alajuela Centro", "AL01"));
        ubicacionesDisponibles.add(new Ubicacion("Heredia Centro", "HE01"));
        ubicacionesDisponibles.add(new Ubicacion("Cartago Centro", "CA01"));
        ubicacionesDisponibles.add(new Ubicacion("Puntarenas Centro", "PU01"));
        ubicacionesDisponibles.add(new Ubicacion("Limón Centro", "LI01"));
        ubicacionesDisponibles.add(new Ubicacion("Escazú", "SJ02"));
        ubicacionesDisponibles.add(new Ubicacion("Desamparados", "SJ03"));
        ubicacionesDisponibles.add(new Ubicacion("Liberia", "GU01"));
    }

    private static Ubicacion buscarUbicacionPorCodigo(String codigo) {
        for (Ubicacion u : ubicacionesDisponibles) {
            if (u.getCodigo().equals(codigo)) {
                return u;
            }
        }
        return null;
    }

    private static void inicializarGrafoCostaRica() {
        // Obtener ubicaciones
        Ubicacion sj = buscarUbicacionPorCodigo("SJ01");
        Ubicacion al = buscarUbicacionPorCodigo("AL01");
        Ubicacion he = buscarUbicacionPorCodigo("HE01");
        Ubicacion ca = buscarUbicacionPorCodigo("CA01");
        Ubicacion pu = buscarUbicacionPorCodigo("PU01");
        Ubicacion li = buscarUbicacionPorCodigo("LI01");
        Ubicacion es = buscarUbicacionPorCodigo("SJ02");
        Ubicacion de = buscarUbicacionPorCodigo("SJ03");
        Ubicacion gu = buscarUbicacionPorCodigo("GU01");

        // Conexiones con distancias aproximadas en km
        GrafoUbicaciones grafo = tienda.getGrafoUbicaciones();
        
        // Conexiones desde San José
        grafo.agregarConexion(sj, al, 18);   // San José - Alajuela
        grafo.agregarConexion(sj, he, 10);   // San José - Heredia
        grafo.agregarConexion(sj, ca, 24);   // San José - Cartago
        grafo.agregarConexion(sj, es, 8);    // San José - Escazú
        grafo.agregarConexion(sj, de, 6);    // San José - Desamparados
        
        // Otras conexiones
        grafo.agregarConexion(al, he, 25);   // Alajuela - Heredia
        grafo.agregarConexion(al, gu, 150);  // Alajuela - Liberia
        grafo.agregarConexion(ca, li, 85);   // Cartago - Limón
        grafo.agregarConexion(sj, pu, 90);   // San José - Puntarenas
        
        System.out.println("Grafo de ubicaciones inicializado con " + 
                          grafo.getConexiones(sj).size() + " conexiones desde la tienda.");
    }

    private static void cargarDatosEjemplo() {
        tienda.getInventario().insertar(new Mascota("Max", 150000, "Perro", "Labrador", 24));
        tienda.getInventario().insertar(new Mascota("Luna", 120000, "Gato", "Siamés", 18));
        tienda.getInventario().insertar(new Mascota("Rocky", 180000, "Perro", "Pastor Alemán", 36));
        tienda.getInventario().insertar(new Mascota("Bobby", 130000, "Perro", "Beagle", 20));
        
        System.out.println("Inventario cargado con " + tienda.getInventario().getTamaño() + " mascotas.");
    }

    public static void menuPrincipal() {
        int opcion;

        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Gestión de Inventario (Árbol Binario)");
            System.out.println("2. Gestión de Clientes");
            System.out.println("3. Gestión de Ubicaciones y Rutas");
            System.out.println("4. Procesar Atención de Clientes");
            System.out.println("5. Ver Estado del Sistema");
            System.out.println("6. Salir");
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
                    menuGestionUbicaciones();
                    break;
                case 4:
                    procesarAtencion();
                    break;
                case 5:
                    verEstadoSistema();
                    break;
                case 6:
                    System.out.println("¡Gracias por usar Friends for Life!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 6);
    }

    private static void menuGestionInventario() {
        int opcion;

        do {
            System.out.println("\n=== GESTIÓN DE INVENTARIO ===");
            System.out.println("1. Agregar mascota al inventario");
            System.out.println("2. Buscar mascota en inventario");
            System.out.println("3. Mostrar inventario ordenado");
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
                    tienda.getInventario().inOrder();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 4);
    }

    private static void agregarMascotaInventario() {
        System.out.println("\n--- Agregar Mascota al Inventario ---");
        
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
        
        if (tienda.getInventario().insertar(nuevaMascota)) {
            System.out.println("Mascota agregada exitosamente.");
        } else {
            System.out.println("Error: Ya existe una mascota con ese nombre.");
        }
    }

    private static void buscarMascotaInventario() {
        System.out.println("\n--- Buscar Mascota en Inventario ---");
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        
        Mascota mascota = tienda.getInventario().buscar(nombre);
        if (mascota != null) {
            System.out.println("Mascota encontrada:");
            System.out.println(mascota);
        } else {
            System.out.println("No se encontró la mascota.");
        }
    }

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
        System.out.println("\n--- Agregar Cliente a la Cola ---");
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Cédula: ");
        String cedula = scanner.nextLine();
        
        System.out.print("Prioridad (1=Básico, 2=Afiliado, 3=Premium): ");
        int prioridad = scanner.nextInt();
        scanner.nextLine();
        
        if (prioridad < 1 || prioridad > 3) {
            System.out.println("Error: Prioridad debe ser 1, 2 o 3.");
            return;
        }
        
        // Seleccionar ubicación
        System.out.println("\nUbicaciones disponibles:");
        for (int i = 0; i < ubicacionesDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + ubicacionesDisponibles.get(i));
        }
        System.out.print("Seleccione ubicación (número): ");
        int idxUbicacion = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (idxUbicacion < 0 || idxUbicacion >= ubicacionesDisponibles.size()) {
            System.out.println("Ubicación inválida.");
            return;
        }
        
        Ubicacion ubicacionCliente = ubicacionesDisponibles.get(idxUbicacion);
        Cliente nuevoCliente = new Cliente(nombre, cedula, prioridad, ubicacionCliente);
        
        // Agregar mascotas al carrito
        System.out.println("¿Desea agregar mascotas al carrito? (s/n): ");
        String respuesta = scanner.nextLine();
        
        while (respuesta.equalsIgnoreCase("s")) {
            System.out.print("Nombre de la mascota: ");
            String nombreMascota = scanner.nextLine();
            
            Mascota mascota = tienda.getInventario().buscar(nombreMascota);
            if (mascota != null) {
                Mascota copia = new Mascota(
                        mascota.getNombre(),
                        mascota.getPrecio(),
                        mascota.getEspecie(),
                        mascota.getRaza(),
                        mascota.getEdad()
                );
                nuevoCliente.getCarrito().insertarAlFinal(copia);
                System.out.println("Mascota agregada al carrito.");
            } else {
                System.out.println("Mascota no encontrada en inventario.");
            }
            
            System.out.println("¿Agregar otra mascota? (s/n): ");
            respuesta = scanner.nextLine();
        }
        
        // Insertar en cola
        boolean ok = tienda.getColaClientes().encolar(nuevoCliente);
        if (ok) {
            System.out.println("Cliente agregado a la cola exitosamente.");
        } else {
            System.out.println("Error: La cola está llena.");
        }
    }

    private static void menuGestionUbicaciones() {
        int opcion;

        do {
            System.out.println("\n=== GESTIÓN DE UBICACIONES Y RUTAS ===");
            System.out.println("1. Agregar nueva ubicación");
            System.out.println("2. Agregar conexión entre ubicaciones");
            System.out.println("3. Mostrar grafo de ubicaciones");
            System.out.println("4. Calcular ruta óptima");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarUbicacion();
                    break;
                case 2:
                    agregarConexion();
                    break;
                case 3:
                    tienda.getGrafoUbicaciones().mostrarGrafo();
                    break;
                case 4:
                    calcularRutaOptima();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }

    private static void agregarUbicacion() {
        System.out.println("\n--- Agregar Nueva Ubicación ---");
        
        System.out.print("Nombre de la ubicación: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Código único (ej: SJ04): ");
        String codigo = scanner.nextLine();
        
        Ubicacion nuevaUbicacion = new Ubicacion(nombre, codigo);
        ubicacionesDisponibles.add(nuevaUbicacion);
        tienda.getGrafoUbicaciones().agregarUbicacion(nuevaUbicacion);
        
        System.out.println("Ubicación agregada exitosamente.");
    }

    private static void agregarConexion() {
        System.out.println("\n--- Agregar Conexión entre Ubicaciones ---");
        
        System.out.println("Ubicaciones disponibles:");
        for (int i = 0; i < ubicacionesDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + ubicacionesDisponibles.get(i));
        }
        
        System.out.print("Seleccione ubicación de origen (número): ");
        int idxOrigen = scanner.nextInt() - 1;
        
        System.out.print("Seleccione ubicación de destino (número): ");
        int idxDestino = scanner.nextInt() - 1;
        
        System.out.print("Distancia en km: ");
        int distancia = scanner.nextInt();
        scanner.nextLine();
        
        if (idxOrigen < 0 || idxOrigen >= ubicacionesDisponibles.size() ||
            idxDestino < 0 || idxDestino >= ubicacionesDisponibles.size()) {
            System.out.println("Ubicación inválida.");
            return;
        }
        
        Ubicacion origen = ubicacionesDisponibles.get(idxOrigen);
        Ubicacion destino = ubicacionesDisponibles.get(idxDestino);
        
        tienda.getGrafoUbicaciones().agregarConexion(origen, destino, distancia);
        System.out.println("Conexión agregada exitosamente.");
    }

    private static void calcularRutaOptima() {
        System.out.println("\n--- Calcular Ruta Óptima ---");
        
        System.out.println("Ubicaciones disponibles:");
        for (int i = 0; i < ubicacionesDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + ubicacionesDisponibles.get(i));
        }
        
        System.out.print("Seleccione ubicación de destino (número): ");
        int idxDestino = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (idxDestino < 0 || idxDestino >= ubicacionesDisponibles.size()) {
            System.out.println("Ubicación inválida.");
            return;
        }
        
        Ubicacion destino = ubicacionesDisponibles.get(idxDestino);
        Ubicacion origen = tienda.getUbicacionTienda();
        
        // Verificar conexión
        if (!tienda.getGrafoUbicaciones().estaConectada(destino)) {
            System.out.println("ERROR: La ubicación de destino no está conectada al sistema.");
            return;
        }
        
        // Calcular distancias con Dijkstra
        Map<Ubicacion, Integer> distancias = tienda.getGrafoUbicaciones().dijkstra(origen);
        int distanciaTotal = distancias.get(destino);
        
        System.out.println("\n📍 **RUTA ÓPTIMA DE ENTREGA**");
        System.out.println("Desde: " + origen.getNombre());
        System.out.println("Hasta: " + destino.getNombre());
        System.out.println("Distancia mínima: " + distanciaTotal + " km");
        
        if (distanciaTotal == Integer.MAX_VALUE) {
            System.out.println("ADVERTENCIA: No hay ruta disponible.");
        }
    }

    private static void procesarAtencion() {
        if (tienda.getColaClientes().estaVacia()) {
            System.out.println("No hay clientes en la cola.");
            return;
        }

        Cliente clienteAtendido = tienda.getColaClientes().desencolarPorPrioridad();

        if (clienteAtendido != null) {
            // Verificar conexión (según consigna)
            if (!tienda.getGrafoUbicaciones().estaConectada(clienteAtendido.getUbicacion())) {
                System.out.println("ERROR: La ubicación del cliente no está conectada al sistema de entrega.");
                System.out.println("El cliente no puede ser atendido hasta que su ubicación esté conectada.");
                
                // Reinsertar cliente en la cola
                tienda.getColaClientes().encolar(clienteAtendido);
                return;
            }

            // Calcular ruta óptima con Dijkstra
            Ubicacion origen = tienda.getUbicacionTienda();
            Ubicacion destino = clienteAtendido.getUbicacion();
            Map<Ubicacion, Integer> distancias = tienda.getGrafoUbicaciones().dijkstra(origen);
            int distanciaEntrega = distancias.get(destino);

            // Mostrar factura con ruta
            System.out.println("\n=== ATENDIENDO CLIENTE ===");
            System.out.println(clienteAtendido.generarFactura());
            System.out.println("\n📍 **INFORMACIÓN DE ENTREGA**");
            System.out.println("Tienda: " + origen.getNombre());
            System.out.println("Destino: " + destino.getNombre());
            System.out.println("Distancia óptima: " + distanciaEntrega + " km");
            
            if (distanciaEntrega == Integer.MAX_VALUE) {
                System.out.println("⚠️ ADVERTENCIA: Aunque está conectada, no hay ruta calculable.");
            }
            
            System.out.println("Cliente atendido y removido de la cola.");
        }
    }

    private static void verSiguienteCliente() {
        if (tienda.getColaClientes().estaVacia()) {
            System.out.println("No hay clientes en la cola.");
            return;
        }

        Cliente siguiente = tienda.getColaClientes().verSiguientePorPrioridad();

        if (siguiente != null) {
            System.out.println("Siguiente cliente a atender:");
            System.out.println(siguiente);
        }
    }

    private static void mostrarColaClientes() {
        if (tienda.getColaClientes().estaVacia()) {
            System.out.println("La cola está vacía.");
            return;
        }

        System.out.println("\n=== COLA DE CLIENTES ===");
        Cliente[] clientes = tienda.getColaClientes().snapshot();
        for (int i = 0; i < clientes.length; i++) {
            System.out.println((i + 1) + ". " + clientes[i]);
        }
    }

    private static void verEstadoSistema() {
        System.out.println("\n=== ESTADO DEL SISTEMA ===");
        System.out.println("Tienda: " + tienda.getNombre());
        System.out.println("Ubicación tienda: " + tienda.getUbicacionTienda().getNombre());
        System.out.println("Mascotas en inventario: " + tienda.getInventario().getTamaño());
        System.out.println("Clientes en cola: " + tienda.getColaClientes().getTamaño());
        System.out.println("Ubicaciones en sistema: " + ubicacionesDisponibles.size());
        
        System.out.println("\n--- Inventario actual ---");
        tienda.getInventario().inOrder();
    }
}