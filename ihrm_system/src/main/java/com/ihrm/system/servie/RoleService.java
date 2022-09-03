package com.ihrm.system.servie;

import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.system.Role;
import com.ihrm.system.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 添加角色
     */
    public void save(Role role){
        role.setId(idWorker.nextId()+"");
        roleDao.save(role);
    }

    /**
     *  更新角色
     * @param role
     */
    public void update(Role role){
        Role one = roleDao.getOne(role.getId());
        one.setName(role.getName());
        one.setDescription(role.getDescription());
        roleDao.save(one);
    }

    /**
     * 根据ID查找角色
     * @param id
     * @return
     */
    public Role findById(String id){
        return roleDao.findById(id).get();
    }

    /**
     * 删除角色
     * @param id
     */
    public void delete(String id){
        roleDao.deleteById(id);
    }

    public Page<Role> findSearch(String companyId, int page, int size){
        Specification<Role> specification = new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("companyId").as(String.class),companyId);
            }
        };
        return roleDao.findAll(specification, PageRequest.of(page-1,size));
    }
}
