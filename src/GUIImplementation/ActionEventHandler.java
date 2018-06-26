package GUIImplementation;

import java.awt.*;

import java.awt.event.*;
import java.io.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GUIImplementation.PasswordCellRenderer;
import password.*;

public class ActionEventHandler implements ActionListener {

	private testGui gui;
	
	public ActionEventHandler(testGui g) {
		gui = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == gui.createItem) {
			JLabel userNameLabel, passwordLabel, conPasswordLabel;
			JDialog signUpDialog = new JDialog(gui, "Sign Up", true);

			signUpDialog.setSize(400, 300);
			signUpDialog.setLocationRelativeTo(null);
			signUpDialog.setLayout(new FlowLayout(FlowLayout.CENTER));

			userNameLabel = new JLabel("Username");
			JTextField userNameTextField = new JTextField(15);

			passwordLabel = new JLabel("Password");
			JPasswordField passwordTextField = new JPasswordField(15);

			conPasswordLabel = new JLabel("Confirm Password");
			JPasswordField confPasswordTextField = new JPasswordField(15);

			JButton submit = new JButton("Submit");
			signUpDialog.getRootPane().setDefaultButton(submit);
			submit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String pass = new String(passwordTextField.getPassword());
					String confpass = new String(confPasswordTextField.getPassword());
					System.out.println("user " + userNameTextField.getText().isEmpty() + "pass " + pass.isEmpty());
					if (!(userNameTextField.getText().isEmpty())){
						if(!pass.isEmpty()) {
							if (pass.equals(confpass)) {
								try {
									Authorization.createNewUser(new User(userNameTextField.getText(),
											new String(passwordTextField.getPassword()),
											new String(passwordTextField.getPassword())));
									gui.refreshTable("");
									gui.bottomTextArea.setText("Welcome, " + userNameTextField.getText());
									gui.table.getColumnModel().getColumn(3).setCellRenderer(new PasswordCellRenderer());
									gui.table.setCellSelectionEnabled(true);
									gui.setupJTableSelection();
									gui.setControlStatus(true);

								} catch (UserNotFoundException e3) {
									JOptionPane.showMessageDialog(null, "Username Not Found");
								} catch (UserAlreadyExistException e2) {
									JOptionPane.showMessageDialog(null, "Username Already Exist");
								}

								catch (ClassNotFoundException | IOException | CryptoException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								signUpDialog.dispose();
							} else {
								JOptionPane.showMessageDialog(null, "Password does not match");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Enter Details properly");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Enter Details properly");
					}

				}

			});
			JPanel createPanel = new JPanel(new GridBagLayout());
			signUpDialog.getContentPane().add(createPanel);
			Utility.addComp(createPanel, userNameLabel, 0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
			Utility.addComp(createPanel, userNameTextField, 1, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
			Utility.addComp(createPanel, passwordLabel, 0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
			Utility.addComp(createPanel, passwordTextField, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
			Utility.addComp(createPanel, conPasswordLabel, 0, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
			Utility.addComp(createPanel, confPasswordTextField, 1, 2, 1, 1, GridBagConstraints.EAST,
					GridBagConstraints.NONE);
			Utility.addComp(createPanel, submit, 0, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
			signUpDialog.setVisible(true);	
		}
		
		else if(e.getSource() == gui.loginItem) {
			JLabel userNameLabel, passwordLabel;
			JDialog loginDialog = new JDialog(gui, "Login", true);
			loginDialog.setSize(400, 300);
			loginDialog.setLocationRelativeTo(null);
			loginDialog.setLayout(new FlowLayout(FlowLayout.CENTER));

			userNameLabel = new JLabel("Username");
			JTextField userNameTextField = new JTextField(15);

			passwordLabel = new JLabel("Password");
			JPasswordField passwordTextField = new JPasswordField(15);

			JButton submit = new JButton("Submit");
			loginDialog.getRootPane().setDefaultButton(submit);

			submit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String pass = new String(passwordTextField.getPassword());
					if (!(userNameTextField.getText().isEmpty())){ 
						if(!(pass.isEmpty())) {
							
							boolean auth = false;
							try {
								auth = Authorization.loginUser(userNameTextField.getText(), pass);
								if (auth) {
									gui.bottomTextArea.setText("Welcome, " + userNameTextField.getText());
									loginDialog.dispose();
									gui.refreshTable("");
									gui.table.getColumnModel().getColumn(3).setCellRenderer(new PasswordCellRenderer());
									gui.table.setCellSelectionEnabled(true);
									gui.setupJTableSelection();
									gui.setControlStatus(true);
								} else {
									JOptionPane.showMessageDialog(null, "Password does not match");
								}
							} catch (UserNotFoundException unfe) {
								JOptionPane.showMessageDialog(null,
										"Username not found. Make sure you spell it in properly");
							} catch (ClassNotFoundException | CryptoException | IOException e4) {
							
								e4.printStackTrace();
						}
						
						
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Enter Details properly");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Enter Details properly");
					}
					
				}

			});
			JPanel loginPanel = new JPanel(new GridBagLayout());
			loginDialog.getContentPane().add(loginPanel);
			Utility.addComp(loginPanel, userNameLabel, 0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
			Utility.addComp(loginPanel, userNameTextField, 1, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
			Utility.addComp(loginPanel, passwordLabel, 0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
			Utility.addComp(loginPanel, passwordTextField, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
			Utility.addComp(loginPanel, submit, 0, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
			loginDialog.setVisible(true);
			
		}
		
		else if (e.getSource() == gui.addItem) {
			JLabel loginWebsiteLabel, loginUserLabel, loginPassLabel, loginURLLabel, commentsLabel,
				passwordStrength , passwordStrengthLabel;
			JDialog addDialog = new JDialog(gui, "Add New Login Details", true);
			Checkbox showPasswordCheckbox;
			addDialog.setSize(500, 400);
			addDialog.setLocationRelativeTo(null);
			addDialog.setLayout(new FlowLayout(FlowLayout.CENTER));

			loginWebsiteLabel = new JLabel("Login Website Name");
			JTextField loginWebsiteName = new JTextField(15);

			loginUserLabel = new JLabel("Login User Name");
			JTextField loginUserNameTextField = new JTextField(15);

			loginPassLabel = new JLabel("Password");
			JPasswordField loginpassword = new JPasswordField(15);
			
			
			
			passwordStrengthLabel = new JLabel("");
			
			
			passwordStrength = new JLabel("Password Strength: ");
			
			
			loginpassword.getDocument().addDocumentListener(new DocumentListener() {
				
				@Override
				public void removeUpdate(DocumentEvent e) {
					valueChanged(e);
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					valueChanged(e);
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {
					valueChanged(e);
				}
				
				private void valueChanged(DocumentEvent e) {
					PasswordStrength passwordStrength = Utility.getPasswordStrength(
							new String(loginpassword.getPassword()));
					
					passwordStrengthLabel.setText(passwordStrength.getDisplayString());
					passwordStrengthLabel.setForeground(passwordStrength.getColor());
				}
			});

			JButton generatePassword = new JButton("Generate Password");
			generatePassword.addActionListener(new GeneratePasswordActionListener(loginpassword , gui));

			showPasswordCheckbox = new Checkbox("Show Password");
			showPasswordCheckbox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						loginpassword.setEchoChar((char) 0);

					} else {
						loginpassword.setEchoChar('*');
					}
				}
			});
			loginURLLabel = new JLabel("Login URL");
			JTextField loginURL = new JTextField(15);

			commentsLabel = new JLabel("Comments");
			JTextArea comments = new JTextArea(5, 15);

			JButton save = new JButton("Save");
			addDialog.getRootPane().setDefaultButton(save);
			save.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String pass = new String(loginpassword.getPassword());
					if (!(loginWebsiteName.getText().isEmpty() && pass.isEmpty() && loginURL.getText().isEmpty()
							&& loginUserNameTextField.getText().isEmpty())) {
						try {
							Authorization.getCurrentUser()
									.addNewLoginDetails(new LoginDetails(0, loginWebsiteName.getText(),
											loginUserNameTextField.getText(), pass, loginURL.getText(),
											comments.getText()));
							gui.refreshTable("");
							addDialog.dispose();

						} catch (ClassNotFoundException | IOException | CryptoException e1) {
		
							e1.printStackTrace();
						}

					} else {
						JOptionPane.showMessageDialog(null, "Enter Details properly");
					}

				}

			});
			JPanel addDialogPanel = new JPanel(new GridBagLayout());
			comments.setLineWrap(true);
			comments.setWrapStyleWord(true);
			JScrollPane commentsScrollPane = new JScrollPane(comments, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			addDialog.getContentPane().add(addDialogPanel);
			Utility.addComp(addDialogPanel, loginWebsiteLabel, 0, 0, 1, 1, GridBagConstraints.WEST,
					GridBagConstraints.NONE);
			Utility.addComp(addDialogPanel, loginWebsiteName, 1, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
			Utility.addComp(addDialogPanel, loginUserLabel, 0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
			Utility.addComp(addDialogPanel, loginUserNameTextField, 1, 1, 1, 1, GridBagConstraints.EAST,
					GridBagConstraints.NONE);
			Utility.addComp(addDialogPanel, loginPassLabel, 0, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
			Utility.addComp(addDialogPanel, loginpassword, 1, 2, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
			Utility.addComp(addDialogPanel, generatePassword, 2, 2, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
			
			Utility.addComp(addDialogPanel, passwordStrength, 0, 3, 1, 1, GridBagConstraints.WEST,
					GridBagConstraints.NONE);
			Utility.addComp(addDialogPanel, passwordStrengthLabel, 1, 3, 1, 1, GridBagConstraints.WEST,
					GridBagConstraints.NONE);
			
			Utility.addComp(addDialogPanel, showPasswordCheckbox, 2, 3, 1, 1, GridBagConstraints.WEST,
					GridBagConstraints.NONE);
			Utility.addComp(addDialogPanel, loginURLLabel, 0, 4, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
			Utility.addComp(addDialogPanel, loginURL, 1, 4, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
			Utility.addComp(addDialogPanel, commentsLabel, 0, 5, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
			Utility.addComp(addDialogPanel, commentsScrollPane, 1, 5, 5, 1, GridBagConstraints.WEST,
					GridBagConstraints.NONE);
			Utility.addComp(addDialogPanel, save, 1, 6, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
			addDialog.setVisible(true);
		}
		
		else if(e.getSource() == gui.editItem) {
			try {
				gui.refreshEditContainerPanel();
				
				JLabel editLoginWebsiteLabel, editLoginUserLabel, editLoginPassLabel, editLoginURLLabel,
						editCommentsLabel, editLabel, passwordStrengthLabel, passwordStrength;
				Border border = BorderFactory.createLineBorder(Color.BLACK);
		
				JTextField editLoginWebsiteName, editLoginUserNameTextField, editLoginURL;
				JPasswordField editLoginPassword;
				Checkbox showPasswordCheckbox;

				JTextArea editComments;
		
				gui.editContainerPanel.setVisible(false);
				gui.editContainerPanel.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10,10,10,10)));
		
				gui.editPanel.setBorder(BorderFactory.createCompoundBorder(border,
						BorderFactory.createEmptyBorder(10, 10, 10, 10)));

				int row = gui.table.getSelectedRow();

				editLabel = new JLabel("Edit Login Details");
				editLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));

				editLoginWebsiteLabel = new JLabel("Login Website Name");
				editLoginWebsiteName = new JTextField(15);
				editLoginWebsiteName.setText((String) gui.data[row][1]);
		
				editLoginWebsiteLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
				editLoginUserLabel = new JLabel("Login User Name");
				editLoginUserLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
				editLoginUserNameTextField = new JTextField(15);
				editLoginUserNameTextField.setText((String) gui.data[row][2]);
		
				editLoginPassLabel = new JLabel("Password");
				editLoginPassLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
				editLoginPassword = new JPasswordField(15);
				editLoginPassword.setText((String) gui.data[row][3]);
				
				PasswordStrength strength = Utility.getPasswordStrength(
						new String(editLoginPassword.getPassword()));
				
				passwordStrengthLabel = new JLabel(strength.getDisplayString());
				passwordStrengthLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
				passwordStrengthLabel.setForeground(strength.getColor());
				
				passwordStrength = new JLabel("Password Strength: ");
				passwordStrength.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
				
				editLoginPassword.getDocument().addDocumentListener(new DocumentListener() {
					
					@Override
					public void removeUpdate(DocumentEvent e) {
						valueChanged(e);
					}
					
					@Override
					public void insertUpdate(DocumentEvent e) {
						valueChanged(e);
					}
					
					@Override
					public void changedUpdate(DocumentEvent e) {
						valueChanged(e);
					}
					
					private void valueChanged(DocumentEvent e) {
						PasswordStrength passwordStrength = Utility.getPasswordStrength(
								new String(editLoginPassword.getPassword()));
						
						passwordStrengthLabel.setText(passwordStrength.getDisplayString());
						passwordStrengthLabel.setForeground(passwordStrength.getColor());
					}
				});
				
				JButton generatePassword = new JButton("Generate Password");
				
				generatePassword.addActionListener(new GeneratePasswordActionListener(editLoginPassword , gui));
				showPasswordCheckbox = new Checkbox("Show Password");
				showPasswordCheckbox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) {
							editLoginPassword.setEchoChar((char) 0);

						} else {
							editLoginPassword.setEchoChar('*');
						}
					}
				});
		

				editLoginURLLabel = new JLabel("Login URL");
				editLoginURLLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
				editLoginURL = new JTextField(15);
				editLoginURL.setText((String) gui.data[row][4]);
		

				editCommentsLabel = new JLabel("Comments");
				editCommentsLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
				editComments = new JTextArea(5, 15);
				editComments.setText((String) gui.data[row][5]);
		

				JButton save = new JButton("Save");
				save.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String pass = new String(editLoginPassword.getPassword());
						try {
							String txt = editLoginWebsiteName.getText();
							if(txt.equals(null) || txt.equals("")) {
								JOptionPane.showMessageDialog(gui, "Website Name is compulsory");
							}
							else{
								Authorization.getCurrentUser()
										.editLoginDetials(new LoginDetails((int) (gui.data[row][6]),
												editLoginWebsiteName.getText(), editLoginUserNameTextField.getText(),
												pass, editLoginURL.getText(), editComments.getText()));
								gui.refreshTable("");
								gui.refreshEditContainerPanel();
							}

						} catch (IOException | CryptoException e1) {
		
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
		
							e1.printStackTrace();
						}
					}

				});
				
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						gui.refreshEditContainerPanel();
					}
				});
				
				gui.editContainerPanel.setVisible(true);
				editComments.setLineWrap(true);
				editComments.setWrapStyleWord(true);
				JScrollPane editCommentsScrollPane = new JScrollPane(editComments,
						JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				gui.editPanel.add(gui.editContainerPanel);
				Utility.addComp(gui.editContainerPanel, editLabel, 0, 3, 2, 1, GridBagConstraints.CENTER,
						GridBagConstraints.NONE);
				Utility.addComp(gui.editContainerPanel, editLoginWebsiteLabel, 0, 4, 1, 1, GridBagConstraints.WEST,
						GridBagConstraints.NONE);
				Utility.addComp(gui.editContainerPanel, editLoginWebsiteName, 1, 4, 1, 1, GridBagConstraints.EAST,
						GridBagConstraints.NONE);
				Utility.addComp(gui.editContainerPanel, editLoginUserLabel, 0, 5, 1, 1, GridBagConstraints.WEST,
						GridBagConstraints.NONE);
				Utility.addComp(gui.editContainerPanel, editLoginUserNameTextField, 1, 5, 1, 1, GridBagConstraints.EAST,
						GridBagConstraints.NONE);
				Utility.addComp(gui.editContainerPanel, editLoginPassLabel, 0, 6, 1, 1, GridBagConstraints.WEST,
						GridBagConstraints.NONE);
				Utility.addComp(gui.editContainerPanel, editLoginPassword, 1, 6, 1, 1, GridBagConstraints.EAST,
						GridBagConstraints.NONE);
				Utility.addComp(gui.editContainerPanel, generatePassword, 2, 6, 1, 1, GridBagConstraints.EAST,
						GridBagConstraints.NONE);
				
				
				Utility.addComp(gui.editContainerPanel ,  passwordStrength, 0, 7, 1, 1, GridBagConstraints.WEST,
						GridBagConstraints.NONE);
				Utility.addComp(gui.editContainerPanel ,  passwordStrengthLabel, 1, 7, 1, 1, GridBagConstraints.WEST,
						GridBagConstraints.NONE);
				
				Utility.addComp(gui.editContainerPanel, showPasswordCheckbox, 2, 7, 1, 1, GridBagConstraints.WEST,
						GridBagConstraints.NONE);
				Utility.addComp(gui.editContainerPanel, editLoginURLLabel, 0, 8, 1, 1, GridBagConstraints.WEST,
						GridBagConstraints.NONE);
				Utility.addComp(gui.editContainerPanel, editLoginURL, 1, 8, 1, 1, GridBagConstraints.WEST,
						GridBagConstraints.NONE);
				Utility.addComp(gui.editContainerPanel, editCommentsLabel, 0, 9, 1, 1, GridBagConstraints.WEST,
						GridBagConstraints.NONE);
				Utility.addComp(gui.editContainerPanel, editCommentsScrollPane, 1, 9, 5, 1, GridBagConstraints.WEST,
						GridBagConstraints.NONE);
				Utility.addComp(gui.editContainerPanel, save, 0, 10, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
				Utility.addComp(gui.editContainerPanel, cancelButton, 1, 10, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
				gui.editPanel.setVisible(true);
			} catch (ArrayIndexOutOfBoundsException e1) {
				JOptionPane.showMessageDialog(null, "Please select a row to be edited", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else if(e.getSource() == gui.deleteItem) {
			try {
				int row = gui.table.getSelectedRow();
				int select = JOptionPane.showConfirmDialog(gui, "Are you sure you want to delete " + gui.data[row][1]);
				if(select == JOptionPane.YES_OPTION) {
					Authorization.getCurrentUser().deleteLoginDetails(
							new LoginDetails((int) (gui.data[row][6]), (String) gui.data[row][1], (String) gui.data[row][2],
									(String) gui.data[row][3], (String) gui.data[row][4], (String) gui.data[row][5]));
					gui.refreshTable("");
				}
				
			} catch (ArrayIndexOutOfBoundsException ex) {
				JOptionPane.showMessageDialog(null, "Please select a row to be edited", "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (CryptoException e1) {
				e1.printStackTrace();
			}
		}
		
		
		else if( e.getSource() == gui.backupItem) {
			try {
				JFileChooser fileChooser = new JFileChooser();
				
//				String filename = Authorization.getCurrentUser().getLoginDetailsFileName();
//				filename += new Date();
//				SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
//			    Date now = new Date();
//			    String strDate = sdfDate.format(now);
//				filename += strDate + ".backup";
				fileChooser.addChoosableFileFilter(new FileFilter() {
					 
				    public String getDescription() {
				        return "Backup file (.backup)";
				    }
				 
				    public boolean accept(File f) {
				        if (f.isDirectory()) {
				            return false;
				        } else {
				            return f.getName().toLowerCase().endsWith(".backup");
				        }
				    }
				});
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					
					String filename = selectedFile.getAbsolutePath();
					Authorization.getCurrentUser().takeBackup(filename);
					gui.refreshTable("");
					JOptionPane.showMessageDialog(gui, "Backup Taken");
				}
			} catch (IOException | CryptoException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		
		else if( e.getSource() == gui.loadFromBackupItem) {
			try {
				int select = JOptionPane.showConfirmDialog(gui, "Are you sure you want to restore backup? Current data would be lost.","Confirm", JOptionPane.YES_NO_OPTION);
				if(select == JOptionPane.YES_OPTION) {
					JFileChooser fileChooser = new JFileChooser();
					int returnValue = fileChooser.showOpenDialog(null);
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fileChooser.getSelectedFile();
						
						String filename = selectedFile.getAbsolutePath();
//						if(filename.endsWith(".backup")) {
						
							Authorization.getCurrentUser().loadFromBackup(filename);
							gui.refreshTable("");
//						}
					}
					
				}
			}catch(Exception e1) {
//			} catch (ClassNotFoundException | IOException | CryptoException e1) {
				e1.printStackTrace();
			}
		}
		
		else if(e.getSource() == gui.searchButton) {
			gui.refreshTable(gui.searchTextField.getText());
		}
		
		else if(e.getSource() == gui.clearButton) {
		
			gui.refreshTable("");
			gui.searchTextField.setText("");
		}
		
		else if(e.getSource() == gui.logoutItem) {

			try {
				Authorization.getCurrentUser().saveUserLoginDetailsData();
				Authorization.logoutUser();
			} catch (IOException | CryptoException e1) {
				e1.printStackTrace();
			}
		
			gui.setControlStatus(false);
		}
		
	} 

}
