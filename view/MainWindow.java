package view;

import java.awt.Color;
import javax.swing.*;

public class MainWindow {
    private JFrame frame;
    private javax.swing.JPanel mainPanel;

    public MainWindow() {
        frame = new JFrame("Friends For Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);


    mainPanel = new javax.swing.JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBackground(new Color(12,12,12));

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public javax.swing.JPanel getMainPanel() {
        return mainPanel;
    }

    //Cuando se hacen cambios en la interfaz grafica, se refresca la ventana
    public void refresh() {
        frame.revalidate();
        frame.repaint();
    }
}
