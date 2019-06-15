package com.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class LockAndSync {
    public static void main(String[] args){
        synchronized (new Object()){

        }
        new ReentrantLock();
    }
}
