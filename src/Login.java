import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame{
    private JPanel panel0;
    private JTextField usuario0;
    private JPasswordField contrasena0;
    private JButton iniciarSesionButton;
    private JComboBox opcionesPerfil;
    private JLabel imagenLabel;

    public Login(){
        super("Inicio de sesión");
        setContentPane(panel0);

        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String url = "jdbc:mysql://localhost:3306/medicare";
                String user = "root";
                String password = "";

                try (Connection conecta = DriverManager.getConnection(url,user,password)){
                    System.out.println("Conectado a la base de datos");

                    String usuario1 = usuario0.getText();
                    String contrasenia1 = new String(contrasena0.getPassword());
                    //seleccion del combo box del perfil
                    String item = opcionesPerfil.getSelectedItem().toString();

                    //Se verifica si el perfil fue seleccionado o no para realizar la consulta en la base de datos
                    if(item.equals("Selecciona perfil")){
                        JOptionPane.showMessageDialog(null,"Por favor seleccione un perfil","ERROR",JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Consulta de las credenciales en la base de datos
                    String sql = "select * from acceso where usuario=? and contraseña =?";
                    PreparedStatement pst = conecta.prepareStatement(sql);
                    pst.setString(1, usuario1);
                    pst.setString(2, contrasenia1);

                    ResultSet resultado = pst.executeQuery();

                    if (resultado.next()){
                        //Se verifica si el perfil es correcto de acuerdo a la base de datos
                        String perfil = resultado.getString("perfil");
                        if(item.equals(perfil)){
                            System.out.println("Inicio exitosoo");

                            if(item.equals("Administrador")){
                                JOptionPane.showMessageDialog(null,"Inicio Exitoso \nBienvenido al panel del Administrador","ÉXITO",JOptionPane.INFORMATION_MESSAGE);

                                Panel_Administrador admi = new Panel_Administrador();
                                admi.iniciar();
                                dispose();
                            }
                            else if (item.equals("Medico")) {
                                JOptionPane.showMessageDialog(null, "Inicio Exitoso \nBienvenido al panel del Médico", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);

                                Panel_Medico medico = new Panel_Medico();
                                medico.iniciar();
                                dispose();
                            }
                        }else {
                            JOptionPane.showMessageDialog(null,"Perfil Incorrecto","ERROR",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        System.out.println("Usuario o contraseña incorrectos. Intente de nuevo");
                        JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos \nIntente de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
                        usuario0.setText("");
                        contrasena0.setText("");
                    }
                }
                catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "No se ha conectado a la base de datos","ERROR",JOptionPane.ERROR_MESSAGE);
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
