<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.io.File,com.yc.iserinfo.biz.*"%>
<%
	request.setCharacterEncoding("utf-8");
	//创建一个磁盘工厂
	DiskFileItemFactory factory=new DiskFileItemFactory();
	//通过磁盘文件项来创建一个文件上传对象
	ServletFileUpload upload=new ServletFileUpload(factory);
	List list=upload.parseRequest(request);
	FileItem item;
	String fieldName=null;
	String uname=null;
	String pwd=null;
	String sex=null;
	String tel=null;
	String photo="";
	String fileName="";
	for(int i=0;i<list.size();i++){
		item=(FileItem)list.get(i);
		if(item.isFormField()){
			fieldName=item.getFieldName();
			if("uname".equals(fieldName)){
				uname=item.getString("utf-8");
			}else if("sex".equals(fieldName)){
				sex=item.getString("utf-8");
			}else if("pwd".equals(fieldName)){
				pwd=item.getString("utf-8");
			}else if("tel".equals(fieldName)){
				tel=item.getString("utf-8");
			}
		}else{
			fieldName=item.getName();
			if(fieldName!=null && !"".equals(fieldName)){//说明有上传照片
			//System.out.println("大小："+item.getSize());
			//System.out.println("类型："+item.getContentType());
			//System.out.println("名称："+item.getName());
			//现在webroot下面创建一个upload
			//System.out.println(pageContext.getServletContext().getContextPath());
			//System.out.println(pageContext.getServletContext().getRealPath("/"));
				fileName=System.currentTimeMillis()+fieldName.substring(fieldName.indexOf("."));
				System.out.println(fileName);
				File file=new File(pageContext.getServletContext().getRealPath("/")+"upload",fileName);
				photo="upload/"+fileName;
				item.write(file);
			}
		}
	}
	UserInfoBizImpl user= new UserInfoBizImpl();
	if(user.addUserInfo(uname, pwd, sex, tel, photo)>0){
		response.sendRedirect("info.jsp");
	}else{
		out.print("<script>alert('注册失败，请稍后重试。。。');location.href='index.jsp'</script>");
	}
	
%>

