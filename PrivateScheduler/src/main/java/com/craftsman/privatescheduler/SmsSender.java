package com.craftsman.privatescheduler;

import java.util.Observable;
import java.util.Observer;

public class SmsSender implements Observer {

    public void send(String phoneNumber, Event event) {
        // send message to PhoneNumber
    }

    @Override
    public void update(Observable o, Object arg) {
        PrivateScheduler privateScheduler = (PrivateScheduler) o;
        Event event = (Event) arg;


        send(privateScheduler.getOwner().getPhoneNumber(), event);
    }
}
