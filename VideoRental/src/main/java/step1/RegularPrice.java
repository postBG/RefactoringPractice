package step1;

public class RegularPrice extends Price {
    public RegularPrice() {
        super(Price.REGULAR);
    }

    @Override
    double getAmount(int daysRented) {
        double thisAmount = 2;

        if (daysRented > 2)
            thisAmount += (daysRented - 2) * 1.5;

        return thisAmount;
    }

    @Override
    int getFrequentRenterPoints(int daysRented) {
        return Movie.BASE_FREQUENT_RENTER_POINTS;
    }
}
