package tmdbobjects;

public class Movie extends TMDBObject {
	private int id;
	private String imdb_id;
	private String original_title;
	private String overview;
	private String poster_path;
	private double popularity;
	private String release_date;

	
	@Override
	public String toString() {
		StringBuilder resultString = new StringBuilder();
		resultString.append("Title: " + original_title + "\n");
		resultString.append("Release date: " + release_date + "\n");
		resultString.append("Popularity: " + popularity + "\n");
		return resultString.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImdb_id() {
		return imdb_id;
	}

	public void setImdb_id(String imdb_id) {
		this.imdb_id = imdb_id;
	}

	public String getOriginal_title() {
		return original_title;
	}

	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public double getPopularity() {
		return popularity;
	}

	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	
}
