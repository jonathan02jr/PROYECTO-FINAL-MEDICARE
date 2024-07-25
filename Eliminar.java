import javax.swing.*;

public class Eliminar extends JFrame{
    private JPanel panel5;
    private JTextField cedula0_eliminar;
    private JButton volverButton;
    private JButton eliminarButton;
    private JButton salirButton;

    public Eliminar(){
        super("Eliminar");
        setContentPane(panel5);
    }




    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
