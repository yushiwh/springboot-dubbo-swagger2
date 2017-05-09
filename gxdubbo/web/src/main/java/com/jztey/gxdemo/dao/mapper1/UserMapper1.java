package com.jztey.gxdemo.dao.mapper1;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jztey.gxdemo.domain.Users;

@Repository
public interface UserMapper1 {
	List<Users> findUserInfo();
}
