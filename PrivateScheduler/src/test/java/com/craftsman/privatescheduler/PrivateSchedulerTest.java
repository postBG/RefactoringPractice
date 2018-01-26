package com.craftsman.privatescheduler;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PrivateSchedulerTest {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testCreate() {
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("Hello");
		
		Person owner = null;
		PrivateScheduler privateScheduler = new PrivateScheduler(owner);
		
		String title = null;
		DateTime dateTime = new DateTime().minusSeconds(1);
		Event event = new Event(title, dateTime);
		privateScheduler.addEvent(event);
	}

}
