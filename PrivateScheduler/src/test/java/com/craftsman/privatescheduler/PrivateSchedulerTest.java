package com.craftsman.privatescheduler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PrivateSchedulerTest {
    public static final DateTime TIME_BEFORE_NIGHT = new DateTime(2018, 1, 27, 5, 0, 0);

    private static final String KAKAO_ID = "KAKAO_ID_NOT_IMPORTANT";
    private static final String PHONE_NUMBER = "PHONE_NUMBER_NOT_IMPORTANT";
    private static final String EMAIL = "EMAIL_NOT_IMPORTANT";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private PrivateScheduler privateScheduler;


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
    public void addEvent__서로다른_시간이고_현재이후의_시간이면_이벤트에_다_추가된다() {
        DateTime timeAfterNow = new DateTime().plusSeconds(2);
        DateTime timeAfterNow2 = timeAfterNow.plusSeconds(2);

        privateScheduler.addEvent(createEventAt(timeAfterNow));
        privateScheduler.addEvent(createEventAt(timeAfterNow2));

        assertTrue(privateScheduler.hasEvents(timeAfterNow));
        assertTrue(privateScheduler.hasEvents(timeAfterNow2));
    }

    @Test
    public void addEvent_1시간_이내의_이벤트는_sms와_email_kakao들을_발송한다() {
        MailSender mailSender = mock(MailSender.class);
        SmsSender smsSender = mock(SmsSender.class);
        TimeService timeService = mock(TimeService.class);
        when(timeService.now()).thenReturn(TIME_BEFORE_NIGHT);
        KakaoTalkSender kakaoTalkSender = mock(KakaoTalkSender.class);

        privateScheduler = new PrivateScheduler(createPerson(EMAIL, PHONE_NUMBER, KAKAO_ID), smsSender, timeService);
        privateScheduler.addObserver(kakaoTalkSender);
        privateScheduler.addObserver(mailSender);

        DateTime timeLessThan1hourLeft = TIME_BEFORE_NIGHT.plusSeconds(2);
        Event event = createEventAt(timeLessThan1hourLeft);
        privateScheduler.addEvent(event);

        verify(smsSender, times(1)).send(PHONE_NUMBER, event);
        verify(mailSender, times(1)).update(privateScheduler, event);
        verify(kakaoTalkSender, times(1)).update(privateScheduler, event);
    }

    @Test
    public void addEvent__1시간이_넘게_남은_이벤트는_email만_보내고_sms는_보내지_않는다() {
        MailSender mailSender = mock(MailSender.class);
        SmsSender smsSender = mock(SmsSender.class);
        TimeService timeService = mock(TimeService.class);
        when(timeService.now()).thenReturn(TIME_BEFORE_NIGHT);
        KakaoTalkSender kakaoTalkSender = mock(KakaoTalkSender.class);

        privateScheduler = new PrivateScheduler(createPerson(EMAIL, PHONE_NUMBER, KAKAO_ID), smsSender, timeService);
        privateScheduler.addObserver(kakaoTalkSender);
        privateScheduler.addObserver(mailSender);

        DateTime timeMoreThan1hourLeft = TIME_BEFORE_NIGHT.plusSeconds(2).plusHours(1);
        Event event = createEventAt(timeMoreThan1hourLeft);
        privateScheduler.addEvent(event);

        verify(smsSender, never()).send(PHONE_NUMBER, event);
        verify(mailSender, times(1)).update(privateScheduler, event);
        verify(kakaoTalkSender, times(1)).update(privateScheduler, event);
    }

    @Test
    public void addEvent__밤11시후부터_자정까지는_이벤트를_추가할_수_없다() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Event should not be added at night.");

        SmsSender smsSender = mock(SmsSender.class);
        TimeService timeService = mock(TimeService.class);
        when(timeService.now()).thenReturn(new DateTime(2018, 1, 26, 23, 0));

        privateScheduler = new PrivateScheduler(createPerson(EMAIL, PHONE_NUMBER, KAKAO_ID), smsSender, timeService);

        Event event = createEventAt(TIME_BEFORE_NIGHT);
        privateScheduler.addEvent(event);
    }

    @Test
    public void addEvent__자정부터_익일새벽5시전까지는_이벤트를_추가할_수_없다() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Event should not be added at night.");

        SmsSender smsSender = mock(SmsSender.class);
        TimeService timeService = mock(TimeService.class);
        when(timeService.now()).thenReturn(new DateTime(2018, 1, 26, 4, 0));

        privateScheduler = new PrivateScheduler(createPerson(EMAIL, PHONE_NUMBER, KAKAO_ID), smsSender, timeService);

        Event event = createEventAt(TIME_BEFORE_NIGHT);
        privateScheduler.addEvent(event);
    }

    private Event createEventAt(DateTime dateTime) {
        return new Event("TITLE_NOT_IMPORTANT", dateTime);
    }

    private Person createPerson(String email) {
        return createPerson(email, null, KAKAO_ID);
    }

    private Person createPerson(String email, String phoneNumber, String kakaoId) {
        return new Person(null, phoneNumber, email, KAKAO_ID);
    }

}
