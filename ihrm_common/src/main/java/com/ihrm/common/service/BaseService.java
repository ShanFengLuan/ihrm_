package com.ihrm.common.service;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
/*
* 公共服务类
* */

public class BaseService<T> {
     protected    Specification<T> getSpecification(String companyId){
            Specification<T> spec = new Specification() {
                @Override
                public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    System.out.println("companyID:-----------"+companyId);
                    return   criteriaBuilder.equal(root.get("companyId").as(String.class),companyId);
                }
            };
        return spec;
        }
}
