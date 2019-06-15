package com.concurrent;

import java.util.concurrent.Executor;

public class MyExecutor implements Executor {
    public static void main(String[] args){
        new MyExecutor().execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程"+Thread.currentThread().getName()+" run...");
            }
        });

    }

    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
