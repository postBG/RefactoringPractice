package com.craftsman.privatescheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.joda.time.DateTime;
import org.joda.time.Hours;

public class PrivateScheduler extends Observable {
    private Person owner;
    private List<Event> events = new ArrayList<Event>();

    private TimeService timeService;
    private SmsSender smsSender;

    PrivateScheduler(Person owner) {
        this(owner, new SmsSender(), new TimeService());
    }

    PrivateScheduler(Person owner, SmsSender smsSender, TimeService timeService) {
        this.owner = owner;

        this.timeService = timeService;
        this.smsSender = smsSender;
    }

    public Person getOwner() {
        return owner;
    }

    public void addEvent(Event event) {
        if (hasEvents(event.getDateTime()))
            throw new RuntimeException("Have an event already.");

        DateTime now = timeService.now();
        if (event.getDateTime().isBefore(now)) {
            throw new RuntimeException("Event is before now.");
        }

        if (now.getHourOfDay() < 5 || now.getHourOfDay() > 22) {
            throw new RuntimeException("Event should not be added at night.");
        }

        events.add(event);

        this.setChanged();
        this.notifyObservers(event);

        Hours hours = Hours.hoursBetween(now, event.getDateTime());
        if (hours.getHours() < 1) {
            smsSender.send(owner.getPhoneNumber(), event);
        }

    }

    public boolean hasEvents(DateTime dateTime) {
        for (Event event : events) {
            if (event.getDateTime().isEqual(dateTime)) {
                return true;
            }
        }
        return false;
    }

    public Event getMeeting(DateTime dateTime) {
        for (Event event : events) {
            if (event.getDateTime().equals(dateTime) && !timeService.isHoliday(event.getDateTime())) {
                return event;
            }
        }
        return null;
    }
}
