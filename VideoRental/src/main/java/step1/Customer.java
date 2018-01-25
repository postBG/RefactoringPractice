package step1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Customer {
	private String name;
	private List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String getName() {
		return name;
	};

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";

		for(Rental rental : rentals) {
            frequentRenterPoints += rental.getFrequentRenterPoints();
            totalAmount += rental.getAmount();

            // show figures
			result += "\t" +  String.valueOf(rental.getAmount()) + "(" + rental.getMovie().getTitle() + ")" + "\n";
		}

		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter pointers";

		return result;
	}

}