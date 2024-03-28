package com.second.hand.trading.server.controller;

import com.second.hand.trading.server.enums.ErrorMsg;
import com.second.hand.trading.server.model.AdminModel;
import com.second.hand.trading.server.model.IdleItemModel;
import com.second.hand.trading.server.model.UserModel;
import com.second.hand.trading.server.service.AdminService;
import com.second.hand.trading.server.service.IdleItemService;
import com.second.hand.trading.server.service.OrderService;
import com.second.hand.trading.server.service.UserService;
import com.second.hand.trading.server.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private IdleItemService idleItemService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("login")
    public ResultVo login(@RequestParam("accountNumber") @NotNull @NotEmpty String accountNumber,
                          @RequestParam("adminPassword") @NotNull @NotEmpty String adminPassword,
                          HttpSession session){
        AdminModel adminModel=adminService.login(accountNumber,adminPassword);
        if (null == adminModel) {
            return ResultVo.fail(ErrorMsg.EMAIL_LOGIN_ERROR);
        }
        session.setAttribute("admin",adminModel);
        return ResultVo.success(adminModel);
    }

    @GetMapping("loginOut")
    public ResultVo loginOut( HttpSession session){
        session.removeAttribute("admin");
        return ResultVo.success();
    }

    @GetMapping("list")
    public ResultVo getAdminList(HttpSession session,
                                 @RequestParam(value = "page",required = false) Integer page,
                                 @RequestParam(value = "nums",required = false) Integer nums){
        if(session.getAttribute("admin")==null){
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        int p=1;
        int n=8;
        if(null!=page){
            p=page>0?page:1;
        }
        if(null!=nums){
            n=nums>0?nums:8;
        }
        return ResultVo.success(adminService.getAdminList(p,n));
    }

    @PostMapping("add")
    public ResultVo addAdmin(HttpSession session,
                             @RequestBody AdminModel adminModel){
        if(session.getAttribute("admin")==null){
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        if(adminService.addAdmin(adminModel)){
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.PARAM_ERROR);
    }

    @GetMapping("idleList")
    public ResultVo idleList(HttpSession session,
                             @RequestParam("status") @NotNull @NotEmpty Integer status,
                             @RequestParam(value = "page",required = false) Integer page,
                             @RequestParam(value = "nums",required = false) Integer nums){
        if(session.getAttribute("admin")==null){
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        int p=1;
        int n=8;
        if(null!=page){
            p=page>0?page:1;
        }
        if(null!=nums){
            n=nums>0?nums:8;
        }
        return ResultVo.success(idleItemService.adminGetIdleList(status,p,n));
    }

    @GetMapping("updateIdleStatus")
    public ResultVo updateIdleStatus(HttpSession session,
                                     @RequestParam("id") @NotNull @NotEmpty Long id,
                                     @RequestParam("status") @NotNull @NotEmpty Integer status
                                     ){
        if(session.getAttribute("admin")==null){
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        IdleItemModel idleItemModel=new IdleItemModel();
        idleItemModel.setId(id);
        idleItemModel.setIdleStatus(status.byteValue());
        if(idleItemService.updateIdleItem(idleItemModel)){
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @GetMapping("orderList")
    public ResultVo orderList(HttpSession session,
                              @RequestParam(value = "page",required = false) Integer page,
                              @RequestParam(value = "nums",required = false) Integer nums){
        if(session.getAttribute("admin")==null){
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        int p=1;
        int n=8;
        if(null!=page){
            p=page>0?page:1;
        }
        if(null!=nums){
            n=nums>0?nums:8;
        }
        return ResultVo.success(orderService.getAllOrder(p,n));
    }

    @GetMapping("deleteOrder")
    public ResultVo deleteOrder(HttpSession session,
                              @RequestParam("id") @NotNull @NotEmpty Long id){
        if(session.getAttribute("admin")==null){
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        if(orderService.deleteOrder(id)){
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @GetMapping("userList")
    public ResultVo userList(HttpSession session,
                             @RequestParam(value = "page",required = false) Integer page,
                             @RequestParam(value = "nums",required = false) Integer nums,
                             @RequestParam("status") @NotNull @NotEmpty Integer status){
        if(session.getAttribute("admin")==null){
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        int p=1;
        int n=8;
        if(null!=page){
            p=page>0?page:1;
        }
        if(null!=nums){
            n=nums>0?nums:8;
        }
        return ResultVo.success(userService.getUserByStatus(status,p,n));
    }

    @GetMapping("updateUserStatus")
    public ResultVo updateUserStatus(HttpSession session,
                                     @RequestParam("id") @NotNull @NotEmpty Long id,
                                     @RequestParam("status") @NotNull @NotEmpty Integer status){
        if(session.getAttribute("admin")==null){
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        UserModel userModel=new UserModel();
        userModel.setId(id);
        userModel.setUserStatus(status.byteValue());
        if(userService.updateUserInfo(userModel))
            return ResultVo.success();
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

}
//这段代码也是一个使用Spring框架的Java类，名为`AdminController`，也是一个RESTful API控制器，用于处理与管理员相关的HTTP请求。下面是这段代码的解读：
//
//        1. **注解**：
//
//
//        * `@CrossOrigin`：允许来自不同源的跨源请求。
//        * `@RestController`：标记这个类是一个RESTful控制器，所有的方法返回的对象都会被序列化为JSON格式。
//        * `@RequestMapping("admin")`：将所有的HTTP请求映射到这个类的方法上，并指定了处理地址为"/admin"的请求。
//        2. **字段**：
//
//
//        * `private AdminService adminService;`：一个管理员服务对象，用于处理与管理员相关的业务逻辑。
//        * `private IdleItemService idleItemService;`：一个空闲物品服务对象。
//        * `private OrderService orderService;`：一个订单服务对象。
//        * `private UserService userService;`：一个用户服务对象。
//        3. **方法**：
//
//
//        * `login`：一个GET请求方法，用于管理员登录。它接受两个参数，一个是账号(accountNumber)，一个是管理员密码(adminPassword)，并返回登录结果。
//        * `loginOut`：一个GET请求方法，用于管理员退出登录。它删除session中的管理员信息。
//        * `getAdminList`：一个GET请求方法，用于获取管理员列表。它接受两个参数，一个是页码(page)，一个是每页显示数量(nums)，并返回管理员列表。
//        * `addAdmin`：一个POST请求方法，用于添加新的管理员。它接受一个管理员信息的请求体，并返回添加结果。
//        * `idleList`：一个GET请求方法，用于获取空闲物品列表。它接受三个参数，一个是状态(status)，一个是页码(page)，一个是每页数量(nums)，并返回空闲物品列表。
//        * `updateIdleStatus`：一个GET请求方法，用于更新空闲物品状态。它接受两个参数，一个是空闲物品ID(id)，一个是状态(status)，并返回更新结果。
//        * `orderList`：一个GET请求方法，用于获取订单列表。它接受两个参数，一个是页码(page)，一个是每页数量(nums)，并返回订单列表。
//        * `deleteOrder`：一个GET请求方法，用于删除订单。它接受一个订单ID(id)，并返回删除结果。
//
//        这段代码中的方法都通过使用`@NotNull`和`@NotEmpty`注解来验证参数的非空性，以确保参数传递是有效的。同时，通过使用`HttpSession`来管理管理员的登录状态，以保护敏感操作的安全性。这些方法用于处理管理员相关的操作，并返回相应的结果。