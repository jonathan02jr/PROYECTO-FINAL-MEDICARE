import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame{
    private JPanel panel0;
    private JTextField usuario0;
    private JPasswordField contrasena0;
    private JButton iniciarSesiónButton;

    public Login(){
        super("Inicio de sesión");
        setContentPane(panel0);


        iniciarSesiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://u2qpn7yz95j6wbhy:pLrGc4nM66TVKiQzS34d@b4dtcv5rsu8iwiir6knr-mysql.services.clever-cloud.com:3306/b4dtcv5rsu8iwiir6knr";
                String user = "u2qpn7yz95j6wbhy";
                String password = "pLrGc4nM66TVKiQzS34d";

                try (Connection conecta = DriverManager.getConnection(url,user,password)){
                    System.out.println("Conectado a la base de datos");

                    String usuario1 = usuario0.getText();
                    String contrasenia1 = new String(contrasena0.getPassword());

                    // Consulta de la base de datos
                    String sql = "select * from acceso where usuario=? and password =?";
                    PreparedStatement pst = conecta.prepareStatement(sql);
                    pst.setString(1, usuario1);
                    pst.setString(2, contrasenia1);

                    ResultSet resultado = pst.executeQuery();

                    if (resultado.next()){
                        System.out.println("Inicio exitosoo");
                        JOptionPane.showMessageDialog(null,"Inicio exitoso", "Exito", JOptionPane.INFORMATION_MESSAGE);

                        Menu opciones = new Menu();
                        opciones.iniciar();
                        dispose();
                    }
                    else{
                        System.out.println("Usuario o contrasela incorrectos. Intente de nuevo");
                        JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos. Intente de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
                        usuario0.setText("");
                        contrasena0.setText("");
                    }
                }
                catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "No se conectdo a la base de datos","ERROR",JOptionPane.ERROR_MESSAGE);
                }
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
