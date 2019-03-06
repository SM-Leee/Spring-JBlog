package com.douzone.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogDao;
import com.douzone.jblog.repository.CategoryDao;
import com.douzone.jblog.repository.PostDao;
import com.douzone.jblog.repository.UserDao;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PostDao postDao;

	public Map<String, Object> getAll(String id, long category_no, long post_no) {
		long user_no = userDao.idCheck(id).getNo();
		BlogVo blogVo = blogDao.blogCheck(user_no);
		Map<String, Object> map = new HashMap<String, Object>();

		List<CategoryVo> categoryList = categoryDao.categoryList(user_no);
		if(category_no == 0) {
			category_no = categoryDao.categoryMin(user_no);
		}

		List<PostVo> postList = postDao.postTitleList(user_no,category_no);

		PostVo postVo = new PostVo();
		if(postList.size() != 0 ) {
			if(post_no == 0) {
				post_no = postDao.postMin(category_no);
			}

			postVo = postDao.getPost(post_no);				
		}
		map.put("userVo", userDao.idCheck(id));
		map.put("blogVo", blogVo);
		map.put("categoryList", categoryList);
		map.put("postList", postList);
		map.put("postVo", postVo);

		return map;
	}

	public BlogVo get(String id) {
		long user_no = userDao.idCheck(id).getNo();
		return blogDao.blogCheck(user_no);
	}

	public BlogVo update(BlogVo blogVo) {

		blogDao.blogUpdate(blogVo);
		blogVo = blogDao.blogCheck(blogVo.getUser_no());
		return blogVo;
	}

	public List<CategoryVo> getCategoryList(String id) {
		long user_no = userDao.idCheck(id).getNo();
		return categoryDao.categoryList(user_no);
	}

	public int insertPost(PostVo postVo) {
		postVo.setCategory_no(categoryDao.categoryName(postVo.getCategory()));
		return postDao.insertPost(postVo);


	}

	public List<CategoryVo> getCategoryListAndPost(String id){
		long user_no = userDao.idCheck(id).getNo();
		return categoryDao.categoryListAndPost(user_no);
	}

	public CategoryVo getCategory(CategoryVo categoryVo) {
		return categoryDao.get(categoryDao.insertCategory(categoryVo));
	}

	public boolean delete(long no) {
		return categoryDao.delete(no);
	}

}
