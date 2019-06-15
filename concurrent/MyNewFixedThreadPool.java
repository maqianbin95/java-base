package com.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyNewFixedThreadPool {
    public static void main(String[] args){
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try{
            for (int i=0;i<20;i++){
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"办理业务");
                });
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            threadPool.shutdown();
        }

    }
}
