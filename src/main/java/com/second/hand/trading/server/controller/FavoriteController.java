package com.second.hand.trading.server.controller;

import com.second.hand.trading.server.enums.ErrorMsg;
import com.second.hand.trading.server.model.FavoriteModel;
import com.second.hand.trading.server.service.FavoriteService;
import com.second.hand.trading.server.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
@CrossOrigin
@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/add")
    public ResultVo addFavorite(@CookieValue("shUserId")
                                    @NotNull(message = "登录异常 请重新登录")
                                    @NotEmpty(message = "登录异常 请重新登录") String shUserId,
                                    @RequestBody FavoriteModel favoriteModel){
        favoriteModel.setUserId(Long.valueOf(shUserId));
        favoriteModel.setCreateTime(new Date());
        if(favoriteService.addFavorite(favoriteModel)){
            return ResultVo.success(favoriteModel.getId());
        }
        return ResultVo.fail(ErrorMsg.FAVORITE_EXIT);
    }

    @GetMapping("/delete")
    public ResultVo deleteFavorite(@CookieValue("shUserId")
                                       @NotNull(message = "登录异常 请重新登录")
                                       @NotEmpty(message = "登录异常 请重新登录") String shUserId,
                                       @RequestParam Long id){
        if(favoriteService.deleteFavorite(id)){
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @GetMapping("/check")
    public ResultVo checkFavorite(@CookieValue("shUserId")
                                      @NotNull(message = "登录异常 请重新登录")
                                      @NotEmpty(message = "登录异常 请重新登录") String shUserId,
                                  @RequestParam Long idleId){
        return ResultVo.success(favoriteService.isFavorite(Long.valueOf(shUserId),idleId));
    }

    @GetMapping("/my")
    public ResultVo getMyFavorite(@CookieValue("shUserId")
                                    @NotNull(message = "登录异常 请重新登录")
                                    @NotEmpty(message = "登录异常 请重新登录") String shUserId){
        return ResultVo.success(favoriteService.getAllFavorite(Long.valueOf(shUserId)));
    }
}
//这段代码是一个Java类，名为`FavoriteController`，它是一个控制器类，负责处理关于用户的收藏操作的HTTP请求。下面对这段代码进行解析：
//
//        1. **字段**：
//
//
//        * `private FavoriteService favoriteService;`：一个收藏服务对象，用于处理与收藏相关的业务逻辑。
//        2. **方法**：
//
//
//        * `addFavorite`：一个POST请求方法，用于向收藏列表中添加新的收藏。它接两个参数，一个是cookie中的用户ID(shUserId)，另一个是收藏模型对象(favoriteModel)，并返回添加结果。
//        * `deleteFavorite`：一个GET请求方法，用于从收藏列表中删除某个收藏。它接受两个参数，一个是cookie中的用户ID(shUserId)，另一个是要删除的收藏ID(id)，并返回删除结果。
//        * `checkFavorite`：一个GET请求方法，用于检查给定用户和物品是否已经收藏。它接受两个参数，一个是cookie中的用户ID(shUserId)，另一个是物品的ID(idleId)，并返回收藏状态。
//        * `getMyFavorite`：一个GET请求方法，用于获取一个用户的所有收藏。它接受一个参数，一个是cookie中的用户ID(shUserId)，并返回收藏列表。
//
//        这些方法都使用了`@NotNull`和`@NotEmpty`注解来验证传递的参数是否符合要求。同时，这些方法都使用`@CookieValue`注解来获取cookie中的用户ID(shUserId)传递给方法中的参数。
//
//        在方法`addFavorite`中，它通过设置收藏模型的属性，调用`favoriteService`中的方法处理具体的业务逻辑，如果成功添加了收藏，那么会返回带有收藏ID的`ResultVo`对象，否则会返回`ErrorMsg.FAVORITE_EXIT`的错误消息。
//
//        在方法`deleteFavorite`中，它通过传递用户ID和收藏ID，调用`favorite`中的方法来删除具体的收藏，并返回相应的结果。
//
//        在方法`checkFavorite`中，它通过传递用户ID和物品ID，调用`favoriteService`中的方法来判断给定用户和物品是否已经收藏，并返回相应的结果。
//
//        方法`getMyFavorite`中，它通过传递用户ID，调用`favoriteService`中的方法来获取给定用户的所有收藏，并返回相应的结果。
