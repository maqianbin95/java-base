package com.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

class ShareData{
    private int number=1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();


    public void print5(){
        lock.lock();
        try{
            //1.判断
            while(number != 1){
                //等待
                c1.await();
            }
            //2.干活
            for (int i=1 ;i<=5;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3.通知唤醒
            number=2;
            c2.signal();
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
    public void print10(){
        lock.lock();
        try{
            //1.判断
            while(number != 2){
                //等待
                c2.await();
            }
            //2.干活
            for (int i=1 ;i<=10;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3.通知唤醒
            number=3;
            c3.signal();
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
    public void print15(){
        lock.lock();
        try{
            //1.判断
            while(number != 3){
                //等待
                c3.await();
            }
            //2.干活
            for (int i=1 ;i<10;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3.通知唤醒
            number=1;
            c1.signal();
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}
public class ConditionTest {
    public static void main(String[] args){
        ShareData shareData = new ShareData();
        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                shareData.print5();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                shareData.print10();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                shareData.print15();
            }
        },"C").start();
    }
}
