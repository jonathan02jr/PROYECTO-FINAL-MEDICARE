package Medico;

import Ingreso.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu_Medico extends JFrame{
    private JPanel Panel_menu_medico;
    private JRadioButton registrarCitasRadioButton;
    private JRadioButton registrarHisotrialesMédicosRadioButton;
    private JRadioButton registrarResultadosDeExámenesRadioButton;
    private JRadioButton asignarTratamientosRadioButton;
    private JButton salirButton;

    public Menu_Medico() {
        super("Menu del Médico");
        setContentPane(Panel_menu_medico);


        registrarCitasRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Citas_Medico citas = new Citas_Medico();
                citas.iniciar();
                dispose();
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login ingreso = new Login();
                ingreso.iniciar();
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
