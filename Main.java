import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        char inputOpt = ' ';
        String inputLine;
        String movieName, movieGenre;
        String review = null, director, productionCompany, totalCollection;
        int rating;
        String outFilename, inFilename;
        String outMsg;
        ReviewManager reviewManager = new ReviewManager();
        boolean opResult;

        try {
            printMenu();
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader stdin = new BufferedReader(isr);

            do {
                System.out.print("\nWhat action would you like to perform?\n");
                inputLine = stdin.readLine().trim();
                if (inputLine.isEmpty()) {
                    continue;
                }
                inputOpt = inputLine.charAt(0);
                inputOpt = Character.toUpperCase(inputOpt);

                switch (inputOpt) {

                    case 'A':
                        System.out.print("Please enter the movie information:\n");
                        System.out.print("Enter the movie name:\n");
                        movieName = stdin.readLine().trim();
                        System.out.print("Enter the review:\n");
                        review = stdin.readLine().trim();
                        System.out.print("Enter the total collection:\n");
                        totalCollection = stdin.readLine().trim();
                        System.out.print("Enter the rating:\n");
                        rating = Integer.parseInt(stdin.readLine().trim());
                        System.out.print("Enter the movie genre:\n");
                        movieGenre = stdin.readLine().trim();
                        System.out.print("Enter the movie's Director:\n");
                        director = stdin.readLine().trim();
                        System.out.print("Enter the movie's production company\n");
                        productionCompany = stdin.readLine().trim();

                        opResult = reviewManager.addReview(movieName, rating, review, totalCollection, movieGenre,
                                director, productionCompany);
                        if (opResult) {
                            System.out.println("Movie added to the database!");
                        } else {
                            System.out.println("Movie NOT added!");
                        }
                        break;

                    case 'D':
                        System.out.print("Please enter the Movie name to search:\n");
                        movieName = stdin.readLine().trim();
                        System.out.print("Please enter the movies's director':\n");
                        director = stdin.readLine().trim();
                        int M = reviewManager.movieExists(movieName, director);
                        if (M != -1) {
                            System.out.print(
                                    "Movie found. Here's the review:\n" + reviewManager.getMovie(M).getReview() + "\n");
                        } else {
                            System.out.print("Movie not found. Please try again\n");
                        }
                        break;

                    case 'E':
                        System.out.print("Please enter the movie genre to search:\n");
                        movieGenre = stdin.readLine().trim();
                        ArrayList<Integer> SimilarMovies = reviewManager.movieGenreExists(movieGenre);
                        if (SimilarMovies.size() == 0) {
                            System.out.printf("Movie Genre: %s was NOT found\n", movieGenre);
                        } else {
                            System.out.printf("%d Movies matching %s type were found:\n", SimilarMovies.size(),
                                    movieGenre);
                            for (int i : SimilarMovies) {
                                System.out.println(reviewManager.getMovie(i).toString());
                            }
                        }
                        break;

                    case 'L':
                        System.out.print("\n" + reviewManager.listReviews() + "\n");
                        break;

                    case 'N':
                        reviewManager.sortByRating();
                        System.out.print("sorted by rating\n");
                        break;

                    case 'P':
                        reviewManager.sortByMovieGenre();
                        System.out.print("sorted by genre\n");
                        break;

                    case 'Q':
                        break;

                    case 'R':
                        System.out.print("Please enter the name of the movie for which you want the review removed:\n");
                        movieName = stdin.readLine().trim();
                        System.out.print("Please enter the name of the movie's director:\n");
                        director = stdin.readLine().trim();

                        if (reviewManager.removeReview(movieName, director) == true) {
                            System.out.print(movieName + ", " + director + " was removed\n");
                        } else {
                            System.out.print(movieName + ", " + director + " was NOT removed\n");
                        }
                        break;

                    case 'T':
                        reviewManager.closeReviewManager();
                        System.out.print("The movie management system was reset!\n");
                        break;

                    case 'U':
                        System.out.print("Please enter a file name that we will write to:\n");
                        outFilename = stdin.readLine().trim();
                        System.out.print("Please enter the name of the movie:\n");
                        movieName = stdin.readLine().trim();
                        System.out.println("Please enter a review to save locally:\n");
                        review = stdin.readLine().trim();
                        outMsg = movieName + "\n" + review + "\n";

                        try {
                            FileWriter fileWriter = new FileWriter(outFilename, true);
                            fileWriter.write(outMsg);
                            fileWriter.close();
                            System.out.print(outFilename + " is written\n");
                        } catch (IOException exception) {
                            System.out.print("Write string inside the file error\n");
                        }
                        break;

                    case 'V':
                        System.out.print("Please enter a file name which we will read from:\n");
                        inFilename = stdin.readLine().trim();

                        try {
                            FileReader filereader = new FileReader(inFilename);
                            BufferedReader bufferreader = new BufferedReader(filereader);
                            System.out.print(inFilename + " was read\n");
                            System.out.print("The contents of the file are:\n");
                            String mN = bufferreader.readLine();
                            String rw = bufferreader.readLine();
                            System.out.println(mN);
                            System.out.println(rw);
                            System.out.println();

                            bufferreader.close();
                            filereader.close();
                        } catch (IOException exception) {
                            System.out.print(inFilename + " was not found\n");
                        }
                        break;

                    case 'W':
                        System.out.print("Please enter a file name to write:\n");
                        outFilename = stdin.readLine().trim();

                        try {
                            FileOutputStream FileOutput = new FileOutputStream(outFilename);
                            ObjectOutputStream Output = new ObjectOutputStream(FileOutput);
                            Output.writeObject(reviewManager);
                            Output.close();
                            FileOutput.close();

                        } catch (NotSerializableException exception) {
                            System.out.print("Not serializable exception\n");
                        } catch (IOException e) {
                            System.out.print("Data file written exception\n");
                        }
                        break;

                    case 'X':
                        System.out.print("Please enter a file name which we will read from:\n");
                        inFilename = stdin.readLine().trim();
                        try {
                            FileInputStream FileInput = new FileInputStream(inFilename);
                            ObjectInputStream Input = new ObjectInputStream(FileInput);
                            reviewManager = (ReviewManager) Input.readObject();
                            Input.close();
                            FileInput.close();
                            System.out.print(inFilename + " was read\n");
                        } catch (ClassNotFoundException exception) {
                            System.out.print("Class not found exception\n");
                        } catch (NotSerializableException exception) {
                            System.out.print("Not serializable exception\n");
                        } catch (IOException exception) {
                            System.out.print("Data file read exception\n");
                        }
                        break;
                    case '?':
                        printMenu();
                        break;

                    default:
                        System.out.print("Unknown action\n");
                        break;
                }

            } while (inputOpt != 'Q' || inputLine.length() != 1);
        } catch (IOException exception) {
            System.out.print("IO Exception\n");
        }
    }

    public static void printMenu() {
        System.out.println("Welcome to Movie Review Management System! ");
        System.out.println("Find or post reviews for the movies you have watched.");

        System.out.print("Choice\t\tAction\n" + "------\t\t------\n" + "A\t\tAdd a review\n"
                + "D\t\tSearch for a movie\n" + "E\t\tSearch for a genre\n"
                + "L\t\tList all reviews\n" + "N\t\tSort by stars\n" + "P\t\tSort by genre\n"
                + "Q\t\tQuit\n" + "R\t\tRemove a review\n"
                + "U\t\tAdd personal review to a local file\n" + "V\t\tRetrieve personal review from a local file\n"
                + "W\t\tSave reviews to a file\n"
                + "X\t\tUpload reviews from a file\n"
                + "T\t\t(admin) reset database\n"
                + "?\t\tDisplay Help\n");
    }
}
