package com.pdxx.ms.April.April_4_2;

import android.graphics.Bitmap;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableOperator;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by Administrator on 2018/4/2.
 */

public class RxjavaTest {
    public static String[] strings = new String[]{"aaa", "bbb", "ccc"};

    public static void main(String[] args) {
//        Observable.fromArray(strings)
////                .subscribe(new Observer<String>() {
////                    @Override
////                    public void onSubscribe(Disposable d) {
////
////                    }
////
////                    @Override
////                    public void onNext(String s) {
////                        System.out.print(s+"---");
////                    }
////
////                    @Override
////                    public void onError(Throwable e) {
////
////                    }
////
////                    @Override
////                    public void onComplete() {
////
////                    }
////                });
//        //map变换将string转为bitmap
//        Observable.just("images/i.png")
//                .map(new Function<String, Bitmap>() {
//                    @Override
//                    public Bitmap apply(String s) throws Exception {
//                        return null;
//                    }
//                }).subscribe(new Observer<Bitmap>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Bitmap bitmap) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
//
//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                emitter.onNext("hehe");
//            }
//        }).lift(new ObservableOperator<Bitmap, String>() {
//            @Override
//            public Observer<? super String> apply(Observer<? super Bitmap> observer) throws Exception {
//                return new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                };
//            }
//        });

        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {

            }
        });
        test3();
    }

    public void test1() {
        //创建观察者
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        //from创建
        Observable observable = Observable.fromArray(strings);
        //defer创建 当订阅者订阅时才创建observable 并且每次都是新的
//        Observable observable1 = Observable.defer(new Callable<ObservableSource>() {
//            @Override
//            public ObservableSource call() throws Exception {
//                return null;
//            }
//        });
        //创建一个按固定时间间隔发射整数序列的observable，可用作定时器,循环调用
        Observable observable2 = Observable.interval(2, TimeUnit.DAYS);
        //range
        Observable observable3 = Observable.range(1, 20);
        //timer 定时器 2秒后发送事件，仅发送一次
        Observable observable4 = Observable.timer(2, TimeUnit.SECONDS);
        //repeat 重复发送事件，重复次数
        Observable observable5 = Observable.just(1, 2, 3).repeat(3);
    }

    public static void test2() {
        //简便的观察者模式
        Observable
                .just(1, 2, 3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.print(integer + "----" + System.currentTimeMillis());
                    }
                });
    }

    public static void test3() {
        //map 操作符 将int转化为String
//        Observable.just(1, 2, 3)
//                .map(new Function<Integer, String>() {
//                    @Override
//                    public String apply(Integer integer) throws Exception {
//                        return integer + "hehe";
//                    }
//                }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                System.out.print(s);
//            }
//        });

        //flat操作符
//        Observable.just(1, 2, 3)
//                .flatMap(new Function<Integer, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(Integer integer) throws Exception {
//                        return Observable.just(2);
//                    }
//                }).subscribe(new Consumer<Object>() {
//            @Override
//            public void accept(Object o) throws Exception {
//                System.out.print((Integer) o + "");
//            }
//        });
        //filter

        Observable.just(1, 2, 3).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {

                return integer < 3;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.print(integer + "");
            }
        });

        //take
//        Observable.just(strings).take(1).subscribe(new Consumer<String[]>() {
//            @Override
//            public void accept(String[] strings) throws Exception {
//
//            }
//        });

        Observable.just(strings).flatMap(new Function<String[], ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(String[] strings) throws Exception {
                return Observable.fromArray(strings);
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.print(o + "");
            }
        });

        //doOnNext
    }

}
