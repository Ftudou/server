package com.second.hand.trading.server.controller;

import com.second.hand.trading.server.enums.ErrorMsg;
import com.second.hand.trading.server.model.AddressModel;
import com.second.hand.trading.server.service.AddressService;
import com.second.hand.trading.server.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@CrossOrigin
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/info")
    public ResultVo  getAddress(@CookieValue("shUserId")
                                    @NotNull(message = "登录异常 请重新登录")
                                    @NotEmpty(message = "登录异常 请重新登录") String shUserId,
                                @RequestParam(value = "id",required = false) Long id){
        if(null==id){
            return ResultVo.success(addressService.getAddressByUser(Long.valueOf(shUserId)));
        }else {
            return ResultVo.success(addressService.getAddressById(id,Long.valueOf(shUserId)));
        }
    }
    @PostMapping("/add")
    public ResultVo addAddress(@CookieValue("shUserId")
                                   @NotNull(message = "登录异常 请重新登录")
                                   @NotEmpty(message = "登录异常 请重新登录") String shUserId,
                               @RequestBody AddressModel addressModel){
        addressModel.setUserId(Long.valueOf(shUserId));
        if(addressService.addAddress(addressModel)){
            return ResultVo.success(addressModel);
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }
    @PostMapping("/update")
    public ResultVo updateAddress(@CookieValue("shUserId")
                               @NotNull(message = "登录异常 请重新登录")
                               @NotEmpty(message = "登录异常 请重新登录") String shUserId,
                               @RequestBody AddressModel addressModel){
        addressModel.setUserId(Long.valueOf(shUserId));
        if(addressService.updateAddress(addressModel)){
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @PostMapping("/delete")
    public ResultVo deleteAddress(@CookieValue("shUserId")
                                  @NotNull(message = "登录异常 请重新登录")
                                  @NotEmpty(message = "登录异常 请重新登录") String shUserId,
                                  @RequestBody AddressModel addressModel){
        addressModel.setUserId(Long.valueOf(shUserId));
        if(addressService.deleteAddress(addressModel)){
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }
}
//这段代码是一个使用Spring框架的Java类，名为`AddressController`，它是一个RESTful API控制器，用于处理与地址相关的HTTP请求。下面是这段代码的解读：

//        1. **注解**：
//
//
//        * `@CrossOrigin`：允许来自不同源的跨源请求。
//        * `@RestController`：标记这个类是一个RESTful控制器，所有的方法返回的对象都会被序列化为JSON格式。
//        * `@RequestMapping("/address")`：将所有的HTTP请求映射到这个类的方法上。
//        2. **字段**：
//
//
//        * `private AddressService addressService;`：一个地址服务对象，用于处理与地址相关的业务逻辑。
//        3. **方法**：
//
//
//        * `getAddress`：一个GET请求方法，用于获取地址信息。它接受两个参数，一个是cookie中的用户ID，另一个是可选的ID参数。如果ID为空，则使用cookie中的用户ID获取地址信息；否则，使用ID和cookie中的用户ID获取地址信息。
//        * `addAddress`：一个POST请求方法，用于添加新的地址信息。它接受两个参数，一个是cookie中的用户ID，另一个是地址模型对象。它将用户ID设置为地址模型的属性，并尝试将地址模型添加到服务中。
//        * `updateAddress`：一个POST请求方法，用于更新已有的地址信息。它接受三个参数，一个是cookie中的用户ID，另一个是地址模型对象，还有一个可选的ID参数。它将用户ID设置为地址模型的属性，并尝试将地址模型更新到服务中。
//        * `deleteAddress`：一个POST请求方法，用于删除已有的地址信息。它接受两个参数，一个是cookie中的用户ID，另一个是地址模型对象。它将用户ID设置为地址模型的属性，并尝试将地址模型从服务中删除。
//
//        这段代码中使用了Spring框架的依赖注入特性（通过`@Autowired`注解），以便能够轻松地使用`AddressService`对象进行业务处理。此外，代码还使用了Spring框架的验证特性（通过注解），以确保输入的数据符合预期的格式和要求（如用户ID不能为空或为空字符串）。

