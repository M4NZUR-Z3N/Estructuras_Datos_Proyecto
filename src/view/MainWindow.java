package src.view;

import java.awt.*;
import javax.swing.*;

public class MainWindow {
    private JFrame frame;
    private JPanel mainPanel;

    public MainWindow() {
        frame = new JFrame("Friends For Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        // Panel principal
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE); // Fondo claro
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100)); // Márgenes
        mainPanel.setBackground(new Color(180, 235, 180));
        frame.setContentPane(mainPanel);

        // Título principal
        JLabel title1 = new JLabel("🐾 FRIENDS FOR LIFE 🐾");
        title1.setFont(new Font("SansSerif", Font.BOLD, 48)); // Más grande
        title1.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtítulo
        JLabel title2 = new JLabel("Sistema de Gestión de Mascotas");
        title2.setFont(new Font("SansSerif", Font.PLAIN, 28)); // Más grande
        title2.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botón Comenzar
        JButton startButton = new JButton("Comenzar");
        startButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setMaximumSize(new Dimension(200, 50));
        startButton.addActionListener(e -> {
            PanelManager.mostrarMenuPrincipal();
        });

        // Botón Salir
        JButton exitButton = new JButton("Salir");
        exitButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setMaximumSize(new Dimension(200, 50));
        exitButton.addActionListener(e -> System.exit(0));

        // Agregar componentes con espaciado
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(title1);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(title2);
        mainPanel.add(Box.createVerticalStrut(40));
        mainPanel.add(startButton);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(exitButton);
        mainPanel.add(Box.createVerticalGlue());

        frame.setVisible(true);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void refresh() {
        frame.revalidate();
        frame.repaint();
    }
}
