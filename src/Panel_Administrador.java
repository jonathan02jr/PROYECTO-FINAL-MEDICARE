import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
import java.sql.*;

public class Panel_Administrador extends JFrame{
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JPanel agregar_pacientes;
    private JTextField cedula_paciente1;
    private JTextField nombre_paciente1;
    private JTextField fechaNacimiento_paciente1;
    private JTextField telefono_paciente1;
    private JTextField email_paciente1;
    private JTabbedPane tabbedPane3;
    private JPanel agregar_personal;
    private JButton guardar_info_medico;
    private JPanel pantalla_admi;
    private JTabbedPane tabbedPane4;
    private JPanel ver_registros;
    private JButton buscarButton;
    private JButton eliminarButton;
    private JPanel ver_resportes;
    private JComboBox sexo_paciente1;
    private JButton salirButton;
    private JTextField apellido_paciente1;
    private JTextField apellido_medico1;
    private JTextField cedula_medico1;
    private JTextField nombre_medico1;
    private JTextField especialidad_medico1;
    private JTextField telefono_medico1;
    private JTextField email_medico1;
    private JButton guardar_info_pacientes;

    /**
     * Atributos para la actualizacion de los datos de los medicos
     */
    private JTextField cedula_actualizar_medico1;
    private JTextField nombre_actualizar_medico1;
    private JTextField apellido_actualizar_medico1;
    private JTextField especialidad_actualiza_medico1;
    private JTextField telefono_actualizar_medico1;
    private JTextField email_actualizar_medico1;
    private JTextField cedula_mostrar_medico1;
    private JButton mostrar_medico;

    /**
     * Atributos para la actualizacion de los datos de los pacientes
     */
    private JButton actualizar_medico;
    private JTextField cedula_actualizar_paciente1;
    private JTextField nombre_actualizar_paciente1;
    private JTextField apellido_actualizar_paciente1;
    private JTextField fechaNacimiento_actualizar_paciente1;
    private JTextField telefono_actualizar_paciente1;
    private JTextField email_actualizar_paciente1;
    private JTextField cedula_mostrar_paciente1;
    private JButton actualizar_paciente;
    private JComboBox sexo_actualizar_paciente1;
    private JButton mostrar_pacientes;

    public Panel_Administrador(){
        super("Panel del Administrador");
        setContentPane(pantalla_admi);

        guardar_info_medico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registro_medicos();
                }catch (SQLException ex){
                    throw new RuntimeException(ex);
                }

            }
        });
        guardar_info_pacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registro_pacientes();
                }catch (SQLException ex){
                    throw new RuntimeException(ex);
                }
            }
        });
        mostrar_medico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mostrar_info_medicos();
                }catch (SQLException ex){
                    throw new RuntimeException(ex);
                }
            }
        });
        mostrar_pacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mostrar_info_pacientes();
                }catch (SQLException ex){
                    throw new RuntimeException(ex);
                }
            }
        });
        actualizar_medico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actualizar_info_medicos();
                }catch (SQLException ex){
                    throw new RuntimeException(ex);
                }

            }
        });
        actualizar_paciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actualizar_info_pacientes();
                }catch (SQLException ex){
                    throw new RuntimeException(ex);
                }
            }
        });

        //boton para salir del panel al login
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login salir = new Login();
                salir.iniciar();
                dispose();
            }
        });
    }

    /**
     * Conexion de la base de datos
     * @return
     * @throws SQLException
     */
    public Connection conexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/medicare";
        String user = "root";
        String password = "";

        return DriverManager.getConnection(url,user,password);
    }

    /**
     * Registro de medicos a la base de datos
     * @throws SQLException
     */
    public void registro_medicos() throws SQLException{

        String cedula_medico0 = cedula_medico1.getText();
        String nombre_medico0 = nombre_medico1.getText();
        String apellido_medico0 = apellido_medico1.getText();
        String especialidad_medico0 = especialidad_medico1.getText();
        String telefono_medico0 = telefono_medico1.getText();
        String email_medico0 = email_medico1.getText();

        Connection conectar = conexion();
        String sql = "INSERT INTO medicos (cedula, nombre, apellido, especialidad, telefono, email) VALUES(?,?,?,?,?,?)";

        PreparedStatement pstm = conectar.prepareStatement(sql);
        pstm.setString(1,cedula_medico0);
        pstm.setString(2,nombre_medico0);
        pstm.setString(3,apellido_medico0);
        pstm.setString(4,especialidad_medico0);
        pstm.setString(5,telefono_medico0);
        pstm.setString(6,email_medico0);

        int alterar_fila = pstm.executeUpdate();

        if(alterar_fila >0){
            System.out.println("Datos ingresados con éxito");
            JOptionPane.showMessageDialog(null,"Datos ingresados con éxito","ÉXTO",JOptionPane.INFORMATION_MESSAGE);

            cedula_medico1.setText("");
            nombre_medico1.setText("");
            apellido_medico1.setText("");
            especialidad_medico1.setText("");
            telefono_medico1.setText("");
            email_medico1.setText("");
        }else {
            JOptionPane.showMessageDialog(null,"Fallo con los datos ingresados","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        pstm.close();
        conectar.close();
    }

    /**
     * Registro de los pacientes a la base de datos
     * @throws SQLException
     */
    public void registro_pacientes() throws SQLException{

        String cedula_paciente0 = cedula_paciente1.getText();
        String nombre_paciente0 = nombre_paciente1.getText();
        String apellido_paciente0 = apellido_paciente1.getText();
        String fechaNacimiento0 = fechaNacimiento_paciente1.getText();
        String sexo_paciente0 = sexo_paciente1.getSelectedItem().toString();
        String telefono_paciente0 = telefono_paciente1.getText();
        String email_paciente0 = email_paciente1.getText();

        Connection conectar = conexion();
        String numero_historial = obtener_n_historial(conectar);

        String sql = "INSERT INTO pacientes (n_historial_clinico,cedula, nombre, apellido, fecha_nacimiento, sexo, telefono, email) VALUES(?,?,?,?,?,?,?,?)";

        PreparedStatement pstm = conectar.prepareStatement(sql);
        pstm.setInt(1,Integer.parseInt(numero_historial));
        pstm.setString(2,cedula_paciente0);
        pstm.setString(3,nombre_paciente0);
        pstm.setString(4,apellido_paciente0);
        pstm.setString(5,fechaNacimiento0);
        pstm.setString(6,sexo_paciente0);
        pstm.setString(7,telefono_paciente0);
        pstm.setString(8,email_paciente0);

        int alterar_fila = pstm.executeUpdate();

        if(alterar_fila >0){
            System.out.println("Datos ingresados con éxito");
            JOptionPane.showMessageDialog(null,"Datos ingresados con éxito","ÉXTO",JOptionPane.INFORMATION_MESSAGE);


            cedula_paciente1.setText("");
            nombre_paciente1.setText("");
            apellido_paciente1.setText("");
            fechaNacimiento_paciente1.setText("");
            sexo_paciente1.setSelectedIndex(-1);
            telefono_paciente1.setText("");
            email_paciente1.setText("");

        }else {
            JOptionPane.showMessageDialog(null,"Fallo con los datos ingresados","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        pstm.close();
        conectar.close();

    }

    /**
     * Se obtiene el numero del historial que se genero y se mencione porque numero se empieza
     * @param connection
     * @return
     * @throws SQLException
     */
    public String obtener_n_historial(Connection connection) throws SQLException {
        String sql = "SELECT n_historial_clinico FROM pacientes ORDER BY n_historial_clinico DESC LIMIT 1";

        try (PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet resultSet = pstm.executeQuery()) {

            // Comprobamos si el resultado contiene una fila
            if (resultSet.next()) {
                // Extraemos el número del historial clínico y lo incrementamos
                int ultimoNumero = resultSet.getInt("n_historial_clinico");
                return String.format("%04d", ultimoNumero + 1);
            } else {
                // Si no hay filas, devolvemos el primer número de historial clínico
                return "0001";
            }
        }
    }

    /**
     * Se muestra la informacion de los medicos que se encuentra en la base de datos
     * @throws SQLException
     */
    public void mostrar_info_medicos() throws SQLException{

        String cedula_mostrar_medico0 = cedula_mostrar_medico1.getText();

        Connection conectar = conexion();

        String sql = "SELECT * FROM medicos WHERE cedula = ?";
        PreparedStatement pstm = conectar.prepareStatement(sql);
        pstm.setString(1,cedula_mostrar_medico0);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()){
            System.out.println("Registro encontrado");
            JOptionPane.showMessageDialog(null,"Registro encontrado","Éxito",JOptionPane.INFORMATION_MESSAGE);

            //se muestra la informacion en los campos vacios
            cedula_actualizar_medico1.setText(rs.getString("cedula"));
            nombre_actualizar_medico1.setText(rs.getString("nombre"));
            apellido_actualizar_medico1.setText(rs.getString("apellido"));
            especialidad_actualiza_medico1.setText(rs.getString("especialidad"));
            telefono_actualizar_medico1.setText(rs.getString("telefono"));
            email_actualizar_medico1.setText(rs.getString("email"));
        }else {
            JOptionPane.showMessageDialog(null,"No existen registros","Error",JOptionPane.ERROR_MESSAGE);
        }
        pstm.close();
        rs.close();
        conectar.close();
    }

    /**
     * Se muestra la informacion de los pacientes que se encuentra en la base de datos
     * @throws SQLException
     */
    public void mostrar_info_pacientes() throws SQLException{

        String cedula_mostrar_paciente0 = cedula_mostrar_paciente1.getText();

        Connection conectar = conexion();

        String sql = "SELECT * FROM pacientes WHERE cedula = ?";
        PreparedStatement pstm = conectar.prepareStatement(sql);
        pstm.setString(1,cedula_mostrar_paciente0);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()){
            System.out.println("Registro encontrado");
            JOptionPane.showMessageDialog(null,"Registro encontrado","Éxito",JOptionPane.INFORMATION_MESSAGE);

            //se muestra la informacion de los pacientes en los campos vacios
            cedula_actualizar_paciente1.setText(rs.getString("cedula"));
            nombre_actualizar_paciente1.setText(rs.getString("nombre"));
            apellido_actualizar_paciente1.setText(rs.getString("apellido"));
            fechaNacimiento_actualizar_paciente1.setText(rs.getString("fecha_nacimiento"));
            sexo_actualizar_paciente1.setSelectedItem(rs.getString("sexo"));
            telefono_actualizar_paciente1.setText(rs.getString("telefono"));
            email_actualizar_paciente1.setText(rs.getString("email"));

        }else {
            JOptionPane.showMessageDialog(null,"No existen registros","Error",JOptionPane.ERROR_MESSAGE);
        }
        pstm.close();
        rs.close();
        conectar.close();
    }

    /**
     * Actualizacion de la informacion de los medicos que se encuentran en la base de datos
     * @throws SQLException
     */
    public void actualizar_info_medicos() throws SQLException {

        String cedula_actualizar_medico0 = cedula_actualizar_medico1.getText();
        String nombre_actualizar_medico0 = nombre_actualizar_medico1.getText();
        String apellido_actualizar_medico0 = apellido_actualizar_medico1.getText();
        String especilidad_actualizar_medico0 = especialidad_actualiza_medico1.getText();
        String telefono_actualizar_medico0 = telefono_actualizar_medico1.getText();
        String email_actualizar_medico0 = email_actualizar_medico1.getText();

        //Cedula que busca la informacion que va a ser modificada
        String cedula_mostrar_medico0 = cedula_mostrar_medico1.getText();

        Connection conectar = conexion();

        String sql = "UPDATE medicos SET cedula = ?, nombre=?, apellido=?, especialidad=?, telefono=?, email=? WHERE cedula=?";
        PreparedStatement pstm = conectar.prepareStatement(sql);
        pstm.setString(1,cedula_actualizar_medico0);
        pstm.setString(2,nombre_actualizar_medico0);
        pstm.setString(3,apellido_actualizar_medico0);
        pstm.setString(4,especilidad_actualizar_medico0);
        pstm.setString(5,telefono_actualizar_medico0);
        pstm.setString(6,email_actualizar_medico0);
        pstm.setString(7,cedula_mostrar_medico0);

        int alterar_filas = pstm.executeUpdate();

        if(alterar_filas > 0){
            System.out.println("Registro actualizado");
            JOptionPane.showMessageDialog(null,"Registro Actualizado Correctamente","Éxito",JOptionPane.INFORMATION_MESSAGE);

            //Se limpian los campos ya actualizados
            cedula_actualizar_medico1.setText("");
            nombre_actualizar_medico1.setText("");
            apellido_actualizar_medico1.setText("");
            especialidad_actualiza_medico1.setText("");
            telefono_actualizar_medico1.setText("");
            email_actualizar_medico1.setText("");

            cedula_mostrar_medico1.setText("");
        }else {
            JOptionPane.showMessageDialog(null,"Error en la actualizacion","Error",JOptionPane.ERROR_MESSAGE);
        }
        pstm.close();
        conectar.close();
    }

    /**
     * Actualizacion de la informacion de los pacientes que se encuentran en la base de datos
     * @throws SQLException
     */
    public void actualizar_info_pacientes() throws SQLException {

        String cedula_actualizar_paciente0 = cedula_actualizar_paciente1.getText();
        String nombre_actualizar_paciente0 = nombre_actualizar_paciente1.getText();
        String apellido_actualizar_paciente0 = apellido_actualizar_paciente1.getText();
        String fechaNacimiento_actualizar_paciente0 = fechaNacimiento_actualizar_paciente1.getText();
        String sexo_actualizar_paciente0 = sexo_actualizar_paciente1.getSelectedItem().toString();
        String telefono_actualizar_paciente0 = telefono_actualizar_paciente1.getText();
        String email_actualizar_paciente0 = email_actualizar_paciente1.getText();

        //Cedula que identifica la informacion que se va a modificar
        String cedula_mostrar_paciente0 = cedula_mostrar_paciente1.getText();

        Connection conectar = conexion();

        String sql = "UPDATE pacientes SET cedula=?, nombre=?, apellido=?, fecha_nacimiento=?, sexo=?, telefono=?, email=? WHERE cedula=?";
        PreparedStatement pstm = conectar.prepareStatement(sql);
        pstm.setString(1,cedula_actualizar_paciente0);
        pstm.setString(2,nombre_actualizar_paciente0);
        pstm.setString(3,apellido_actualizar_paciente0);
        pstm.setString(4, fechaNacimiento_actualizar_paciente0);
        pstm.setString(5,sexo_actualizar_paciente0);
        pstm.setString(6,telefono_actualizar_paciente0);
        pstm.setString(7,email_actualizar_paciente0);
        pstm.setString(8,cedula_mostrar_paciente0);

        int alterar_filas = pstm.executeUpdate();

        if(alterar_filas > 0){
            System.out.println("Registro actualizado");
            JOptionPane.showMessageDialog(null,"Registro Actualizado Correctamente","Éxito",JOptionPane.INFORMATION_MESSAGE);

            cedula_actualizar_paciente1.setText("");
            nombre_actualizar_paciente1.setText("");
            apellido_actualizar_paciente1.setText("");
            fechaNacimiento_actualizar_paciente1.setText("");
            sexo_actualizar_paciente1.setSelectedItem("...");
            telefono_actualizar_paciente1.setText("");
            email_actualizar_paciente1.setText("");

            cedula_mostrar_paciente1.setText("");
        }else {
            JOptionPane.showMessageDialog(null,"Error en la actualizacion","Error",JOptionPane.ERROR_MESSAGE);
        }
        pstm.close();
        conectar.close();
    }


    /**
     * Método inciar: Se nos muestra la pantalla del panel principal con el tamañaño por el cual se va a visualizar
     */
    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
