package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo blogCheck(long user_no) {
		return sqlSession.selectOne("blog.blogcheck",user_no);
	}
	
	public int blogCreate(long user_no) {
		return sqlSession.insert("blog.blogcreate", user_no);
	}
	
	public int blogUpdate(BlogVo blogVo) {
		return sqlSession.update("blog.blogupdate", blogVo);
	}
}
