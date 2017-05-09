package com.jztey.gxdemo.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.jztey.framework.mvc.BaseDao;
import com.jztey.gxdemo.entity.User;

/**
 * Created by ys on 2016/8/31.
 */
@Repository
public class UserDao extends BaseDao<User> {

	public User findByUniqueKey(String name) {
		List<User> result = this.em.createQuery("select o from User o where o.name=:name", User.class)
				.setParameter("name", name).getResultList();
		return CollectionUtils.isEmpty(result) ? null : result.get(0);
	}

	public List<User> findByKeys(int age, int sex) {
		List<User> result = this.em.createQuery("select o from User o where o.sex=:sex and o.age=:age", User.class)
				.setParameter("sex", sex).setParameter("age", age).getResultList();
		return CollectionUtils.isEmpty(result) ? null : result;
	}

	public List<User> findByKey(int sex) {
		List<User> result = this.em.createQuery("select o from User o where o.sex=:sex  ", User.class)
				.setParameter("sex", sex).getResultList();
		return CollectionUtils.isEmpty(result) ? null : result;
	}

}
