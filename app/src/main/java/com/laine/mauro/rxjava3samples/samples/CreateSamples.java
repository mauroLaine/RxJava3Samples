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
//        tryJust();
//        tryRange();
        tryMerge();
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

    private static void tryRange() {
        final Observable<Integer> observable = Observable.range(1, 50);
        final Observable<Integer> observable2 = Observable.range(51, 50);

        final Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext: " + integer);
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
        observable.concatWith(observable2).subscribe(observer);
    }

    private static void tryMerge() {
        final String[] namesSquadA = {"Brock", "John", "Eric", "Ali", "John", "Edward", "Brian"};
        final Observable<String> observableA = Observable.fromArray(namesSquadA);
        final Observable<String> observableB = Observable.just("Joan", "Edward", "Alex", "Max", "Brian", "Eric");
        final Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                System.out.println(s);
            }
        };
        final CompositeDisposable compositeDisposable = new CompositeDisposable();
        final Disposable disposable = observableA.mergeWith(observableB).distinct().subscribe(consumer);
        compositeDisposable.add(disposable);
    }
}
