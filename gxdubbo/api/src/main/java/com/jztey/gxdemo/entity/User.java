package com.jztey.gxdemo.entity;

import com.jztey.framework.mvc.Id;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by ys on 2016/9/21
 * 
 * ps：最好都使用包装类型，否则用int的话会自动赋值0，会造成查询的错误
 * 
 */
@ApiModel(description = "用户表")
@Entity
@Table(name = "user")
public class User implements Id, Serializable {
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ApiModelProperty(value = "姓名")
	@Column(name = "name")
	private String name;

	@ApiModelProperty(value = "年龄")
	@Column(name = "age")
	@NotNull
	@Size(min = 6, max = 100)
	private Integer age;

	@ApiModelProperty(value = "性别", allowableValues = "1,2")
	@Column(name = "sex")
	@NotNull
	private Integer sex;

	@ApiModelProperty(value = "地址")
	@Column(name = "address")
	private String address;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
