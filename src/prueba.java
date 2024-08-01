import javax.swing.*;

public class prueba extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel Panel_historial_medico;
    private JTextField textField1;
    private JButton buscarButton;
    private JButton volverButton;
    private JPanel panel_resultado_examen;
    private JButton cancelarButton;
    private JPanel panel_tratamientos_medico;
    private JTextField textField6;
    private JTextField textField7;
    private JPanel prueba_admi;
    private JPanel agregar_citas;
    private JTextField textField2;
    private JTextField textField4;
    private JButton guardarButton;
    private JTextField textField5;
    private JTextField textField3;
    private JButton mostrarButton;

    public prueba(){
        super("Médico");
        setContentPane(prueba_admi);
    }



    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
