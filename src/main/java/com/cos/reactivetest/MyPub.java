package com.cos.reactivetest;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class MyPub implements Publisher<Integer> {

    private Logger log = LogManager.getLogger();

    Iterable<Integer> its = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    @Override
    public void subscribe(Subscriber<? super Integer> s) {
        log.info("구독자 : 신문사야 너희 신문 볼게");
        log.info("신문사 : 구독 정보 만들어서 돌려줄테니 기다려.");
        // 1. Publisher에 본인이 소유할 Subscription을 구현하고 publishing할 data를 만듭니다.
        MySubscription mySubscription = new MySubscription(s, its);
        log.info("신문사 : 구독정보 생성 완료");
        s.onSubscribe(mySubscription);
    }
}
