package com.craftsman.privatescheduler;

import org.joda.time.Hours;

import java.util.Observable;
import java.util.Observer;

public class SmsSender implements Observer {

    private TimeService timeService;

    SmsSender(TimeService timeService){
        this.timeService = timeService;
    }

    public void send(String phoneNumber, Event event) {
        // send message to PhoneNumber
    }

    @Override
    public void update(Observable o, Object arg) {
        PrivateScheduler privateScheduler = (PrivateScheduler) o;
        Event event = (Event) arg;

        Hours hours = Hours.hoursBetween(timeService.now(), event.getDateTime());
        if (hours.getHours() < 1) {
            send(privateScheduler.getOwner().getPhoneNumber(), event);
        }
    }
}
