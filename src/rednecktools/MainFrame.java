package rednecktools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements ActionListener {

	// Initialize objects
	JComboBox<String> cboScan;
	JLabel lblURL;
	JTextField txtURL;
	JButton btnLookup;
	static JTextArea txtResults;

	public MainFrame() {
		// Setup the frame
		super("Redneck Networking Tools 1.0");
		setSize(720, 425);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the control layout
		FlowLayout flo = new FlowLayout();
		setLayout(flo);

		// Add the interface controls
		cboScan = new JComboBox<String>();
			cboScan.addItem("Hoodat"); // Whois
			cboScan.addItem("Yoohoo"); // Ping
			cboScan.addItem("Wutdis"); // Port Scan
			cboScan.addItem("Weredey"); // Traceroute
		add(cboScan);

		lblURL = new JLabel("Address:");
		add(lblURL);

		txtURL = new JTextField(30);
		add(txtURL);

		btnLookup = new JButton("Go");
		btnLookup.addActionListener(this);
		add(btnLookup);

		// Text area with scrollbars
		txtResults = new JTextArea("", 22, 62);
		txtResults.setLineWrap(true);
		txtResults.setWrapStyleWord(true);

		JScrollPane scrollResults = new JScrollPane(txtResults, 
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollResults);
		
		// Set the default action for <enter> key to press the button
		getRootPane().setDefaultButton(btnLookup);
		
		// Make the frame visible
		setVisible(true);
	}
	
	// Check for button click, then do something
	public void actionPerformed(ActionEvent event) {
		String msg			= null;
		String repl 		= "repl";
		String append		= "append";	
		String actionType 	= cboScan.getSelectedItem().toString();
		String domain 		= txtURL.getText().toString();
		String serverReply 	= null;
		
		if(actionType == "Hoodat") {
			try {
				// Instantiate a new hoodat object and initialize
				hoodat scanner = new hoodat(domain);
				msg = scanner.executeQuery();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				msg = e.toString();
				this.updateUser(msg, append);
			}		
			// Update the user
			this.updateUser(msg, append);
			
		} else if (actionType == "Wutdis") {
			try {
				// Instantiate a new wutdis object and initialize
				wutdis scanner = new wutdis(domain, 1, 10, this);
				scanner.scan();
			} catch (Exception e) {
				msg = e.toString();
				this.updateUser(msg, append);
			}
		}
	}

	// Method for updating the results window
	// method types: append, repl
	public void updateUser(String msg, String method) {	
		if(method == "repl") {
			txtResults.setText(msg);
		} else if(method == "append") {
			txtResults.append(msg);
		}
	}
	
	public static void main(String[] args) {
		// Instantiate the window object
		MainFrame window = new MainFrame();

		// Center the window on the screen
		window.setLocationRelativeTo(null); 
	}
}


