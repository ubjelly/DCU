package org.dcu.admin;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JDialog {
	
	/**
	 * Dunno what this is for, just removes warning lol.
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel infoLabel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;

	/**
	 * Create the dialog.
	 */
	public Login() {
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
		usernameField.setText("Username");
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setText("Password");
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
				setVisible(false);
				AdminPanel ap = new AdminPanel("Stephen"); //TODO: Fix text boxes
				ap.setVisible(true);
			}
		});
	}
}
