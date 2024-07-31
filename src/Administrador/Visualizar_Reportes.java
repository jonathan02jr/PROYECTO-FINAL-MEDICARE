package Administrador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Visualizar_Reportes extends JFrame{
    private JButton volverButton;
    private JPanel ver_resportes;


    public Visualizar_Reportes(){
        super("Visualizacion de Reportes");
        setContentPane(ver_resportes);
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu_Admi admi = new Menu_Admi();
                admi.iniciar();
                dispose();
            }
        });
    }

    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
