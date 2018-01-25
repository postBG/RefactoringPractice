package step1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *  Characterization Test
 * */
public class CustomerTest {

    private Customer customer = new Customer("NAME_NOT_IMPORTANT");
    private Statement statement = new Statement();

    @Test
    public void statement_for_noRental(){
        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "Amount owed is 0.0\n" +
                "You earned 0 frequent renter pointers", statement.statement(customer));
    }

    @Test
    public void statement_for_regularMovie(){
        customer.addRental(createRental(2, Movie.REGULAR));

        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t2.0(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter pointers", statement.statement(customer));
    }

    @Test
    public void statement_for_regularMovie_daysRented_is_greater_than_2(){
        customer.addRental(createRental(3, Movie.REGULAR));

        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t3.5(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter pointers", statement.statement(customer));
    }

    @Test
    public void statement_for_newReleasedMovie(){
        customer.addRental(createRental(3, Movie.NEW_RELEASE));

        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t9.0(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 9.0\n" +
                "You earned 2 frequent renter pointers", statement.statement(customer));
    }

    @Test
    public void statement_for_newReleasedMovie_daysRented_less_equal_than_1(){
        customer.addRental(createRental(1, Movie.NEW_RELEASE));

        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t3.0(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter pointers", statement.statement(customer));
    }

    @Test
    public void statement_for_childrenMovie(){
        customer.addRental(createRental(3, Movie.CHILDRENS));

        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t1.5(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter pointers", statement.statement(customer));
    }

    @Test
    public void statement_for_childrenMovie_daysRented_is_greater_than_3(){
        customer.addRental(createRental(4, Movie.CHILDRENS));

        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t3.0(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter pointers", statement.statement(customer));
    }

    @Test
    public void statement_for_rent_more_than_one_movies(){
        customer.addRental(createRental(1, Movie.REGULAR));
        customer.addRental(createRental(2, Movie.NEW_RELEASE));
        customer.addRental(createRental(3, Movie.CHILDRENS));

        assertEquals("Rental Record for NAME_NOT_IMPORTANT\n" +
                "\t2.0(TITLE_NOT_IMPORTANT)\n" +
                "\t6.0(TITLE_NOT_IMPORTANT)\n" +
                "\t1.5(TITLE_NOT_IMPORTANT)\n" +
                "Amount owed is 9.5\n" +
                "You earned 4 frequent renter pointers", statement.statement(customer));
    }

    private Rental createRental(int daysRented, int priceCode) {
        Movie movie = new Movie("TITLE_NOT_IMPORTANT", priceCode);
        return new Rental(movie, daysRented);
    }

}