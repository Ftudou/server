package com.second.hand.trading.server.service.impl;

import com.second.hand.trading.server.dao.AdminDao;
import com.second.hand.trading.server.model.AdminModel;
import com.second.hand.trading.server.service.AdminService;
import com.second.hand.trading.server.vo.PageVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;

    public AdminModel login(String accountNumber, String adminPassword){
        return adminDao.login(accountNumber,adminPassword);
    }

    public PageVo<AdminModel> getAdminList(int page, int nums){
        List<AdminModel> list=adminDao.getList((page-1)*nums,nums);
        int count=adminDao.getCount();
        return new PageVo<>(list,count);
    }

    public boolean addAdmin(AdminModel adminModel){
        return adminDao.insert(adminModel)==1;
    }
}
//这段代码是一个Java服务层实现类 `AdminServiceImpl`，它实现了 `AdminService` 接口。在这个服务层中，主要提供了三个方法：`login` 用于管理员登录，`getAdminList` 用于获取管理员列表，`addAdmin` 用于添加新的管理员。
//
//        1. `@Service` 注解表示这个类是一个服务层的组件，Spring框架会对其进行管理。
//        2. `@Resource` 注解用于注入 `AdminDao`，它是数据访问层（DAO）的一个组件，用于与数据库进行交互。
//        3. `login` 方法接受用户名和密码作为参数，调用 `adminDao.login` 方法进行登录验证，并返回一个 `AdminModel` 对象，该对象可能包含登录管理员的详细信息。
//        4. `getAdminList` 方法接受页码 `page` 和每页显示数量 `nums` 作为参数，调用 `adminDao.getList` 方法获取对应页码的管理员列表，并计算总的管理员数量，最后返回一个 `PageVo<AdminModel>` 对象，其中包含列表和总数。
//        5. `addAdmin` 方法接受一个 `AdminModel` 对象作为参数，调用 `adminDao.insert` 方法将新的管理员信息添加到数据库中，并返回一个布尔值，表示添加操作是否成功。
//
//        这个服务层是典型的三层架构（表示层、业务逻辑层、数据访问层）中的业务逻辑层，它通过调用DAO层的方法来操作数据库，并将结果返回给控制器层（Controller），控制器层再将结果呈现给用户。这种设计模式有利于代码的维护和扩展。

