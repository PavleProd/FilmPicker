package tmdbapi;

public class Request {
	private String name;
	private String query;
	private String pageNum;
	
	public Request(String name) {
		this(name, "", 0);
	}
	
	public Request(String name, String query) {
		this(name, query, 0);
	}
	
	public Request(String name, String query, int pageNum) {
		this.name = name;
		this.query = (query == "" ? "" : "&query=" + query.replace(' ', '+')); // to match query pattern
		this.pageNum = (pageNum == 0 ? "" : "&page=" + pageNum);
	}

	public String getName() {
		return name;
	}

	public String getQuery() {
		return query;
	}

	public String getPageNum() {
		return pageNum;
	}
	
}
