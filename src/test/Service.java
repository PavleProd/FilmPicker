package test;

import java.util.List;

import tmdbapi.TMDBService;
import tmdbobjects.Movie;

/**
 * Class for providing service for most common TMDB requests that needs multiple TMDBService calls
 * @author pavle
 *
 */
public class Service {
	/**
	 * Prints titles of "num" top rated movies of all time
	 * @param num number of top rated movies to be printed
	 */
	public static void getTopRatedMovies(int num) {
		List<Movie> movieList = null;
		
		int currPageNum = 1, currPageListIndex = 0;
		for(int i = 0; i < num; i++) {
			if(i%20 == 0) {
				movieList = TMDBService.getTopRatedMovies(currPageNum++);
				currPageListIndex = 0;
			}
			System.out.println(i+1 + "." + movieList.get(currPageListIndex++).getTitle());
		}
	}
}
