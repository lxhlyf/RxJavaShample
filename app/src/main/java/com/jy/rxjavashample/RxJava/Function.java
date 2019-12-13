package com.jy.rxjavashample.RxJava;

public interface Function<T,R> {
    R apply(T t) throws Exception;
}
