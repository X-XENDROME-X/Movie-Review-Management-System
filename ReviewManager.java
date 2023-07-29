import java.io.Serializable;
import java.util.ArrayList;

public class ReviewManager implements Serializable {
    private static final long serialVersionUID = 205L;
    private ArrayList<Movie> reviewList;

    public ReviewManager() {
        reviewList = new ArrayList<>();
    }

    public int movieExists(String MovieName, String director) {
        int i = 0;
        for (Movie m : reviewList) {
            if (m.getMovieName().equalsIgnoreCase(MovieName) && m.getDirector().equalsIgnoreCase(director)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public ArrayList<Integer> movieGenreExists(String mvgenre) {
        ArrayList<Integer> L = new ArrayList<>();
        int p = 0;
        for (Movie MO : reviewList) {
            if (MO.getMovieGenre().getGenre().equalsIgnoreCase(mvgenre)) {
                L.add(p);
            }
            p++;
        }
        return L;
    }

    public Movie getMovie(int i) {
        return reviewList.get(i);
    }

    public void sortByRating() {
        Sorts.sort(reviewList, new ReviewRatingComparator());
    }

    public void sortByMovieGenre() {
        Sorts.sort(reviewList, new ReviewMovieGenreComparator());
    }

    public void closeReviewManager() {
        reviewList.clear();
    }

    public String listReviews() {
        if (reviewList.isEmpty()) {
            return "No Reviews available";
        } else {
            String r = "";
            for (Movie movie : reviewList) {
                r += movie.toString() + "\n";
            }
            return r;
        }
    }

    public boolean addReview(String movieName, int stars, String review, String totalCollection, String genre,
            String director, String productionCompany) {
        if (movieExists(movieName, director) == -1) {
            int collection = totalCollection.length();
            MovieGenre newGenre = new MovieGenre(genre, productionCompany);
            Movie newMovie = new Movie(movieName, stars, review, collection, director, newGenre);
            reviewList.add(newMovie);
            return true;
        }
        return false;
    }

    public boolean removeReview(String movieName, String director) {
        int I = movieExists(movieName, director);
        if (I != -1) {
            reviewList.remove(I);
            return true;
        }
        return false;
    }
}
