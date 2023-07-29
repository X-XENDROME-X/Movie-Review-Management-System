import java.io.Serializable;

public class MovieGenre implements Serializable {
    private static final long serialVersionUID = 205L;
    private String genre;
    private String productionCompany;

    public MovieGenre(String genre, String productionCompany) {
        this.genre = genre;
        this.productionCompany = productionCompany;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return genre + " Movie\n" +
                "Production Company:\t" + productionCompany + '\n';
    }
}