package org.dcu.admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.dcu.patch.Generator;
import org.dcu.util.Language;

public class AdminPanel extends JFrame {
	
	/**
	 * A patch generator to use.
	 */
	Generator generator;
	
	/**
	 * The username that logged in.
	 */
	private String username;
	
	/**
	 * The local location of the user's cache.
	 */
	private String localCache = "";
	
	/**
	 * The remove location of the user's cache.
	 */
	private String remoteCache = "";
	
	private JPanel contentPane;
	private JLabel welcomeLabel;
	private JSeparator separator;
	private JLabel localCacheLabel;
	private JTextField localCacheField;
	private JButton browseButton;
	private JLabel remoteCacheLabel;
	private JTextField remoteCacheField;
	private JButton generateButton;

	/**
	 * Create the frame.
	 */
	public AdminPanel(String username) {
		setResizable(false);
		setTitle("DCU Admin Panel");
		setBounds(100, 100, 450, 190);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.username = username;
		addComponents();
		addBounds();
		addEvents();
	}
	
	/**
	 * Initializes the components and adds them to the application.
	 */
	private void addComponents() {
		welcomeLabel = new JLabel("Welcome back, " + username);
		welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		separator = new JSeparator();
		localCacheLabel = new JLabel("Local cache location:");
		remoteCacheLabel = new JLabel("Remote cache location:");
		localCacheField = new JTextField();
		localCacheField.setEditable(false);
		localCacheField.setColumns(10);
		remoteCacheField = new JTextField();
		remoteCacheField.setColumns(10);
		browseButton = new JButton("Browse");
		generateButton = new JButton("Generate Patch");
		
		contentPane.add(welcomeLabel);
		contentPane.add(separator);
		contentPane.add(localCacheLabel);
		contentPane.add(localCacheField);
		contentPane.add(browseButton);	
		contentPane.add(remoteCacheLabel);
		contentPane.add(remoteCacheField);
		contentPane.add(generateButton);
	}
	
	/**
	 * Sets the location of the components.
	 */
	private void addBounds() {
		welcomeLabel.setBounds(10, 11, 414, 37);
		separator.setBounds(0, 59, 444, 2);
		localCacheLabel.setBounds(10, 72, 125, 14);
		remoteCacheLabel.setBounds(10, 103, 135, 14);
		localCacheField.setBounds(145, 69, 199, 20);
		remoteCacheField.setBounds(145, 100, 289, 20);
		browseButton.setBounds(354, 68, 80, 23);
		generateButton.setBounds(303, 131, 131, 23);
	}
	
	/**
	 * Adds the component events.
	 */
	private void addEvents() {
		//Browse
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectCacheLocation();
			}
		});
		
		//Generate
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (localCacheField.getText().equals(Language.EMPTY_STRING) || remoteCacheField.getText().equals(Language.EMPTY_STRING)) {
					JOptionPane.showMessageDialog(null, "Please fill out all fields.", "Oops!", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				remoteCache = remoteCacheField.getText();
				generator = new Generator(localCache);
				try {
					generator.createPatch();
				} catch (IOException e) {
					e.printStackTrace();
				}
				//TODO: Generate method
			}
		});
	}
	
	/**
	 * Folder browser dialog for users to select their cache location.
	 */
	private void selectCacheLocation() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    int returnVal = chooser.showOpenDialog(this);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	localCacheField.setText(chooser.getSelectedFile().getPath());
	        localCache = chooser.getSelectedFile().getPath();
	    }
	}
}
