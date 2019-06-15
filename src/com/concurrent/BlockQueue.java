package com.concurrent;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class BlockQueue {
    public static void main(String[] args)throws Exception{
        BlockingQueue<String> blockQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockQueue.offer("a",2L, TimeUnit.SECONDS));
        System.out.println(blockQueue.offer("a",2L, TimeUnit.SECONDS));
        System.out.println(blockQueue.offer("a",2L, TimeUnit.SECONDS));
        System.out.println(blockQueue.offer("a",2L, TimeUnit.SECONDS));

        BlockingQueue<String> blockingQueue1= new SynchronousQueue<>();

        new Thread(()->{
            try{
                System.out.println(Thread.currentThread().getName()+"\t put 1");
                blockingQueue1.put("1");
                System.out.println(Thread.currentThread().getName()+"\t put 2");
                blockingQueue1.put("2");
                System.out.println(Thread.currentThread().getName()+"\t put 3");
                blockingQueue1.put("3");
            }catch(Exception e) {
                e.printStackTrace();
            }finally{

            }

        },"AAA").start();
        new Thread(()->{
            try{
                try {TimeUnit.SECONDS.sleep(5);}catch (Exception e){e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+"\t take "+blockingQueue1.take());

                try {TimeUnit.SECONDS.sleep(5);}catch (Exception e){e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+"\t take "+blockingQueue1.take());

                try {TimeUnit.SECONDS.sleep(5);}catch (Exception e){e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+"\t take "+blockingQueue1.take());
            }catch(Exception e) {
                e.printStackTrace();
            }

        },"BBB").start();

    }

}
