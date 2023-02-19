package tmdbapi;

import tmdbobjects.*;

import java.util.List;

import com.fasterxml.jackson.databind.JavaType;

public class TMDBService {
	// returns movie with given id
	public static Movie getMovie(int id) {
		String name = "movie/" + id;
		JavaType objType = TMDBRequestHandler.getMapper().getTypeFactory().constructType(Movie.class);
		return (Movie)TMDBRequestHandler.request(objType, new Request(name));
	}
	
	/**
	 * Finds 20 movies(pageSize) that contain movieName and are on the page pageNum
	 * @param movieName name of the movie
	 * @param pageNum page number, indexed from 1
	 * @return ArrayList of size 20
	 */
	@SuppressWarnings("unchecked")
	public static List<Movie> searchMovieByName(String movieName, int pageNum) {
		if(pageNum <= 0) return null;
		
		String name = "search/movie";
		JavaType objType = TMDBRequestHandler.getMapper().getTypeFactory().constructParametricType(TMDBListWrapper.class, Movie.class);
		TMDBListWrapper<Movie> wrapper = (TMDBListWrapper<Movie>)TMDBRequestHandler.request(objType, new Request(name, movieName, pageNum));
		return (wrapper.getTotal_pages() >= pageNum ? wrapper.getResults() : null);
	}
	
}
