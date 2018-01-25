package step1;

public class Statement {
    public String statement(Customer customer) {
        String result = statementHeader(customer);
        result += getRentalLines(customer);
        result += statementFooter(customer);

        return result;
    }

    private String statementFooter(Customer customer) {
        String result = "Amount owed is " + String.valueOf(customer.getTotalAmount()) + "\n";
        result += "You earned " + String.valueOf(customer.getFrequentRenterPoints()) + " frequent renter pointers";
        return result;
    }

    private String getRentalLines(Customer customer) {
        String result = "";

        for (Rental rental : customer.getRentals()) {
            // show figures
            result += "\t" + String.valueOf(rental.getAmount()) + "(" + rental.getMovie().getTitle() + ")" + "\n";
        }
        return result;
    }

    private String statementHeader(Customer customer) {
        return "Rental Record for " + customer.getName() + "\n";
    }
}
