<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="UTF-8"%>
<% 
		request.setCharacterEncoding("utf-8");
		String uname=request.getParameter("uname");
		String pwd=request.getParameter("pwd");
		
		//重定向 会发生两次请求
		//response.sendRedirect("success.jsp");
		//重定向可以访问当前服务器以外的资源
		//response.sendRedirect("http://www.baidu.com");
		//response.sendRedirect("http://META-INF");
		//内部转发，只会发生一次，请求地址栏不会发生变化
		//request.getRequestDispatcher("success.jsp").forward(request, response);
		//内部转发只能访问当前服务器中的资源
		//request.getRequestDispatcher("http://www.baidu.com").forward(request, response);
		//request.getRequestDispatcher("http://META-INF").forward(request, response);
	
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","scott","a");
		String sql="select usid,uname,pwd,tel,photo from denglu where uname=? and pwd=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,uname);
		pstmt.setString(2,pwd);
		ResultSet result=pstmt.executeQuery();
		if(result.next()){
			response.sendRedirect("infos.jsp");
		}else{
		   out.print("<script>alert('您输入的用户名或者密码不正确，请重新输入');location.href='index.jsp';</script>");
	    }
%>