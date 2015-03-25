package ziegler.network;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

public class Client {

	public static void main(String args[]) {
		Socket socket = null;
		try {
			// use localhost - talking to yourself
			socket = new Socket("localhost", 3765);
			// will block at the constructor for the connection - waiting for
			// operating system to initiate the connection

			// write to the socket
			OutputStream out = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(out);

			writer.println("Hello there!");
			writer.println("Hi there!");
			writer.println("Hello and hi there!");

			// now actually send it
			writer.flush();

		}
		catch (IOException e) {
			e.printStackTrace();
		}
		// will always execute exception no matter if works or catches exception
		finally {
			IOUtils.closeQuietly(socket);
		}
	}
}
