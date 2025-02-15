package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

@WebFilter("/*")
public class CommonFilter implements Filter {
	private final CategoryDAO categoryDao;
    public CommonFilter() {
    	categoryDao = new CategoryDAO();
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServlet = (HttpServletRequest) request;
		String url = httpServlet.getServletPath();
		if(!url.startsWith("/admin/")) {
			List<Category> listCategory = categoryDao.listAll();
			
			request.setAttribute("listCategory", listCategory);
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
