package rednecktools;

import java.io.*;
import java.net.*;
import java.net.Socket;

public class wutdis {

	// Declare variables
	static int startPort 	= 0; // Begin the scan at port 0, unless otherwise specified.
	static int endPort		= 100; // End the scan at port 10,000, unless otherwise specified. 
	static String target	= null; // The target server.
	static String serverResponse	= null; // The server response string.
	static MainFrame window; // Variable to receive the window object.
	
	public wutdis(String host, int start, int end, MainFrame frame) throws IOException {
		
		// Initialize variables
		target		= host;
		startPort	= start;
		endPort		= end;
		window		= frame;
	}
	
	public void scan() {
		int currentPort = startPort;
		String msg		= null;
		String repl 	= "repl";
		String append	= "append";
		
		// Update the user
		msg = "*** Performing scan on " + target + " ***\n\n";
		window.updateUser(msg, repl);
		
		// Run the scan loop
		for(int count = currentPort; count <= endPort; count++) {		
		
			// Connect to the server
			try(Socket socket = new Socket(target, currentPort)) {
		
				// Initialize input stream to receive the server response.
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
		
				// Initialize variable for reading data
				String line = null;
		
				// Read the response and display it for the user.
				while((line = reader.readLine()) != null && (line = reader.readLine()) != "") {
					msg = target + ":" + currentPort + " (open) -> " + line + "\n";
					window.updateUser(msg, append);
				}
				
				// Close the socket and stream
				reader.close();
				socket.close();
				
			// Throw errors	
			} catch (UnknownHostException ex) {
				msg = "Server not found: " + ex.getMessage();
				window.updateUser(msg, append);
	 
	        } catch (IOException ex) {
	        	// In the event of an IOException, the port is unreachable (i.e. closed).
	        	msg = target + ":" + currentPort + " (closed)\n";
	        	window.updateUser(msg, append);
	        }// End try/catch
			
			// Advance the port counter
			currentPort++;
			
		}// End for loop

		// Notify the user that the scan is complete
		msg = "\n*** Scan completed ***";
		window.updateUser(msg, append);
		
	}// End 


}// End class
