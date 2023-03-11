package tmdbapi;

import tmdbobjects.*;

import java.util.List;

import com.fasterxml.jackson.databind.JavaType;

import test.Service;

public class TMDBService extends Service {
	/**
	 * Request example: https://api.themoviedb.org/3/movie/64690?api_key=<key>
	 * @param id
	 * @return movie with given id
	 */
	public static Movie getMovie(int id) {
		String name = "movie/" + id;
		// pravimo objType da bi ObjectMapper znao da prepozna genericku klasu
		JavaType objType = TMDBRequestHandler.getMapper().getTypeFactory().constructType(Movie.class);
		return (Movie)TMDBRequestHandler.request(objType, new Request(name));
	}
	
	/**
	 *
	 * Request example: https://api.themoviedb.org/3/search/movie?api_key=<key>&query=Drive&page=1
	 * @param movieName name of the movie
	 * @param pageNum page number, indexed from 1
	 * @return ArrayList of size 20(pageSize) containing movies that matches the name the most
	 */
	@SuppressWarnings("unchecked")
	public static List<Movie> searchMovieByName(String movieName, int pageNum) {
		if(pageNum <= 0) return null;
		movieName = movieName.replace(' ', '+');
		String suffix = "&query=" + movieName + "&page=" + pageNum;
		String prefix = "search/movie";
		JavaType objType = TMDBRequestHandler.getMapper().getTypeFactory().constructParametricType(TMDBListWrapper.class, Movie.class);
		TMDBListWrapper<Movie> wrapper = (TMDBListWrapper<Movie>)TMDBRequestHandler.request(objType, new Request(prefix, suffix));
		return (wrapper.getTotal_pages() >= pageNum ? wrapper.getResults() : null);
	}
	
	/**
	 * Request example: https://api.themoviedb.org/3/discover/movie?api_key=<key>&sort_by=popularity.desc&page=1
	 * @param pageNum page number, indexed from 1
	 * @return ArrayList of size 20(pageSize) containing most popular movies in descending order
	 */
	@SuppressWarnings("unchecked")
	public static List<Movie> getMostPopularMovies(int pageNum) {
		String prefix = "discover/movie";
		String suffix = "&sort_by=popularity.desc&page=" + pageNum;
		JavaType objType = TMDBRequestHandler.getMapper().getTypeFactory().constructParametricType(TMDBListWrapper.class, Movie.class);
		TMDBListWrapper<Movie> wrapper = (TMDBListWrapper<Movie>)TMDBRequestHandler.request(objType, new Request(prefix, suffix));
		return (wrapper.getTotal_pages() >= pageNum ? wrapper.getResults() : null);
	}
	
	/**
	 * Request example: https://api.themoviedb.org/3/discover/movie?api_key=<key>&sort_by=vote_average.desc&page=1&vote_count.gte=10000
	 * @param pageNum
	 * @return ArrayList of size 20(pageSize) containing top rated movies in descending order 
	 */
	@SuppressWarnings("unchecked")
	public static List<Movie> getTopRatedMovies(int pageNum) {
		String prefix = "discover/movie";
		String suffix = "&sort_by=vote_average.desc&page=" + pageNum + "&vote_count.gte=" + minVoteCount;
		JavaType objType = TMDBRequestHandler.getMapper().getTypeFactory().constructParametricType(TMDBListWrapper.class, Movie.class);
		TMDBListWrapper<Movie> wrapper = (TMDBListWrapper<Movie>)TMDBRequestHandler.request(objType, new Request(prefix, suffix));
		return (wrapper.getTotal_pages() >= pageNum ? wrapper.getResults() : null);
	}
	
	/**
	 * Request example: https://api.themoviedb.org/3/movie/latest?api_key=<key>
	 * @return id(max id) of the latest released movie
	 */
	public static int getLatestID() {
		String prefix = "movie/latest";
		String suffix = "";
		JavaType objType = TMDBRequestHandler.getMapper().getTypeFactory().constructType(Movie.class);
		Movie latestMovie = (Movie)TMDBRequestHandler.request(objType, new Request(prefix, suffix));
		return latestMovie.getId();
	}
	
}
