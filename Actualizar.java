import javax.swing.*;

public class Actualizar extends JFrame {
    private JPanel panel4;





    public Actualizar(){
        super("Actualizacion");
        setContentPane(panel4);
    }

    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
