package tmdbapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Client class that communicates with TMDB API and stores resulting raw String in JSON format
 * @author Pavle
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
	
	// returns URL for HTTP communication with database from given request
	private URL createURL() {
		URL ret = null;
		try {
			 ret = new URL(request + "?" + apiKey);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * Creates TMDB client thread that communicates with TMDB database and waits for it to write resulting string
	 * in client.result string
	 * @param request String from a specific request type
	 * @return results from communicating with the database
	 */
	public static String createTMDBRequest(StringBuilder request) {
		TMDBClient client = new TMDBClient(request);
		Thread thread = new Thread(client);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return client.result.toString();
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
			
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
