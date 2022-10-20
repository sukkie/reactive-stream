package com.cos.reactivetest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

// 5. Subscription의 request()에는 조건에 따라 Subscriber의 onNext(), onComplete() 또는 onError()를 호출합니다.
// 그러면 Subscriber의 해당 메서드의 로직에 따라 request() 또는 cancel()로 제어하게 됩니다.
public class MySub implements Subscriber<Integer> {

    private Logger log = LogManager.getLogger();

    private Subscription s;
    private int bufferSize = 2;

    // 3. Subscriber는 onSubscribe() 메서드를 통해 Subscription을 등록하고 Publisher를 구독하기 시작합니다.
    // 이는 Publisher에 구현된 Subscription을 통해 이루어집니다.
    // 이렇게 하면 Publisher와 Subscriber는 Subscription을 통해 연결된 상태가 됩니다.
    // onSubscribe() 내부에 Subscription의 request()를 요청하면 그때부터 data 구독이 시작됩니다.
    @Override
    public void onSubscribe(Subscription s) {
        this.s = s;
        log.info("구독자 : 구독정보 잘 받았어.");
        log.info("구독자 : 나 이제 신문 하나씩 줘");
        // 4. Suscriber는 Subscription 메서드의 request() 또는 cancel()을 호출을 통해 data의 흐름을 제어할 수 있습니다.
        s.request(bufferSize);  // 신문 한개씩 매일매일 줘, 소비자가 한번에 처리할 수 있는 데이터 요청

    }

    @Override
    public void onNext(Integer integer) {
        log.info("onNext() : " + integer);
//        log.info("하루지남");
        bufferSize--;
        if (bufferSize == 0) {
            log.info("하루지남");
            bufferSize = 2;
            s.request(bufferSize);
        }
    }

    @Override
    public void onError(Throwable t) {
        log.info("에러");
    }

    @Override
    public void onComplete() {
        log.info("구독 완료");
    }
}
