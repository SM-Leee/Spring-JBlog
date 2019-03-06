package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CommentVo;

@Repository
public class CommentDao {
	@Autowired
	private SqlSession sqlSession;
	
	public List<CommentVo> getListComment(long post_no){
		return sqlSession.selectList("comment.getListComment", post_no);
	}
	
	public int insertComment(CommentVo commentVo) {
		return sqlSession.insert("comment.insertComment", commentVo);
	}
	
	public int deleteComment(long comment_no) {
		return sqlSession.delete("comment.deleteComment", comment_no);
	}
}
