package com.ihrm.company.service;

import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.common.exception.CommonException;
import com.ihrm.common.service.BaseService;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.company.dao.DepartmentDao;
import com.ihrm.domain.company.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class DepartmentService extends BaseService {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private IdWorker idWorker;


    //保存部门
    public void save(Department department){
        //设计主键的值
        String id = idWorker.nextId()+"";
        department.setId(id);
        //调用dao保存部门
        departmentDao.save(department);
    }
    //更新部门
    public void update(Department department){
        //根据ID查询部门
        Department department1 = departmentDao.findById(department.getId()).get();
        //设置部门属性
        department1.setCode(department.getCode());
        department1.setIntroduce(department.getIntroduce());
        department1.setName(department.getName());
        department1.setCompanyId(department.getCompanyId());
        department1.setManager(department.getManager());
        department1.setCreateTime(department.getCreateTime());
        department1.setPid(department.getPid());
        //更新部门
        departmentDao.save(department1);

    }
    //根据ID查询部门
    public Department findById(String id){
        Department department = departmentDao.findById(id).get();

        return department;
    }
    //查询全部部门列表
    public List<Department> findAll(String companyId){

        return  departmentDao.findAll(getSpecification(companyId));

    }
    //根据ID删除部门
    public Result deleteById(String id){
        departmentDao.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

}
