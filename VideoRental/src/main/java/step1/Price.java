package step1;

public class Price {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private final int priceCode;
    private double baseAmount;
    private int daysForBaseAmount;
    private double additionalAmount;

    public Price(int priceCode, double baseAmount, int daysForBaseAmount, double additionalAmount) {
        this.priceCode = priceCode;
        this.baseAmount = baseAmount;
        this.daysForBaseAmount = daysForBaseAmount;
        this.additionalAmount = additionalAmount;
    }

    double getAmount(int daysRented) {
        double thisAmount = baseAmount;

        if (daysRented > daysForBaseAmount) {
            thisAmount += (daysRented - daysForBaseAmount) * additionalAmount;
        }

        return thisAmount;
    }

    int getFrequentRenterPoints(int daysRented) {
        return Movie.BASE_FREQUENT_RENTER_POINTS;
    }
}