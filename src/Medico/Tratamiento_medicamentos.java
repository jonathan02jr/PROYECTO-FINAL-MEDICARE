package Medico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tratamiento_medicamentos extends JFrame{
    private JPanel panel_tratamientos_medico;
    private JTextField textField1;
    private JButton mostrarButton;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton guardarButton;
    private JButton volverButton;
    private JButton cancelarButton;

    public Tratamiento_medicamentos(){
        super("Tratamientos y medicametos");
        setContentPane(panel_tratamientos_medico);
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu_Medico medico = new Menu_Medico();
                medico.iniciar();
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
