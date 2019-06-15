package com.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 传统版生产者消费者模式
 */
public class ProConsumer {
    public static void main(String[] args){
        ShareDate shareDate =new ShareDate();
        new Thread(()->{
            for (int i=0;i<5;i++){
                try{
                    shareDate.increment();
                }catch(Exception e) {
                    e.printStackTrace();
                }

            }
        },"A").start();
        new Thread(()->{
            for (int i=0;i<5;i++){
                try{
                    shareDate.decrement();
                }catch(Exception e) {
                    e.printStackTrace();
                }

            }
        },"B").start();
    }
}
@SuppressWarnings("all")
class ShareDate{
    private int number;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void increment()throws Exception{
        lock.lock();

        try{
            //1.先判断
            while(number != 0){
                //number != 0,则等待
                condition.await();
            }
            //2.到这里说明number==0,则number++;
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3.通知唤醒
            condition.signalAll();
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void decrement()throws Exception{
        lock.lock();

        try{
            //1.先判断
            while(number == 0){
                //number != 0,则等待
                condition.await();
            }
            //2.到这里说明number==0,则number++;
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3.通知
            condition.signalAll();
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}