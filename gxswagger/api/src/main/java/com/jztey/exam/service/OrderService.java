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

@RequestMapping("/dubbo")

@ResponseBody
public interface OrderService {

	/**
	 * 因为@ApiImplicitParam中的paramType参数配置不对，导致调试请求失败的情况。 关于paramType参数的配置： *
	 * 
	 * query：对应@RequestParam的参数。 header：对应头部信息的参数 path：对用url中的参数
	 * body：对应@RequestBody的参数
	 * 
	 */

	@ApiOperation(value = "查询用户", notes = "查询所有的用户")
	@RequestMapping(value = { "/pb/users" }, method = RequestMethod.GET)
	RestfulResult<User> select();

	@ApiOperation(value = "创建用户", notes = "创建用户")
	@RequestMapping(value = "/pt/users", method = RequestMethod.POST)
	@ApiImplicitParam(name = "user", value = "用户详细实体user", paramType = "body", required = true, dataType = "User")
	RestfulResult<User> createUser(@RequestBody User user);

	@ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
	@ApiImplicitParam(name = "id", value = "用户ID", paramType = "path", required = true, dataType = "Long")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	RestfulResult<String> deleteUser(@PathVariable Long id);

	@ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "id", value = "用户ID", paramType = "path", required = true, dataType = "Long")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	RestfulResult<User> selectById(@PathVariable Long id);

	@ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "用户ID", paramType = "path", required = true, dataType = "Long"),
			@ApiImplicitParam(name = "user", value = "用户详细实体user", paramType = "body", required = true, dataType = "User") })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	RestfulResult<User> updateUser(@PathVariable Long id, @RequestBody User user);

}
