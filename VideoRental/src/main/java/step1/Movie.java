package step1;

public class Movie {
    public static final int BASE_FREQUENT_RENTER_POINTS = 1;
    public static final int ADDITIONAL_FREQUENT_RENTAL_POINTS = 1;
    private Price price;

    private String title;

    public Movie(String title, int priceCode) {
        this.title = title;
        setPriceCode(priceCode);
    }

    public void setPriceCode(int priceCode){
        switch (priceCode){
            case Price.REGULAR:
                this.price = new RegularPrice();
                return;
            case Price.NEW_RELEASE:
                this.price = new NewReleasePrice();
                return;
            case Price.CHILDRENS:
                this.price = new ChildrenPrice();
                return;
        }

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