package com.yc.userinfo.entity;

public class Userinfo {
	private int usid;
	private String uname;
	private String pwd;
	private String sex;
	private String tel;
	private String photo;
	
	public String toJson(){
		return "{\"usid\":\""+usid+"\",\"uname\":\""+uname+"\",\"sex\":\""+sex+"\",\"tel\":\""+tel+"\",\"photos\":\""+this.getPhotos()+"\"}";
	}
	
	@Override
	public String toString() {
		return "Userinfo [usid=" + usid + ", uname=" + uname + ", pwd=" + pwd
				+ ", tel=" + tel + ", photo=" + photo + "]";
	}
	public int getUsid() {
		return usid;
	}
	public void setUsid(int usid) {
		this.usid = usid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPhoto() {
		return photo;
	}
	public String getPhotos() {
		if(photo==null||"".equals(photo)||"null".equals(photo)){
			return "<img src='images/0.jpg'/>";
		}else if(photo.indexOf(",")>0){
			String[] pics=photo.split(",");
			photo="";
			for(String pic:pics){
				photo+="<img src='"+pic+"'/>&nbsp;";
			}
			return photo;
		}else{
			return "<img src='"+photo+"'/>";
		}
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	

}
