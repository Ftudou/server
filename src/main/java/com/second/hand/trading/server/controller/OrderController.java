package com.second.hand.trading.server.controller;

import com.second.hand.trading.server.enums.ErrorMsg;
import com.second.hand.trading.server.model.OrderModel;
import com.second.hand.trading.server.service.OrderService;
import com.second.hand.trading.server.utils.IdFactoryUtil;
import com.second.hand.trading.server.utils.OrderTaskHandler;
import com.second.hand.trading.server.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public ResultVo addOrder(@CookieValue("shUserId")
                             @NotNull(message = "登录异常 请重新登录")
                             @NotEmpty(message = "登录异常 请重新登录") String shUserId,
                             @RequestBody OrderModel orderModel){
        if(OrderTaskHandler.orderService==null){
            OrderTaskHandler.orderService=orderService;
        }
        orderModel.setOrderNumber(IdFactoryUtil.getOrderId());
        orderModel.setCreateTime(new Date());
        orderModel.setUserId(Long.valueOf(shUserId));
        orderModel.setOrderStatus((byte) 0);
        if(orderService.addOrder(orderModel)){
            return ResultVo.success(orderModel);
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @GetMapping("/info")
    public ResultVo getOrderInfo(@CookieValue("shUserId")
                                 @NotNull(message = "登录异常 请重新登录")
                                 @NotEmpty(message = "登录异常 请重新登录") String shUserId,
                                 @RequestParam Long id){
        OrderModel orderModel=orderService.getOrder(id);
        if(orderModel.getUserId().equals(Long.valueOf(shUserId))||
                orderModel.getIdleItem().getUserId().equals(Long.valueOf(shUserId))){
            return ResultVo.success(orderModel);
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @PostMapping("/update")
    public ResultVo updateOrder(@CookieValue("shUserId")
                             @NotNull(message = "登录异常 请重新登录")
                             @NotEmpty(message = "登录异常 请重新登录") String shUserId,
                             @RequestBody OrderModel orderModel){
        if(orderService.updateOrder(orderModel)){
            return ResultVo.success(orderModel);
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @GetMapping("/my")
    public ResultVo getMyOrder(@CookieValue("shUserId")
                                 @NotNull(message = "登录异常 请重新登录")
                                 @NotEmpty(message = "登录异常 请重新登录") String shUserId){
        return ResultVo.success(orderService.getMyOrder(Long.valueOf(shUserId)));
    }

    @GetMapping("/my-sold")
    public ResultVo getMySoldIdle(@CookieValue("shUserId")
                               @NotNull(message = "登录异常 请重新登录")
                               @NotEmpty(message = "登录异常 请重新登录") String shUserId){
        return ResultVo.success(orderService.getMySoldIdle(Long.valueOf(shUserId)));
    }
}
//这段代码是一个Java类，名为`OrderController`，它是一个控制器类，负责处理与订单相关的HTTP请求。下面对这段代码进行解读：
//
//        1. **注解**：
//
//        - `@CrossOrigin`：允许来自不同源的跨源请求。
//        - `@RestController`：标记这个类是一个RESTful控制器，所有的方法返回的对象都会被序列化为JSON格式。
//        - `@RequestMapping("/order")`：将所有的HTTP请求映射到这个类的方法上，并指定了处理地址为"/order"的请求。
//
//        2. **字段**：
//
//        - `private OrderService orderService;`：订单服务对象，用于与订单相关的业务逻辑。
//
//        3. **方法**：
//
//        - `addOrder`：一个POST请求方法，用于创建新的订单。它接受两个参数，一个是cookie中的用户ID(shUserId)，另一个是模型对象(orderModel)，并返回创建的订单。
//        - `getOrderInfo`：一个GET请求方法，用于获取特定订单的信息。它接受两个参数，一个是cookie中的用户ID(shUserId)，另一个是订单ID(id)，并返回对应的订单信息。
//        - `updateOrder`：一个POST请求方法，用于更新订单信息。它接受两个参数，一个是cookie中的用户ID(shUserId)，另一个是要更新的订单模型对象(orderModel)，并返回更新结果。
//        - `getMyOrder`：一个GET请求方法，用于获取当前用户的订单列表。它接受一个参数，即cookie用户ID(shUserId)，返回该用户的订单列表。
//        - `getMySoldIdle`：一个GET请求方法，用于获取当前用户已售的闲置物品列表。它接受一个参数，即cookie中的用户ID(shUser)，返回该用户已售的闲置物品列表。
//
//        这些方法都使用了`@NotNull`和`@NotEmpty`注解来验证传递的参数是否符合要求。通过这些方法，用户可以创建订单，获取信息，更新订单状态，以及查看自己的订单和已售的闲置物品。这样的设计可以帮助用户管理自己的订单，查看订单详情，并对订单进行更新操作。

