import java.util.ArrayList;
import java.util.Comparator;

public class Sorts {
    public static void sort(ArrayList<Movie> reviewList, Comparator<Movie> xComparator) {
        int number = reviewList.size();
        for (int i = 0; i < number - 1; i++) {
            int min = i;
            for (int j = i + 1; j < number; j++) {
                if (xComparator.compare(reviewList.get(j), reviewList.get(min)) > 0) {
                    min = j;
                }
            }
            if (min != i) {
                Movie store = reviewList.get(i);
                reviewList.set(i, reviewList.get(min));
                reviewList.set(min, store);
            }
        }
    }
}
