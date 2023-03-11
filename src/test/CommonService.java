package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tmdbapi.TMDBService;
import tmdbobjects.Movie;

/**
 * Class for providing service for most common TMDB requests that needs multiple TMDBService calls
 * @author pavle
 *
 */
public class CommonService extends Service {
	/**
	 * Returns Movie objects of "num" top rated movies of all time
	 * @param num number of top rated movies to be printed
	 */
	public static List<Movie> getTopRatedMovies(int num) {
		List<Movie> movieList = new ArrayList<>();
		List<Movie> auxiliaryList = null;
		
		int currPageNum = 1, currPageListIndex = 0;
		for(int i = 0; i < num; i++) {
			if(i%20 == 0) {
				auxiliaryList = TMDBService.getTopRatedMovies(currPageNum++);
				currPageListIndex = 0;
				movieList.addAll(auxiliaryList);
			}
			//System.out.println(i+1 + "." + auxiliaryList.get(currPageListIndex++).getTitle());
		}
		
		return movieList;
	}
	
	/**
	 * Getting random movie by random picking movie ID. Other implementation: get all movies with >=MinScore, and then pick random
	 * TODO: test which implmentation is better
	 * @param minScore
	 * @return
	 */
	public static Movie getRandomMovie(double minScore) {
		int min = 0;
		int max = TMDBService.getLatestID();
		
		Random randomGenerator = new Random();
		Movie movie = null;
		while(movie == null) { // TODO: exception handle
			int movieID = randomGenerator.nextInt();
			movie = (Movie)TMDBService.getMovie(movieID);
		}
		
		return movie;
	}
}
