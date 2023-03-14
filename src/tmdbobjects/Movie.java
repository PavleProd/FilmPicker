package tmdbobjects;

public class Movie extends TMDBObject {
    private int id;
    private String imdb_id;
    private String original_title;
    private String overview;
    private String poster_path;
    private double popularity;
    private String release_date;
    private String title;
    private int vote_count; // number of votes
    private double vote_average; // average movie score

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        resultString.append("Title: " + title + "\n");
        resultString.append("Release date: " + release_date + "\n");
        resultString.append("Popularity: " + popularity + "\n");
        resultString.append("Vote count: " + vote_count + "\n");
        resultString.append("Vote average: " + vote_average + "\n");
        return resultString.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIMDBLink() {
        if (imdb_id == null) return null;
        return "http://www.imdb.com/title/" + this.imdb_id;
    }
}
