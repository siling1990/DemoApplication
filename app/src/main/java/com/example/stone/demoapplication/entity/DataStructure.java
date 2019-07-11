package com.example.stone.demoapplication.entity;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataStructure {

    //duplicate data in integer array
    public static void duplicateDataWithCount(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            int oldCount = 0;
            //mark is -1
            if (array[i] != -1) {
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j] != -1) {
                        if (array[i] == array[j]) {
                            //if duplicate key count +1
                            System.out.println("duplicate key:" + array[i]);
                            if (map.get(array[i]) != null) {
                                oldCount = map.get(array[i]);
                            }
                            map.put(array[i], oldCount + 1);
                            array[j] = -1;
                        }
                    }
                }
            }

        }
        for (Integer key : map.keySet()) {
            System.out.println("Key = " + key + "\tcounts:" + map.get(key));
        }
    }


    //    public static void main(String args[]){
//        int[] array = {2,3,1,0,2,5,3,1,3};
//
//    }
    private static int i = 0;
    private static int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static void threeThreadSingle() {
        i = 0;
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + "\t" + array[i]);
                i++;
            }
        }, "T1");
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + "\t" + array[i]);
                i++;
            }
        }, "T2");
        Thread t3 = new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + "\t" + array[i]);
                i++;
            }
        }, "T3");


//   method 2 使用 单个任务的线程池来实现。保证线程的依次执行
        ExecutorService executor = Executors.newSingleThreadExecutor();
        while (i < array.length) {
            executor.submit(t1);
            executor.submit(t2);
            executor.submit(t3);
        }

        executor.shutdown();
    }

    public static int fuleAndCar(int[] cars, int x, int y, int z) {//log(3n)

        int time = -1;
        int countDown = 0;
        int fules[] = {x, y, z};
        int fulesLastTime[] = {0, 0, 0};
        int stations[] = {0, 0, 0};

        for (int i = 0; i < cars.length; i++) {
            int car = cars[i];//get car
            System.out.println( "car:\t"+i);
            if (car > fules[0] && car > fules[1] && car > fules[2]) {//not enough fule
                return -1;

            } else {
                boolean free = false;
                boolean next = false;
                for (int j = 0; j < stations.length; j++) {
                    if (!next) {
                        if (stations[j] == 0) {//free


                            if (fules[j] >= car) {//enough fule
                                free = true;
                                System.out.println( "free:\t"+j+"\tfule:"+fules[j]);
                                fules[j] -= car;//add fule;
                                stations[j] = 1;// zhan
                                fulesLastTime[j] = car;
                                System.out.println( "left fule:"+fules[j]);
                                System.out.println( "fulesLastTime:\t"+j+"\t"+fulesLastTime[j]+"\n");
                                //waicengxunhuan
                                next = true;
                            }
                        }
                    }

                }
                if (next) {
                    continue;
                }
                //nofree
                if (!free) {
                    System.out.println( "no free:\t");
                    int currentCount = 0;
                    for (int j = 0; j < stations.length; j++) {
                        if (fules[j] >= car) {//enough fule

                            System.out.println( "fule enough:\t"+j+"\tfule:"+fules[j]);
                            fules[j] -= car;//add fule;

                            currentCount = fulesLastTime[j];//剩余等待时间
                            System.out.println( "fulesLastTime:\t"+j+"\t"+currentCount);

                            countDown += fulesLastTime[j];//等待时间为count+剩余等待时间
                            System.out.println( "countDownTime:\t"+countDown);
                        }

                    }

                    //chongzhi状态
                    for (int j = 0; j < stations.length; j++) {

                        if (fulesLastTime[j] - currentCount <= 0) {//是否到0
                            stations[j] = 0;
                            fulesLastTime[j] = 0;
                        } else {
                            fulesLastTime[j] -= currentCount;//去掉消耗的等待时间
                        }
                        System.out.println( "current station:\t"+j+"\tstatus:"+ stations[j]+"\tlastTime:"+ fulesLastTime[j]+"\t");

                    }
                }
                if (time < countDown) {
                    time = countDown;
                }
            }
        }
        return time;
    }


    public static void main(String[] args) throws InterruptedException {
        int cars[] = {2,8,4,3,2};
        System.out.println( "\ntime:\t" +fuleAndCar(cars,7,11,3));
    }

}
