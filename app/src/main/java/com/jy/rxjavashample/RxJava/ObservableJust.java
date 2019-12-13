package com.jy.rxjavashample.RxJava;

public class ObservableJust<T> extends Observable<T> {

    private T item;
    public ObservableJust(T item) {
        this.item = item;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        ScaleDisponse scaleDisponse = new ScaleDisponse(observer, item);
        observer.onSubscribe();
        scaleDisponse.run();
    }

    private class ScaleDisponse<T> {

        private Observer<T> observer;
        private T item;

        public ScaleDisponse(Observer<T> observer, T item) {
            this.observer = observer;
            this.item = item;
        }

        public void run() {
            try {
                // 3.第三步 observer -> MapObserver.onNext(String)
                observer.onNext(item);
                observer.onComplete();
            }catch (Exception e){
                observer.onError(e);
            }
        }
    }
}
