package org.dcu.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.dcu.admin.net.LoginRequest;

public class Login extends JDialog {
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Create the dialog.
	 */
	public Login() {
		setTitle("Admin Login");
		setBounds(100, 100, 450, 134);
		getContentPane().setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(10, 54, 147, 20);
		getContentPane().add(usernameField);
		usernameField.setColumns(10);
		usernameField.requestFocus();
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(324, 53, 100, 23);
		getContentPane().add(loginButton);
		
		JLabel lblPleaseEnterYour = new JLabel("Please enter your administrator username and password below");
		lblPleaseEnterYour.setBounds(10, 11, 414, 14);
		getContentPane().add(lblPleaseEnterYour);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(167, 54, 147, 20);
		getContentPane().add(passwordField);
	
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				
				new LoginRequest(Login.this, username, password).login();
			}
		});
	}
}
