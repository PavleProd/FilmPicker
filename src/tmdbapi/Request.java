package tmdbapi;

public class Request {
	private String name;
	private String query;
	
	public Request(String name) {
		this(name, "");
	}
	
	public Request(String name, String query) {
		this.name = name;
		this.query = (query == "" ? "" : "&" + query.replace(' ', '+')); // to match query pattern
	}

	public String getName() {
		return name;
	}

	public String getQuery() {
		return query;
	}
	
	
}
