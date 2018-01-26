package step1;

public class Price {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private final int priceCode;

    public Price(int priceCode) {
        this.priceCode = priceCode;
    }

    double getAmount(int daysRented) {
        return (double) 0;
    }

    int getFrequentRenterPoints(int daysRented) {
        return Movie.BASE_FREQUENT_RENTER_POINTS;
    }
}