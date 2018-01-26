package step1;

public class Movie {
    public static final int BASE_FREQUENT_RENTER_POINTS = 1;
    public static final int ADDITIONAL_FREQUENT_RENTAL_POINTS = 1;
    private final Price price;

    private String title;

    public Movie(String title, int priceCode) {
        this.title = title;
        this.price = new Price(priceCode);
    }

    public String getTitle() {
        return title;
    }

    public double getAmount(int daysRented) {
        return price.getAmount(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented) {
        return price.getFrequentRenterPoints(daysRented);
    }

}