package com.craftsman.privatescheduler;

import java.util.Observable;
import java.util.Observer;

public class MailSender implements Observer {

    public void sendMail(String email, Event event) {
        // send mail
    }

    @Override
    public void update(Observable o, Object arg) {
        PrivateScheduler privateScheduler = (PrivateScheduler) o;
        Event event = (Event) arg;

        sendMail(privateScheduler.getOwner().getEmail(), event);
    }
}
