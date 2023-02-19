package test;

import java.util.List;

import tmdbapi.TMDBService;
import tmdbobjects.Movie;

class Wrapper<T> {
	public int num;
	public T[] res;
}

public class Main {
	public static void main(String[] args) {
		List<Movie> movieList = TMDBService.searchMovieByName("Drive", 1);
		
		for(Movie movie : movieList) {
			System.out.println(movie);
		}
		
		/*Movie movie = TMDBService.getMovie(64690);
		System.out.println(movie);*/
	}
	
}
