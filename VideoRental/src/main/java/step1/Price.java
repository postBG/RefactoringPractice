package step1;

public class Price {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    int priceCode;

    public Price() {
    }

    public int getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(int arg) {
        priceCode = arg;
    }

    double getAmount(int daysRented) {
        switch (getPriceCode()) {
            case REGULAR:
                double thisAmount = 2;

                if (daysRented > 2)
                    thisAmount += (daysRented - 2) * 1.5;

                return thisAmount;
            case CHILDRENS:
                double thisAmount1 = 1.5;

                if (daysRented > 3)
                    thisAmount1 += (daysRented - 3) * 1.5;

                return thisAmount1;
            case NEW_RELEASE:
                return (double) (daysRented * 3);
        }

        return (double) 0;
    }

    int getFrequentRenterPoints(int daysRented) {
        if (getPriceCode() == NEW_RELEASE && daysRented > 1) {
            return Movie.BASE_FREQUENT_RENTER_POINTS + Movie.ADDITIONAL_FREQUENT_RENTAL_POINTS;
        }

        return Movie.BASE_FREQUENT_RENTER_POINTS;
    }
}