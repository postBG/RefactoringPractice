package step1;

public class NewReleasePrice extends Price {
    public NewReleasePrice() {
        super(Price.NEW_RELEASE, 0, 0, 3.0);
    }

    @Override
    int getFrequentRenterPoints(int daysRented) {
        if (daysRented > 1) {
            return Movie.BASE_FREQUENT_RENTER_POINTS + Movie.ADDITIONAL_FREQUENT_RENTAL_POINTS;
        }

        return Movie.BASE_FREQUENT_RENTER_POINTS;
    }
}
