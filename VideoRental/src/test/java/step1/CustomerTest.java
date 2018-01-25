package step1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *  Characterization Test
 * */
public class CustomerTest {

    private Customer customer = new Customer("NAME_NOT_IMPORTANT");

    @Test
    public void statement_for_noRental(){
        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "Amount owed is 0.0\n" +
                "You earned 0 frequent renter pointers", customer.statement());
    }

    @Test
    public void statement_for_regularMovie(){
        customer.addRental(createRental());

        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t2.0(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter pointers", customer.statement());
    }

    private Rental createRental() {
        Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.REGULAR);
        return new Rental(movie, 0);
    }

}