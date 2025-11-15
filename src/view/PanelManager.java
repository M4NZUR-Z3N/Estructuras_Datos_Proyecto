package src.view;

import src.entities.Mascota;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.function.Consumer;
import javax.swing.*;
import src.lists.ListaMascotas;


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
                mostrarReporteCostos();
                break;
            case 6:
                mostrarTodasLasMascotas();
                break;
            case 7:
                buscarMascota();
                break;
            case 8:
                eliminarMascota();
                break;
            case 9:
                System.out.println("¡Gracias por usar Friends for Life!");
                System.exit(0);
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
                boolean exito = listaMascotas.agregarImagenAMascota(nombre, destino.getPath());
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
        panel.removeAll();

        JPanel buscarPanel = new JPanel();
        buscarPanel.setLayout(new BoxLayout(buscarPanel, BoxLayout.Y_AXIS));
        buscarPanel.setBackground(Color.WHITE);
        buscarPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JLabel titulo = new JLabel("Buscar Mascota");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        buscarPanel.add(titulo);
        buscarPanel.add(Box.createVerticalStrut(30));

        JTextField campoNombre = new JTextField();
        buscarPanel.add(crearCampo("Nombre de la mascota:", campoNombre));

        JButton buscarBtn = new JButton("Buscar");
        buscarBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        buscarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buscarBtn.setMaximumSize(new Dimension(200, 40));

        JButton volverBtn = new JButton("Volver");
        volverBtn.addActionListener(e ->{
            mostrarMenuPrincipal();
        });

        buscarBtn.addActionListener(e -> {
            String nombre = campoNombre.getText().trim();
            Mascota mascota = listaMascotas.buscarMascota(nombre);

            if (mascota != null) {
                panel.removeAll();
                panel.add(mostrarMascota(mascota));
                panel.add(volverBtn);
                ventana.refresh();
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una mascota con ese nombre.");
                PanelManager.mostrarMenuPrincipal();
            }
        });

        buscarPanel.add(Box.createVerticalStrut(20));
        buscarPanel.add(buscarBtn);

        panel.add(buscarPanel);
        ventana.refresh();
    }

    private static void mostrarTodasLasMascotas(){
        panel.removeAll();

        JPanel listaPanel = new JPanel();
        listaPanel.setLayout(new BoxLayout(listaPanel, BoxLayout.Y_AXIS));
        listaPanel.setBackground(Color.WHITE);
        listaPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (Mascota mascota : listaMascotas.obtenerTodasLasMascotas()) {
            listaPanel.add(mostrarMascota(mascota));
            listaPanel.add(Box.createVerticalStrut(10)); // Espaciado entre mascotas
        }

        JScrollPane scrollPane = new JScrollPane(listaPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        
        JButton volverBtn = new JButton("Volver");
        volverBtn.addActionListener(e ->{
            mostrarMenuPrincipal();
        });

        panel.add(scrollPane);
        panel.add(volverBtn);
        ventana.refresh();
    }
    

    private static void eliminarMascota() {
        panel.removeAll();

        JPanel eliminarPanel = new JPanel();
        eliminarPanel.setLayout(new BoxLayout(eliminarPanel, BoxLayout.Y_AXIS));
        eliminarPanel.setBackground(Color.WHITE);
        eliminarPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JLabel titulo = new JLabel("Eliminar Mascota");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        eliminarPanel.add(titulo);
        eliminarPanel.add(Box.createVerticalStrut(30));

        JTextField campoNombre = new JTextField();
        eliminarPanel.add(crearCampo("Nombre de la mascota a eliminar:", campoNombre));

        JButton eliminarBtn = new JButton("Eliminar");
        eliminarBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        eliminarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        eliminarBtn.setMaximumSize(new Dimension(200, 40));

        JButton volverBtn = new JButton("Volver");
        volverBtn.addActionListener(e -> mostrarMenuPrincipal());

        eliminarBtn.addActionListener(e -> {
            String nombre = campoNombre.getText().trim();
            if (listaMascotas.eliminarMascota(nombre)) {
                JOptionPane.showMessageDialog(null, "Mascota eliminada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una mascota con ese nombre.");
            }
            mostrarMenuPrincipal();
        });

        eliminarPanel.add(Box.createVerticalStrut(20));
        eliminarPanel.add(eliminarBtn);
        eliminarPanel.add(Box.createVerticalStrut(10));
        eliminarPanel.add(volverBtn);

        panel.add(eliminarPanel);
        ventana.refresh();
    }
    
    private static JPanel mostrarMascota(Mascota mascota) {
        JPanel mascotaPanel = new JPanel();

        mascotaPanel.setLayout(new BorderLayout());
        mascotaPanel.setBackground(Color.WHITE);
        mascotaPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel para mostrar información de la mascota
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        JLabel nombreLabel = new JLabel("Nombre: " + mascota.getNombre());
        JLabel precioLabel = new JLabel("Precio: " + mascota.getPrecio());
        JLabel especieLabel = new JLabel("Especie: " + mascota.getEspecie());
        JLabel razaLabel = new JLabel("Raza: " + mascota.getRaza());
        JLabel edadLabel = new JLabel("Edad: " + mascota.getEdad() + " meses");

        nombreLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        precioLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        especieLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        razaLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        edadLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));

        infoPanel.add(nombreLabel);
        infoPanel.add(precioLabel);
        infoPanel.add(especieLabel);
        infoPanel.add(razaLabel);
        infoPanel.add(edadLabel);

        mascotaPanel.add(infoPanel, BorderLayout.WEST);

        // Panel para mostrar imágenes
        JPanel imagenPanel = new JPanel();
        imagenPanel.setLayout(new BorderLayout());
        imagenPanel.setBackground(Color.WHITE);
        imagenPanel.setPreferredSize(new Dimension(150, 150)); // Reducir el tamaño del panel de la imagen

        File carpetaImagenes = new File(mascota.getRuta());
        File[] imagenes = carpetaImagenes.exists() ? carpetaImagenes.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png")) : new File[0];

        JLabel imagenLabel = new JLabel();
        imagenLabel.setHorizontalAlignment(JLabel.CENTER);
        imagenLabel.setVerticalAlignment(JLabel.CENTER);

        // Si no hay imágenes, usar la imagen por defecto
        if (imagenes == null || imagenes.length == 0) {
            ImageIcon defaultIcon = new ImageIcon("resources/imagenesMascotas/default.jpg");
            imagenLabel.setIcon(new ImageIcon(defaultIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        } else {
            // Mostrar la primera imagen
            //ToDo Luego se podrán mostrar las distintas imagenes ingresadas
            final int[] indiceActual = {0};
            ImageIcon primeraImagen = new ImageIcon(imagenes[indiceActual[0]].getPath());
            imagenLabel.setIcon(new ImageIcon(primeraImagen.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
            
        }

        imagenPanel.add(imagenLabel, BorderLayout.CENTER);
        mascotaPanel.add(imagenPanel, BorderLayout.EAST);

        return mascotaPanel;
    }

    public static void mostrarReporteCostos() {
        panel.removeAll();

        JPanel reportePanel = new JPanel();
        reportePanel.setLayout(new BoxLayout(reportePanel, BoxLayout.Y_AXIS));
        reportePanel.setBackground(Color.WHITE);
        reportePanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JLabel titulo = new JLabel("Reporte de Costos");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        reportePanel.add(titulo);
        reportePanel.add(Box.createVerticalStrut(30));

        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("SansSerif", Font.PLAIN, 16));
        areaTexto.setText(listaMascotas.generarReporteCostos());
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        reportePanel.add(scrollPane);

        JButton volverBtn = new JButton("Volver al Menú Principal");
        volverBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        volverBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        volverBtn.addActionListener(e -> mostrarMenuPrincipal());

        reportePanel.add(Box.createVerticalStrut(20));
        reportePanel.add(volverBtn);

        panel.add(reportePanel);
        ventana.refresh();
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