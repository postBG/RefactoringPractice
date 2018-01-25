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
		Iterator<Rental> iterator = rentals.iterator();
		String result = "Rental Record for " + getName() + "\n";

		while ( iterator.hasNext() ) {
			Rental rental = iterator.next();
			double thisAmount = amountFor(rental);

			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1)
				frequentRenterPoints++;
			// show figures
			result += "\t" +  String.valueOf(thisAmount) + "(" + rental.getMovie().getTitle() + ")" + "\n";

			totalAmount += thisAmount;
		}

		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter pointers";

		return result;
	}

	private double amountFor(Rental rental) {
		double thisAmount = 0;

		switch (rental.getMovie().getPriceCode()) {
        case Movie.REGULAR:
            thisAmount += 2;
            if (rental.getDaysRented() > 2)
                thisAmount += (rental.getDaysRented() - 2) * 1.5;
            break;

        case Movie.NEW_RELEASE:
            thisAmount += rental.getDaysRented() * 3;
            break;

        case Movie.CHILDRENS:
            thisAmount += 1.5;
            if (rental.getDaysRented() > 3)
                thisAmount += (rental.getDaysRented() - 3) * 1.5;
            break;
        }
		return thisAmount;
	}
}