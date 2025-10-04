package view;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelManager {
    // Crear la ventana y el panel cuando se necesiten
    private static MainWindow ventana = new MainWindow();
    private static JPanel panel = ventana.getMainPanel();

    public static void programa(){
        JButton boton = new JButton();
        panel.add(boton);
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