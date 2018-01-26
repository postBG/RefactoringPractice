package com.craftsman.privatescheduler;

import org.joda.time.DateTime;

public class Event {
	DateTime dateTime;
	private String title;
	EventState eventState;

	public Event(String title,DateTime dateTime) {
		this.title = title;
		this.dateTime = dateTime;
		this.eventState = EventState.REQUESTED;
	}
	
	public DateTime getDateTime() {
		return dateTime;
	}

	public String getTitle() {
		return title;
	}
	
	public void cancel() {
		eventState = EventState.CANCELED;
	}
	
	public void confirm() {
		eventState = EventState.CONFIRMED;
	}
}
