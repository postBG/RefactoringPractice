package step1;

public class NewReleasePrice extends Price {
    public NewReleasePrice() {
        super(Price.NEW_RELEASE);
    }

    @Override
    double getAmount(int daysRented) {
        return (double) (daysRented * 3);
    }

    @Override
    int getFrequentRenterPoints(int daysRented) {
        if (daysRented > 1) {
            return Movie.BASE_FREQUENT_RENTER_POINTS + Movie.ADDITIONAL_FREQUENT_RENTAL_POINTS;
        }

        return Movie.BASE_FREQUENT_RENTER_POINTS;
    }
}
