package rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxJava1 {

    public static void main(String[] args) {
        Observable.create(emitter -> {
                    emitter.onNext(1);
                    emitter.onNext(2);
                    emitter.onNext(3);
                    emitter.onNext(4);
                    emitter.onComplete();
                })
                .map(integer -> {
                    log(integer + " - I want this happen on an single thread");
                    return integer + "";
                })
                .subscribeOn(Schedulers.single())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(i -> {
                    log("Consume: " + i);
                    return i;
                })
                .observeOn(Schedulers.io())
                .subscribe(s -> log("Consume: " + s));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void log(String msg) {
        System.out.println("Current Thread Name:" + Thread.currentThread().getName() + ", " + msg);
    }
}
