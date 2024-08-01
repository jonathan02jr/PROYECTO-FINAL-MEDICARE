package Medico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Historial_Medico extends JFrame {
    private JTextField textField1;
    private JButton buscarButton;
    private JPanel Panel_historial_medico;
    private JButton volverButton;

    public Historial_Medico(){
        super("Historial Medico");
        setContentPane(Panel_historial_medico);
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
