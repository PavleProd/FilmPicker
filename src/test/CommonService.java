package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tmdbapi.TMDBService;
import tmdbobjects.Genre;
import tmdbobjects.Movie;

/**
 * Class for providing service for most common TMDB requests that needs multiple TMDBService calls
 *
 * @author pavle
 */
public class CommonService extends Service {
    /**
     * Returns Movie objects of "num" top-rated movies of all time
     *
     * @param num number of top-rated movies to be printed
     */
    public static List<Movie> getTopRatedMovies(int num) {
        List<Movie> movieList = new ArrayList<>();
        List<Movie> auxiliaryList = null;

        int currPageNum = 1;
        for (int i = 0; i < num; i++) {
            if (i % 20 == 0) {
                auxiliaryList = TMDBService.getTopRatedMovies(currPageNum++);
                movieList.addAll(auxiliaryList);
            }
        }

        return movieList;
    }

    public static void printAllGenres() {
        for (Genre genre : genreList) {
            System.out.println(genre);
        }
    }

    public static Genre getGenre(String name) {
        for (Genre genre : genreList) {
            if (genre.getName().equals(name)) {
                return genre;
            }
        }
        return null;
    }
}
