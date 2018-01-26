package step1;

public class ChildrenPrice extends Price {
    public ChildrenPrice() {
        super(Price.CHILDRENS);
    }

    @Override
    double getAmount(int daysRented) {
        double thisAmount1 = 1.5;

        if (daysRented > 3)
            thisAmount1 += (daysRented - 3) * 1.5;

        return thisAmount1;
    }

    @Override
    int getFrequentRenterPoints(int daysRented) {
        return Movie.BASE_FREQUENT_RENTER_POINTS;
    }
}
