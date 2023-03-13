package test;

import java.util.List;

import tmdbapi.TMDBService;
import tmdbobjects.Movie;

public class Main {
	public static void main(String[] args) {
		
		/*List<Movie> movieList = Service.getTopRatedMovies(250);
		
		for(Movie movie : movieList) {
			System.out.println(movie.getTitle());
		}*/
		
		Movie movie = TMDBService.getMovie(2);
		System.out.println(movie);
		
		//Movie movie = CommonService.getRandomMovie(7.0);
		//System.out.println(movie);
	}
	
}
