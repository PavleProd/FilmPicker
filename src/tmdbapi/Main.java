package tmdbapi;

public class Main {
	public static void main(String[] args) {
		Movie movie = new Movie(64690);
		movie.getRequestString();
		System.out.println(movie.getUnparsedData());
	}
	
}
