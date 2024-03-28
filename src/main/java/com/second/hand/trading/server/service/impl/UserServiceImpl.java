package com.second.hand.trading.server.service.impl;

import com.second.hand.trading.server.dao.UserDao;
import com.second.hand.trading.server.model.UserModel;
import com.second.hand.trading.server.service.UserService;
import com.second.hand.trading.server.vo.PageVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;



@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    /**
     * 查询一个用户的公开信息
     * @param id
     * @return
     */
    public UserModel getUser(Long id){
        return userDao.selectByPrimaryKey(id);
    }

    /**
     * 登录，安全问题未解决
     * @param accountNumber
     * @param userPassword
     * @return
     */
    public UserModel userLogin(String accountNumber, String userPassword){
        return userDao.userLogin(accountNumber,userPassword);
    }

    /**
     *注册
     * @param userModel
     * @return
     */
    public boolean userSignIn(UserModel userModel){
        return userDao.insert(userModel) == 1;
    }

    /**
     *修改用户公开信息，未验证用户身份
     * @param userModel
     * @return
     */
    public boolean updateUserInfo(UserModel userModel){
        return userDao.updateByPrimaryKeySelective(userModel)==1;
    }

    /**
     * 修改密码
     * @param newPassword
     * @param oldPassword
     * @param id
     * @return
     */
    public boolean updatePassword(String newPassword, String oldPassword,Long id){
        return userDao.updatePassword(newPassword,oldPassword,id)==1;
    }

    public PageVo<UserModel> getUserByStatus(int status,int page ,int nums){
        List<UserModel> list;
        int count=0;
        if(status==0){
            count=userDao.countNormalUser();
            list=userDao.getNormalUser((page-1)*nums, nums);
        }else {
            count=userDao.countBanUser();
            list=userDao.getBanUser((page-1)*nums, nums);
        }
        return new PageVo<>(list,count);
    }

}
//这段代码是一个Java类，它实现了UserService接口，并使用了Spring框架的@Service和@Resource注解。这个类包含了几个方法，用于处理用户相关的操作，如查询用户信息、登录、注册、修改用户信息、修改密码等。这个类中还包含了一个方法用于分页获取用户信息。
//
//        以下是代码的主要部分：
//
//        * `getUser(Long id)`：根据用户的ID查询用户的公开信息。
//        * `userLogin(String accountNumber, String userPassword)`：使用用户名和密码进行登录操作。
//        * `userSignIn(UserModel userModel)`：将新用户的信息插入数据库中以进行注册。
//        * `updateUserInfo(UserModel userModel)`：根据提供的用户模型更新用户的公开信息，但并未验证用户身份。
//        * `updatePassword(String newPassword, String oldPassword, Long id)`：根据新密码、旧密码和用户ID更新用户的密码。
//        * `getUserByStatus(int status, int page, int nums)`：根据用户的状态和分页参数获取特定用户的信息。这个方法可以根据状态（正常用户或封禁用户）获取不同的用户列表，并返回一个PageVo对象，其中包含了分页信息和用户列表。
//
//        这段代码主要实现了对用户信息的基本操作，但在某些方法中，如`userLogin`和`updateUserInfo`，需要额外的验证和安全性措施，以防止未经授权的操作或恶意行为。此外，对于登录操作，应该考虑使用更安全的方法，如使用密码哈希存储密码，并使用安全的加密算法进行通信。

