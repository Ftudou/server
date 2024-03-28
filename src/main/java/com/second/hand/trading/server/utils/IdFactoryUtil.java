package com.second.hand.trading.server.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class IdFactoryUtil {

    private static AtomicInteger orderIdEnd=new AtomicInteger(1);
    private static AtomicInteger fileIdEnd=new AtomicInteger(1);

    public static String getOrderId(){
        int newI;
        int ord;
        do{
            ord=orderIdEnd.get();
            newI=(ord+1)%10000;
        }
        while (!orderIdEnd.compareAndSet(ord,newI));
        return System.currentTimeMillis()+""+(newI+10000);
    }

    public static String getFileId(){
        int newI;
        int ord;
        do{
            ord=fileIdEnd.get();
            newI=(ord+1)%1000;
        }
        while (!fileIdEnd.compareAndSet(ord,newI));
        return System.currentTimeMillis()+""+(newI+1000);
    }
}
//这段Java代码定义了一个名为`IdFactoryUtil`的类，该类包含两个静态方法，`getOrderId`和`getFileId`，用于生成唯一编号，分别用于订单ID和文件ID。
//
//        以下是代码逐行解读：
//
//        - `private static AtomicInteger orderIdEnd=new AtomicInteger(1);`
//        - 定义一个类型为`AtomicInteger`的静态变量`orderIdEnd`，用于存储订单ID的生成序列，初始化为1。`AtomicInteger`Java并发包`java.util.concurrent.atomic`下的一个类，它可以确保在多线程环境下的原子性操作。
//
//        - `private static AtomicInteger fileIdEnd=new AtomicInteger(1);`
//        - 定义一个类型为`AtomicInteger`的静态变量`fileIdEnd`，用于存储文件ID的生成序列，也初始化为1。
//
//        - `public static String getOrderId(){`
//        - 定义一个公开的静态方法`getOrderId`，该方法没有参数，并且返回一个字符串类型的订单ID。
//
//        - `int newI;`
//        - 定义一个整型变量`newI`，用于存储新生成的ID。
//
//        - `int ord;`
//        - 定义一个整型变量`ord`，用于存储当前的ID序列。
//
//        - `do{`
//        开始一个`do-while`循环，确保ID的生成是线程安全的。
//
//        - `ord=orderIdEnd.get();`
//        - 获取当前的订单ID序列值。
//
//        - `newI=(ord+1)%10000;`
//        - 计算新ID值。`(ord+1)`得到新的序列值，然后取模10000，确保生成的ID在0到999之间。

