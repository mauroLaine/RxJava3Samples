package com.laine.mauro.rxjava3samples.samples;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RxJava3Sample {

    public static void main(String[] args) {

        final Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println(integer);
            }
        };

        final CompositeDisposable compositeDisposable = new CompositeDisposable();
        final Disposable disposable = tryFrom(consumer);
        compositeDisposable.add(disposable);
    }


    private static Disposable tryJust(Consumer<Integer> consumer) {
        final Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
        return observable.map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Throwable {
                return integer + 10;
            }
        }).subscribe(consumer);
    }

    private static Disposable tryFrom(Consumer<Integer> consumer) {
        final Integer[] numbers = {10, 20, 30, 40, 50};
        final Observable<Integer> observable = Observable.fromArray(numbers);
        return observable.map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Throwable {
                return integer * 10;
            }
        }).subscribe(consumer);
    }
}
