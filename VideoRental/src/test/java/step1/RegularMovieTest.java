package step1;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegularMovieTest {
    private static final double DELTA = 1e-7;
    private RegularMovie regularMovie = new RegularMovie("TITLE_NOT_IMPORTANT");

    @Test
    public void getAmount_daysRented가_기본날짜보다_작을_경우_기본_amount만큼_준다(){
        assertEquals(2, regularMovie.getAmount(2), DELTA);
    }

    @Test
    public void getAmount_daysRented가_2일보다_크면_추가적인_amount를_준다(){
        assertEquals(3.5, regularMovie.getAmount(3), DELTA);
    }

}