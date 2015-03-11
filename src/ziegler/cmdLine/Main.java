package ziegler.cmdLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

	public static void main(String args[]) {
		// spawning child process
		Process proc = null;
		try {
			// process giving back results
			// proc = Runtime.getRuntime().exec("cmd.exe /c echo %cd%"); //prints working directory
			proc = Runtime.getRuntime().exec("Java -cp ./bin ziegler.network.Server");
			Runtime.getRuntime().exec("Java -cp ./bin ziegler.network.Client");

			// giving you back results
			// can do Error Stream if want to get Errors instead of Input
			InputStream inputStream = proc.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			// printing out results
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}