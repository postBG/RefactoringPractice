package com.craftsman.privatescheduler;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Days;
import org.joda.time.Hours;

public class PrivateScheduler {
	private Person owner;
	private List<Event> events = new ArrayList<Event>();
	
	private TimeService timeService;
	private SmsSender smsSender;
	private MailSender mailSender;

	public PrivateScheduler(Person owner) {
		this.owner = owner;
		
		this.timeService = new TimeService();
		this.smsSender = new SmsSender();
		this.mailSender = new MailSender();
	}
	
	public void addEvent(Event event) {
		if ( hasEvents(event.getDateTime()) )
			throw new RuntimeException("Have an event already.");

		DateTime now = new DateTime();
		if ( event.getDateTime().isBefore(now) ) {
			throw new RuntimeException("Event is before now.");
		}

		/*
		if ( now.getHourOfDay() < 5 || now.getHourOfDay() > 22 ) {
			throw new RuntimeException("Event should not be added at night.");
		}
		*/

		events.add(event);

		Hours hours = Hours.hoursBetween(now, event.getDateTime());
		if ( hours.getHours() < 1 ) {
			smsSender.send(owner.getPhoneNumber(),event);
		}

		mailSender.sendMail(owner.getEmail(),event);
	}

	public boolean hasEvents(DateTime dateTime) {
		for ( Event event : events ) {
			if ( event.getDateTime().isEqual(dateTime) ) {
				return true;
			}
		}
		return false;
	}

	public Event getMeeting(DateTime dateTime) {
		for ( Event event : events ) {
			if ( event.getDateTime().equals(dateTime) && !timeService.isHoliday(event.getDateTime()) ) {
				return event;
			}
		}
		return null;
	}
}
