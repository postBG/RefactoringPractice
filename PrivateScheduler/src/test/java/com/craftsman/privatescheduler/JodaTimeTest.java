package com.craftsman.privatescheduler;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;

public class JodaTimeTest {
	DateTime now = new DateTime(2017,2,20,1,3);

	@Test
	public void tomorrow() {
		DateTime tomorrow = now.plusDays(1);
		assertEquals(20 + 1,tomorrow.getDayOfMonth());
	}

	@Test
	public void nextHour() {
		DateTime nextHour = now.withHourOfDay(2);
		
		assertEquals(1 + 1,nextHour.getHourOfDay());
	}
	
	@Test
	public void isBefore() {
		DateTime yesterday = now.minusDays(1);
		
		assertTrue(yesterday.isBefore(now));
	}
	
	@Test
	public void isAfter() {
		DateTime yesterday = now.minusDays(1);
		
		assertTrue(now.isAfter(yesterday));
	}	
}
