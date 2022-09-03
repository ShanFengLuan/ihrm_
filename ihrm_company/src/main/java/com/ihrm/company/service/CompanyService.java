package com.ihrm.company.service;

import com.ihrm.common.utils.IdWorker;
import com.ihrm.company.dao.CompanyDao;
import com.ihrm.domain.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private IdWorker idWorker;

    /*
    * 保存企业
    */
    public void add(Company company){
        /*
        基本属性设置
        * */
       String id = idWorker.nextId() + "";
       company.setId(id);
       /*
       默认的状态
       * */
       company.setAuditState("0");//0:未审核，，1：已审核
       company.setState(1); //激活状态
        companyDao.save(company);//保存
    }

    /*
    * 更新企业
    * */
    public void update(Company company){
        Company temp = companyDao.findById(company.getId()).get();
        temp.setName(company.getName());
        temp.setCompanyPhone(company.getCompanyPhone());
        companyDao.save(temp);
    }

    /*
    * 删除企业
    * */
    public void delete(String id){
        companyDao.deleteById(id);
    }

    /*
    * 根据ID查询企业
    * */
    public Company findById(String id){
        Company company = companyDao.findById(id).get();
        return company;
    }

    /*
    * 查询企业列表
    * */
    public List<Company> findAll(){
        return companyDao.findAll();
    }

}
