package com.craftsman.privatescheduler;

import java.util.Observable;
import java.util.Observer;

public class KakaoTalkSender implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        PrivateScheduler privateScheduler = (PrivateScheduler) o;
        Event event = (Event) arg;
        sendKakao(privateScheduler.getOwner().getKakaoId(), event);
    }

    public void sendKakao(String kakaoId, Event event){

    }
}
