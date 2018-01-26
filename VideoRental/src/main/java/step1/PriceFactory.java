package step1;

public class PriceFactory {
    Price createPrice(int priceCode) {
        Price price = null;
        switch (priceCode){
            case Price.REGULAR:
                price = new RegularPrice();
                break;
            case Price.NEW_RELEASE:
                price = new NewReleasePrice();
                break;
            case Price.CHILDRENS:
                price = new ChildrenPrice();
                break;
        }
        return price;
    }
}
