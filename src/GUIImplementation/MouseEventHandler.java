package GUIImplementation;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.*;

import password.Timer;

public class MouseEventHandler implements MouseListener {
	private JTable table;
	private JTextArea bottomTextArea;
	private Timer timer;
	public MouseEventHandler(JTable table, JTextArea bottomTextArea, Timer timer) {
		this.table= table;
		this.bottomTextArea = bottomTextArea;
		this.timer = timer;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		String Data = null;
		int row = table.rowAtPoint(e.getPoint());

		int column = table.columnAtPoint(e.getPoint());
		System.out.println(e.getLocationOnScreen().getX() + " " + e.getLocationOnScreen().getY() + " " + row
				+ " " + column);
		if (row != -1 && column != -1) {
			if(e.getClickCount() == 2) {
				if (column == 3) {
					Data = (String) table.getValueAt(row, 3);

					StringSelection stringSelection = new StringSelection(Data);
					java.awt.datatransfer.Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
					clpbrd.setContents(stringSelection, null);
					System.out.println("Password copied");
					bottomTextArea.setText("Password copied");
					System.out.println("Timer started");
					if (timer != null) {
						timer.stop();
					}
					timer = new Timer(bottomTextArea, "Timer");

					table.clearSelection();
				}
				if (column == 4) {
					String url = (String) table.getValueAt(row, 4);

					if (Desktop.isDesktopSupported()) {
						Desktop desktop = Desktop.getDesktop();
						try {
							desktop.browse(new URI(url));
						} catch (IOException | URISyntaxException ex) {

							JOptionPane.showMessageDialog(null, "URL is not valid", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						Runtime runtime = Runtime.getRuntime();
						try {
							runtime.exec("xdg-open " + url);
						} catch (IOException ex) {

							JOptionPane.showMessageDialog(null, "URL is not valid", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		}

		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
