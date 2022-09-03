package com.ihrm.company.dao;
import com.ihrm.domain.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompanyDao extends JpaRepository<Company,String>, JpaSpecificationExecutor<Company> {
//                                    jpa仓库                                  jpa执行规范
}
