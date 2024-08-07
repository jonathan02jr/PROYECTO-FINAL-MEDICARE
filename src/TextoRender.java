import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TextoRender extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Configura el aspecto del componente (en este caso, el texto)
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setText(value != null ? value.toString() : "");
        setHorizontalAlignment(CENTER); // O LEFT, RIGHT según el formato deseado
        return component;
    }
}
