import java.io.Serializable;

public class Movie implements Serializable {
    private String movieName;
    private int stars;
    private String review;
    private int totalCollection;
    private String director;
    private MovieGenre movieGenre;

    public Movie(String movieName, int stars, String review, int totalCollection, String director,
            MovieGenre movieGenre) {
        this.movieName = movieName;
        this.stars = stars;
        this.review = review;
        this.totalCollection = totalCollection;
        this.director = director;
        this.movieGenre = movieGenre;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getStars() {
        return stars;
    }

    public int getTotalCollection() {
        return totalCollection;
    }

    public String getReview() {
        return review;
    }

    public String getDirector() {
        return director;
    }

    public MovieGenre getMovieGenre() {
        return movieGenre;
    }

    public String toString() {
        String STARSTRING = "";
        for (int i = 0; i < stars; i++) {
            STARSTRING += "*";
        }

        String TOTALCOLLECTIONSTRING = "";
        for (int i = 0; i < totalCollection; i++) {
            TOTALCOLLECTIONSTRING += "$";
        }

        return movieName + " Movie\n" + STARSTRING + "\n" + "Total Collection earned: " + TOTALCOLLECTIONSTRING + "\n"
                + movieGenre.toString() + "Director: " + director + "\n" + "Review:\t" + review + "\n";
    }
}
