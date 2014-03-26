package org.dcu.admin;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.dcu.admin.net.LoginRequest;
import org.dcu.util.Encryption;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminLogin extends JDialog {
	
	/**
	 * Dunno what this is for, just removes warning lol.
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel infoLabel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdminLogin dialog = new AdminLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AdminLogin() {
		setTitle("Admin Area");
		setModal(true);
		setBounds(100, 100, 407, 139);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		addComponents();
		addBounds();
		addEvents();
	}
	
	/**
	 * Initializes the components and adds them to the application.
	 */
	private void addComponents() {
		infoLabel = new JLabel("If you are not the owner of this server, please disregard this area.");
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setText("admin");
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setText("test");
		loginButton = new JButton("Login");
		
		getContentPane().add(infoLabel);
		getContentPane().add(usernameField);
		getContentPane().add(passwordField);
		getContentPane().add(loginButton);
	}
	
	/**
	 * Sets the location of the components.
	 */
	private void addBounds() {
		infoLabel.setBounds(10, 11, 371, 42);
		usernameField.setBounds(10, 64, 141, 20);
		passwordField.setBounds(161, 64, 141, 20);
		loginButton.setBounds(312, 63, 69, 23);
	}
	
	/**
	 * Adds the component events.
	 */
	private void addEvents() {
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				
				new LoginRequest(AdminLogin.this, username, Encryption.stringToMD5(password)).login();
			}
		});
	}
}
