package com.jy.rxjavashample.RxJava;

import android.support.annotation.NonNull;


public class ObservableMap<T,R> extends Observable<R> {
    final Observable<T> source;// 前面的 Observable
    final Function<T, R> function;// 当前转换
    public ObservableMap(Observable<T> source, Function<T, R> function) {
        this.source = source;
        this.function = function;
    }

    @Override
    protected void subscribeActual(Observer<R> observer) {
        source.subscribe(new ObserverMap<T>(observer, function));
    }

    class ObserverMap<T> implements Observer<T> {
        final Observer<R> observer;
        final Function<T, R> function;
        public ObserverMap(Observer<R> observer, Function<T, R> function) {
            this.observer = observer;
            this.function = function;
        }

        @Override
        public void onSubscribe() {
            observer.onSubscribe();
        }

        @Override
        public void onNext(@NonNull T item) {
            try {
                R apply = function.apply(item);
                observer.onNext(apply);
            } catch (Exception e) {
                e.printStackTrace();
                observer.onError(e);
            }
        }

        @Override
        public void onError(@NonNull Throwable e) {
            observer.onError(e);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }
    }
}
