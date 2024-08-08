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
    private JTextField cedula_ver_cita1;
    private JButton buscar_info_tabla;
    private JButton limpiar_tabla;
    private JButton actualizar_tabla;
    private JButton verAgendaButton;
    private JButton eliminar_info_tabla;
    private JComboBox medico_ver;
    private JTable tablaCitasPendientes;


    public Panel_Medico(){
        super("Panel del Médico");
        setContentPane(pantalla_medico);

        //para poder utilizar la tabla y crear los campos de la informacion que se obtendra de la base de datos
        //Titulos de los campos
        // Configurar el JComboBox
        medico_ver.setModel(new DefaultComboBoxModel<>(new String[]{"Alex Torres", "Victoria Paez"}));

        // Configurar la tabla de registros
        String[] columnNames = {"Cedula Paciente", "Fecha Cita", "Hora Cita", "Médico","Motivo"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        tablaCitasPendientes.setModel(model);


        /**
         * BOTONONES PARA HACER FUNCIONAR LAS FUNCIONALIDADES DEL SISTEMA
         */

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
        buscar_info_tabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buscar_citas_tabla();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        actualizar_tabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actualizar_citas_tabla();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        limpiar_tabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiar_citas_tabla();
            }
        });
        verAgendaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Citas_Pendientes_agenda agenda = new Citas_Pendientes_agenda();
                agenda.iniciar();
            }
        });
        eliminar_info_tabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminar_citas_tabla();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
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
        String url = "jdbc:mysql://uxswl9ehwsjiepbv:T6PAStsLarGkxAVYarvg@b4xspwhutpsd0fxmtffw-mysql.services.clever-cloud.com:3306/b4xspwhutpsd0fxmtffw";
        String user = "uxswl9ehwsjiepbv";
        String password = "T6PAStsLarGkxAVYarvg";

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
            cedula_agregar_cita1.setText("");
            fecha_cita1.setText("");
            hora_cita1.setText("");
            especialidad_cita1.setText("");
            nombre_medico1.setText("");
            motivo_cita1.setText("");
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

            //se limpia los campos
            cedula_consulta1.setText("");
            fecha_consulta1.setText("");
            motivo_consulta1.setText("");
            diagnostico_consulta1.setText("");
            tratamiento_consulta1.setText("");
            medicamento_consulta1.setText("");
            examen_consulta1.setText("");
            resultados_consulta1.setText("");
            notas_medico_consulta1.setText("");

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

    //MODIFICANDO LOS DATOS DE LAS CITAS QUE SE ENCEUNTRAN EN LA TABLA (ELIMINAR)

    /**
     * Método para buscar las citas pendientes en la tabla según la cédula y el médico seleccionado
     * @throws SQLException
     */
    public void buscar_citas_tabla() throws SQLException {
        Connection conectar = conexion();
        String cedula = cedula_ver_cita1.getText();
        String medico = (String) medico_ver.getSelectedItem();
        String sql = "";

        if (medico.equals("Alex Torres")) {
            sql = "SELECT * FROM citas WHERE cedula_paciente = ? AND estado_cita = 'Pendiente' AND nombre_medico = 'Alex Torres'";
        } else if (medico.equals("Victoria Paez")) {
            sql = "SELECT * FROM citas WHERE cedula_paciente = ? AND estado_cita = 'Pendiente' AND nombre_medico = 'Victoria Paez'";
        }

        PreparedStatement pstm = conectar.prepareStatement(sql);
        pstm.setString(1, cedula);
        ResultSet rs = pstm.executeQuery();
        DefaultTableModel model = (DefaultTableModel) tablaCitasPendientes.getModel();
        model.setRowCount(0); // Limpiar tabla

        while (rs.next()) {
            Object[] row = {
                    rs.getString("cedula_paciente"),
                    rs.getDate("fecha_cita"),
                    rs.getTime("hora_cita"),
                    rs.getString("nombre_medico"),
                    rs.getString("motivo_cita")
            };
            model.addRow(row);
        }
        rs.close();
        pstm.close();
        conectar.close();
    }

    /**
     * Método para actualizar la tabla con todas las citas pendientes según el médico seleccionado
     * @throws SQLException
     */
    public void actualizar_citas_tabla() throws SQLException {
        Connection conectar = conexion();
        String medico = (String) medico_ver.getSelectedItem();
        String sql = "";

        if (medico.equals("Alex Torres")) {
            sql = "SELECT * FROM citas WHERE estado_cita = 'Pendiente' AND nombre_medico = 'Alex Torres'";
        } else if (medico.equals("Victoria Paez")) {
            sql = "SELECT * FROM citas WHERE estado_cita = 'Pendiente' AND nombre_medico = 'Victoria Paez'";
        }

        PreparedStatement pstm = conectar.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        DefaultTableModel model = (DefaultTableModel) tablaCitasPendientes.getModel();
        model.setRowCount(0); // Limpiar tabla

        while (rs.next()) {
            Object[] row = {
                    rs.getString("cedula_paciente"),
                    rs.getDate("fecha_cita"),
                    rs.getTime("hora_cita"),
                    rs.getString("nombre_medico"),
                    rs.getString("motivo_cita")
            };
            model.addRow(row);
        }
        rs.close();
        pstm.close();
        conectar.close();
    }

    /**
     * Método para limpiar la información visualizada en la tabla
     */
    public void limpiar_citas_tabla() {
        DefaultTableModel model = (DefaultTableModel) tablaCitasPendientes.getModel();
        model.setRowCount(0); // Limpiar todas las filas
    }

    /**
     * Método para eliminar una cita seleccionada de la tabla
     * @throws SQLException
     */
    public void eliminar_citas_tabla() throws SQLException {
        int selectedRow = tablaCitasPendientes.getSelectedRow();
        if (selectedRow >= 0) {
            String cedula = (String) tablaCitasPendientes.getValueAt(selectedRow, 0);
            Date fecha = (Date) tablaCitasPendientes.getValueAt(selectedRow, 1);
            Time hora = (Time) tablaCitasPendientes.getValueAt(selectedRow, 2);
            String medico = (String) medico_ver.getSelectedItem();
            String sql = "";

            if (medico.equals("Alex Torres")) {
                sql = "DELETE FROM citas WHERE cedula_paciente = ? AND fecha_cita = ? AND hora_cita = ? AND nombre_medico = 'Alex Torres'";
            } else if (medico.equals("Victoria Paez")) {
                sql = "DELETE FROM citas WHERE cedula_paciente = ? AND fecha_cita = ? AND hora_cita = ? AND nombre_medico = 'Victoria Paez'";
            }

            Connection conectar = conexion();
            PreparedStatement pstm = conectar.prepareStatement(sql);
            pstm.setString(1, cedula);
            pstm.setDate(2, fecha);
            pstm.setTime(3, hora);
            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Cita eliminada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiar_citas_tabla(); // Limpiar tabla después de eliminar
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo eliminar la cita", "Error", JOptionPane.ERROR_MESSAGE);
            }
            pstm.close();
            conectar.close();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una cita para eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }



    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
