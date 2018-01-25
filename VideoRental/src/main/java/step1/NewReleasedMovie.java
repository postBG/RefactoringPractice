package step1;

public class NewReleasedMovie extends Movie {

    public NewReleasedMovie(String title) {
        super(title, Movie.NEW_RELEASE);
    }

    @Override
    public double getAmount(int daysRented) {
        return (double) (daysRented * 3);
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        if (daysRented > 1)
            return BASE_FREQUENT_RENTER_POINTS + ADDITIONAL_FREQUENT_RENTAL_POINTS;

        return BASE_FREQUENT_RENTER_POINTS;
    }
}
