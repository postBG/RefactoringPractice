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

        switch (priceCode){
            case Movie.REGULAR:
                double thisAmount = 2;

                if (daysRented > 2)
                    thisAmount += (daysRented - 2) * 1.5;

                return thisAmount;
            case Movie.CHILDRENS:
                double thisAmount1 = 1.5;

                if (daysRented > 3)
                    thisAmount1 += (daysRented - 3) * 1.5;

                return thisAmount1;
            case Movie.NEW_RELEASE:
                return (double) (daysRented * 3);
        }

        return (double) 0;
    }

    public int getFrequentRenterPoints(int daysRented) {
        if(priceCode == Movie.NEW_RELEASE && daysRented > 1){
            return BASE_FREQUENT_RENTER_POINTS + ADDITIONAL_FREQUENT_RENTAL_POINTS;
        }

        return BASE_FREQUENT_RENTER_POINTS;
    }

}