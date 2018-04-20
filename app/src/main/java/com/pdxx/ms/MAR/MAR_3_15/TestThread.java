package com.pdxx.ms.MAR.MAR_3_15;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Administrator on 2018/3/15.
 */

public class TestThread {
    public void textThead() throws ExecutionException, InterruptedException {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {

                    }
                }
        ).start();

        TestThread2 testThread2 = new TestThread2();
        FutureTask futureTask = new FutureTask(testThread2);
        Object o = futureTask.get();
    }

    public class TestThread1 implements Runnable{

        @Override
        public void run() {

        }
    }

    public class TestThread2 implements Callable{

        @Override
        public Object call() throws Exception {
            return null;
        }
    }


}
