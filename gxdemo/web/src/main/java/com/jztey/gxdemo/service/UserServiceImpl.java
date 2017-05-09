package com.jztey.gxdemo.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jztey.framework.cache.SpelCacheNameCacheResolver;
import com.jztey.framework.mvc.BaseDao;
import com.jztey.framework.mvc.BaseService;
import com.jztey.framework.mvc.Paging;
import com.jztey.framework.mvc.RestfulPagingResult;
import com.jztey.framework.mvc.RestfulResult;
import com.jztey.gxdemo.dao.UserDao;
import com.jztey.gxdemo.dao.mapper1.UserMapper1;
import com.jztey.gxdemo.dao.mapper2.UserMapper2;
import com.jztey.gxdemo.domain.Users;
import com.jztey.gxdemo.entity.Demo;
import com.jztey.gxdemo.entity.User;
import com.jztey.gxdemo.entity.UserVo;


@com.alibaba.dubbo.config.annotation.Service
@Named
@Service
@CacheConfig(cacheNames = SpelCacheNameCacheResolver.SPEL_CACHE_NAME) 
@ConfigurationProperties(prefix = "demoN", locations = { "classpath:test.properties" }) // 双重配置文件读取，自定义
public class UserServiceImpl extends BaseService<User> implements UserService {
	private final static String DEMO_KEY = "DEMO: com.jztey.demo.service.UserServiceImpl";
	@Autowired
	private UserDao userDao;

	@Autowired
	private UserMapper1 userMapper1;

	@Autowired
	private UserMapper2 userMapper2;

	private String userNameN;// 自定义读取配置文件的变量

	public String getUserNameN() {
		return userNameN;
	}

	public void setUserNameN(String userNameN) {
		this.userNameN = userNameN;
	}

	@Value("${demo.account}")
	private String configaccount;
	@Value("${demo.remark}")
	private String remark;

	@Value("${test.url}")
	private String testUrl;
	@Value("${test.url.get}")
	private String testUrlGet;

	@Inject
	@Resource(name = "redisTemplate")
	private ValueOperations<String, String> valueOperations;

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public BaseDao<User> getDao() {
		return userDao;
	}

	@Override
	@Transactional
	public RestfulResult<User> insertOrUpdate(User user) {
		User result = userDao.findByUniqueKey(user.getName());
		if (null == result) {
			userDao.persist(user);// 没有就增加
		} else {
			result.setId(user.getId());// 有就更新
			userDao.merge(result);
		}
		return new RestfulResult<>(user);
	}

	@Override
	@Transactional
	public RestfulResult<User> delete(int age, int sex) {
		List<User> result = userDao.findByKeys(age, sex);
		if (null != result) {
			for (User user : result) {
				this.remove(user.getId());// 进行批量删除
			}
			return new RestfulResult<>(result);
		}
		return new RestfulResult<>();
	}

	@Override
	@Transactional(readOnly = true)
	public RestfulPagingResult<User> findPage(int sex, int page, int pageSize, int total) {
		System.out.println("get");

		User query = new User();
		query.setSex(sex);
		// 统一使用查询接口
		Paging<User> paging = new Paging<>(page, pageSize, query);
		paging.setTotal(total);
		return this.search(paging);
	}

	@Override
	@Transactional(readOnly = true)
	public RestfulPagingResult<User> search(Paging<User> paging) {
		System.out.println("search");
		List<User> userList = new ArrayList<>();

		if (-1 == paging.getTotal()) { // total没有传上来
			// 查询total
			paging.setTotal(this.countByExample(paging.getQuery()).intValue());
		}

		userList = this.findByExample(paging);
		return new RestfulPagingResult<User>(userList, paging.getTotal());
	}

	@Override
	@Transactional(readOnly = true)
	public RestfulPagingResult<User> findPageByEntity(UserVo user) {
		System.out.println("findPageByEntity");
		User query = new User();
		query.setSex(user.getUser().getSex());
		// 统一使用查询接口
		Paging<User> paging = new Paging<>(user.getPaging().getPage(), user.getPaging().getPageSize(), query);
		paging.setTotal(user.getPaging().getTotal());
		return this.search(paging);

	}

	@Override
	@Transactional(readOnly = true)
	public RestfulResult<User> findBySex(int sex) {
		System.out.println("findPageBySex");
		List<User> result = userDao.findByKey(sex);
		return new RestfulResult<>(result);
	}

	@Override
	@Transactional(readOnly = true)
	public RestfulResult<User> findBySexSimple(int sex) {
		return findBySex(sex);
	}

	@Override
	public RestfulPagingResult<User> paramHeader(String headParam) {
		System.out.println("headParam:" + headParam);
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public RestfulResult<String> getConfig() throws Exception {
		String configvalue = configaccount + "----" + remark;
		String configvalueN = configvalue + "$$$$$$$" + userNameN;
		return new RestfulResult<>(configvalueN);
	}

	@Override
	@Transactional(readOnly = true)
	public RestfulResult<User> findBySexPathParam(int sex) {
		return findBySex(sex);
	}

	@Override
	@Transactional(readOnly = true)
	public RestfulResult<User> findBySexQueryParam(int sex) {
		return findBySex(sex);
	}

	@Override
	public String getRedis(int healthAccount) throws Exception {

		User user = new User();
		user.setId(Long.parseLong(healthAccount + ""));

		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.get(DEMO_KEY);
		if (null != result) {
			System.out.println("缓存中的数据：" + ((User) result).getId());
		} else {
			System.out.println("设置缓存");
			operations.set(DEMO_KEY, user);

			redisTemplate.expire(DEMO_KEY, 60l, TimeUnit.SECONDS);// 设置过期时间

		}

		return healthAccount + "";
	}

	@Override
	public RestfulResult<Demo> getConfigHttpClient(int healthAccount, String httpType) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestfulResult<Users> getMybatis() throws Exception {
		List<Users> result = userMapper1.findUserInfo();
		return new RestfulResult<>(result);
	}

	@Override
	public RestfulResult<Users> getMybatis2() throws Exception {
		List<Users> result = userMapper2.abc();
		return new RestfulResult<>(result);
	}

}
