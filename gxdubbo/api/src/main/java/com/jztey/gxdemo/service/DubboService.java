package com.jztey.gxdemo.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jztey.framework.mvc.RestfulResult;

@RequestMapping("/dubbo")
@ResponseBody
public interface DubboService {
	@RequestMapping(value = "/pb", method = RequestMethod.GET)
	RestfulResult<String> dubbo();
}
