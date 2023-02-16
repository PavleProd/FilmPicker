package tmdbapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Client thread that communicates with TMDB API
 * @author pavle
 * 
 */
public class TMDBClient implements Runnable {
	private static final String apiKey = "api_key=2330bf4c6a895a88a713da945dfd0dad";
	private static final String getDataMethod = "GET";
	private StringBuilder request;
	private StringBuilder result;
	
	public TMDBClient(StringBuilder request) {
		this.request = request;
		result = new StringBuilder();
	}
	
	public URL createURL() {
		URL ret = null;
		try {
			 ret = new URL(request + "?" + apiKey);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	public static StringBuilder createTMDBRequest(StringBuilder request) {
		TMDBClient client = new TMDBClient(request);
		Thread thread = new Thread(client);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return client.result;
	}
	
	public StringBuilder getResult() {
		return result;
	}

	@Override
	public void run() {
		URL requestURL = createURL();
		try {
			HttpURLConnection connection = (HttpURLConnection)requestURL.openConnection();
			connection.setRequestMethod(getDataMethod);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while(true) {
				String input = in.readLine();
				if(input == null) break;
				result.append(input);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
