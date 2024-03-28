package com.second.hand.trading.server.vo;

import java.util.ArrayList;
import java.util.List;

public class PageVo <E>{
    private List<E> list;
    private int count;

    public PageVo() {
    }

    public PageVo(List<E> list, int count) {
        this.list = list;
        this.count = count;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"list\":")
                .append(list);
        sb.append(",\"count\":")
                .append(count);
        sb.append('}');
        return sb.toString();
    }


}
//这段Java代码定义了一个名为`PageVo`的泛型类，用于表示分页数据。分页数据通常包括当前页的数据列表（`list`）和数据总数（`count`）。
//
//        以下是代码逐行：
//
//        - `public class PageVo <E>{`
//        - 定义一个公开的泛型类`PageVo`，其中`<E>`表示类型参数，代表分页数据中的元素类型。
//
//        - `private List<E> list;`
//        - 定义一个私有的泛型列表`list`，用于存储分页数据。
//
//        - `private int count;`
//        - 定义一个私有的整型变量`count`，用于存储数据总数。
//
//        - `public PageVo() { }`
//        - 定义一个无参构造方法。
//
//        - `public PageVo(List<E> list, int count) { ... }`
//        - 定义一个有参构造方法，接收一个列表和一个整数，分别用于初始化`list`和`count`。
//
//        - `public List<E> getList() { ... }`
//        - 定义一个公开的方法`getList`，用于获取`list`。
//
//        - `public void setList(List<E> list) { ... }`
//        - 定义一个公开的方法`setList`，用于设置`list`。
//
//        - `public int getCount() { ... }`
//        - 定义一个公开的方法`getCount`，用于获取`count`。
//
//        - `public void setCount(int count) { ... }`
//        - 定义一个公开的方法`setCount`，用于设置`count`。
//
//        - `@Override public String toString() { ... }`
//        - 重写`toString`方法，用于返回分页数据的可打印串表示。
//
//        - `final StringBuilder sb = new StringBuilder("{");`
//        - 创建一个`StringBuilder`对象`sb`，并初始化为一个包含左括号的字符串，用于构建最终的JSON字符串。
//
//        - `sb.("\"list\":")                .append(list);`
//        - 将`list`转换为字符串，并追加到`sb`中，作为JSON对象的一部分。
//
//        - `sb.append(",\"count\":")                .append(count);`
//        - 将`count`转换为字符串，并追加到`sb`中，作为JSON对象的一部分。
//
//        - `sb.append('}');`
//        - 在JSON对象结束处添加右括号。
//
//        - `return sb.toString();`
//        - 返回构建好的JSON字符串。
//
//    总的来说，`PageVo`类是一个简单的泛型容器，用于封装分页数据。它提供了构造方法、getter和setter方法以及一个重写的`toString`方法，以便以JSON格式打印分页数据。

