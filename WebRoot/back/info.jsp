<%@ page language="java" import="java.util.*,com.yc.iserinfo.biz.*" pageEncoding="UTF-8"%>
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
  	<%!
  	UserInfoBizImpl userinfo=new UserInfoBizImpl();
    String photo="";
  	%>
  	<table align="center" width="90%" cellspace="0px" cellspadding="0px" border="1px">
  		<caption>用户信息列表</caption>
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
  		List<Map<String,Object>> list=userinfo.find();
  		for(Map<String,Object> map:list){
  		%>
  		<tr>
  			<td><%=map.get("USID") %></td>
  			<td><%=map.get("UNAME") %></td>
  			<td><%=map.get("SEX") %></td>
  			<td><%=map.get("TEL") %></td>
  			<%
  			photo=String.valueOf(map.get("PHOTO"));
  			if(photo==null || "".equals(photo)||"null".equals(photo)){
  			%>
  			<td><img src="images/0.jpg"  width="40px"  height="40px"/></td>
  			<%
  			}else{
  			
  			%>
  			<td><img src="photo"  width="40px"  height="40px"/></td>
  			<% 
  				}
  			%>
  			</tr>
  			<%
  			}
  			%>
  			</tbody>
  	</table>
   
  </body>
</html>
