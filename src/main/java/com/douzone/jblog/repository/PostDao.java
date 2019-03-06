package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession sqlSession;

	public List<PostVo> postTitleList(long user_no, long category_no) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_no", user_no);
		map.put("category_no", category_no);

		List<PostVo> list = sqlSession.selectList("post.listTitleAndDate", map);
		return list;
	}

	public long postMin(long category_no) {
		return sqlSession.selectOne("post.postMin", category_no);
	}

	public PostVo getPost(long post_no) {
		return sqlSession.selectOne("post.getPost", post_no);
	}

	public int insertPost(PostVo postVo) {
		return sqlSession.insert("post.insertPost", postVo);
	}
}
