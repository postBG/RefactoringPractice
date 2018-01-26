package com.craftsman.privatescheduler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PrivateSchedulerTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private PrivateScheduler privateScheduler;
    private static final String PHONE_NUMBER = "PHONE_NUMBER_NOT_IMPORTANT";
    private static final String EMAIL = "EMAIL_NOT_IMPORTANT";

    @Before
    public void setUp() {
        Person owner = createPerson(null);
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
    public void addEvent__이미_존재하는_시간의_이벤트가_추가되면_익셉션이_발생한다() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Have an event already.");

        DateTime timeAfterNow = new DateTime().plusSeconds(2);
        privateScheduler.addEvent(createEventAt(timeAfterNow));
        privateScheduler.addEvent(createEventAt(timeAfterNow));
    }

    @Test
    public void addEvent__서로다른_시간이고_현재이후의_시간이면_이벤트에_다_추가된다(){
        DateTime timeAfterNow = new DateTime().plusSeconds(2);
        DateTime timeAfterNow2 = timeAfterNow.plusSeconds(2);

        privateScheduler.addEvent(createEventAt(timeAfterNow));
        privateScheduler.addEvent(createEventAt(timeAfterNow2));

        assertTrue(privateScheduler.hasEvents(timeAfterNow));
        assertTrue(privateScheduler.hasEvents(timeAfterNow2));
    }

    @Test
    public void addEvent__이벤트가_등록되면_mail을_보낸다(){
        DateTime timeAfterNow = new DateTime().plusSeconds(2);
        MailSender mailSender = mock(MailSender.class);

        String email = "EMAIL_NOT_IMPORTANT";
        privateScheduler = new PrivateScheduler(createPerson(email), mailSender);

        Event event = createEventAt(timeAfterNow);
        privateScheduler.addEvent(event);

        verify(mailSender, times(1)).sendMail(email, event);
    }

    @Test
    public void addEvent_1시간_이내의_이벤트는_sms와_email을_발송한다(){
        DateTime timeLessThan1hourLeft = new DateTime().plusSeconds(2);
        MailSender mailSender = mock(MailSender.class);
        SmsSender smsSender = mock(SmsSender.class);

        privateScheduler = new PrivateScheduler(createPerson(EMAIL, PHONE_NUMBER), mailSender, smsSender);

        Event event = createEventAt(timeLessThan1hourLeft);
        privateScheduler.addEvent(event);

        verify(smsSender, times(1)).send(PHONE_NUMBER, event);
        verify(mailSender, times(1)).sendMail(EMAIL, event);
    }

    @Test
    public void addEvent__1시간이_넘게_남은_이벤트는_email만_보내고_sms는_보내지_않는다(){
        DateTime timeMoreThan1hourLeft = new DateTime().plusSeconds(2).plusHours(1);
        MailSender mailSender = mock(MailSender.class);
        SmsSender smsSender = mock(SmsSender.class);

        privateScheduler = new PrivateScheduler(createPerson(EMAIL, PHONE_NUMBER), mailSender, smsSender);

        Event event = createEventAt(timeMoreThan1hourLeft);
        privateScheduler.addEvent(event);

        verify(smsSender, never()).send(PHONE_NUMBER, event);
        verify(mailSender, times(1)).sendMail(EMAIL, event);
    }

    private Event createEventAt(DateTime dateTime) {
        return new Event("TITLE_NOT_IMPORTANT", dateTime);
    }

    private Person createPerson(String email) {
        return createPerson(email, null);
    }

    private Person createPerson(String email, String phoneNumber) {
        return new Person(null, phoneNumber, email);
    }

}
