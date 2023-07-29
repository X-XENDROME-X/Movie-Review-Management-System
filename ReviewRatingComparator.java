import java.util.Comparator;

public class ReviewRatingComparator implements Comparator<Movie> {

    public int compare(Movie Movie1, Movie Movie2) {
        int RatingDifference = Movie2.getStars() - Movie1.getStars();

        if (RatingDifference != 0) {
            return RatingDifference;
        } else {
            int CompareName = Movie2.getMovieName().compareTo(Movie1.getMovieName());
            if (CompareName != 0) {
                return CompareName;
            } else {
                int CompareDirector = Movie2.getDirector().compareTo(Movie1.getDirector());
                if (CompareDirector != 0) {
                    return CompareDirector;
                } else {
                    int CompareReview = Integer.parseInt(Movie2.getReview()) - Integer.parseInt(Movie1.getReview());
                    return CompareReview;
                }
            }
        }
    }
}
