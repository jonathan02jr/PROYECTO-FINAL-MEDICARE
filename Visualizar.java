import javax.swing.*;

public class Visualizar extends JFrame {
    private JPanel panel3;
    private JTextField cedula0_ver;
    private JButton buscarButton;
    private JButton volverButton;
    private JButton salirButton;

    public Visualizar(){
        super("Visualizar");
        setContentPane(panel3);
    }



    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
