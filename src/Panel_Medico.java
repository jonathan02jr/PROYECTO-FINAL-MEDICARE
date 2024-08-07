import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Panel_Medico extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel pantalla_medico;
    private JTextField cedula_agregar_cita1;
    private JTextField fecha_cita1;
    private JTextField hora_cita1;
    private JTextField motivo_cita1;
    private JTextField nombre_medico1;
    private JButton buscar_cedula_cita;
    private JButton guardar_cita;
    private JPanel panel_tratamientos_medico;
    private JTextField examen_consulta1;
    private JPanel panel_citas_medicas;
    private JLabel nombre_paciente_cita1;
    private JLabel historial_paciente_cita1;
    private JTextField especialidad_cita1;
    private JTextField diagnostico_consulta1;
    private JTextField resultados_consulta1;
    private JTextField notas_medico_consulta1;
    private JTextField tratamiento_consulta1;
    private JTextField cedula_consulta1;
    private JLabel nombre_paciente_consulta1;
    private JLabel historial_paciente_consulta1;
    private JTextField fecha_consulta1;
    private JTextField motivo_consulta1;
    private JTextField medicamento_consulta1;
    private JButton guardar_consulta;
    private JButton buscar_cedula_consulta;
    private JButton Sallir;
    private JTable tabla_citas_pendientes;
    private JTextField cedula_historial_cita1;
    private JButton buscar_cedula_historial1;
    private JButton limpiar_campos_citas;
    private JButton actualizar_tabla_citas;

    public Panel_Medico(){
        super("Panel del Médico");
        setContentPane(pantalla_medico);

        //para visualizar todos los datos de la base de datos en la tabla
        try {
            mostrarTodasCitasPendientes();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        buscar_cedula_cita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mostrar_paciente_cita();
                }catch (SQLException ex){
                    throw new RuntimeException(ex);
                }

            }
        });
        guardar_cita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registro_citas();
                }catch (SQLException ex){
                    throw new RuntimeException(ex);
                }
            }
        });
        buscar_cedula_consulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mostrar_paciente_consulta();
                }catch (SQLException ex){
                    throw new RuntimeException(ex);
                }
            }
        });
        guardar_consulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registro_consultas();
                }catch (SQLException ex){
                    throw new RuntimeException(ex);
                }
            }
        });

        //Boton para salir del menu al login
        Sallir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login salir = new Login();
                salir.iniciar();
                dispose();
            }
        });
        buscar_cedula_historial1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mostrarCitasPendientes();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        actualizar_tabla_citas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actualizarTabla();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        limpiar_campos_citas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
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
     * Se muestra el nombre y el n° historial para la cita medica
     * @throws SQLException
     */
    public void mostrar_paciente_cita() throws SQLException{

        String cedula_agregar_cita0 = cedula_agregar_cita1.getText();

        Connection conectar = conexion();

        String sql = "SELECT * FROM pacientes WHERE cedula=?";
        PreparedStatement pstm = conectar.prepareStatement(sql);
        pstm.setString(1,cedula_agregar_cita0);

        ResultSet rs = pstm.executeQuery();

        if(rs.next()){
            System.out.println("Registro encontrado");
            JOptionPane.showMessageDialog(null,"Registro encontrado","Éxito",JOptionPane.INFORMATION_MESSAGE);

            //unimos los campos nombre y apellido para poder mostrar el nombre completo
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");

            String nombreCompleto = nombre +" "+apellido;

            //mostrando la info en los campos
            nombre_paciente_cita1.setText(nombreCompleto);
            historial_paciente_cita1.setText(rs.getString("n_historial_clinico"));
        }else {
            JOptionPane.showMessageDialog(null,"No existen registros","Error",JOptionPane.ERROR_MESSAGE);
        }
        pstm.close();
        rs.close();
        conectar.close();
    }

    /**
     * Método para registrar las citas médicas
     * @throws SQLException
     */
    public void registro_citas() throws SQLException{

        String cedula_agregar_cita0 = cedula_agregar_cita1.getText();
        String fecha_cita0 = fecha_cita1.getText();
        String hora_cita0 = hora_cita1.getText();
        String especialidad_cita0 = especialidad_cita1.getText();
        String nombre_medico0 = nombre_medico1.getText();
        String motivo_cita0 = motivo_cita1.getText();



        Connection conectar = conexion();
        String sql = "INSERT INTO citas(cedula_paciente, fecha_cita, hora_cita, especialidad, nombre_medico, motivo_cita) VALUES (?,?,?,?,?,?)";
        PreparedStatement pstm = conectar.prepareStatement(sql);
        pstm.setString(1, cedula_agregar_cita0);
        pstm.setDate(2, java.sql.Date.valueOf(fecha_cita0));
        pstm.setTime(3, java.sql.Time.valueOf(hora_cita0));
        pstm.setString(4, especialidad_cita0);
        pstm.setString(5, nombre_medico0);
        pstm.setString(6, motivo_cita0);

        int alterar_fila = pstm.executeUpdate();

        if (alterar_fila > 0){
            JOptionPane.showMessageDialog(null, "Cita registrada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar la cita", "Error", JOptionPane.ERROR_MESSAGE);
        }
        pstm.close();
        conectar.close();
    }

    /**
     * Se muestra el nombre y el n° historial para la consulta medica
     * @throws SQLException
     */
    public void mostrar_paciente_consulta() throws SQLException {

        String cedula_consulta0 = cedula_consulta1.getText();

        Connection conectar = conexion();

        String sql = "SELECT * FROM pacientes WHERE cedula=?";
        PreparedStatement pstm = conectar.prepareStatement(sql);
        pstm.setString(1,cedula_consulta0);

        ResultSet rs = pstm.executeQuery();

        if(rs.next()){
            System.out.println("Registro encontrado");
            JOptionPane.showMessageDialog(null,"Registro encontrado","Éxito",JOptionPane.INFORMATION_MESSAGE);

            //unimos los campos nombre y apellido para poder mostrar el nombre completo
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");

            String nombreCompleto = nombre +" "+apellido;

            //mostrando la info en los campos
            nombre_paciente_consulta1.setText(nombreCompleto);
            historial_paciente_consulta1.setText(rs.getString("n_historial_clinico"));
        }else {
            JOptionPane.showMessageDialog(null,"No existen registros","Error",JOptionPane.ERROR_MESSAGE);
        }
        pstm.close();
        rs.close();
        conectar.close();

    }

    /**
     * Metodo para registrar las consultas medicas realizadas
     * @throws SQLException
     */
    public void registro_consultas() throws SQLException {

        String cedula_consulta0 = cedula_consulta1.getText();
        String fecha_consulta0 = fecha_consulta1.getText();
        String motivo_consulta0= motivo_consulta1.getText();
        String diagnostico_consulta0= diagnostico_consulta1.getText();
        String tratamiento_consulta0 = tratamiento_consulta1.getText();
        String medicamento_consulta0 = medicamento_consulta1.getText();
        String examen_consulta0 = examen_consulta1.getText();
        String resultados_consulta0 = resultados_consulta1.getText();
        String notas_medico_consulta0 = notas_medico_consulta1.getText();

        Connection conectar = conexion();
        String sql = "INSERT INTO consultas_medicas(cedula_paciente, fecha_consulta, motivo_consulta, diagnostico, tratamiento, medicamentos, tipo_examen,resultados, notas_medico) VALUES (?,?,?,?,?,?,?,?,?)";

        PreparedStatement pstm = conectar.prepareStatement(sql);
        pstm.setString(1, cedula_consulta0);
        pstm.setDate(2, java.sql.Date.valueOf(fecha_consulta0));
        pstm.setString(3, motivo_consulta0);
        pstm.setString(4, diagnostico_consulta0);
        pstm.setString(5, tratamiento_consulta0);
        pstm.setString(6, medicamento_consulta0);
        pstm.setString(7, examen_consulta0);
        pstm.setString(8,resultados_consulta0);
        pstm.setString(9,notas_medico_consulta0);

        int alterar_fila = pstm.executeUpdate();

        if (alterar_fila > 0){
            JOptionPane.showMessageDialog(null, "Cita registrada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            int idCita = obtenerIdCita(cedula_consulta0, fecha_consulta0);

            actualizarEstadoCita(idCita);
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar la cita", "Error", JOptionPane.ERROR_MESSAGE);
        }
        pstm.close();
        conectar.close();
    }


    /**
     * Método para obtener el ID de una cita basada en la cédula del paciente y la fecha de la cita.
     * @param cedula Cédula del paciente
     * @param fecha Fecha de la cita
     * @return ID de la cita
     * @throws SQLException
     */
    public int obtenerIdCita(String cedula, String fecha) throws SQLException {
        Connection conectar = conexion();
        String sql = "SELECT id_cita FROM citas WHERE cedula_paciente = ? AND fecha_cita = ? AND estado_cita = 'Pendiente'";
        PreparedStatement pstm = conectar.prepareStatement(sql);
        pstm.setString(1, cedula);
        pstm.setDate(2, java.sql.Date.valueOf(fecha));

        ResultSet rs = pstm.executeQuery();
        int idCita = -1;  // Valor por defecto si no se encuentra la cita

        if (rs.next()) {
            idCita = rs.getInt("id_cita");
        }

        rs.close();
        pstm.close();
        conectar.close();

        return idCita;
    }

    /**
     * Método para actualizar el estado de una cita a "Completada"
     * @param idCita ID de la cita a actualizar
     * @throws SQLException
     */
    public void actualizarEstadoCita(int idCita) throws SQLException {
        Connection conectar = conexion();
        String sql = "UPDATE citas SET estado_cita = 'Completada' WHERE id_cita = ?";
        PreparedStatement pstm = conectar.prepareStatement(sql);
        pstm.setInt(1, idCita);

        int filasActualizadas = pstm.executeUpdate();

        if (filasActualizadas > 0) {
            JOptionPane.showMessageDialog(null, "Estado de la cita actualizado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el estado de la cita", "Error", JOptionPane.ERROR_MESSAGE);
        }

        pstm.close();
        conectar.close();
    }


    /**
     * Método para mostrar las citas pendientes en la tabla
     * @throws SQLException
     */
    public void mostrarCitasPendientes() throws SQLException {
        String cedula = cedula_historial_cita1.getText();

        Connection conectar = conexion();
        String sql = "SELECT * FROM citas WHERE cedula_paciente = ? AND estado_cita = 'Pendiente'";
        PreparedStatement pstm = conectar.prepareStatement(sql);
        pstm.setString(1, cedula);

        ResultSet rs = pstm.executeQuery();

        // Crear el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Fecha de la Cita");
        modelo.addColumn("Hora de la Cita");
        modelo.addColumn("Especialidad");
        modelo.addColumn("Motivo de la Cita");

        // Llenar el modelo con los datos
        while (rs.next()) {
            Object[] fila = new Object[4];
            fila[0] = rs.getDate("fecha_cita");
            fila[1] = rs.getTime("hora_cita");
            fila[2] = rs.getString("especialidad");
            fila[3] = rs.getString("motivo_cita");
            modelo.addRow(fila);
        }

        // Configurar la tabla
        tabla_citas_pendientes.setModel(modelo);

        rs.close();
        pstm.close();
        conectar.close();
    }

    /**
     * Método para mostrar todas las citas pendientes en la tabla
     * @throws SQLException
     */
    public void mostrarTodasCitasPendientes() throws SQLException {
        Connection conectar = conexion();
        String sql = "SELECT * FROM citas WHERE estado_cita = 'Pendiente'";
        PreparedStatement pstm = conectar.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();

        // Crear el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Fecha de la Cita");
        modelo.addColumn("Hora de la Cita");
        modelo.addColumn("Especialidad");
        modelo.addColumn("Motivo de la Cita");

        // Llenar el modelo con los datos
        while (rs.next()) {
            Object[] fila = new Object[4];
            fila[0] = rs.getDate("fecha_cita");
            fila[1] = rs.getTime("hora_cita");
            fila[2] = rs.getString("especialidad");
            fila[3] = rs.getString("motivo_cita");
            modelo.addRow(fila);
        }

        // Configurar la tabla
        tabla_citas_pendientes.setModel(modelo);

        rs.close();
        pstm.close();
        conectar.close();
    }


    /**
     * Método para actualizar la tabla con todas las citas pendientes
     * @throws SQLException
     */
    public void actualizarTabla() throws SQLException {
        mostrarTodasCitasPendientes();
    }

    /**
     * Método para limpiar los campos de entrada y la tabla
     */
    public void limpiarCampos() {
        // Limpiar campos de la tabla
        cedula_historial_cita1.setText("");
        nombre_paciente_cita1.setText("");
        historial_paciente_cita1.setText("");

        // Limpiar la tabla
        DefaultTableModel modelo = (DefaultTableModel) tabla_citas_pendientes.getModel();
        modelo.setRowCount(0); // Elimina todas las filas de la tabla
    }



    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
