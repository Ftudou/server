package com.second.hand.trading.server.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.second.hand.trading.server.model.OrderModel;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class OrderTask implements Delayed {
    /**
     * 延迟时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private long time;

    private OrderModel orderModel;

    public OrderTask(){

    }

    public OrderTask(OrderModel orderModel, long time) {
        this.orderModel = orderModel;
        this.time = System.currentTimeMillis()+1000*time;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        OrderTask Order = (OrderTask) o;
        long diff = this.time - Order.time;
        if (diff <= 0) {
            return -1;
        } else {
            return 1;
        }
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public OrderModel getOrderModel() {
        return orderModel;
    }

    public void setOrderModel(OrderModel orderModel) {
        this.orderModel = orderModel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"time\":")
                .append(time);
        sb.append(",\"orderModel\":")
                .append(orderModel);
        sb.append('}');
        return sb.toString();
    }
}
//这段代码定义了一个名为`OrderTask`的类，实现了`Delayed`接口，用于表示订单任务。
//
//        以下是代码逐行解读：
//
//        - `@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")`
//        - 使用`@JsonFormat`注解指定时间的格式。
//
//        - `private long time;`
//        - 定义一个私有的长整型变量`time`，用于存储延迟时间（单位为毫秒）。
//
//        - `private OrderModel orderModel;`
//        - 定义一个私有的`OrderModel`类型变量`orderModel`，用于存储订单模型。
//
//        - `public OrderTask(){}`
//        - 无参构造方法。
//
//        - `public OrderTask(OrderModel orderModel, long time) { ... }`
//        - 有参构造方法，接收一个`OrderModel`对象和延迟时间参数。
//
//        - `@Override public long getDelay(TimeUnit unit) { ... }`
//        - 实现`Delayed`接口的`getDelay`方法，返回指定时间单位内的延迟时间。
//
//        - `@Override public int compareTo(Delayed o) { ... }`
//        - 实现`Delayed`接口的`compareTo`方法，用于比较当前任务和另一个任务的延迟时间。
//
//        - `public long getTime() { ... }`
//        - 返回`time`的值。
//
//        - `public void setTime(long time) { ... }`
//        - 设置`time`的值。
//
//        - `public OrderModel getOrderModel() { ... }`
//        - 返回`orderModel`的值。
//
//        - `public void setOrderModel(OrderModel orderModel) { ... }`
//        - 设置`orderModel`的值。
//
//        - `@Override public String toString() { ... }`
//        - 重写`toString`方法，返回包含时间和订单模型信息的字符串表示形式。
