package rednecktools;

//A simple console Whois query app
//Author: Isaac Hopper

import java.io.*;
import java.net.*;


public class hoodat {

	public static void main(String[] args) throws Exception {
		
		//Initialize variables and store arguments
		String domainName 	= args[0];
		String whoisServer	= "whois.verisign-grs.com";
		int port			= 43;

		//Update the user
		System.out.println("*** Performing Whois Lookup on " + domainName + " ***");

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
		System.out.println("DEBUG: " + (line = reader.readLine()));

		//Read the response
		while((line = reader.readLine()) != null) {
			System.out.println(line);
		}

		//Close the socket and streams
		reader.close();
		writer.close();
		socket.close();

		//Notify the user
		System.out.println("*** Connection closed by client ***");

	}//end main

}//end class