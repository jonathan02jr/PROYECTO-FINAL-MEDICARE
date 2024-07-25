import javax.swing.*;

public class Crear extends JFrame{
    private JPanel panel2;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton guardarButton;

    public Crear(){
        super("Crear");
        setContentPane(panel2);
    }




    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
