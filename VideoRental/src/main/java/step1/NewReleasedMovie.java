package step1;

public class NewReleasedMovie extends Movie {
    public NewReleasedMovie(String title) {
        super(title, Movie.NEW_RELEASE);
    }

    @Override
    public double getAmount(int daysRented) {
        double thisAmount = daysRented * 3;
        return thisAmount;
    }
}
