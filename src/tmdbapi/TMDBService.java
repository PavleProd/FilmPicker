package tmdbapi;

import tmdbobjects.*;
import tmdbapi.TMDBRequestHandler;

public class TMDBService {
	public static Movie getMovie(int id) {
		String name = "movie/" + id;
		return (Movie)TMDBRequestHandler.request(Movie.class, new Request(name));
	}
	
	public static Movie[] searchMovieByName(String movieName) {
		String name = "search/movie";
		return (Movie[])TMDBRequestHandler.request(Movie.class, new Request(name, movieName));
	}
}
