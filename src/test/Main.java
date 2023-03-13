package test;

import java.util.List;
import java.util.Random;

import tmdbapi.TMDBService;
import tmdbobjects.Movie;

public class Main {
	public static void main(String[] args) {
		//testPerformanceSingleID(2);
		testPerformanceMultipleIDs(4);
	}

	/**
	 * Tests performance of calling request for a single movie ID
	 * @param numIterations maximum number(10^numIterations) to be requested.
	 */
	public static void testPerformanceSingleID(int numIterations) {
		int numOfRequests = 1;
		for(int i = 0; i < numIterations; i++) {
			long startTime = System.currentTimeMillis();
			for(int j = 0; j < numOfRequests; j++) {
				TMDBService.getMovie(64690);
			}
			long endTime = System.currentTimeMillis();
			System.out.println("Time needed for " + numOfRequests + " iterations = " + (endTime - startTime)*1.0/1000 + "s");
			numOfRequests *= 10;
		}
	}

	/**
	 * Tests performance of calling request for multiple movie IDs
	 * @param numIterations maximum number(10^numIterations) to be requested.
	 */
	public static void testPerformanceMultipleIDs(int numIterations) {
		int maxID = TMDBService.getLatestID();
		Random random = new Random();
		int numOfRequests = 1;
		for(int i = 0; i < numIterations; i++) {
			long startTime = System.currentTimeMillis();
			for(int j = 0; j < numOfRequests; j++) {
				TMDBService.getMovie(random.nextInt(maxID));
			}
			long endTime = System.currentTimeMillis();
			System.out.println("Time needed for " + numOfRequests + " iterations = " + (endTime - startTime)*1.0/1000 + "s");
			numOfRequests *= 10;
		}
	}
	
}
