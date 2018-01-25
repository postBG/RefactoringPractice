package step1;

import java.util.ArrayList;
import java.util.List;

class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public double getTotalAmount() {
        double totalAmount = 0;

        for (Rental rental : rentals) {
            totalAmount += rental.getAmount();
        }
        return totalAmount;
    }

    public int getFrequentRenterPoints() {
        int frequentRenterPoints = 0
                ;
        for (Rental rental : rentals) {
            frequentRenterPoints += rental.getFrequentRenterPoints();
        }
        return frequentRenterPoints;
    }

}