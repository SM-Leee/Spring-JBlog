package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<CategoryVo> categoryList(long user_no) {
		return sqlSession.selectList("category.selectAll", user_no);
	}
	
	public long categoryMin(long user_no) {
		return sqlSession.selectOne("category.selectmin", user_no);
	}
	
	public long categoryName(String category, long user_no) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("category", category);
		map.put("user_no", user_no);
		long no = sqlSession.selectOne("category.getNo", map);
		
		return no;
		
	}
	
	public int noInsertCategory(long user_no) {
		return sqlSession.insert("category.noInsertCategory",user_no);
	}
	
	public List<CategoryVo> categoryListAndPost(long user_no){
		return sqlSession.selectList("category.selectAllAndPost", user_no);
	}
	
	public CategoryVo get(long no) {
		return sqlSession.selectOne("category.get", no);
	}
	
	public long insertCategory(CategoryVo categoryVo) {
		long no = sqlSession.insert("category.insertCategory", categoryVo);
		System.out.println(no);
		return no;
	}
	
	public boolean delete(long no) {
		return 1==sqlSession.delete("category.deleteCategory",no);
	}
}
