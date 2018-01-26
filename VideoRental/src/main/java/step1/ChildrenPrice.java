package step1;

public class ChildrenPrice extends Price {
    public ChildrenPrice() {
        super(Price.CHILDRENS, 1.5, 3, 1.5);
    }

    @Override
    int getFrequentRenterPoints(int daysRented) {
        return Movie.BASE_FREQUENT_RENTER_POINTS;
    }
}
