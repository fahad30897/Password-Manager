package GUIImplementation;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;

public class PasswordCellRenderer extends DefaultTableCellRenderer {

	public PasswordCellRenderer() {
		setOpaque(false);
	}

	private static final String ASTERISKS = "************************";

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		int length = 0;
		if (value instanceof String) {
			length = ((String) value).length();
		} else if (value instanceof char[]) {
			length = ((char[]) value).length;
		}
		setText(asterisks(length));
		return this;
	}

	private String asterisks(int length) {
		if (length > ASTERISKS.length()) {
			StringBuilder sb = new StringBuilder(length);
			for (int i = 0; i < length; i++) {
				sb.append('*');
			}
			return sb.toString();
		} else {
			return ASTERISKS.substring(0, length);
		}
	}
}