package com.second.hand.trading.server.controller;

import com.second.hand.trading.server.enums.ErrorMsg;
import com.second.hand.trading.server.model.IdleItemModel;
import com.second.hand.trading.server.service.IdleItemService;
import com.second.hand.trading.server.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
@CrossOrigin
@RestController
@RequestMapping("idle")
public class IdleItemController {

    @Autowired
    private IdleItemService idleItemService;

    @PostMapping("add")
    public ResultVo addIdleItem(@CookieValue("shUserId")
                                    @NotNull(message = "登录异常 请重新登录")
                                    @NotEmpty(message = "登录异常 请重新登录") String shUserId,
                                @RequestBody IdleItemModel idleItemModel){
        idleItemModel.setUserId(Long.valueOf(shUserId));
        idleItemModel.setIdleStatus((byte) 1);
        idleItemModel.setReleaseTime(new Date());
        if(idleItemService.addIdleItem(idleItemModel)){
            return ResultVo.success(idleItemModel);
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @GetMapping("info")
    public ResultVo getIdleItem(@RequestParam Long id){
        return ResultVo.success(idleItemService.getIdleItem(id));
    }

    @GetMapping("all")
    public ResultVo getAllIdleItem(@CookieValue("shUserId")
                                       @NotNull(message = "登录异常 请重新登录")
                                       @NotEmpty(message = "登录异常 请重新登录") String shUserId){
        return ResultVo.success(idleItemService.getAllIdelItem(Long.valueOf(shUserId)));
    }

    @GetMapping("find")
    public ResultVo findIdleItem(@RequestParam(value = "findValue",required = false) String findValue,
                                 @RequestParam(value = "page",required = false) Integer page,
                                 @RequestParam(value = "nums",required = false) Integer nums){
        if(null==findValue){
            findValue="";
        }
        int p=1;
        int n=8;
        if(null!=page){
            p=page>0?page:1;
        }
        if(null!=nums){
            n=nums>0?nums:8;
        }
        return ResultVo.success(idleItemService.findIdleItem(findValue,p,n));
    }

    @GetMapping("lable")
    public ResultVo findIdleItemByLable(@RequestParam(value = "idleLabel",required = true) Integer idleLabel,
                                 @RequestParam(value = "page",required = false) Integer page,
                                 @RequestParam(value = "nums",required = false) Integer nums){
        int p=1;
        int n=8;
        if(null!=page){
            p=page>0?page:1;
        }
        if(null!=nums){
            n=nums>0?nums:8;
        }
        return ResultVo.success(idleItemService.findIdleItemByLable(idleLabel,p,n));
    }

    @PostMapping("update")
    public ResultVo updateIdleItem(@CookieValue("shUserId")
                                       @NotNull(message = "登录异常 请重新登录")
                                       @NotEmpty(message = "登录异常 请重新登录") String shUserId,
                                   @RequestBody IdleItemModel idleItemModel){
        idleItemModel.setUserId(Long.valueOf(shUserId));
        if(idleItemService.updateIdleItem(idleItemModel)){
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }
}
//这段代码是一个Java类，名为`IdleItemController`，它是一个控制器类，用于处理与闲置物品相关的HTTP请求。下面对这段代码进行解读：
//
//        1. **字段**：
//
//
//        * `private IdleItemService idleItemService;`：闲置物品服务对象，用于处理与闲置物品相关的业务逻辑。
//        2. **方法**：
//
//
//        * `addIdleItem`：一个POST请求方法，用于添加闲置物品信息。它接受两个参数，一个是cookie中的用户ID(shUserId)，另一个是闲置物品模型对象(idleItemModel)，并返回添加结果。
//        * `getIdleItem`：一个GET请求方法，用于获取特定ID的闲置物品信息。它接受一个参数，即闲置物品的ID，返回对应的闲置物品信息。
//        * `getAllIdleItem`：一个GET请求方法，用于获取用户的所有闲置物品信息。它接受一个参数，即cookie中的用户ID(shUserId)，返回该用户的所有闲置物品信息。
//        * `findIdleItem`：一个GET请求方法，用于按条件查找闲置物品信息。它接受三个可选参数，查找条件(findValue)，页码(page)，每页数量(nums)，并返回符合条件的闲置物品信息。
//        * `findIdleItemByLabel`：一个GET请求方法，用于按标签查找闲置物品信息。它接受三个参数，查找条件(idleLabel)、页码(page)、每页数量(nums)，并返回符合该标签的闲置物品信息。
//        * `updateIdleItem`：一个POST请求方法，用于更新闲置物品信息。它接受两个参数，一个是cookie中的用户ID(shUserId)，另一个是要更新的闲置物品模型对象(idleItemModel)，并返回更新结果。
//
//        这些方法都使用了`@NotNull`和`@NotEmpty`注解来验证传入的参数是否符合要求。通过这些方法，用户可以添加、获取、查找、更新自己的闲置物品信息，同时还可以按照特定标签进行检索。这样的设计能够帮助用户方便地管理自己的闲置物品，并根据需要进行搜索和更新。

