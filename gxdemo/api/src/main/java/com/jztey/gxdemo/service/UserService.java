package com.jztey.gxdemo.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jztey.framework.mvc.Paging;
import com.jztey.framework.mvc.RestfulPagingResult;
import com.jztey.framework.mvc.RestfulResult;
import com.jztey.gxdemo.domain.Users;
import com.jztey.gxdemo.entity.Demo;
import com.jztey.gxdemo.entity.User;
import com.jztey.gxdemo.entity.UserVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 定义接口，仅仅只是展示功能使用 功能不断的丰富中
 * 
 * @author ys 2016-09-20
 */
@RequestMapping("/user")
@Api(tags = "用户")
@ResponseBody
public interface UserService {
	// 只为生成API用
	class RestfulResultUser extends RestfulResult<User> {
	}

	class RestfulPagingResultUser extends RestfulPagingResult<User> {
	}

	//////////////////////////////////////////////////////////
	//////////////////// 增加//////////////////////////////////
	//////////////////////////////////////////////////////////

	@RequestMapping(value = "/pt/add", method = RequestMethod.POST)
	@ApiOperation(value = "添加User表数据", response = RestfulResultUser.class)
	RestfulResult<User> insertOrUpdate(@ApiParam @RequestBody @Valid User user);

	//////////////////////////////////////////////////////////
	//////////////////// 删除//////////////////////////////////
	//////////////////////////////////////////////////////////

	@RequestMapping(value = "/pt/{age}/{sex}", method = RequestMethod.DELETE)
	@ApiOperation(value = "根据年龄性别删除User表数据", response = RestfulResultUser.class)
	RestfulResult<User> delete(@PathVariable("age") int age, @PathVariable("sex") int sex);

	//////////////////////////////////////////////////////////
	//////////////////// GET方法提交查询//////////////////////
	//////////////////////////////////////////////////////////

	@RequestMapping(value = "/pb", method = RequestMethod.GET)
	@ApiOperation(value = "User列表，GET方法带参数，如http://localhost:8080/user/pb?sex=1&page=2&pageSize=10", response = RestfulResultUser.class)
	RestfulPagingResult<User> findPage(@RequestParam("sex") int sex, @RequestParam("page") int page,
			@RequestParam("pageSize") int pageSize, @RequestParam(value = "total", defaultValue = "11") int total);

	@RequestMapping(value = "/pb/pathparam/{sex}", method = RequestMethod.GET)
	@ApiOperation(value = "传递单个参数,不带分页，GET方法PathParam,指定的是URL中只出现参数的值，不出现键值对，如http://localhost:8080/user/pb/pathparam/1", response = RestfulResultUser.class)
	RestfulResult<User> findBySexPathParam(@PathVariable("sex") int sex);

	@RequestMapping(value = "/pb/queryparam", method = RequestMethod.GET)
	@ApiOperation(value = "User列表,POST传递单个参数,不带分页，GET方法,URL中的参数是以键值对的形式出现的，如http://localhost:8080/user/pb/queryparam?sex=1", response = RestfulResultUser.class)
	RestfulResult<User> findBySexQueryParam(@RequestParam("sex") int sex);

	@RequestMapping(value = "/pb/getConfig", method = RequestMethod.GET)
	@ApiOperation(value = "取得配置文件", response = RestfulResultUser.class)
	public RestfulResult<String> getConfig() throws Exception;

	//////////////////////////////////////////////////////////
	//////////////////// POST方法提交查询//////////////////////
	//////////////////////////////////////////////////////////

	@RequestMapping(value = "/pb/users", method = RequestMethod.POST)
	@ApiOperation(value = "User列表,复杂对象传递带分页", response = RestfulResultUser.class)
	RestfulPagingResult<User> findPageByEntity(@ApiParam @Valid UserVo userVo);

	@RequestMapping(value = "/pb/sex", method = RequestMethod.POST)
	@ApiOperation(value = "User列表,POST传递单个参数,不带分页", response = RestfulResultUser.class)
	RestfulResult<User> findBySex(@RequestParam("sex") int sex);

	@RequestMapping(value = "/pb/sexNoPage", method = RequestMethod.POST)
	@ApiOperation(value = "User列表,POST传递单个参数,不带分页,不带｛｝", response = RestfulResultUser.class)
	RestfulResult<User> findBySexSimple(@RequestParam("sex") int sex);

	@RequestMapping(value = "/pb/search", method = RequestMethod.POST)
	@ApiOperation(value = "通用的搜索User列表", response = RestfulResultUser.class)
	RestfulPagingResult<User> search(@ApiParam Paging<User> paging);

	@RequestMapping(value = "/pb/paramHeader", method = RequestMethod.POST)
	@ApiOperation(value = "通过head传递参数", response = RestfulResultUser.class)
	RestfulPagingResult<User> paramHeader(@RequestHeader("headParam") String headParam);

	///// 调用RestFul接口//////
	/// user/pb/getConfigHttpClient/2000560699/111
	@RequestMapping(value = "/pb/getConfigHttpClient/{healthAccount}/{httpType}", method = RequestMethod.GET)
	@ApiOperation(value = "从配置文件中取出URL，根据传入的get或者post并且进行http请求", response = RestfulResultUser.class)
	public RestfulResult<Demo> getConfigHttpClient(@PathVariable("healthAccount") int healthAccount,
			@PathVariable("httpType") String httpType) throws Exception;

	///// redis的使用////////
	@RequestMapping(value = "/pb/redis/{healthAccount}", method = RequestMethod.GET)
	@ApiOperation(value = "redis请求", response = RestfulResultUser.class)
	public String getRedis(@PathVariable("healthAccount") int healthAccount) throws Exception;

	///// mybatis的使用////////
	@RequestMapping(value = "/pb/mybatis", method = RequestMethod.GET)
	@ApiOperation(value = "mybatis请求", response = RestfulResultUser.class)
	public RestfulResult<Users> getMybatis() throws Exception;

	///// mybatis的使用////////
	@RequestMapping(value = "/pb/mybatis2", method = RequestMethod.GET)
	@ApiOperation(value = "mybatis2请求", response = RestfulResultUser.class)
	public RestfulResult<Users> getMybatis2() throws Exception;

}
