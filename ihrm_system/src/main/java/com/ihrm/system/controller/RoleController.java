package com.ihrm.system.controller;

import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.PageResult;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.domain.system.Role;
import com.ihrm.system.servie.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/sys/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     * 添加角色
     * @param role
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Role role){
        role.setCompanyId(companyId);
        roleService.save(role);
        return Result.SUCCESS();
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable(value = "id") String id){
        roleService.delete(id);
        return Result.SUCCESS();
    }

    /**
     * 更新角色
     * @param id
     * @param role
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable(value ="id") String id,@RequestBody Role role){
        roleService.update(role);
        return Result.SUCCESS();

    }

    /**
     * 根据ID获取角色信息
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public Result findById(@PathVariable(value = "id") String id){
        Role role = roleService.findById(id);
        return new Result(ResultCode.SUCCESS,role);
    }

    /**
     * 分页查询角色
     * @param page
     * @param pagesize
     * @return
     */
    @GetMapping
    public Result findByPage(int page,int pagesize){
        System.out.println(page+","+pagesize);
        Page<Role> search = roleService.findSearch(companyId, page, pagesize);
        PageResult<Role> pageResult = new PageResult(search.getTotalElements(), search.getContent());
        return new Result(ResultCode.SUCCESS,pageResult);
    }
}
