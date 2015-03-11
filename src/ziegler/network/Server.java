package ziegler.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String args[]) {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(3765);

			// every person is new socket - loop allows multiple simultanious connections
			while (true) {
				// block at constructor until get something to read = the listener
				Socket socket = serverSocket.accept();

				// when find one, latched on with InputStream

				// InputStream reads in bytes
				InputStream in = socket.getInputStream();

				// wrap input stream in reader so can read String instead of bytes
				// (can also wrap it in Scanner)
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));

				// read in a whole line
				String line;

				while ((line = reader.readLine()) != null) {
					// print the line recieve from client
					System.out.println(line);
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
}
