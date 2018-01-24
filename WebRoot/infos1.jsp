<%@page import="come.yc.utis.PageUtil"%>
<%@ page language="java" import="java.util.*,com.yc.iserinfo.biz.*,com.yc.userinfo.entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  
  	<table align="center" width="90%" cellspace="0px" cellspadding="0px" border="1px">
  		<caption>用户信息列表&nbsp;&nbsp;&nbsp;&nbsp;<a href="index.jsp">注册</a></caption>
  		<thead>
  			<tr>
  				<th>用户编号</th>
  				<th>用户姓名</th>
  				<th>用户性别</th>
  				<th>联系方式</th>
  				<th>用户图像</th>
  			</tr>
  		</thead>
  		<tbody align="center">
  		<%
  		//从session中获取pageUtil对象，如果没有，则说明用户是刚进来的，如果有了，则说明用户已经访问过其他页面
  			Object obj=session.getAttribute("pageUtil");
  			UserInfoBizImpl userinfo=new UserInfoBizImpl();
  			PageUtil pageUtil;
  			if(obj==null){
  				pageUtil=new PageUtil();
  				pageUtil.setPageNo(1);
  				pageUtil.setTotalSize(userinfo.getTotal());
  				pageUtil.setPageSize(5);
  			}else{
  				pageUtil=(PageUtil)obj;
  			}
  			//将最新的分页信息存起来
  			session.setAttribute("pageUtil",pageUtil);
  			List<Userinfo> users=userinfo.finds(pageUtil.getPageNo(),pageUtil.getPageSize());
  			for(Userinfo u:users){
  		%>
  		<tr>
  			<td><%=u.getUsid()%></td>
  			<td><%=u.getUname()%></td>
  			<td><%=u.getSex()%></td>
  			<td><%=u.getTel()%></td>
  			<td><%=u.getPhotos()%></td>
  			<!--<td><a href="dopage.jsp?op=5&usid=<%=u.getUsid()%>">[删除]</a></td>-->
  			<td><a href="pageServlet?op=5&usid=<%=u.getUsid()%>">[删除]</a></td>
  		</tr>
  			<%
  				}
  			%>
  			</tbody>
  	</table>
  	<center>
  		<a href="pageServlet?op=1">首页</a>&nbsp;&nbsp;
  		<a href="pageServlet?op=2">上一页</a>&nbsp;&nbsp;
  		<a href="pageServlet?op=3">下一页</a>&nbsp;&nbsp;
  		<a href="pageServlet?op=4">末页</a>&nbsp;&nbsp;
  		<span>当前第${pageUtil.pageNo}页/共${pageUtil.totalPage}页&nbsp;&nbsp;每页${pageUtil.pageSize}条/共${pageUtil.totalSize}条</span>
  	</center>
   
  </body>
</html>
