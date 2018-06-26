package GUIImplementation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import password.Utility;

public class GeneratePasswordActionListener implements ActionListener {
	
	private JPasswordField editLoginPassword;
	private testGui gui;
	public GeneratePasswordActionListener(JPasswordField password, testGui gui) {
		editLoginPassword = password;
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		JLabel lengthLabel, typesOfCharLabel ;
		JDialog dialog = new JDialog( gui ,"Generate Password" , true);
		dialog.setSize(300, 400);
		JTextField lengthText;
		JCheckBox uppercaseCheckBox, lowercaseCheckBox, numberCheckBox, specialCharacterCheckBox;
		JButton submitButton;
		
		lengthLabel = new JLabel("Enter password length :");
		typesOfCharLabel = new JLabel("What should be included in the password?");
		
		lengthText = new JTextField(10);
		
		uppercaseCheckBox = new JCheckBox("Upper Case letters");
		lowercaseCheckBox = new JCheckBox("Lower Case letters");
		numberCheckBox = new JCheckBox("Numbers");
		specialCharacterCheckBox = new JCheckBox("Special Characters");
		
		submitButton = new JButton("Submit");
		
		submitButton.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String generateString = "";
					boolean someSelected =false;
					
					if(uppercaseCheckBox.isSelected()) {
						if(!generateString.equals("")) {
							generateString += ",";
						}
						
						generateString += "ABCDEFG,HIJKLMNOPQR,STUVWXYZ";
						
						someSelected = true;
					}
					if(lowercaseCheckBox.isSelected()) {
						if(!generateString.equals("")) {
							generateString += ",";
						}
						
						generateString += "abcdefg,hijklmnopq,rstuvwxyz";
						
						someSelected = true;
					}
					if(numberCheckBox.isSelected()) {
						if(!generateString.equals("")) {
							generateString += "," ;
						}
						generateString += "0123456789";
						
						someSelected = true;
					}
					if(specialCharacterCheckBox.isSelected()) {
						if(!generateString.equals("")) {
							generateString += ",";
						}
						generateString +=  "@#$%&";
						
						someSelected = true;
					}
					int length = Integer.parseInt(lengthText.getText());
					if(someSelected) {
						editLoginPassword.setText(Utility.generatePassword(length , generateString));
						dialog.dispose();
					}
					else{
						JOptionPane.showMessageDialog(dialog, "Please select atleast one checkbox");
					}
					
				}
				catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(gui.editContainerPanel, "Enter valid number");
				}
			}
		});
		JPanel generatePasswordPanel = new JPanel(new GridBagLayout());
		dialog.getContentPane().add(generatePasswordPanel);
		
		
		Utility.addComp(generatePasswordPanel, lengthLabel, 0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		Utility.addComp(generatePasswordPanel, lengthText, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		Utility.addComp(generatePasswordPanel, typesOfCharLabel, 0, 2, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		Utility.addComp(generatePasswordPanel, uppercaseCheckBox, 0, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		Utility.addComp(generatePasswordPanel, lowercaseCheckBox, 0, 4, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		Utility.addComp(generatePasswordPanel, numberCheckBox, 0, 5, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		Utility.addComp(generatePasswordPanel, specialCharacterCheckBox, 0, 6, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		Utility.addComp(generatePasswordPanel, submitButton, 1, 7, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
		
		dialog.setVisible(true);
		
	}
}
