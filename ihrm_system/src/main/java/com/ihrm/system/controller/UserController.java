package com.ihrm.system.controller;

import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.PageResult;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.domain.company.Company;
import com.ihrm.domain.company.Department;
import com.ihrm.domain.company.response.DeptListResult;
import com.ihrm.domain.system.User;
import com.ihrm.system.servie.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/sys/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;


    //保存部门
    @PostMapping
    public Result save(@RequestBody User user){
        user.setCompanyId(companyId);//companyId 来自本类继承的父类
        user.setCompanyName(companyName);
        userService.save(user);
        return new Result(ResultCode.SUCCESS);
    }

    //更新部门
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable(value = "id") String id, @RequestBody User user){
        user.setId(id);
        userService.update(user);
        return new Result(ResultCode.SUCCESS);
    }

    //删除部门
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable(value = "id") String id){

        userService.deleteById(id);
        return new Result(ResultCode.SUCCESS);

    }

    //根据ID查询部门
    @GetMapping(value = "/{id}")
    public Result findById(@PathVariable(value = "id") String id){
        User user = userService.findById(id);
        return  new Result(ResultCode.SUCCESS,user);
    }

    //查询所有
    @GetMapping
    public Result findAll(int page,int size,@RequestParam Map map){
        map.put("companyId",companyId);
        Page all = userService.findAll(map, page, size);
        PageResult pageResult = new PageResult(all.getTotalElements(), all.getContent());
        return new Result(ResultCode.SUCCESS,pageResult);
    }
}
