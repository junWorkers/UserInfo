package com.yc.iserinfo.biz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.userinfo.entity.Userinfo;

import come.yc.dao.DBHelper;

public class UserInfoBizImpl {
	private DBHelper db=new DBHelper();
	
	public Userinfo loginUser(String uname,String pwd){
		String sql="select usid,uname,pwd,tel,photo from denglu where uname=? and pwd=?";
		List<Object> params=new ArrayList<Object>();
		params.add(uname);
		params.add(pwd);
		List<Userinfo> list=db.finds(sql, params,Userinfo.class);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 *查询所有用户
	 */
	public List<Map<String,Object>> find(){
		String sql="select usid,uname,pwd,sex,tel,photo from denglu";
		return db.find(sql, null);
	}
	public List<Userinfo> finds(){
		String sql="select usid,uname,pwd,sex,tel,photo from denglu";
		return db.finds(sql, null, Userinfo.class);
	}
	public int addUserInfo(String uname,String pwd,String sex,String tel,String photo){
		String sql="insert into denglu values(seq_denglu_usid.nextval,?,?,?,?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(uname);
		params.add(pwd);
		params.add(sex);
		params.add(tel);
		params.add(photo);
		return db.update(sql, params);
	}
	
	
//	public static void main(String args[]){
//		UserInfoBizImpl userInfoBizImpl=new UserInfoBizImpl();
//		userInfoBizImpl.finds();
//	}
	/**
	 * 
	 * @param pageNo:要查询的页数
	 * @param pageSize：每页显示的条数
	 * @return
	 */
	public List<Userinfo> finds(Integer pageNo,Integer pageSize){
		String sql="select *from (select a.*,rownum rn from(select *from denglu order by usid) a where rownum<=?) b where rn>?";
		List<Object> params=new ArrayList<Object>();
		params.add(pageNo*pageSize);
		params.add((pageNo-1)*pageSize);
		return db.finds(sql, params, Userinfo.class);
		
	}
	
	public int getTotal(){
		String sql="select count(usid) from denglu";
		return (int)db.findPloymer(sql, null);
	}
	
	public int delUserInfo(String usid){
		String sql="delete from denglu where usid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(usid);
		return db.update(sql, params);
	}
	
	public boolean checkUname(String uname){
		List<Object> params=new ArrayList<Object>();
		params.add(uname);
		List<Userinfo> list=db.finds("select * from denglu where uanme=?", params, Userinfo.class);
		if(list!=null&&list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean checkTel(String tel){
		List<Object> params=new ArrayList<Object>();
		params.add(tel);
		List<Userinfo> list=db.finds("select * from denglu where tel=?", params, Userinfo.class);
		if(list!=null&&list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
}
