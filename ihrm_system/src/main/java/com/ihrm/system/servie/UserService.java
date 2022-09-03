package com.ihrm.system.servie;

import com.ihrm.common.entity.Result;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.domain.system.User;
import com.ihrm.system.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

    public void save(User user){
        String id = idWorker.nextId()+"";
        user.setId(id);
        user.setPassword("1234");
        user.setEnableState(1);//1：启用，0：禁用
        userDao.save(user);
    }

    public void update(User user){
        //User users = userDao.findById(user.getId()).get();
        //users.setCompanyId(user.getCompanyId());
        userDao.save(user);

    }

    public User findById(String id){
        User user = userDao.findById(id).get();
        return user;
    }

    //按照给定的条件查询，并且分页展示给前端
    public Page findAll(Map<String,Object> map, int page, int size){
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> list = new ArrayList<>();
                if(!StringUtils.isEmpty(map.get("companyId"))){
                      list.add(criteriaBuilder.equal(root.get("companyId").as(String.class),(String)map.get("companyId")));
                }
                if(!StringUtils.isEmpty(map.get("departmentId"))){
                    list.add(criteriaBuilder.equal(root.get("departmentId").as(String.class),(String)map.get("departmentId")));
                }
                if(!StringUtils.isEmpty(map.get("hasDept"))){
                    if("0".equals((String)map.get("hasDept"))){
                        list.add(criteriaBuilder.isNull(root.get("departmentId")));
                    }else{
                        list.add(criteriaBuilder.isNotNull(root.get("departmentId")));
                    }
                }

                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        Page<User> all = userDao.findAll(spec, new PageRequest(page-1, size));


        return all;
    }

    public void deleteById(String id){
        userDao.deleteById(id);
    }
}
