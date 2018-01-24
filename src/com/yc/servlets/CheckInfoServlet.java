package com.yc.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.iserinfo.biz.UserInfoBizImpl;

public class CheckInfoServlet extends HttpServlet {
	private UserInfoBizImpl userInfo=new UserInfoBizImpl();
	private PrintWriter out;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String op=request.getParameter("op");
		out=response.getWriter();
		if("checkUname".equals(op)){
			checkUname(request,response);
		}
	}


	private void checkUname(HttpServletRequest request,HttpServletResponse response) {
		String uname=request.getParameter("uname");
		if(userInfo.checkUname(uname)){
			out.print("1");
		}else{
			out.print("0");
		}
		out.flush();
		out.close();
		
	}

}
