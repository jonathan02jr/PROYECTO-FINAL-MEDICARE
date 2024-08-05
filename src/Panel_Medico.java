import javax.swing.*;

public class Panel_Medico extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel pantalla_medico;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton mostrarButton;
    private JButton guardarButton;
    private JPanel panel_resultado_examenes;
    private JButton cancelarButton;
    private JButton salirButton;
    private JPanel panel_tratamientos_medico;
    private JTextField textField6;
    private JTextField textField7;
    private JPanel panel_citas_medicas;
    private JPanel Panel_historial_medico;
    private JButton buscarButton;

    public Panel_Medico(){
        super("Panel del Médico");
        setContentPane(pantalla_medico);
    }

    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
