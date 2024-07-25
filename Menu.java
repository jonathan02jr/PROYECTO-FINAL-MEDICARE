import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
    private JPanel panel1;
    private JRadioButton crearRadioButton;
    private JRadioButton visualizarRadioButton;
    private JRadioButton actualizarRadioButton;
    private JRadioButton eliminarRadioButton;


    public Menu(){
        super("Menu Principal");
        setContentPane(panel1);

        crearRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Crear registrar = new Crear();
                registrar.iniciar();
                dispose();
            }
        });
        visualizarRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Visualizar ver = new Visualizar();
                ver.iniciar();
                dispose();
            }
        });
        actualizarRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Actualizar modificar = new Actualizar();
                modificar.iniciar();
                dispose();
            }
        });
        eliminarRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Eliminar borrar = new Eliminar();
                borrar.iniciar();
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
