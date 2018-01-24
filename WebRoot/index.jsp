<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
   <!--	<form action="doregisters.jsp" method="post">
   		<p><label for="uname">用户名：</label><input type="text" name="uname" id="uname"/></p>
   		<p><label for="pwd">密码:</label><input type="password" name="pwd" id="pwd"/></p>
   		<p><input type="submit" value="提交"/></p>
   	</form>
   	  
   	<form action="doregisters.jsp" method="post" enctype="multipart/form-data">
   		<p><label for="uname">用户名：</label><input type="text" name="uname" id="uname"/></p>
   		<p><label for="pwd">密码:</label><input type="password" name="pwd" id="pwd"/></p>
   		<p><label for="sex">性别：</label><input type="text" name="sex" id="sex"/></p>
   		<p><label for="tel">电话号码：</label><input type="text" name="tel" id="tel"/></p>
   		<p>照片：<input multiple type="file" name="photo" id="photo"/></p>
   		<p><input type="submit" value="注册"/></p>
   	</form>
   	-->
   	<form action="userinfoServlet"  method="post">
   		<p><label for="uname">用户名：</label><input type="text" name="uname" id="uname"/></p>
   		<p><label for="pwd">密码:</label><input type="password" name="pwd" id="pwd"/></p>
   		<p><input type="submit" value="提交"/><a href="register.jsp">注册</a>
   	</form>
   	<form action="registerServlet" method="post" enctype="multipart/form-data">
   		<p><label for="uname">用户名：</label><input type="text" name="uname" id="uname"/></p>
   		<p><label for="pwd">密码:</label><input type="password" name="pwd" id="pwd"/></p>
   		<p><label for="sex">性别：</label><input type="text" name="sex" id="sex"/></p>
   		<p><label for="tel">电话号码：</label><input type="text" name="tel" id="tel"/></p>
   		<p>照片：<input multiple type="file" name="photo" id="photo"/></p>
   		<p><input type="submit" value="注册"/></p>
   	</form>
  </body>
</html>
