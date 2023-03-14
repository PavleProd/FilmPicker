package test;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import tmdbapi.TMDBService;
import tmdbobjects.Genre;
import tmdbobjects.Movie;

public class Main {
    public static void main(String[] args) {
        init();
        CommonService.printAllGenres();
        Scanner s = new Scanner(System.in);
        System.out.print("Unesite minimalnu prosecnu ocenu: ");
        double minScore = s.nextDouble();
        System.out.print("Unesite zanr filma(/ za nijedan):");
        Genre genre = CommonService.getGenre(s.next());
        Movie movie = TMDBService.getRandomMovie(minScore, genre);

        if (movie == null)
            System.out.println("Ne postoji film sa zadatim parametrima!");
        else if (genre == null) {
            System.out.println("Zanr je bio nevalidan");
        } else
            System.out.println("Title: " + movie.getTitle() + "\nAverage rating: " + movie.getVote_average() + "\nIMDB link: " + movie.getIMDBLink());
    }

    /**
     * First method to be called when appliactions runs
     */
    public static void init() {
        TMDBService.createGenreList();
    }

    /**
     * Tests performance of calling request for a single movie ID
     *
     * @param numIterations maximum number(10^numIterations) to be requested.
     */
    public static void testPerformanceSingleID(int numIterations) {
        int numOfRequests = 1;
        for (int i = 0; i < numIterations; i++) {
            long startTime = System.currentTimeMillis();
            for (int j = 0; j < numOfRequests; j++) {
                TMDBService.getMovie(64690);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Time needed for " + numOfRequests + " iterations = " + (endTime - startTime) * 1.0 / 1000 + "s");
            numOfRequests *= 10;
        }
    }

    /**
     * Tests performance of calling request for multiple movie IDs
     *
     * @param numIterations maximum number(10^numIterations) to be requested.
     */
    public static void testPerformanceMultipleIDs(int numIterations) {
        int maxID = TMDBService.getLatestID();
        Random random = new Random();
        int numOfRequests = 1;
        for (int i = 0; i < numIterations; i++) {
            long startTime = System.currentTimeMillis();
            for (int j = 0; j < numOfRequests; j++) {
                TMDBService.getMovie(random.nextInt(maxID));
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Time needed for " + numOfRequests + " iterations = " + (endTime - startTime) * 1.0 / 1000 + "s");
            numOfRequests *= 10;
        }
    }

}
