package com.jztey.exam.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import com.jztey.exam.domain.User;
import com.jztey.framework.mvc.RestfulResult;


@Named
public class OrderServiceImpl3 implements OrderService4 {
	static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

	@Override
	public RestfulResult<User> select() {
		// TODO Auto-generated method stub
		return null;
	}

}
