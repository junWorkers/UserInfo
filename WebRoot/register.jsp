<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8"/>
    <title>注册页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#uname").bind({
				blur:function(){
					$.post("checkInfoServlet",{op:"checkUname",uname:$(this).val()},function(data){
						data=parseInt($.trim(data));
						if(data==1){
							$("#uname").next("label").css("color","red").html("该昵称已被使用。。。");
						}else{
							$("#uname").next("label").css("color","green").html("该昵称可以使用");
						}
					},"text");
				},
				focus:function(){
					$(this).next("label").css("color","#000").html("请输入你的昵称");
				}
			});
		});
	</script>

  </head>
  
  <body>
    <form action="registerServlet" method="post" enctype="multipart/form-data">
   		<p><label for="uname">用户名：</label><input type="text" name="uname" id="uname"/><label>请输入你的昵称</label></p>
   		<p><label for="pwd">密码:</label><input type="password" name="pwd" id="pwd"/></p>
   		<p>性别：<input type="radio" value="男"  name="sex" id="man"/>男<input type="radio" value="女"  name="sex" id="wuman"/>女</p>
   		<p>电话号码：<input type="text" name="tel" id="tel"/></p>
   		<p>照片：<input multiple type="file" name="photo" id="photo"/></p>
   		<p><input type="submit" value="注册"/><input type="reset" value="重置"/></p>
   	</form>
  </body>
</html>
