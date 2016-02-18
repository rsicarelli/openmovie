package br.com.rsicarelli.openmovie.bus;

import br.com.rsicarelli.openmovie.bus.events.AbstractEvent;
import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by rodrigosicarelli on 1/20/16.
 */
public class RxBus {
    private final Subject<Object, Object> bus;

    public RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    public void send(AbstractEvent resultEvent) {
        bus.onNext(resultEvent);
    }

    public Observable<Object> toObservable() {
        return bus;
    }
}
