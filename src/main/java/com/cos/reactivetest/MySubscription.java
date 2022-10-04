package com.cos.reactivetest;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;

// 구독정보(구독자, 어떤 데이터를 구독할지)
public class MySubscription implements Subscription {

    private Subscriber sub;

    private Iterator<Integer> it;

    public MySubscription(Subscriber sub, Iterable<Integer> its) {
        this.sub = sub;
        this.it = its.iterator();
    }

    @Override
    public void request(long n) {
        while(n > 0) {
            if (it.hasNext()) {
                sub.onNext(it.next());
            } else {
                sub.onComplete();
                break;
            }
            n--;
        }
    }

    @Override
    public void cancel() {

    }
}
