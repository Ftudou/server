package com.second.hand.trading.server.controller;

import com.second.hand.trading.server.enums.ErrorMsg;
import com.second.hand.trading.server.model.MessageModel;
import com.second.hand.trading.server.service.MessageService;
import com.second.hand.trading.server.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
@CrossOrigin
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public ResultVo sendMessage(@CookieValue("shUserId")
                                @NotNull(message = "登录异常 请重新登录")
                                @NotEmpty(message = "登录异常 请重新登录") String shUserId,
                                @RequestBody MessageModel messageModel){
        messageModel.setUserId(Long.valueOf(shUserId));
        messageModel.setCreateTime(new Date());
        if(messageService.addMessage(messageModel)){
            return ResultVo.success(messageModel);
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @GetMapping("/info")
    public ResultVo getMessage(@RequestParam Long id){
        return ResultVo.success(messageService.getMessage(id));
    }

    @GetMapping("/idle")
    public ResultVo getAllIdleMessage(@RequestParam Long idleId){
        return ResultVo.success(messageService.getAllIdleMessage(idleId));
    }

    @GetMapping("/my")
    public ResultVo getAllMyMessage(@CookieValue("shUserId")
                                        @NotNull(message = "登录异常 请重新登录")
                                        @NotEmpty(message = "登录异常 请重新登录") String shUserId){
        return ResultVo.success(messageService.getAllMyMessage(Long.valueOf(shUserId)));
    }

    @GetMapping("/delete")
    public ResultVo deleteMessage(@CookieValue("shUserId")
                                  @NotNull(message = "登录异常 请重新登录")
                                  @NotEmpty(message = "登录异常 请重新登录") String shUserId,
                                  @RequestParam Long id){
        if(messageService.deleteMessage(id)){
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }
}
//这段代码是一个Java类，名为`MessageController`，它是一个控制器类，负责处理与消息相关的HTTP请求。下面对这段代码进行解读：
//
//        1. **注解**：
//
//
//        * `@CrossOrigin`：允许来自不同源的跨源请求。
//        * `@RestController`：标记这个类是一个RESTful控制器，所有的方法返回的对象都会被序列化为JSON格式。
//        * `@RequestMapping("/message")`：将所有的HTTP请求映射到这个类的方法上，并指定了处理地址为"/message"的请求。
//        2. **字段**：
//
//
//        * `private MessageService messageService;`：消息服务对象，用于处理与消息相关的业务逻辑。
//        3. **方法**：
//
//
//        * `sendMessage`：一个POST请求方法，用于发送消息。它接受两个参数，一个是cookie中的用户ID(shUserId)，另一个是消息模型对象(messageModel)，并返回发送结果。
//        * `getMessage`：一个GET请求方法，用于获取特定ID的消息内容。它接受一个参数，即消息的ID，返回对应的消息内容。
//        * `getAllIdleMessage`：一个GET请求方法，用于获取特定闲置物品相关的所有消息。它接受一个参数，即闲置物品的ID，返回与该闲置物品相关的所有消息。
//        * `getAllMyMessage`：一个GET请求方法，用于获取当前用户的所有消息。它接受一个参数，即cookie中的用户ID(shUserId)，返回该用户所有的消息。
//        * `deleteMessage`：一个GET请求方法，用于删除指定ID的消息。它接受两个参数，一个是cookie中的用户ID(shUserId)，另一个是要删除的消息ID(id)，并返回删除结果。
//
//        这些方法都使用了`@NotNull`和`@NotEmpty`注解来验证传递的参数是否符合要求。通过这些方法，用户可以发送消息，获取消息内容，查看闲置物品相关的消息，以及删除指定ID的消息。这样的设计可以帮助用户进行消息管理和与他人进行交流。

