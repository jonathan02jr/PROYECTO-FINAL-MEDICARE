package Administrador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Visualizar_Registro extends JFrame{
    private JButton cédulaButton;
    private JTextField textField1;
    private JButton buscarButton;
    private JButton eliminarButton;
    private JPanel ver_registros;
    private JButton volverButton;

    public Visualizar_Registro(){
        super("Visualizacion de Registros");
        setContentPane(ver_registros);
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
