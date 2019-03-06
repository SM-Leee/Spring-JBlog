package com.douzone.jblog.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.dto.JSONResult;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileuploadService;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.CommentVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;
import com.douzone.security.AuthUser;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private FileuploadService fileuploadService;
	@Autowired
	private UserService userService;
	@Autowired
	private BlogService blogService;
	
	@RequestMapping({"","/{category_no}","/{category_no}/{post_no}"})
	public String blog_main(
			@PathVariable("id") String id,
			@PathVariable("category_no") Optional<Long> category_no,
			@PathVariable("post_no") Optional<Long> post_no,
			Model model) {
			
		if(category_no.isPresent() && post_no.isPresent()) {
			Map<String, Object> map = blogService.getAll(id, category_no.get(), post_no.get());
			model.addAllAttributes(map);		
			} else {
			Map<String, Object> map = blogService.getAll(id, category_no.orElse(0L), post_no.orElse(0L));
			System.out.println("here----------->>" + map);
			
			model.addAllAttributes(map);

		}

			return "blog/blog-main";			
	}
	
	@RequestMapping("/admin/basic")
	public String blog_admin_basic(@AuthUser UserVo authuser,
			@PathVariable("id") String id, Model model) {
		
		if(authuser == null) {
			return "user/login";
		}
		BlogVo blogVo = blogService.get(id);
		model.addAttribute("blogVo",blogVo);
		
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="/update", method =RequestMethod.POST)
	public String blog_update(@AuthUser UserVo authuser,
			@PathVariable("id") String id,
			@ModelAttribute BlogVo blogVo,
			@RequestParam(value="logo-file") MultipartFile multipartFile) {
		
		if(authuser == null) {
			return "user/login";
		}
		long user_no = userService.checkId(id).getNo();		
		blogVo.setUser_no(user_no);
		String logo = fileuploadService.restore(multipartFile);
		blogVo.setLogo(logo);
		blogService.update(blogVo);
	
		return "redirect:/"+id+"/admin/basic";
	}
	
	@RequestMapping("/admin/write")
	public String blog_write(@AuthUser UserVo authuser,
			@PathVariable("id") String id,
			Model model) {
		if(authuser == null) {
			return "user/login";
		}
		BlogVo blogVo = blogService.get(id);
		List<CategoryVo> categoryList = blogService.getCategoryList(id);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("blogVo",blogVo);
		return "blog/blog-admin-write";
	}
	@RequestMapping(value="/write", method= RequestMethod.POST)
	public String blog_write(@AuthUser UserVo authuser,
			@PathVariable("id") String id,
			@ModelAttribute PostVo postVo) {
		if(authuser == null) {
			return "user/login";
		}
		blogService.insertPost(postVo);
		return "redirect:/"+id;
	}
	
	@RequestMapping("/admin/category")
	public String blog_category(
			@AuthUser UserVo authuser,
			@PathVariable("id") String id,
			Model model) {
		if(authuser == null) {
			return "user/login";
		}
		BlogVo blogVo = blogService.get(id);
		model.addAttribute("blogVo",blogVo);
		return "blog/blog-admin-category";
	}
	
	@ResponseBody
	@RequestMapping("/admin/category/list")
	public JSONResult blog_category(
			@PathVariable("id") String id,
			Model model) {
		
		BlogVo blogVo = blogService.get(id);
		model.addAttribute("blogVo",blogVo);
		
		return JSONResult.success(blogService.getCategoryListAndPost(id));
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/category/insert", method=RequestMethod.POST)
	public JSONResult blog_category_insert(
			@RequestParam(value="name") String name,
			@RequestParam(value="description") String description,
			@PathVariable("id") String id,
			Model model) {
		
		BlogVo blogVo = blogService.get(id);
		model.addAttribute("blogVo",blogVo);
		
		CategoryVo categoryVo1 = new CategoryVo();
		categoryVo1.setName(name);
		categoryVo1.setDescription(description);
		categoryVo1.setUser_no(blogVo.getUser_no());
		
		return JSONResult.success(blogService.getCategory(categoryVo1));
	}
	@ResponseBody
	@RequestMapping(value="/admin/category/delete", method=RequestMethod.POST)
	public JSONResult category_delete(
			@RequestParam(value="no") long no,
			@PathVariable("id") String id,
			Model model){
		BlogVo blogVo = blogService.get(id);
		model.addAttribute("blogVo",blogVo);
		
		return JSONResult.success(blogService.delete(no));
		
	}
	
	@RequestMapping(value="/{category_no}/{post_no}/comment/insert", method=RequestMethod.POST)
	public String commentInsert(
			@PathVariable("id") String id,
			@PathVariable("category_no") long category_no,
			@PathVariable("post_no") long post_no,
			@ModelAttribute CommentVo commentVo,
			Model model) {

		blogService.insertComment(commentVo);
		return "redirect:/"+id+"/"+category_no+"/"+post_no;
	}
	
	@RequestMapping(value="/{category_no}/{post_no}/{comment_no}/comment/delete")
	public String commentDelete(
			@PathVariable("id") String id,
			@PathVariable("category_no") long category_no,
			@PathVariable("post_no") long post_no,
			@PathVariable("comment_no") long comment_no,
			@ModelAttribute CommentVo commentVo,
			Model model) {
		
		blogService.deleteComment(comment_no);
		return "redirect:/"+id+"/"+category_no+"/"+post_no;
	}

}
