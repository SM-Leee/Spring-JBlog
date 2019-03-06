package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogDao;
import com.douzone.jblog.repository.CategoryDao;
import com.douzone.jblog.repository.UserDao;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	
	public void join(UserVo userVo) {
		userDao.join(userVo);
		System.out.println(userVo.getNo());
		blogDao.blogCreate(userVo.getNo());
		categoryDao.noInsertCategory(userVo.getNo());
	}
	
	public UserVo login(UserVo userVo) {
		return userDao.login(userVo);
	}
	
	public boolean existId(String id) {
		UserVo userVo = userDao.idCheck(id);
		return userVo != null;
	}
	public UserVo checkId(String id) {
		return userDao.idCheck(id);
	}

}
