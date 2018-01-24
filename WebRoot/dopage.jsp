<%@page import="come.yc.utis.PageUtil"%>
<%@page import="com.yc.iserinfo.biz.UserInfoBizImpl"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	UserInfoBizImpl userInfo=new UserInfoBizImpl();
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
		pageUtil.getProPageNo();
	}else if("3".equals(op)){
		pageUtil.getNextPageNo();
	}else if("4".equals(op)){
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
	response.sendRedirect("infos.jsp");
%>

