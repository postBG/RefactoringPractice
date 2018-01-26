package com.craftsman.privatescheduler;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PrivateSchedulerTest {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private PrivateScheduler privateScheduler;

	@Before
	public void setUp(){
		Person owner = new Person(null, null, null);
		privateScheduler = new PrivateScheduler(owner);
	}

	@Test
	public void addEvent__현재시간보다_이전_시간으로_이벤트를_등록하면_익셉션이_일어난다() {
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("Event is before now.");

		DateTime timeBeforeNow = new DateTime().minusSeconds(2);
		privateScheduler.addEvent(createEventAt(timeBeforeNow));
	}

	@Test
	public void addEvent__현재시간_이후의_이벤트는_정상적으로_들어간다() {
		DateTime timeAfterNoe = new DateTime().plusSeconds(2);
		privateScheduler.addEvent(createEventAt(timeAfterNoe));

		assertTrue(privateScheduler.hasEvents(timeAfterNoe));
	}

	@Test
	public void addEvent__이미_존재하는_시간의_이벤트가_추가되면_익셉션이_발생한다(){
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("Have an event already.");

		DateTime timeAfterNow = new DateTime().plusSeconds(2);
		privateScheduler.addEvent(createEventAt(timeAfterNow));
		privateScheduler.addEvent(createEventAt(timeAfterNow));
	}

	private Event createEventAt(DateTime dateTime) {
		return new Event("TITLE_NOT_IMPORTANT", dateTime);
	}

}
