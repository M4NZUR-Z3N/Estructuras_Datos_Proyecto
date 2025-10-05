package view;

import entities.Mascota;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.function.Consumer;
import javax.swing.*;
import lists.ListaMascotas;


    public class PanelManager {
        // Crear la ventana y el panel cuando se necesiten
        private static MainWindow ventana = new MainWindow();
        private static JPanel panel = ventana.getMainPanel();

        private static ListaMascotas listaMascotas = new ListaMascotas();
        private static Scanner scanner = new Scanner(System.in);

        public static void programa(){
            ventana.refresh();
        }

        public static void mostrarMenuPrincipal() {
            panel.removeAll();

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JLabel titulo = new JLabel("🐾 MENÚ PRINCIPAL 🐾");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 32));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(titulo);
        menuPanel.add(Box.createVerticalStrut(30));

        // Crear botones para cada opción
        String[] opciones = {
            "Agregar mascota al inicio",
            "Agregar mascota al final",
            "Modificar mascota",
            "Agregar imagen a mascota",
            "Generar reporte de costos",
            "Mostrar todas las mascotas",
            "Buscar mascota por nombre",
            "Eliminar mascota",
            "Salir"
        };

        for (int i = 0; i < opciones.length; i++) {
            JButton boton = new JButton(opciones[i]);
            boton.setFont(new Font("SansSerif", Font.PLAIN, 18));
            boton.setAlignmentX(Component.CENTER_ALIGNMENT);
            boton.setMaximumSize(new Dimension(300, 40));
            final int opcion = i + 1;

            boton.addActionListener(e -> ejecutarOpcion(opcion));
            menuPanel.add(boton);
            menuPanel.add(Box.createVerticalStrut(10));
        }

        MainWindow ventana = getWindow();
        panel.add(menuPanel);
        panel.show(true);
        ventana.refresh();
    }

    private static void ejecutarOpcion(int opcion) {
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
    }

    private static void agregarMascotaAlInicio() {
        mostrarFormularioMascota(mascota -> listaMascotas.insertarAlInicio(mascota));
    }
    
    private static void agregarMascotaAlFinal() {
        mostrarFormularioMascota(mascota -> listaMascotas.insertarAlFinal(mascota));
    }
    
    
    public static void mostrarFormularioMascota(Consumer<Mascota> accionConMascota) {
    panel.removeAll();

    JPanel formularioPanel = new JPanel();
    formularioPanel.setLayout(new BoxLayout(formularioPanel, BoxLayout.Y_AXIS));
    formularioPanel.setBackground(Color.WHITE);
    formularioPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

    JLabel titulo = new JLabel("Ingresar Datos de Mascota");
    titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
    titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
    formularioPanel.add(titulo);
    formularioPanel.add(Box.createVerticalStrut(30));

    JTextField campoNombre = new JTextField();
    JTextField campoPrecio = new JTextField();
    JTextField campoEspecie = new JTextField();
    JTextField campoRaza = new JTextField();
    JTextField campoEdad = new JTextField();

    formularioPanel.add(crearCampo("Nombre:", campoNombre));
    formularioPanel.add(crearCampo("Precio:", campoPrecio));
    formularioPanel.add(crearCampo("Especie:", campoEspecie));
    formularioPanel.add(crearCampo("Raza:", campoRaza));
    formularioPanel.add(crearCampo("Edad (meses):", campoEdad));

    JButton guardarBtn = new JButton("Guardar");
    guardarBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
    guardarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    guardarBtn.setMaximumSize(new Dimension(200, 40));

        guardarBtn.addActionListener(e -> {
            try {
                String nombre = campoNombre.getText().trim();
                double precio = Double.parseDouble(campoPrecio.getText().trim());
                String especie = campoEspecie.getText().trim();
                String raza = campoRaza.getText().trim();
                int edad = Integer.parseInt(campoEdad.getText().trim());

                Mascota mascota = new Mascota(nombre, precio, especie, raza, edad);
                accionConMascota.accept(mascota); // Ejecutar acción con la mascota
                JOptionPane.showMessageDialog(null, "Mascota procesada exitosamente.");
                mostrarMenuPrincipal();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error en los datos. Verifica los campos.");
            }
        });

        formularioPanel.add(Box.createVerticalStrut(20));
        formularioPanel.add(guardarBtn);

        panel.add(formularioPanel);
        ventana.refresh();
    }

    private static JPanel crearCampo(String etiqueta, JTextField campo) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setMaximumSize(new Dimension(600, 40));
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel(etiqueta);
        label.setFont(new Font("SansSerif", Font.PLAIN, 16));
        panel.add(label, BorderLayout.WEST);
        panel.add(campo, BorderLayout.CENTER);

        return panel;
    }

        
    private static void modificarMascota() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la mascota a modificar:");
        Mascota existente = listaMascotas.buscarMascota(nombre);
        if (existente == null) {
            JOptionPane.showMessageDialog(null, "Mascota no encontrada.");
            return;
        }

        mostrarFormularioMascota(mascotaActualizada -> {
            listaMascotas.modificarMascota(nombre, mascotaActualizada);
        });
    }

    
    private static void agregarImagenAMascota() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la mascota:");
        if (nombre == null || nombre.isBlank()) return;
    
        Mascota mascota = listaMascotas.buscarMascota(nombre);
        if (mascota == null) {
            JOptionPane.showMessageDialog(null, "No se encontró una mascota con ese nombre.");
            return;
        }
    
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona una imagen para " + nombre);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    
        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
    
            // Carpeta destino
            File carpetaDestino = new File(mascota.getRuta());
            if (!carpetaDestino.exists()) carpetaDestino.mkdirs();
    
            // Ruta destino con nombre único
            String extension = archivoSeleccionado.getName().substring(archivoSeleccionado.getName().lastIndexOf("."));
            File destino = new File(carpetaDestino, nombre + extension);
    
            try {
                Files.copy(archivoSeleccionado.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                boolean exito = listaMascotas.agregarImagenAMascota(mascota, destino.getPath());
                if (exito) {
                    JOptionPane.showMessageDialog(null, "Imagen agregada exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar la imagen.");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al copiar la imagen: " + ex.getMessage());
            }
        }
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
    

    public static JButton estiloBoton(){
        return null;
    }

    public static JPanel getPanel() {
        return panel;
    }

    public static MainWindow getWindow() {
        return ventana;
    }
}