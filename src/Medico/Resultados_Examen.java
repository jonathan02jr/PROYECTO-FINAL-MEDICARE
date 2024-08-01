package Medico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Resultados_Examen extends JFrame {
    private JPanel panel_resultado_examen;
    private JTextField textField1;
    private JButton mostrarButton;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton guardarButton;
    private JButton cancelarButton;
    private JButton volverButton;

    public Resultados_Examen(){
        super("Resultado de examenes");
        setContentPane(panel_resultado_examen);
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
