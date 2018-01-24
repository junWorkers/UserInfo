<%@ page language="java" import="java.util.*,java.sql.*"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String uname = request.getParameter("uname");
	String pwd = request.getParameter("pwd");
	String sex = request.getParameter("sex");
	String tel = request.getParameter("tel");
	String photo = request.getParameter("photo");

	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con = DriverManager.getConnection(
			"jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott", "a");
	String sql1 = "select * from denglu where uname=?";
	PreparedStatement pstmt1 = con.prepareStatement(sql1);
	pstmt1.setString(1, uname);
	ResultSet rs = pstmt1.executeQuery();
	if (rs.next()) {
		out.print("<script>alert('此用户已经存在')</script>;location.replace(index.jsp);");
		
	} else {
		String sql = "insert into denglu values(seq_denglu_usid.nextval,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, uname);
		pstmt.setString(2, pwd);
		pstmt.setString(3, sex);
		pstmt.setString(4, tel);
		pstmt.setString(5, photo);
		
		//ResultSet result = pstmt.executeQuery();
		if (pstmt.executeUpdate(sql)>0) {
			response.sendRedirect("success.jsp");
		} else {
			response.sendRedirect("index.jsp");
		}
	}
%>