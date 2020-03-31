package rednecktools;

//A simple console Whois query app
//Author: Isaac Hopper

import java.io.*;
import java.net.*;


public class hoodat {
	
	// Initialize variables
	static String domainName;
	final static String whoisServer	= "whois.verisign-grs.com";
	final static int port			= 43;
	static String serverResponse	= null;
	
	public hoodat (String domain) {
		
		//Initialize the hoodat object
		domainName = domain;
		
	}
	
	public String executeQuery() throws Exception {
	
		//Update the user
		serverResponse = "*** Performing Whois Lookup on " + domainName + " ***\n\n";

		//Connect to the server
		Socket socket = new Socket(whoisServer, port);

		//initialize input and output streams
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));

		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(socket.getOutputStream()));

		//Send the query
		writer.write(domainName + "\n");
		writer.flush();

		//Initialize variable for reading data
		String line = null;

		//debug
		//System.out.println("DEBUG: " + (line = reader.readLine()));

		//Read the response
		while((line = reader.readLine()) != null) {
			//System.out.println(line);
			// Build the return string
			serverResponse = serverResponse + line + "\n";
		}

		//Close the socket and streams
		reader.close();
		writer.close();
		socket.close();

		//Notify the user
		//System.out.println("*** Connection closed by client ***");
		return serverResponse;
		
	}//end main

}//end class