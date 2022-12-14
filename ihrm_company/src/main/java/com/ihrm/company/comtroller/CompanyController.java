package com.ihrm.company.comtroller;

import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.common.exception.CommonException;
import com.ihrm.company.service.CompanyService;
import com.ihrm.domain.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    //保存企业
   @PostMapping()
    public Result save(@RequestBody Company company){
        companyService.add(company);

        return new Result(ResultCode.SUCCESS);
    }
//    根据ID更新企业
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable(value = "id") String id,@RequestBody Company company){
       company.setId(id);
       companyService.update(company);
       return new Result(ResultCode.SUCCESS);

    }
//    根据id删除企业
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable(value = "id") String id){
       companyService.delete(id);
       return new Result(ResultCode.SUCCESS);

    }
//    根据id查询企业
    @GetMapping(value = "/{id}")
    public Result findById(@PathVariable(value = "id") String id){
        System.out.println("接收到的ID："+id);
        Company company = companyService.findById(id);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(company);
        return result;


    }
//    查询全部企业列表
    @GetMapping
    public Result findAll(){

        List<Company> companies = companyService.findAll();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(companies);
        return result;
    }
}
