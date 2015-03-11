package ziegler.threads;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImgDownloadThread extends Thread {
	private URL url;
	private String filename;

	public ImgDownloadThread(URL url, String filename) {
		this.url = url;
		this.filename = filename;
	}

	@Override
	public void run() {
		try {
			byte[] byteChunk = new byte[50];
			FileOutputStream out = new FileOutputStream(filename);
			InputStream input = url.openStream();
			int n = 0;
			while ((n = input.read(byteChunk)) > 0) {
				out.write(byteChunk, 0, n);
			}
			input.close();
			out.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}