package com.jztey.exam.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jztey.exam.domain.User;
import com.jztey.framework.mvc.RestfulResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/dubbo2")
@ResponseBody
public interface OrderService3 {

	/**
	 * 因为@ApiImplicitParam中的paramType参数配置不对，导致调试请求失败的情况。 关于paramType参数的配置： *
	 * 
	 * query：对应@RequestParam的参数。 header：对应头部信息的参数 path：对用url中的参数
	 * body：对应@RequestBody的参数
	 * 
	 */

	@ApiOperation(value = "查询用户3", notes = "查询所有的用户2")
	@RequestMapping(value = { "/pb/users" }, method = RequestMethod.GET)
	RestfulResult<User> select();

	 

}
