package Medico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Citas_Medico extends JFrame{
    private JPanel agregar_citas;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton guardarButton;

    public Citas_Medico(){
        super("Crear");
        setContentPane(agregar_citas);
        guardarButton.addActionListener(new ActionListener() {
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
