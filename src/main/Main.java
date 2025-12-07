package main;

import entities.*;
import lists.GrafoUbicaciones;
import java.util.*;

public class Main {
    private static Tienda tienda;
    private static Scanner scanner = new Scanner(System.in);
    private static List<Ubicacion> ubicacionesDisponibles;

    public static void main(String[] args) {
        inicializarUbicacionesCR();
        
        Ubicacion ubicacionTienda = buscarUbicacionPorCodigo("SJ01");
        tienda = new Tienda("Friends for Life", ubicacionTienda, 50);
        
        inicializarGrafoCostaRica();
        
        System.out.println("BIENVENIDO A FRIENDS FOR LIFE");
        System.out.println("Sistema de Gestion de Mascotas y Entregas\n");
        
        cargarDatosEjemplo();
        menuPrincipal();
    }

    private static void inicializarUbicacionesCR() {
        ubicacionesDisponibles = new ArrayList<>();
        
        ubicacionesDisponibles.add(new Ubicacion("San Jose Centro", "SJ01"));
        ubicacionesDisponibles.add(new Ubicacion("Alajuela Centro", "AL01"));
        ubicacionesDisponibles.add(new Ubicacion("Heredia Centro", "HE01"));
        ubicacionesDisponibles.add(new Ubicacion("Cartago Centro", "CA01"));
        ubicacionesDisponibles.add(new Ubicacion("Puntarenas Centro", "PU01"));
        ubicacionesDisponibles.add(new Ubicacion("Limon Centro", "LI01"));
        ubicacionesDisponibles.add(new Ubicacion("Escazu", "SJ02"));
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
        Ubicacion sj = buscarUbicacionPorCodigo("SJ01");
        Ubicacion al = buscarUbicacionPorCodigo("AL01");
        Ubicacion he = buscarUbicacionPorCodigo("HE01");
        Ubicacion ca = buscarUbicacionPorCodigo("CA01");
        Ubicacion pu = buscarUbicacionPorCodigo("PU01");
        Ubicacion li = buscarUbicacionPorCodigo("LI01");
        Ubicacion es = buscarUbicacionPorCodigo("SJ02");
        Ubicacion de = buscarUbicacionPorCodigo("SJ03");
        Ubicacion gu = buscarUbicacionPorCodigo("GU01");

        GrafoUbicaciones grafo = tienda.getGrafoUbicaciones();
        
        grafo.agregarConexion(sj, al, 18);
        grafo.agregarConexion(sj, he, 10);
        grafo.agregarConexion(sj, ca, 24);
        grafo.agregarConexion(sj, es, 8);
        grafo.agregarConexion(sj, de, 6);
        grafo.agregarConexion(al, he, 25);
        grafo.agregarConexion(al, gu, 150);
        grafo.agregarConexion(ca, li, 85);
        grafo.agregarConexion(sj, pu, 90);
        
        System.out.println("Grafo de ubicaciones inicializado con " + 
                          grafo.getConexiones(sj).size() + " conexiones desde la tienda.");
    }

    private static void cargarDatosEjemplo() {
        tienda.getInventario().insertar(new Mascota("Max", 150000, "Perro", "Labrador", 24));
        tienda.getInventario().insertar(new Mascota("Luna", 120000, "Gato", "Siames", 18));
        tienda.getInventario().insertar(new Mascota("Rocky", 180000, "Perro", "Pastor Aleman", 36));
        tienda.getInventario().insertar(new Mascota("Bobby", 130000, "Perro", "Beagle", 20));
        
        System.out.println("Inventario cargado con " + tienda.getInventario().getTamaño() + " mascotas.");
    }

    public static void menuPrincipal() {
        int opcion;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Gestion de Inventario (Arbol Binario)");
            System.out.println("2. Gestion de Clientes");
            System.out.println("3. Gestion de Ubicaciones y Rutas");
            System.out.println("4. Procesar Atencion de Clientes");
            System.out.println("5. Ver Estado del Sistema");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");

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
                    System.out.println("Gracias por usar Friends for Life!");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 6);
    }

    private static void menuGestionInventario() {
        int opcion;

        do {
            System.out.println("\n=== GESTION DE INVENTARIO ===");
            System.out.println("1. Agregar mascota al inventario");
            System.out.println("2. Buscar mascota en inventario");
            System.out.println("3. Mostrar inventario ordenado");
            System.out.println("4. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

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
                    System.out.println("Opcion invalida.");
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
            System.out.println("No se encontro la mascota.");
        }
    }

    private static void menuGestionClientes() {
        int opcion;

        do {
            System.out.println("\n=== GESTION DE CLIENTES ===");
            System.out.println("1. Agregar cliente a la cola");
            System.out.println("2. Ver siguiente cliente");
            System.out.println("3. Mostrar cola de clientes");
            System.out.println("4. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

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
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 4);
    }

    private static void agregarClienteCola() {
        System.out.println("\n--- Agregar Cliente a la Cola ---");
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Cedula: ");
        String cedula = scanner.nextLine();
        
        System.out.print("Prioridad (1=Basico, 2=Afiliado, 3=Premium): ");
        int prioridad = scanner.nextInt();
        scanner.nextLine();
        
        if (prioridad < 1 || prioridad > 3) {
            System.out.println("Error: Prioridad debe ser 1, 2 o 3.");
            return;
        }
        
        System.out.println("\nUbicaciones disponibles:");
        for (int i = 0; i < ubicacionesDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + ubicacionesDisponibles.get(i));
        }
        System.out.print("Seleccione ubicacion (numero): ");
        int idxUbicacion = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (idxUbicacion < 0 || idxUbicacion >= ubicacionesDisponibles.size()) {
            System.out.println("Ubicacion invalida.");
            return;
        }
        
        Ubicacion ubicacionCliente = ubicacionesDisponibles.get(idxUbicacion);
        Cliente nuevoCliente = new Cliente(nombre, cedula, prioridad, ubicacionCliente);
        
        System.out.println("Desea agregar mascotas al carrito? (s/n): ");
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
            
            System.out.println("Agregar otra mascota? (s/n): ");
            respuesta = scanner.nextLine();
        }
        
        boolean ok = tienda.getColaClientes().encolar(nuevoCliente);
        if (ok) {
            System.out.println("Cliente agregado a la cola exitosamente.");
        } else {
            System.out.println("Error: La cola esta llena.");
        }
    }

    private static void menuGestionUbicaciones() {
        int opcion;

        do {
            System.out.println("\n=== GESTION DE UBICACIONES Y RUTAS ===");
            System.out.println("1. Agregar nueva ubicacion");
            System.out.println("2. Agregar conexion entre ubicaciones");
            System.out.println("3. Mostrar grafo de ubicaciones");
            System.out.println("4. Calcular ruta optima (con camino)");
            System.out.println("5. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

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
                    calcularYRutaEspecifica();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 5);
    }

    private static void agregarUbicacion() {
        System.out.println("\n--- Agregar Nueva Ubicacion ---");
        
        System.out.print("Nombre de la ubicacion: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Codigo unico (ej: SJ04): ");
        String codigo = scanner.nextLine();
        
        Ubicacion nuevaUbicacion = new Ubicacion(nombre, codigo);
        ubicacionesDisponibles.add(nuevaUbicacion);
        tienda.getGrafoUbicaciones().agregarUbicacion(nuevaUbicacion);
        
        System.out.println("Ubicacion agregada exitosamente.");
    }

    private static void agregarConexion() {
        System.out.println("\n--- Agregar Conexion entre Ubicaciones ---");
        
        System.out.println("Ubicaciones disponibles:");
        for (int i = 0; i < ubicacionesDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + ubicacionesDisponibles.get(i));
        }
        
        System.out.print("Seleccione ubicacion de origen (numero): ");
        int idxOrigen = scanner.nextInt() - 1;
        
        System.out.print("Seleccione ubicacion de destino (numero): ");
        int idxDestino = scanner.nextInt() - 1;
        
        System.out.print("Distancia en km: ");
        int distancia = scanner.nextInt();
        scanner.nextLine();
        
        if (idxOrigen < 0 || idxOrigen >= ubicacionesDisponibles.size() ||
            idxDestino < 0 || idxDestino >= ubicacionesDisponibles.size()) {
            System.out.println("Ubicacion invalida.");
            return;
        }
        
        Ubicacion origen = ubicacionesDisponibles.get(idxOrigen);
        Ubicacion destino = ubicacionesDisponibles.get(idxDestino);
        
        tienda.getGrafoUbicaciones().agregarConexion(origen, destino, distancia);
        System.out.println("Conexion agregada exitosamente.");
    }

    private static void calcularYRutaEspecifica() {
        System.out.println("\n--- Calcular Ruta Especifica ---");
        
        System.out.println("Ubicaciones disponibles:");
        for (int i = 0; i < ubicacionesDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + ubicacionesDisponibles.get(i));
        }
        
        System.out.print("Seleccione ubicacion de destino (numero): ");
        int idxDestino = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (idxDestino < 0 || idxDestino >= ubicacionesDisponibles.size()) {
            System.out.println("Ubicacion invalida.");
            return;
        }
        
        Ubicacion destino = ubicacionesDisponibles.get(idxDestino);
        Ubicacion origen = tienda.getUbicacionTienda();
        
        if (!tienda.getGrafoUbicaciones().estaConectada(destino)) {
            System.out.println("ERROR: La ubicacion de destino no esta conectada al sistema.");
            return;
        }
        
        Map<Ubicacion, GrafoUbicaciones.ResultadoDijkstra> resultado = 
            tienda.getGrafoUbicaciones().dijkstraCompleto(origen);
        
        int distanciaTotal = resultado.get(destino).getDistancia();
        List<Ubicacion> camino = tienda.getGrafoUbicaciones()
            .reconstruirCamino(origen, destino, resultado);
        
        System.out.println("\n** RUTA OPTIMA DE ENTREGA **");
        System.out.println("Desde: " + origen.getNombre());
        System.out.println("Hasta: " + destino.getNombre());
        System.out.println("Distancia minima: " + distanciaTotal + " km");
        
        if (camino.isEmpty()) {
            System.out.println("ADVERTENCIA: No hay ruta disponible.");
        } else {
            System.out.println("\n** CAMINO COMPLETO: **");
            for (int i = 0; i < camino.size(); i++) {
                System.out.print(camino.get(i).getNombre());
                if (i < camino.size() - 1) {
                    System.out.print(" -> ");
                    if ((i + 1) % 3 == 0) System.out.println();
                }
            }
            System.out.println("\n");
            System.out.println("Total de paradas: " + (camino.size() - 1));
        }
    }

    private static void procesarAtencion() {
        if (tienda.getColaClientes().estaVacia()) {
            System.out.println("No hay clientes en la cola.");
            return;
        }

        Cliente clienteAtendido = tienda.getColaClientes().desencolarPorPrioridad();

        if (clienteAtendido != null) {
            if (!tienda.getGrafoUbicaciones().estaConectada(clienteAtendido.getUbicacion())) {
                System.out.println("\nERROR: La ubicacion del cliente no esta conectada al sistema de entrega.");
                System.out.println("   Ubicacion: " + clienteAtendido.getUbicacion().getNombre());
                System.out.println("   El cliente no puede ser atendido hasta que su ubicacion este conectada.");
                
                tienda.getColaClientes().encolar(clienteAtendido);
                return;
            }

            Ubicacion origen = tienda.getUbicacionTienda();
            Ubicacion destino = clienteAtendido.getUbicacion();
            
            Map<Ubicacion, GrafoUbicaciones.ResultadoDijkstra> resultado = 
                tienda.getGrafoUbicaciones().dijkstraCompleto(origen);
            
            int distanciaEntrega = resultado.get(destino).getDistancia();
            List<Ubicacion> camino = tienda.getGrafoUbicaciones()
                .reconstruirCamino(origen, destino, resultado);

            System.out.println("\n==================================================");
            System.out.println("             ATENDIENDO CLIENTE");
            System.out.println("==================================================");
            
            System.out.println(clienteAtendido.generarFacturaConRuta(camino, distanciaEntrega));
            
            System.out.println("\nRESUMEN DE ATENCION:");
            System.out.println("   * Cliente: " + clienteAtendido.getNombre());
            System.out.println("   * Ubicacion: " + destino.getNombre());
            System.out.println("   * Distancia: " + distanciaEntrega + " km");
            
            if (!camino.isEmpty()) {
                System.out.println("   * Paradas: " + camino.size());
                System.out.println("   * Ruta validada: CONEXION CONFIRMADA");
            } else {
                System.out.println("   * Ruta validada: SIN RUTA CALCULABLE");
            }
            
            System.out.println("\nCliente atendido y removido de la cola.");
            System.out.println("==================================================");
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
            System.out.println("La cola esta vacia.");
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
        System.out.println("Ubicacion tienda: " + tienda.getUbicacionTienda().getNombre());
        System.out.println("Mascotas en inventario: " + tienda.getInventario().getTamaño());
        System.out.println("Clientes en cola: " + tienda.getColaClientes().getTamaño());
        System.out.println("Ubicaciones en sistema: " + ubicacionesDisponibles.size());
        
        System.out.println("\n--- Inventario actual ---");
        tienda.getInventario().inOrder();
    }
}