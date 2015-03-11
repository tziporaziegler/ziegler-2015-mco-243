package ziegler.network;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

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
			// do finally instead of doing socket.close both in try and catch
			try {
				// Operating System is managing your socket. When ends, will eventually clean up -
				// but till then, OS doesn't know what to do...so have resource leak until close
				// socket.
				socket.close();
				// this exception doesn't really do anything because already
				// close and now don't care if throws exception
			}
			catch (IOException e) {
				e.printStackTrace();
			}

			// instead of above thousand lines, can just use Apache library and
			// do IOUtils.closeQuietly(socket);
		}
	}
}
