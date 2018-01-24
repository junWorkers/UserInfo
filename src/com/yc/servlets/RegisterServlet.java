package com.yc.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.yc.iserinfo.biz.UserInfoBizImpl;

import come.yc.utis.PageUtil;
import come.yc.utis.UploadUtil;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserInfoBizImpl user=new UserInfoBizImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session=request.getSession();
		PrintWriter out = response.getWriter();
		UploadUtil uploadUtil=new UploadUtil();
		
		PageContext pageContext=JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
		
		Map<String, String> map;
		try {
			map = uploadUtil.upload(pageContext);

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
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
