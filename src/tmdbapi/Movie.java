package tmdbapi;

import java.util.HashMap;


public class Movie extends TMDBObject {
	private int id;
	private String MovieName;
	private StringBuilder unparsedData; // TODO: ukloni
	public Movie(int id) {
		super("movie");
		this.id = id;
		request.append(id);
	}
	
	public StringBuilder getUnparsedData() {
		return unparsedData;
	}
	
	@Override
	public void getRequestString() {
		unparsedData = sendRequest();
		
	}
	
	public String getName() {
		return MovieName;
	}
	
	public void parseData() {
		//ObjectMapper mapper = new ObjectMapper();
	}
}
