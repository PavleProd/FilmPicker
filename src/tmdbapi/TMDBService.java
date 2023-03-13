package tmdbapi;

import tmdbobjects.*;

import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.databind.JavaType;

import test.Service;

public class TMDBService extends Service {
    /**
     * Request example: https://api.themoviedb.org/3/movie/64690?api_key=<key>
     *
     * @param id movie id
     * @return movie with given id
     */
    public static Movie getMovie(int id) {
        String prefix = "movie/" + id;
        // pravimo objType da bi ObjectMapper znao da prepozna genericku klasu
        JavaType objType = TMDBRequestHandler.getMapper().getTypeFactory().constructType(Movie.class);
        return (Movie) TMDBRequestHandler.request(objType, new Request(prefix));
    }

    /**
     * Request example: https://api.themoviedb.org/3/discover/movie?api_key=<key>&vote_count.gte=300&vote_average.gte=7.0
     * Finds random movie with minScore minimum scored and preordered minimum number of views. Finds all movies with given
     * criteria and chooses a random one. Number of API requests: 2
     *
     * @param minScore minimum score for movie
     * @return Movie object of a random movie
     */
    public static Movie getRandomMovie(double minScore) {
        if (minScore < 0.0 || minScore >= 10.0) return null;

        String prefix = "discover/movie";
        String suffix = "&page=1&vote_count.gte=" + minVoteCount + "&vote_average.gte=" + minScore;
        JavaType objType = TMDBRequestHandler.getMapper().getTypeFactory().constructParametricType(TMDBListWrapper.class, Movie.class);
        TMDBListWrapper<Movie> wrapper = (TMDBListWrapper<Movie>) TMDBRequestHandler.request(objType, new Request(prefix, suffix));

        int totalResults = wrapper.getTotal_results();
        Random randomGenerator = new Random();
        int resultNum = randomGenerator.nextInt(totalResults);
        int pageNum = resultNum / 20 + 1; // pages indexed from 1
        int t = resultNum;
        resultNum = resultNum % 20;

        if (pageNum != 1) { // if page == 1 we already got results
            suffix = "&page=" + pageNum + "&vote_count.gte=" + minVoteCount + "&vote_average.gte=" + minScore;
            wrapper = (TMDBListWrapper<Movie>) TMDBRequestHandler.request(objType, new Request(prefix, suffix));
        }

        return wrapper.getResults().get(resultNum);
    }

    /**
     * Request example: https://api.themoviedb.org/3/search/movie?api_key=<key>&query=Drive&page=1
     *
     * @param movieName name of the movie
     * @param pageNum   page number, indexed from 1
     * @return ArrayList of size 20(pageSize) containing movies that matches the name the most
     */
    public static List<Movie> searchMovieByName(String movieName, int pageNum) {
        if (pageNum <= 0) return null;
        movieName = movieName.replace(' ', '+');
        String suffix = "&query=" + movieName + "&page=" + pageNum;
        String prefix = "search/movie";
        return getMoviesList(pageNum, prefix, suffix);
    }

    /**
     * Request example: https://api.themoviedb.org/3/discover/movie?api_key=<key>&sort_by=popularity.desc&page=1
     *
     * @param pageNum page number, indexed from 1
     * @return ArrayList of size 20(pageSize) containing most popular movies in descending order
     */
    public static List<Movie> getMostPopularMovies(int pageNum) {
        String prefix = "discover/movie";
        String suffix = "&sort_by=popularity.desc&page=" + pageNum;
        return getMoviesList(pageNum, prefix, suffix);
    }

    /**
     * Request example: https://api.themoviedb.org/3/discover/movie?api_key=<key>&sort_by=vote_average.desc&page=1&vote_count.gte=10000
     *
     * @param pageNum
     * @return ArrayList of size 20(pageSize) containing top-rated movies in descending order
     */
    public static List<Movie> getTopRatedMovies(int pageNum) {
        String prefix = "discover/movie";
        String suffix = "&sort_by=vote_average.desc&page=" + pageNum + "&vote_count.gte=" + minVoteCount;
        return getMoviesList(pageNum, prefix, suffix);
    }

    private static List<Movie> getMoviesList(int pageNum, String prefix, String suffix) {
        JavaType objType = TMDBRequestHandler.getMapper().getTypeFactory().constructParametricType(TMDBListWrapper.class, Movie.class);
        TMDBListWrapper<Movie> wrapper = (TMDBListWrapper<Movie>) TMDBRequestHandler.request(objType, new Request(prefix, suffix));
        if (wrapper == null) return null;
        return (wrapper.getTotal_pages() >= pageNum ? wrapper.getResults() : null);
    }

    /**
     * Request example: https://api.themoviedb.org/3/movie/latest?api_key=<key>
     *
     * @return id(max id) of the latest released movie
     */
    public static int getLatestID() {
        String prefix = "movie/latest";
        String suffix = "";
        JavaType objType = TMDBRequestHandler.getMapper().getTypeFactory().constructType(Movie.class);
        Movie latestMovie = (Movie) TMDBRequestHandler.request(objType, new Request(prefix, suffix));
        return latestMovie.getId();
    }


}
