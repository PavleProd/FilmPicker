package tmdbapi;

import tmdbobjects.*;
import tmdbapi.TMDBRequestHandler;

public class TMDBService {
	public static Movie getMovie(int id) {
		String name = "movie/" + id;
		return (Movie)TMDBRequestHandler.request(Movie.class, name);
	}
}
