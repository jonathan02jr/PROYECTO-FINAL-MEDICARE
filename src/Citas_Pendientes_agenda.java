import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Citas_Pendientes_agenda extends JFrame {
    private JTable table1;
    private JRadioButton medico1;
    private JRadioButton medico2;
    private JPanel ver_agenda;
    private JButton salirButton;
    private ButtonGroup buttonGroup;

    public Citas_Pendientes_agenda() {
        super("AGENDA");
        setContentPane(ver_agenda);

        // Grupo de botones para asegurar que solo uno esté seleccionado a la vez
        buttonGroup = new ButtonGroup();
        buttonGroup.add(medico1);
        buttonGroup.add(medico2);


        medico1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mostrarCitasPendientesPorMedico("Alex Torres");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        medico2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mostrarCitasPendientesPorMedico("Victoria Paez");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });
    }

    public void iniciar() {
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mostrarCitasPendientesPorMedico(String nombreMedico) throws SQLException {
        Connection conectar = conexion();
        String sql = "SELECT * FROM citas WHERE estado_cita = 'Pendiente' AND nombre_medico = ?";
        PreparedStatement pstm = conectar.prepareStatement(sql);
        pstm.setString(1, nombreMedico);

        ResultSet rs = pstm.executeQuery();

        // Crear el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Cedula del Paciente");
        modelo.addColumn("Fecha de la Cita");
        modelo.addColumn("Hora de la Cita");
        modelo.addColumn("Medico");
        modelo.addColumn("Motivo");

        // Llenar el modelo con los datos
        while (rs.next()) {
            Object[] fila = new Object[5];
            fila[0] = rs.getString("cedula_paciente");
            fila[1] = rs.getDate("fecha_cita");
            fila[2] = rs.getTime("hora_cita");
            fila[3] = rs.getString("nombre_medico");
            fila[4] = rs.getString("motivo_cita");
            modelo.addRow(fila);
        }

        // se configura la tabla
        table1.setModel(modelo);

        rs.close();
        pstm.close();
        conectar.close();
    }

    // Método de conexión con la base de datos
    public Connection conexion() throws SQLException {
        String url = "jdbc:mysql://uxswl9ehwsjiepbv:T6PAStsLarGkxAVYarvg@b4xspwhutpsd0fxmtffw-mysql.services.clever-cloud.com:3306/b4xspwhutpsd0fxmtffw";
        String user = "uxswl9ehwsjiepbv";
        String password = "T6PAStsLarGkxAVYarvg";
        return DriverManager.getConnection(url,user,password);
    }

}
