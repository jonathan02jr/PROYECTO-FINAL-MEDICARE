import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton buscar_info_tabla;
    private JButton eliminar_info_tabla;
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
    private JTable tabla_registros;
    private JTextField cedula_ver_registro1;
    private JButton limpiar_tabla;
    private JComboBox tipo_registro;
    private JButton actualizar_tabla;

    /**
     * Atributos para mostrar los reportes
     */
    private JLabel info_n_citas1;
    private JLabel info_n_pacientes1;
    private JLabel info_n_medicos;
    private JLabel cedula_ultima_act1;
    private JLabel paciente_ultima_act1;
    private JLabel fecha_ultima_act1;
    private JButton actualizar_resportes;
    private JLabel medico_ultima_act1;

    public Panel_Administrador(){
        super("Panel del Administrador");
        setContentPane(pantalla_admi);


        //Para poder utilizar la tabla y crear los campos de la informacion que se obtendra de la base de datos
        //Titulos de los campos
        // Configurar el JComboBox
        tipo_registro.setModel(new DefaultComboBoxModel<>(new String[]{"Pacientes", "Medicos"}));

        // Configurar la tabla de registros
        String[] columnNames = {"Cedula", "Nombre", "Apellido", "Email"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        tabla_registros.setModel(model);


        //BOTONES
        guardar_info_medico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registro_info_medicos();
                }catch (SQLException ex){
                    throw new RuntimeException(ex);
                }

            }
        });
        guardar_info_pacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registro_info_pacientes();
                } catch (SQLException ex) {
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

        //botones para el funcionamiento de la informacion de registross
        buscar_info_tabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buscar_registros_tabla();
                }catch (SQLException ex){
                    throw new RuntimeException(ex);
                }
            }
        });
        eliminar_info_tabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminar_registro_tabla();
                }catch (SQLException ex){
                    throw new RuntimeException(ex);
                }
            }
        });

        limpiar_tabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiar_registros_tabla();
            }
        });
        actualizar_tabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actualizar_registros_tabla();
                }catch (SQLException ex){
                    throw new RuntimeException(ex);
                }
            }
        });
        actualizar_resportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actualizar_info_reportes();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al actualizar la información de reportes.", "Error", JOptionPane.ERROR_MESSAGE);
                }
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
    public void registro_info_medicos() throws SQLException{

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
    public void registro_info_pacientes() throws SQLException{

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


    //FUNCIONALIDADES DE LA TABLA DE LOS REGISTROS DE PASCIENTES Y MEDICOS

    /**
     * Metodo para buscar los registros en la tabla
     * @throws SQLException
     */
    public  void buscar_registros_tabla() throws SQLException {
        Connection conectar = conexion();
        String tipo = tipo_registro.getSelectedItem().toString();
        String cedula = cedula_ver_registro1.getText();
        String sql = "";

        if (tipo.equals("Pacientes")) {
            sql = "SELECT * FROM pacientes WHERE cedula = ?";
        } else if (tipo.equals("Medicos")) {
            sql = "SELECT * FROM medicos WHERE cedula = ?";
        }

        PreparedStatement pstm = conectar.prepareStatement(sql);
        pstm.setString(1, cedula);
        ResultSet rs = pstm.executeQuery();
        DefaultTableModel model = (DefaultTableModel) tabla_registros.getModel();
        model.setRowCount(0); // Limpiar tabla

        while (rs.next()) {
            Object[] row = {
                    rs.getString("cedula"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("email")
            };
            model.addRow(row);
        }
        rs.close();
        pstm.close();
        conectar.close();
    }

    /**
     * Metodo para actualizar la tabla
     * @throws SQLException
     */
    public void actualizar_registros_tabla() throws SQLException {
        Connection conectar = conexion();
        String tipo = tipo_registro.getSelectedItem().toString();
        String sql = "";

        if (tipo.equals("Pacientes")) {
            sql = "SELECT * FROM pacientes";
        } else if (tipo.equals("Medicos")) {
            sql = "SELECT * FROM medicos";
        }

        PreparedStatement pstm = conectar.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        DefaultTableModel model = (DefaultTableModel) tabla_registros.getModel();
        model.setRowCount(0); // Limpiar tabla

        while (rs.next()) {
            Object[] row = {
                    rs.getString("cedula"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("email")
            };
            model.addRow(row);
        }
        rs.close();
        pstm.close();
        conectar.close();
    }

    /**
     * Para limpiar la informacion que se visualiza en la tabla
     */
    public void limpiar_registros_tabla() {
        DefaultTableModel model = (DefaultTableModel) tabla_registros.getModel();
        model.setRowCount(0); // Limpiar todas las filas
    }

    /**
     * Metodo para eliminar los registros de una tabla ya sea del medico o del paciente
     * @throws SQLException
     */
    public void eliminar_registro_tabla() throws SQLException {
        int selectedRow = tabla_registros.getSelectedRow();
        if (selectedRow >= 0) {
            String cedula = (String) tabla_registros.getValueAt(selectedRow, 0);
            String tipo = tipo_registro.getSelectedItem().toString();
            String sql = "";

            if (tipo.equals("Pacientes")) {
                sql = "DELETE FROM pacientes WHERE cedula = ?";
            } else if (tipo.equals("Medicos")) {
                sql = "DELETE FROM medicos WHERE cedula = ?";
            }

            Connection conectar = conexion();
            PreparedStatement pstm = conectar.prepareStatement(sql);
            pstm.setString(1, cedula);
            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Registro eliminado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro", "Error", JOptionPane.ERROR_MESSAGE);
            }
            pstm.close();
            conectar.close();
            actualizar_registros_tabla(); // Actualizar tabla después de eliminar
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un registro para eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    //APARTADO PARA LA VISUALIZACION DE LOS REPORTES

    /**
     * metodo para actualizar los repsortes y asi poder visualizarlos
     * @throws SQLException
     */
    public void actualizar_info_reportes() throws SQLException {
        // Conectar a la base de datos
        Connection conectar = conexion();

        // Consultar el número de citas
        String sqlCitas = "SELECT COUNT(*) AS total FROM citas";
        PreparedStatement pstmCitas = conectar.prepareStatement(sqlCitas);
        ResultSet rsCitas = pstmCitas.executeQuery();
        if (rsCitas.next()) {
            int totalCitas = rsCitas.getInt("total");
            info_n_citas1.setText(String.valueOf(totalCitas));
        }

        // Se consulta el numero de pacientes
        String sqlPacientes = "SELECT COUNT(*) AS total FROM pacientes";
        PreparedStatement pstmPacientes = conectar.prepareStatement(sqlPacientes);
        ResultSet rsPacientes = pstmPacientes.executeQuery();
        if (rsPacientes.next()) {
            int totalPacientes = rsPacientes.getInt("total");
            info_n_pacientes1.setText(String.valueOf(totalPacientes));
        }

        // Se consulta el numero de medicos
        String sqlMedicos = "SELECT COUNT(*) AS total FROM medicos";
        PreparedStatement pstmMedicos = conectar.prepareStatement(sqlMedicos);
        ResultSet rsMedicos = pstmMedicos.executeQuery();
        if (rsMedicos.next()) {
            int totalMedicos = rsMedicos.getInt("total");
            info_n_medicos.setText(String.valueOf(totalMedicos));
        }

        // Se consulta la ultima cita y el nombre del paciente asociado
        String sqlUltimaCita = "SELECT c.cedula_paciente, p.nombre AS paciente_nombre, p.apellido AS paciente_apellido, " +
                "c.fecha_cita, c.nombre_medico " +
                "FROM citas c " +
                "JOIN pacientes p ON c.cedula_paciente = p.cedula " +
                "ORDER BY c.fecha_cita DESC, c.hora_cita DESC LIMIT 1";
        PreparedStatement pstmUltimaCita = conectar.prepareStatement(sqlUltimaCita);
        ResultSet rsUltimaCita = pstmUltimaCita.executeQuery();

        if (rsUltimaCita.next()) {
            String cedulaPaciente = rsUltimaCita.getString("cedula_paciente");
            String nombrePaciente = rsUltimaCita.getString("paciente_nombre") + " " + rsUltimaCita.getString("paciente_apellido");
            java.sql.Date fechaCita = rsUltimaCita.getDate("fecha_cita");
            String nombreMedico = rsUltimaCita.getString("nombre_medico");

            cedula_ultima_act1.setText(cedulaPaciente);
            paciente_ultima_act1.setText(nombrePaciente);
            fecha_ultima_act1.setText(fechaCita.toString());
            medico_ultima_act1.setText(nombreMedico); // Asumiendo que has añadido este campo en tu interfaz
        }

        // Cerrar recursos
        rsCitas.close();
        pstmCitas.close();
        rsPacientes.close();
        pstmPacientes.close();
        rsMedicos.close();
        pstmMedicos.close();
        rsUltimaCita.close();
        pstmUltimaCita.close();
        conectar.close();
    }


    /**
     * Método inciar: Se nos muestra la pantalla del panel principal con el tamañaño por el cual se va a visualizar
     */
    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
