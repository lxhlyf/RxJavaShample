package com.jy.rxjavashample.RxJava;

public interface ObservableSource<T> {
    void subscribe(Observer<T> observer);
}
