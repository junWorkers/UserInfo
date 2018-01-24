<%@ page language="java" import="java.util.*,come.yc.utis.*,com.yc.iserinfo.biz.*" pageEncoding="UTF-8"%>
<%
	UploadUtil uploadUtil=new UploadUtil();
	Map<String,String> map=uploadUtil.upload(pageContext);
	UserInfoBizImpl user= new UserInfoBizImpl();
	if(user.addUserInfo(map.get("uname"),map.get("pwd"),map.get("sex"),map.get("tel"),map.get("photo"))>0){
		//判断有没有分页信息，如果有则要修改记录数
		if(session.getAttribute("pageUtil")!=null){
			PageUtil pageUtil=(PageUtil)session.getAttribute("pageUtil");
			pageUtil.setTotalSize(pageUtil.getTotalSize()+1);
			session.setAttribute("pageUtil", pageUtil);
		}
		
		response.sendRedirect("infos.jsp");
	}else{
		out.print("<script>alert('注册失败，请稍后重试。。。');location.href='index.jsp'</script>");
	}
%>

