package GUIImplementation;

import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import password.*;

public class WindowEventHandler extends WindowAdapter{
	testGui gui;
	public WindowEventHandler(testGui gui) {
		this.gui = gui;
	}
	
	
	@Override
	public void windowClosing(WindowEvent e) {
		if(Authorization.getCurrentUser() != null) {
			
			try {
				int select = JOptionPane.showConfirmDialog(gui, "Do you want to save?" , "Exit" , JOptionPane.YES_NO_CANCEL_OPTION);
				if(select == JOptionPane.YES_OPTION) {
					Authorization.getCurrentUser().saveUserLoginDetailsData();
					gui.dispose();
				}
				else if(select == JOptionPane.NO_OPTION){
					gui.dispose();
				}
			} catch (IOException | CryptoException  e1) {
				JOptionPane.showMessageDialog(gui, "Sorry, some unexpected error occured");
				gui.dispose();

			} catch (Exception e2) {
				JOptionPane.showMessageDialog(gui, "Sorry, some unexpected error occured");
				gui.dispose();

			}
			
		}
		else {
			gui.dispose();
		}
	}

}
