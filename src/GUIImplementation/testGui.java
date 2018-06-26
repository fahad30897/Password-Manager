package GUIImplementation;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.List;
import java.util.stream.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import password.*;
import password.Timer;

public class testGui extends JFrame {

	
	public JMenuItem loginItem, createItem, addItem, editItem, deleteItem , logoutItem, backupItem, loadFromBackupItem;
	public JMenuBar menubar;
	public JMenu fileMenu , editMenu;
	public JButton clearButton, searchButton;
	public JTextField searchTextField;
	public JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	public JPanel centerPanel = new JPanel(new GridLayout(0, 2));
	public JPanel tablePanel = new JPanel(new BorderLayout());
	public JPanel editPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 0));
	public JPanel bottomPanel = new JPanel(new BorderLayout());
	public JPanel editContainerPanel = new JPanel(new GridBagLayout());
	public JPanel startingInformationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER , 0 , 150));
	public JLabel startingInformationLabel = new JLabel();
	public JPanel informationPanel = new JPanel();
	public JLabel informationLabel = new JLabel();
	public JLabel passwordStrengthLabel = new JLabel();
	public JPanel cardPanel = new JPanel();
	public CardLayout cardLayout =new CardLayout();
	public JTextArea bottomTextArea;
	public Object[][] data = new Object[0][7];
	public String[] columnNames = { "Index", "Website", "Username", "Password", "URL", "Comments" };
	Timer timer = null;
	public JTable table = new JTable();
	public DefaultTableModel model = new DefaultTableModel(data, columnNames) {

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}

	};

	public testGui() {
		super("Password Manager");
		this.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		setLayout(new BorderLayout());
		this.addWindowListener(new WindowEventHandler(this));
		
		menubar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");

		this.loginItem = new JMenuItem("Login");
		this.editItem = new JMenuItem("Edit");
		this.addItem = new JMenuItem("Add");
		this.logoutItem = new JMenuItem("Logout");
		this.createItem = new JMenuItem("Create");
		this.deleteItem = new JMenuItem("Delete");
		this.loadFromBackupItem = new JMenuItem("Restore Data From Backup");
		this.backupItem = new JMenuItem("Take Backup");
		
		fileMenu.setFont(new Font(Font.SANS_SERIF , Font.PLAIN, 20));
		editMenu.setFont(new Font(Font.SANS_SERIF , Font.PLAIN, 20));
		
		loginItem.setFont(new Font(Font.SANS_SERIF , Font.PLAIN, 20));
		editItem.setFont(new Font(Font.SANS_SERIF , Font.PLAIN, 20));
		addItem.setFont(new Font(Font.SANS_SERIF , Font.PLAIN, 20));
		logoutItem.setFont(new Font(Font.SANS_SERIF , Font.PLAIN, 20));
		createItem.setFont(new Font(Font.SANS_SERIF , Font.PLAIN, 20));
		deleteItem.setFont(new Font(Font.SANS_SERIF , Font.PLAIN, 20));
		backupItem.setFont(new Font(Font.SANS_SERIF , Font.PLAIN, 20));
		loadFromBackupItem.setFont(new Font(Font.SANS_SERIF , Font.PLAIN, 20));
		
		
		fileMenu.add(loginItem);
		fileMenu.add(createItem);
		fileMenu.add(logoutItem);
		fileMenu.add(backupItem);
		fileMenu.add(loadFromBackupItem);
		
		editMenu.add(editItem);
		editMenu.add(addItem);
		editMenu.add(deleteItem);
		
		menubar.add(fileMenu);
		menubar.add(editMenu);
		
		
		add(topPanel, BorderLayout.NORTH);
		add(cardPanel, BorderLayout.CENTER);
		cardPanel.setLayout(cardLayout);
		cardPanel.add(centerPanel,"Center Panel");
		cardPanel.add(startingInformationPanel,"Information Panel");
		cardPanel.setVisible(true);
		centerPanel.add(tablePanel, BorderLayout.CENTER);
		centerPanel.add(editPanel, BorderLayout.CENTER);
		// editPanel.setBackground(Color.WHITE);
		add(bottomPanel, BorderLayout.SOUTH);
		informationLabel.setFont(new Font(Font.SANS_SERIF , Font.PLAIN , 20));
		String info = "<html>Welcome to password manager.<br>" + 
		     "=>Here is a quick guidline on how to use this software.<br>" +
			 "&nbsp&nbsp==> Add - Click on Add button to add information of a new account.<br>" +
		     "&nbsp&nbsp==> Edit - Select an entry from table and click on Edit button to edit an existing account.<br>" +
			 "&nbsp&nbsp==> Delete - Select an entry from table and click on Delete button to delete the entry.<br>" +
		     "&nbsp&nbsp==> Search - Searches all entries by Website name. <br>" +
			 "&nbsp&nbsp==> Clear Search - Clear search to see all website accounts.<br>" +
		     "&nbsp&nbsp==> Logout - to logout from software.<br>" +
			 "=> Special features: <br>"+
		     "&nbsp&nbsp==> Double click on password field and the password will be copied to your clipboard.<br>" +
			 "&nbsp&nbsp==> Your clipboard will be cleared in 10 seconds for security.<br>" +
		     "&nbsp&nbsp==> Double click on URL field and you will be navigated to that url in default browser.<br></html>";
		informationLabel.setText(info);
		informationPanel.add(informationLabel);
		
		String startupInfo = "<html><center> <h1><b><center>Welcome to Password Manager </center></b></h1><br/>" + 
				"<h2><center><i> A place where you can manage all your passwords. </i></center></h2><br/>"+
				"Tired of clicking on forget password every now and then? Password Manager is one short solution for you.<br/>" +
				"Now you don't need to worry about forgetting your passwords. You can save them in this application and get them on a single click.<br/>" +
				"We know how frustrating it is to get a weak password notification everytime you create an account.<br/>" +
				"Now you don't need to worry whether your password is strong enough to create an account on the website.<br/>" +
				"As we provide you a way to generate a randomized password of desired length.<br/>" +
				"All your data is stored in a secure way so that no one can read it even if he/she gets access to your desktop.<br/>" +
				"No need to worry about data leaks over the internet as all your data is stored securely on your computer itself.<br/>" +
				" </center></html>";
		
		startingInformationLabel.setText(startupInfo);
		startingInformationLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		startingInformationPanel.add(startingInformationLabel);
		
		editPanel.add(informationPanel);
		
		editPanel.setVisible(false);
		
		searchTextField = new JTextField(15);
		searchTextField.setPreferredSize(new Dimension(50, 30));
		topPanel.add(searchTextField);

		searchButton = new JButton("Search");
		searchButton.setPreferredSize(new Dimension(100, 50));
		topPanel.add(searchButton);

		clearButton = new JButton("Clear Search");
		clearButton.setPreferredSize(new Dimension(150, 50));
		topPanel.add(clearButton);


		bottomTextArea = new JTextArea(2, 15);
		bottomPanel.add(bottomTextArea, BorderLayout.CENTER);

		bottomTextArea
				.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		bottomTextArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		bottomTextArea.setEditable(false);

		this.setControlStatus(false);

		table = new JTable(data, columnNames);
		tablePanel.add(new JScrollPane(table));
		this.setJMenuBar(menubar);
		this.setupListeneres();

	}

	

//	public void addComp(JPanel thePanel, Checkbox comp, int xPos, int yPos, int compWidth, int compHeight, int place,
//			int stretch) {
//
//		GridBagConstraints gridConstraints = new GridBagConstraints();
//
//		gridConstraints.gridx = xPos;
//
//		gridConstraints.gridy = yPos;
//
//		gridConstraints.gridwidth = compWidth;
//
//		gridConstraints.gridheight = compHeight;
//
//		gridConstraints.weightx = 100;
//
//		gridConstraints.weighty = 100;
//
//		gridConstraints.insets = new Insets(5, 5, 5, 5);
//
//		gridConstraints.anchor = place;
//
//		gridConstraints.fill = stretch;
//
//		thePanel.add(comp, gridConstraints);
//
//	}

	public void setupListeneres() {
		createItem.addActionListener(new ActionEventHandler(testGui.this));
				

		loginItem.addActionListener(new ActionEventHandler(testGui.this));

		addItem.addActionListener(new ActionEventHandler(testGui.this));
		editItem.addActionListener(new ActionEventHandler(testGui.this));
				
		deleteItem.addActionListener(new ActionEventHandler(testGui.this));
		searchButton.addActionListener(new ActionEventHandler(testGui.this));
		clearButton.addActionListener(new ActionEventHandler(testGui.this));
		logoutItem.addActionListener(new ActionEventHandler(testGui.this));
		backupItem.addActionListener(new  ActionEventHandler(testGui.this));
		loadFromBackupItem.addActionListener(new ActionEventHandler(testGui.this));

	}

	public void setupJTableSelection() {
		
		table.addMouseListener( new MouseEventHandler(this.table , this.bottomTextArea , this.timer));
		
	}

	public void refreshTable(String search) {

		while (this.model.getRowCount() > 0) {
			model.removeRow(0);
		}
		fetchData(search);
		// Delete all existing data
		for (int i = 0; i < data.length; i++) {
			model.addRow(data[i]);
		}
		table.setModel(model);

	}

	public void fetchData(String search) {
		int i = 0;
		int noOfLoginDetails;
		List<LoginDetails> loginList = Authorization.getCurrentUser().getUserLoginDetailsData().getLoginDetailsList();
		if (search.equals(null) || search.equals("")) {
			noOfLoginDetails = loginList.size();
		} else {
			loginList = loginList.stream().filter(p -> p.getLoginWebsiteName().contains((search))).collect(Collectors.toList());
			noOfLoginDetails = loginList.size();
		}
		data = new Object[noOfLoginDetails][7];
		for (LoginDetails detail : loginList) {

			if (detail != null) {
				data[i][0] = i + 1;
				data[i][1] = detail.getLoginWebsiteName();
				data[i][2] = detail.getLoginUsername();
				data[i][3] = detail.getLoginPassword();
				data[i][4] = detail.getLoginURL();
				data[i][5] = detail.getComments();
				data[i][6] = detail.getIndex();
			} else {
				System.out.println("Detail = null");
			}
			i++;
		}

	}

//	public String generatePassword(int l) {
//		final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz@#$%&";
//		SecureRandom rnd = new SecureRandom();
//
//		StringBuilder sb = new StringBuilder(l);
//		for (int i = 0; i < l; i++)
//			sb.append(AB.charAt(rnd.nextInt(AB.length())));
//		return new String(sb);
//	}

	public void setControlStatus(boolean state) {
		createItem.setEnabled(!state);
		loginItem.setEnabled(!state);
		editItem.setEnabled(state);
		addItem.setEnabled(state);
		deleteItem.setEnabled(state);
		logoutItem.setEnabled(state);
		tablePanel.setVisible(state);
		bottomPanel.setVisible(state);
		clearButton.setVisible(state);
		searchButton.setVisible(state);
		searchTextField.setVisible(state);
		informationPanel.setVisible(state);
		editPanel.setVisible(state);
		if(state) {
			cardLayout.show(cardPanel, "Center Panel");
		}
		else {
			cardLayout.show(cardPanel, "Information Panel");
		}
		refreshEditContainerPanel();
		backupItem.setEnabled(state);
		loadFromBackupItem.setEnabled(state);
	}
	
	public void refreshEditContainerPanel() {
		editContainerPanel.removeAll();
		editContainerPanel.setVisible(false);
	}

	
}
