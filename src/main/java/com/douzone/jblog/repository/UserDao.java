package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public long join(UserVo userVo) {
		long no = sqlSession.insert("user.join", userVo);
		return no;
	}
	
	public UserVo login(UserVo userVo) {
		return sqlSession.selectOne("user.login", userVo);
	}
	
	public UserVo idCheck(String id) {
		return sqlSession.selectOne("user.checkid",id);
	}

}
