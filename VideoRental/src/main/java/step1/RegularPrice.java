package step1;

public class RegularPrice extends Price {
    public RegularPrice() {
        super(Price.REGULAR, 2, 2, 1.5);
    }

    @Override
    int getFrequentRenterPoints(int daysRented) {
        return Movie.BASE_FREQUENT_RENTER_POINTS;
    }
}
