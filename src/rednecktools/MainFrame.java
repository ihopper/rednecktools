package rednecktools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements ActionListener {

	// Initialize objects
	JComboBox cboScan;
	JLabel lblURL;
	JTextField txtURL;
	JButton btnLookup;
	JTextArea txtResults;

	public MainFrame() {
		// Setup the frame
		super("Redneck Networking Tools 1.0");
		setSize(720, 425);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		// Set the control layout
		FlowLayout flo = new FlowLayout();
		setLayout(flo);

		// Add the intefrace controls
		cboScan = new JComboBox();
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
	
	public void actionPerformed(ActionEvent event) {
		txtResults.setText("Performing " + cboScan.getSelectedItem() + " on host: " + txtURL.getText() + "\n");
		
		String actionType = cboScan.getSelectedItem().toString();
		String domain = txtURL.getText();
		String serverReply = null;
		
		if(actionType == "Hoodat") {
			try {
				serverReply = hoodat.executeQuery(domain);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				txtResults.setText(e.toString());
			}
			txtResults.setText(serverReply); 
		}
	}

	public static void main(String[] args) {
		// Instantiate the window object
		MainFrame window = new MainFrame();

		// Center the window on the screen
		window.setLocationRelativeTo(null); 
	}
}


