package tmdbapi;

public abstract class TMDBObject {
	private String name;
	protected StringBuilder request = new StringBuilder("https://api.themoviedb.org/3/");
	
	public TMDBObject(String name) {
		this.name = name;
		request.append(name + "/");
	}
	
	public abstract void getRequestString();
	
	// Sends request to the database and returns requested result.
	public StringBuilder sendRequest() {
		return TMDBClient.createTMDBRequest(request);
	}
}
