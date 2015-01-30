/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.helloweenvsfei.forum.struts.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.helloweenvsfei.forum.bean.Category;
import com.helloweenvsfei.forum.service.ICategoryService;
import com.helloweenvsfei.forum.struts.form.CategoryForm;

/**
 * MyEclipse Struts Creation date: 07-04-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/category" name="categoryForm"
 *                input="/form/category/listCategory.jsp" parameter="action"
 *                scope="request" validate="true"
 */
public class CategoryAction extends ForumAction {

	private ICategoryService<Category> categoryService;

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		CategoryForm categoryForm = (CategoryForm) form;

		List<Category> categoryList = categoryService.list(" from Category where deleted = false ", 0, Integer.MAX_VALUE, null);

		request.setAttribute("categoryList", categoryList);

		return new ActionForward("list", "/form/category/listCategory.jsp", false);
	}

	public ActionForward initAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		CategoryForm categoryForm = (CategoryForm) form;
		categoryForm.setTitle("添加类别");

		return new ActionForward("add", "/form/category/addCategory.jsp", false);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		CategoryForm categoryForm = (CategoryForm) form;
		categoryForm.setTitle("添加类别");

		Category category = categoryForm.getCategory();
		category.setDateCreated(new Date());

		categoryService.create(category);

		request.setAttribute("category", category);

		return new ActionForward("add", "/form/category/success.jsp", false);
	}

	public ICategoryService<Category> getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(ICategoryService<Category> categoryService) {
		this.categoryService = categoryService;
	}

}