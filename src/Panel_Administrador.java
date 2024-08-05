import javax.swing.*;

public class Panel_Administrador extends JFrame{
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JPanel agregar_pacientes;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton guardarButton;
    private JTabbedPane tabbedPane3;
    private JPanel agregar_personal;
    private JButton GUARDARButton;
    private JPanel pantalla_admi;
    private JTabbedPane tabbedPane4;
    private JPanel ver_registros;
    private JButton buscarButton;
    private JButton eliminarButton;
    private JButton volverButton;
    private JPanel ver_resportes;

    public Panel_Administrador(){
        super("Panel del Administrador");
        setContentPane(pantalla_admi);




    }


    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
