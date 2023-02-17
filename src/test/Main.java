package test;

import tmdbapi.TMDBService;
import tmdbobjects.*;

public class Main {
	public static void main(String[] args) {
		Movie movie = TMDBService.getMovie(64690);
		System.out.println(movie);
	}
	
}
