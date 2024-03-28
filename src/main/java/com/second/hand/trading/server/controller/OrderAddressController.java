package com.second.hand.trading.server.controller;

import com.second.hand.trading.server.enums.ErrorMsg;
import com.second.hand.trading.server.model.OrderAddressModel;
import com.second.hand.trading.server.service.OrderAddressService;
import com.second.hand.trading.server.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@CrossOrigin
@RestController
@RequestMapping("/order-address")
public class OrderAddressController {

    @Autowired
    private OrderAddressService orderAddressService;

    @PostMapping("/add")
    public ResultVo addOrderAddress(@CookieValue("shUserId")
                                        @NotNull(message = "登录异常 请重新登录")
                                        @NotEmpty(message = "登录异常 请重新登录") String shUserId,
                                    @RequestBody OrderAddressModel orderAddressModel){
        return ResultVo.success(orderAddressService.addOrderAddress(orderAddressModel));
    }

    @PostMapping("/update")
    public ResultVo updateOrderAddress(@CookieValue("shUserId")
                                       @NotNull(message = "登录异常 请重新登录")
                                       @NotEmpty(message = "登录异常 请重新登录") String shUserId,
                                       @RequestBody OrderAddressModel orderAddressModel){
        if(orderAddressService.updateOrderAddress(orderAddressModel)){
            return ResultVo.success(orderAddressModel);
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @GetMapping("/info")
    public ResultVo getOrderAddress(@CookieValue("shUserId")
                                    @NotNull(message = "登录异常 请重新登录")
                                    @NotEmpty(message = "登录异常 请重新登录") String shUserId,
                                    @RequestParam Long orderId){
        return ResultVo.success(orderAddressService.getOrderAddress(orderId));
    }
}
//这段代码是一个Java类，名为`OrderAddressController`，它是一个控制器类，负责处理与订单地址相关的HTTP请求。下面对这段代码进行解读：
//
//        1. **注解**：* `@Autowired`：自动装配，用于注入`OrderAddressService`依赖。
//        2. **字段**：
//
//
//        * `private OrderAddressService orderAddressService;`：订单地址服务对象，用于处理与订单地址相关的业务逻辑。
//        3. **方法**：
//
//
//        * `addOrderAddress`：一个POST请求方法，用于添加订单地址。它接受两个参数，一个是cookie中的用户ID(shUserId)，另一个订单地址模型对象(orderAddressModel)，并返回添加结果。
//        * `updateOrderAddress`：一个POST请求方法，用于更新订单地址。它同样接受两个参数，一个是cookie中的用户ID(shUserId)，另一个是要更新的订单地址模型对象(orderAddressModel)，并返回更新结果。
//        * `getOrderAddress`：一个GET请求方法，用于获取特定订单的地址信息。它接受两个参数，一个是cookie中的用户ID(shUserId)，另一个是订单ID(orderId)，并返回对应的订单地址信息。
//
//        这些方法都使用了`@NotNull`和`@NotEmpty`注解来验证传递的参数是否符合要求。通过这些方法，用户可以添加和更新订单地址，也可以获取特定订单的地址信息。这样的设计可以帮助用户管理自己的订单地址信息，并查看订单的详细地址。

