package Administrador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Ingreso.Login;

public class Menu_Admi extends JFrame {
    private JPanel Panel_Menu_Admi;
    private JRadioButton registrarPersonalRadioButton;
    private JRadioButton actualizarPersonalRadioButton;
    private JRadioButton registrarPacientesRadioButton1;
    private JRadioButton actualizarPacientesRadioButton;
    private JRadioButton visualizarRegistrosRadioButton;
    private JRadioButton visualizarReportesRadioButton;
    private JButton salirButton;

    public Menu_Admi(){
        super("Menu del Administrador");
        setContentPane(Panel_Menu_Admi);

        registrarPersonalRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registro_Personal personal = new Registro_Personal();
                personal.iniciar();
                dispose();
            }
        });
        registrarPacientesRadioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registro_Pacientes pacientes = new Registro_Pacientes();
                pacientes.iniciar();
                dispose();
            }
        });
        visualizarRegistrosRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Visualizar_Registro registros = new Visualizar_Registro();
                registros.iniciar();
                dispose();
            }
        });
        visualizarReportesRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Visualizar_Reportes resportes = new Visualizar_Reportes();
                resportes.iniciar();
                dispose();
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login salir = new Login();
                salir.iniciar();
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
