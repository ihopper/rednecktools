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
		setSize(800, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the control layout
		FlowLayout flo = new FlowLayout();
		setLayout(flo);

		// Add the inteface controls
		cboScan = new JComboBox();
			cboScan.addItem("Whois");
			cboScan.addItem("Ping");
			cboScan.addItem("Scan");
			cboScan.addItem("Traceroute");
		add(cboScan);

		lblURL = new JLabel("Lookup Address:");
		add(lblURL);

		txtURL = new JTextField(30);
		add(txtURL);

		btnLookup = new JButton("Lookup");
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
		
		// Make the frame visible
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event) {
		txtResults.setText("Performing " + cboScan.getSelectedItem() + " on host: " + txtURL.getText() + "\n");
	}

	public static void main(String[] args) {
		// Instantiate the window object
		MainFrame window = new MainFrame();

		// Center the window on the screen
		window.setLocationRelativeTo(null); 
	}
}


