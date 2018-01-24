<%@page import="come.yc.utis.PageUtil"%>
<%@ page language="java" import="java.util.*,com.yc.iserinfo.biz.*,com.yc.userinfo.entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
   <style type="text/css">
   		img{
   			width:50px;
   			height:50px;
   		}
   </style>
   <script type="text/javascript" src="js/jquery-1.11.3.js"></script>
   
   <script type="text/javascript">
   		function pageInfo(op){
   			$.post("pageServlet?t="+new Data(),{op:op},function(data){
   				var str="";
   				alert("fdsfsdfdfsef");
   				$.each(data.users,function(index,item){
   				  //$.each(data,function(index,item){
   					str+="<tr><td>"+item.usid+"</td><td>"+item.uname+"</td><td>"+item.sex+"</td><td>"+item.tel+"</td><td>"+item.photos+"</td><td>";
   					str+="<a href='pageServlet?op=5&usid="+item.usid+"'>[删除]</a></td></tr>";
   				});
   				$("#userInfo1").html($(str));
   				var page=data.pageInfo;
   				str="当前第"+page.pageNo+"页/共"+page.totalPage+"页&nbsp;&nbsp;每页"+page.pageSize+"条/共"+page.totalSize+"条";
   				$("#pageInfo").html(str);
   			},"json");
   		}
   </script>
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
  				<th>操作</th>
  			</tr>
  		</thead>
  		<tbody align="center" id="userInfo1">
  		
  		</tbody>
  	</table>
  	<center>
  		<a href="javascript:pageInfo(1)">首页</a>&nbsp;&nbsp;
  		<a href="javascript:pageInfo(2)"">上一页</a>&nbsp;&nbsp;
  		<a href="javascript:pageInfo(3)"">下一页</a>&nbsp;&nbsp;
  		<a href="javascript:pageInfo(4)"">末页</a>&nbsp;&nbsp;
  		<span id="pageInfo"></span>
  	</center>
   
  </body>
</html>
