package org.dcu;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.dcu.admin.AdminLogin;
import org.dcu.updater.UpdateListener;
import org.dcu.updater.VersionControl;
import org.dcu.util.Language;

/**
 * The main window of DCU.
 * @author Stephen Andrews
 */
public class Application extends JFrame implements UpdateListener {
	
	/**
	 * Application Version
	**/
	public static final String VERSION = "0.2";
	
	
	/**
	 * Download page for DCU
	 */
	private static final String DOWNLOAD_PAGE = "http://www.derithium.com/dcu/dcu.jar";
	
	/**
	 * The name of the server.
	 */
	private String serverName;
	
	/**
	 * A version controller.
	 */
	private VersionControl vc;
	
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem aboutMenuItem;
	private JMenuItem exitMenuItem;
	private JMenu adminMenu;
	private JMenuItem openPanelMenuItem;
	private JProgressBar progressBar;
	private JLabel infoLabel;
	private JButton playButton;
	private JLabel statusLabel;
	private JMenuItem infoMenuItem;

	/**
	 * Launch the application.
	 * TODO: Remove this when finished.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application frame = new Application("Derithium");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Application(String serverName) {
		setResizable(false);
		setTitle("Client Updater - Powered by Derithium");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 438, 210);
		setLocationRelativeTo(null);
		this.serverName = serverName;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addComponents();
		addBounds();
		addEvents();
		
		
		checkForUpdates();
	}
	
	/**
	 * Initializes the components and adds them to the application.
	 */
	private void addComponents() {
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		aboutMenuItem = new JMenuItem("About");
		exitMenuItem = new JMenuItem("Exit");
		adminMenu = new JMenu("Admin");
		openPanelMenuItem = new JMenuItem("Admin Panel");
		infoMenuItem = new JMenuItem("Updater Information");
		progressBar = new JProgressBar();
		infoLabel = new JLabel("Sit tight while we check for updates...");
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		playButton = new JButton("Play");
		statusLabel = new JLabel("Client status: Undetermined");
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		contentPane.add(menuBar);
		menuBar.add(fileMenu);
		fileMenu.add(aboutMenuItem);
		fileMenu.add(exitMenuItem);
		menuBar.add(adminMenu);
		adminMenu.add(openPanelMenuItem);
		adminMenu.add(infoMenuItem);
		contentPane.add(progressBar);
		contentPane.add(infoLabel);
		contentPane.add(playButton);
		contentPane.add(statusLabel);
	}
	
	/**
	 * Sets the location of the components.
	 */
	private void addBounds() {
		menuBar.setBounds(0, 0, 432, 31);
		progressBar.setBounds(9, 144, 414, 31);
		infoLabel.setBounds(9, 42, 414, 31);
		playButton.setBounds(161, 102, 110, 31);
		statusLabel.setBounds(9, 77, 414, 14);
	}
	
	/**
	 * Adds the component events.
	 */
	private void addEvents() {
		//About
		aboutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "This client updater is powered by Derithium.", "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		//Admin
		openPanelMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AdminLogin login = new AdminLogin();
				login.setVisible(true);
			}
		});
		
		//Info
		infoMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Derithium Client Updater"
						//+ Language.NEW_LINE + "Build #: " + vc.getLocalVersion().getBuild()
						+ Language.NEW_LINE + "Configured for server: " + serverName
						+ Language.NEW_LINE
						+ Language.NEW_LINE + "Submit bugs to Stephen@derithium.com", 
						"Client Info", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
	
	/**
	 * Checks to see if DCU is up to date.
	 */
	private void checkForUpdates() {
		vc = new VersionControl(this);
		vc.checkForUpdates();
	}
	
	public static float getVersion() {
		return Float.parseFloat(VERSION);
	}

	@Override
	public void updateRequired(boolean updateRequired) {
		if(updateRequired) {
			int response = JOptionPane.showOptionDialog(this, "A new version of DCU has been released!"
					+ Language.NEW_LINE + "Release Notes:"
					+ Language.NEW_LINE + vc.getLatestVersion().getReleaseNotes()
					+ Language.NEW_LINE 
					+ Language.NEW_LINE + "Would you like to be taken to the download page?",
					"A new version is available!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, 
					null, null, null);
			
			if(response == JOptionPane.YES_OPTION) {
			    try {
			        Desktop.getDesktop().browse(new URL(DOWNLOAD_PAGE).toURI());
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
		}
	}

}
