package com.second.hand.trading.server.utils;

import com.second.hand.trading.server.service.OrderService;

import java.util.concurrent.DelayQueue;

public class OrderTaskHandler {

    public static OrderService orderService=null;

    private static DelayQueue<OrderTask> delayQueue = new DelayQueue<>();

    public static void run(){
        new Thread(() -> {
            System.out.println("Success !");
            while (true) {
                if(orderService!=null&&delayQueue.size() >0){
                    OrderTask orderTask = delayQueue.poll();
                    if (orderTask != null) {
                        if(orderService.updateOrder(orderTask.getOrderModel())){
                            System.out.println("成功取消订单："+orderTask.getOrderModel());
                        }else {
                            System.out.println("取消任务："+orderTask.getOrderModel());
                        }

                    }
                }
            }
        }).start();

    }

    public static void addOrder(OrderTask o){
        System.out.println("添加任务："+o);
        delayQueue.put(o);
    }
}
//这段代码定义了一个名为`OrderTaskHandler`的类，包含静态方法用于处理订单任务。下面逐行解读该类的功能和实现：
//
//        - `public static OrderService orderService = null;`
//        - 定义一个静态的`OrderService`类型的变量`orderService`，初始化为null。
//
//        - `private static DelayQueue<OrderTask> delayQueue = new DelayQueue<>();`
//        - 定义一个私有的`DelayQueue`类型的静态变量`delayQueue`，用于存储`OrderTask`对象，按照延迟时间排序。
//
//        - `public static void run() { ... }`
//        - 定义一个静态的`run`方法，创建一个新线程用于处理订单任务。线程会打印"Success !"，然后进入一个无限循环，检查`orderService`是否为空且`delayQueue`的大小是否大于0。如果条件满足，会从`delayQueue`中取出一个`OrderTask`对象并尝试更新订单状态，根据更新结果打印不同的信息。
//
//        - `public static void addOrder(OrderTask o) { ... }`
//        - 定义一个静态的`addOrder`方法，用于向`delayQueue`中添加订单任务。在方法内会打印"添加任务："和传入的`OrderTask`对象信息，并将该对象加入`delayQueue`中。
//
//        总体来说，`OrderTaskHandler`类提供了两个静态方法，一个用于运行处理订单任务的线程，另一个用于向延迟队列中添加订单任务。通过不断轮询延迟队列，可以及时处理到期的订单任务。
