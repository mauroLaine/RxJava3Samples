package com.laine.mauro.rxjava3samples.samples;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RxJava3Sample {

    public static void main(String[] args) {

        final Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);

        final Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println(integer);
            }
        };

        final CompositeDisposable compositeDisposable = new CompositeDisposable();
        final Disposable disposable = observable.map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Throwable {
                return integer + 10;
            }
        }).subscribe(consumer);
        compositeDisposable.add(disposable);
    }
}
