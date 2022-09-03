package com.ihrm.company.comtroller;

import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.company.service.CompanyService;
import com.ihrm.company.service.DepartmentService;
import com.ihrm.domain.company.Company;
import com.ihrm.domain.company.Department;
import com.ihrm.domain.company.response.DeptListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/company/department")
public class DepartmentController extends BaseController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CompanyService cs;

    //保存部门
    @PostMapping
    public Result save(@RequestBody Department department){
        department.setCompanyId(companyId);//companyId 来自本类继承的父类
        departmentService.save(department);
        return new Result(ResultCode.SUCCESS);
    }

    //更新部门
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable(value = "id") String id, @RequestBody Department department){
        department.setId(id);
        departmentService.update(department);
        return new Result(ResultCode.SUCCESS);
    }

    //删除部门
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable(value = "id") String id){

        departmentService.deleteById(id);
        return new Result(ResultCode.SUCCESS);

    }

    //根据ID查询部门
    @GetMapping(value = "/{id}")
    public Result findById(@PathVariable(value = "id") String id){
        Department department = departmentService.findById(id);
        return  new Result(ResultCode.SUCCESS,department);
    }

    //查询所有部门
    @GetMapping
    public Result findAll(){
        Company company = cs.findById(companyId);
        List<Department> all = departmentService.findAll(companyId);
        DeptListResult deptListResult = new DeptListResult(company,all);
        return new Result(ResultCode.SUCCESS,deptListResult);
    }
}
