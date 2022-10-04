package com.cos.reactivetest;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class MySub implements Subscriber<Integer> {

    private Subscription s;
    private int bufferSize = 2;

    @Override
    public void onSubscribe(Subscription s) {
        this.s = s;
        System.out.println("구독자 : 구독정보 잘 받았어.");
        System.out.println("구독자 : 나 이제 신문 하나씩 줘");
        s.request(bufferSize);  // 신문 한개씩 매일매일 줘, 소비자가 한번에 처리할 수 있는 데이터 요청

    }

    @Override
    public void onNext(Integer integer) {
        System.out.println("onNext() : " + integer);
//        System.out.println("하루지남");
        bufferSize--;
        if (bufferSize == 0) {
            System.out.println("하루지남");
            bufferSize = 2;
            s.request(bufferSize);
        }
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("에러");
    }

    @Override
    public void onComplete() {
        System.out.println("구독 완료");
    }
}
