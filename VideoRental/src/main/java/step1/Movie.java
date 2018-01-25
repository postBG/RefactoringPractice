package step1;

public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int BASE_FREQUENT_RENTER_POINTS = 1;
    public static final int ADDITIONAL_FREQUENT_RENTAL_POINTS = 1;

    private String title;
    private int priceCode;

    public Movie(String title, int priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public int getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(int arg) {
        priceCode = arg;
    }

    public String getTitle() {
        return title;
    }

    public double getAmount(int daysRented) {
        return (double) 0;
    }

    public int getFrequentRenterPoints(int daysRented) {
        return BASE_FREQUENT_RENTER_POINTS;
    }
}