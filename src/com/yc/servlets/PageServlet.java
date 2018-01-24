package com.yc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.yc.iserinfo.biz.UserInfoBizImpl;
import com.yc.userinfo.entity.Userinfo;

import come.yc.utis.PageUtil;

public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserInfoBizImpl userInfo=new UserInfoBizImpl();
	
	private HttpSession session;

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		session=request.getSession();
		
		String op=request.getParameter("op");
		Object obj=session.getAttribute("pageUtil");
		PageUtil pageUtil;
			if(obj==null){
				pageUtil=new PageUtil();
				pageUtil.setPageNo(1);
				pageUtil.setTotalSize(userInfo.getTotal());
				pageUtil.setPageSize(5);
			}else{
				pageUtil=(PageUtil)obj;
			}
			
		if("1".equals(op)){
			pageUtil.setPageNo(1);
		}else if("2".equals(op)){
			//pageUtil.getProPageNo();
			pageUtil.setPageNo(pageUtil.getPageNo()-1);
		}else if("3".equals(op)){
			//pageUtil.getNextPageNo();
			pageUtil.setPageNo(pageUtil.getPageNo()+1);
		}else if("4".equals(op)){
			//pageUtil.setPageNo(pageUtil.getTotalPage());
			pageUtil.setPageNo(pageUtil.getTotalPage());
		}else if("5".equals(op)){
			String usid=request.getParameter("usid");
			int result=userInfo.delUserInfo(usid);
			if(result>0){
				pageUtil.setTotalSize(pageUtil.getTotalSize()-result);
			}
		}
		//将最新的分页信息存起来
		session.setAttribute("pageUtil",pageUtil);
		List<Userinfo> users=userInfo.finds(pageUtil.getPageNo(),pageUtil.getPageSize());
		session.setAttribute("userInfo",users);
		
		JSONArray json=JSONArray.fromObject(users);
		JSONObject jb=new JSONObject();
		JSONArray.fromObject(pageUtil);
		jb.put("users", json);
		jb.put("pageInfo", pageUtil);
//		String str="[";
//		int i=0;
//		for(int j=0;i<users.size()-1;i++){
//			str+=users.get(j).toJson()+",";
//		}
//		str+=users.get(i).toJson()+"]";
		//out.print(jb.toString());
		//out.print(str);
		out.flush();
		out.close();
		//response.sendRedirect("infos.jsp");
	}
}
