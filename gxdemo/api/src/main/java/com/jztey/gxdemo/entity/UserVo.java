package com.jztey.gxdemo.entity;

import com.jztey.framework.mvc.Id;
import com.jztey.framework.mvc.Paging;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Created by ys on 2016/9/21
 * 
 * ps：最好都使用包装类型，否则用int的话会自动赋值0，会造成查询的错误
 * 
 */
@ApiModel(description = "用户表的vo") 
public class UserVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6715118151780614617L;

	private Paging<User> paging;
	private User user;

	public Paging<User> getPaging() {
		return paging;
	}

	public void setPaging(Paging<User> paging) {
		this.paging = paging;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
