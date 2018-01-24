package com.yc.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.iserinfo.biz.UserInfoBizImpl;

public class UserinfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserInfoBizImpl userInfoBizImpl=new UserInfoBizImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		String uname=request.getParameter("uname");
		String pwd=request.getParameter("pwd");
		
		if(userInfoBizImpl.loginUser(uname, pwd)!=null){
			response.sendRedirect("infos.jsp");
		}else{
			 out.print("<script>alert('您输入的用户名或者密码不正确，请重新输入');location.href='index.jsp';</script>");
			 out.flush();
			 out.close();
		}
	}

}
