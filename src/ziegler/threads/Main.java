package ziegler.threads;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {

	public static void main(String[] args) {
		try {
			URL url = new URL("http://solidwallpapers.com/abstract-liquid-abstract-red-wallpaper-hd.html");

			ImgDownloadThread t1 = new ImgDownloadThread(url, "C:/Users/student1/Desktop/file1.jpg");
			url = (new URL(
					"http://moshlab.com/wp-content/uploads/2015/02/Blue-Glow-Abstract-Desktop-Background-Picture-14617.jpg"));
			ImgDownloadThread t2 = new ImgDownloadThread(url, "C:/Users/student1/Desktop/file2.jpg");
			url = new URL(
					"http://freedwallpaper.com/wp-content/uploads/2014/03/7-abstract-rainbows-digital-art-1080x1920.jpg");
			ImgDownloadThread t3 = new ImgDownloadThread(url, "C:/Users/student1/Desktop/file3.jpg");

			t1.start();
			t2.start();
			t3.start();

			t1.join();
			t2.join();
			t3.join();
		}
		catch (InterruptedException | MalformedURLException e) {
			e.printStackTrace();
		}

		System.out.println("done");
	}
}