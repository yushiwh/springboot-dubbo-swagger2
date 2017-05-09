package com.jztey.gxdemo.service;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jztey.framework.mvc.RestfulResult;

@Named
@Service
public class DubboServiceImpl implements DubboService {

	@Reference(url="dubbo://192.168.56.1:28009/com.jztey.gxdemo.service.UserService")//dubbo直连
	private UserService userService;

	@Override
	@Transactional
	public RestfulResult<String> dubbo() {
		

		try {
			System.out.println("这是从gxdubbo打印出来的");
			return userService.getConfig();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
