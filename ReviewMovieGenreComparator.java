import java.util.Comparator;

public class ReviewMovieGenreComparator implements Comparator<Movie> {

    public int compare(Movie Movie1, Movie Movie2) {
        int CompareGenre = Movie2.getMovieGenre().getGenre().compareTo(Movie1.getMovieGenre().getGenre());
        if (CompareGenre != 0) {
            return CompareGenre;
        } else {
            int CompareCollection = Movie2.getTotalCollection() - Movie1.getTotalCollection();
            if (CompareCollection != 0) {
                return CompareCollection;
            } else {
                int CompareName = Movie2.getMovieName().compareTo(Movie1.getMovieName());
                if (CompareName != 0) {
                    return CompareName;
                } else {
                    int CompareReview = Integer.parseInt(Movie2.getReview()) - Integer.parseInt(Movie1.getReview());
                    return CompareReview;
                }
            }
        }
    }
}
