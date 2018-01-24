package come.yc.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties extends Properties {
private static ReadProperties instance=new ReadProperties();
private ReadProperties(){
	InputStream is=null;
	
	try {
		is=this.getClass().getResourceAsStream("/db.properties");
		this.load(is);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		if(is!=null){
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
public static ReadProperties getPro(){
	return instance;
	}
}
