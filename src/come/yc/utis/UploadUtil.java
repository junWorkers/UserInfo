package come.yc.utis;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;

/**
 * 上传
 * @author acer
 *
 */
public class UploadUtil {
	private static final String PATH="upload";
	private static final String ALLOWED="gif,png,jpg,jpeg";
	private static final String DENIED="exe,bat,jsp,com";
	private static final int SINGLEFILESIZE=1024*1024;
	private static final int TOTALSIZE=1024*1024*20;
	
	
	public Map<String,String> upload(PageContext pageContext) throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		SmartUpload su=new SmartUpload();
		su.initialize(pageContext);
		su.setAllowedFilesList(ALLOWED);
		su.setDeniedFilesList(DENIED);
		su.setMaxFileSize(SINGLEFILESIZE);
		su.setTotalMaxFileSize(TOTALSIZE);
		su.setCharset("utf-8");
		
		su.upload();
		
		Request request=su.getRequest();
		Enumeration<String> enums=request.getParameterNames();
		String fieldName;
		while(enums.hasMoreElements()){
			fieldName=enums.nextElement();
			map.put(fieldName, request.getParameter(fieldName));
			
		}
		Files files=su.getFiles();//获取所有要上传的文件
		String photo="";
		if(files!=null && files.getCount()>0){
			String fileName;
			Collection<File> list=files.getCollection();
			for(File fl:list){//循环所有要上传的文件
				if(!fl.isMissing()){//如果丢失文件
					fileName=PATH+"/"+System.currentTimeMillis()+new Random().nextInt(10000)+"."+fl.getFileExt();
					//保存到服务器
					fl.saveAs(fileName, SmartUpload.SAVE_VIRTUAL);
					photo+=fileName+",";//存入数据库的图片路径
				}
			}
			if(photo.indexOf(",")>0){
				photo=photo.substring(0,photo.lastIndexOf(","));//除掉最后一个逗号
			}
		}
		map.put("photo", photo);
		System.out.println(map);
		return map;
	}
}
