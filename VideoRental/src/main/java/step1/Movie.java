package step1;

public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

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
        int frequentRenterPoints = 1;

        if ((getPriceCode() == NEW_RELEASE) && daysRented > 1)
            frequentRenterPoints++;

        return frequentRenterPoints;
    }
}