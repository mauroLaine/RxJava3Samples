package com.laine.mauro.rxjava3samples.samples;


import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class CreateSamples {

    public static void main(String[] args) {
//        tryInterval();
        tryJust();


    }

    private static void tryInterval() {
        final Observable<Long> observable = Observable.interval(5, 1, TimeUnit.SECONDS);

        final Consumer<Long> consumer = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                System.out.println(aLong);
            }
        };

        final CompositeDisposable compositeDisposable = new CompositeDisposable();
        final Disposable disposable = observable.subscribe(consumer);
        compositeDisposable.add(disposable);

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void tryJust() {
        final Observable<String> observable = Observable.just("Juan", "Pedro", "Miguel", "Jose", "Luis");
        final Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };
        observable.filter(new Predicate<String>() {
            @Override
            public boolean test(String s) throws Throwable {
                return s.length() < 5;
            }
        }).subscribe(observer);
    }
}
