import javax.swing.*;

public class Menu extends JFrame{
    private JPanel panel1;
    private JRadioButton crearRadioButton;
    private JRadioButton leerRadioButton;
    private JRadioButton actualizarRadioButton;
    private JRadioButton eliminarRadioButton;


    public Menu(){
        super("Menu Principal");
        setContentPane(panel1);

    }

    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
