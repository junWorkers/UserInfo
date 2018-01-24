package com.yc.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncodingFilter implements Filter {
	private String encoding="utf-8";
	public void destroy() {

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) arg0;
		HttpServletResponse response=(HttpServletResponse) arg1;
		
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		
		response.setContentType("text/html;charset="+encoding);
		//修改完后，调用下一个过滤器
		arg2.doFilter(arg0, arg1);
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		if(filterConfig.getInitParameter("encoding")!=null){
			encoding=filterConfig.getInitParameter("encoding");
		}
	}

}
