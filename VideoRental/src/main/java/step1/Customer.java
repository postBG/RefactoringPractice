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

    public String statement() {

        String result = statementHeader();
        result += getRentalLines();
        result += statementFooter();

        return result;
    }

    private String statementFooter() {
        String result = "Amount owed is " + String.valueOf(getTotalAmount()) + "\n";
        result += "You earned " + String.valueOf(getFrequentRenterPoints()) + " frequent renter pointers";
        return result;
    }

    private String statementHeader() {
        return "Rental Record for " + getName() + "\n";
    }

    private String getRentalLines() {
        String result = "";

        for (Rental rental : rentals) {
            // show figures
            result += "\t" + String.valueOf(rental.getAmount()) + "(" + rental.getMovie().getTitle() + ")" + "\n";
        }
        return result;
    }

    private double getTotalAmount() {
        double totalAmount = 0;

        for (Rental rental : rentals) {
            totalAmount += rental.getAmount();
        }
        return totalAmount;
    }

    private int getFrequentRenterPoints() {
        int frequentRenterPoints = 0
                ;
        for (Rental rental : rentals) {
            frequentRenterPoints += rental.getFrequentRenterPoints();
        }
        return frequentRenterPoints;
    }

}