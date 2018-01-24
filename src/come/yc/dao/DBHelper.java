package come.yc.dao;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



import come.yc.utis.LogUtil;

public class DBHelper {
		private Connection con=null;
		private PreparedStatement pstmt=null;
		private ResultSet rs=null;
		static{//因为驱动在整个程序运行期间，只需要加载一次，所以我们可以放到经太块中
			
			try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");

			Class.forName(ReadProperties.getPro().getProperty("driver"));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				LogUtil.log.error(e.toString());
				e.printStackTrace();
			}
			
		}
		/**
		 * 获取连接
		 */
		public Connection getConnection(){
				try {
					Context ctx = new InitialContext();
					DataSource ds=(DataSource) ctx.lookup("java:comp/env/jdbc/userInfo");
					con=ds.getConnection();
				} catch (NamingException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			return con;
		}
//		public Connection getConnection(){
//			try {
//				//con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","scott","a");
//
//				con=DriverManager.getConnection(ReadProperties.getPro().getProperty("url"),ReadProperties.getPro());
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				LogUtil.log.error(e.toString());
//
//				e.printStackTrace();
//			}
//			return con;
//
//		}
		/**
		 * 关闭资源的的方法
		 * @param con：要关闭的连接
		 * @param pstmt：要关闭的预编译块
		 * @param rs要关闭的结果集
		 */
		
		public void closeAll(Connection con,PreparedStatement pstmt,ResultSet rs){
			
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					LogUtil.log.error(e.toString());

					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					LogUtil.log.error(e.toString());

					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					LogUtil.log.error(e.toString());

					e.printStackTrace();
				}
			}
		}
		/**
		 * 给占位符赋值的方法
		 * @param pstmt
		 * @param params
		 * @throws SQLException
		 */
		
		public void setValue(PreparedStatement pstmt,List<Object> params) throws SQLException{
			
			if(params!=null&&params.size()>0){
				for(int i=0;i<params.size();i++){
					pstmt.setString(i+1, String.valueOf(params.get(i)));
				}
			}
		}
		/**
		 * 更新操作
		 * @param sql:要执行的sql语句
		 * @param params:要执行的sql语句中，对应？（占位符）的值，如果为null，则说明sql语句中没有占位符
		 * @return
		 */
		
		public int update(String sql,List<Object> params){
			int result=0;
			
			try {
				con=this.getConnection();
				pstmt=con.prepareStatement(sql);//Ԥ����ִ�е�sql���
				this.setValue(pstmt, params);
//				if(params!=null && params.size()>0){
//					for(int i=0;i<params.size();i++){
//						pstmt.setString(i+1,String.valueOf(params.get(i)));
//				}
//				}
				//ִ�����
				result=pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				this.closeAll(con, pstmt, null);
			
			}
			return result;
		   }
		
		public List<Map<String,Object>> find(String sql,List<Object> params){
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			Map<String,Object> map=null;//������Ϊ���Զ�Ӧ��ֵΪֵ
			
			try {
				con=this.getConnection();//��ȡ����
				pstmt=con.prepareStatement(sql);//Ԥ����Ҫִ�е�sql���
				this.setValue(pstmt, params);
				rs=pstmt.executeQuery();
				
				ResultSetMetaData rsmd=rs.getMetaData();//��ȡԪ���
				//��Ԫ����л�ȡ�е���Ϣ				
				String[] colNames=new String[rsmd.getColumnCount()];
				for(int i=0;i<colNames.length;i++){
					colNames[i]=rsmd.getColumnName(i+1);
				}
				while(rs.next()){//ÿѭ��һ�ξ���һ������
					map=new HashMap<String,Object>();
					if(colNames!=null && colNames.length>0){
						//ѭ��ȡ��ÿ���е�ֵ
						for(String s:colNames){
							map.put(s, rs.getString(s));
						}
					}
					list.add(map);
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				this.closeAll(con, pstmt, rs);
			}
			
			return list;
			
		}
		/**
		 * 聚合查询
		 * @param sql要执行的sql语句
		 * @param params要执行的sql语句中，对应？（占位符）的值，如果为null，则说明sql语句没有占位符
		 * @return
		 */
		public double findPloymer(String sql,List<Object> params){
			double result=0;
			con=this.getConnection();
			try {
				pstmt=con.prepareStatement(sql);
				this.setValue(pstmt, params);
				rs=pstmt.executeQuery();
				if(rs.next()){
					result=rs.getDouble(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				this.closeAll(con, pstmt, rs);
			}
			return result;
		}
		
		
		public List<Double> findPloymers(String sql,List<Object> params){
			List<Double> result=new ArrayList<Double>();
			con=this.getConnection();
			try {
				pstmt=con.prepareStatement(sql);
				this.setValue(pstmt, params);
				rs=pstmt.executeQuery();
				if(rs.next()){
					int count=rs.getMetaData().getColumnCount();
					for(int i=0;i<count;i++){
						result.add(rs.getDouble(i+1));
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				this.closeAll(con, pstmt, rs);
			}
			return result;
		}
		public boolean createOp(String sql){
			boolean bl=false;
			try {
				con=this.getConnection();
				pstmt=con.prepareStatement(sql);
				bl=pstmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				this.closeAll(con, pstmt, rs);
			}
			return bl;
		}
		
		public <T>List<T> finds(String sql,List<Object> params,Class c){
			List<T> list=new ArrayList<T>();
			try {
				con=this.getConnection();
				pstmt=con.prepareStatement(sql);
				this.setValue(pstmt, params);
				rs=pstmt.executeQuery();
				
				ResultSetMetaData rsmd=rs.getMetaData();
				String[] colsName=new String[rsmd.getColumnCount()];
				for(int i=0;i<colsName.length;i++){
					colsName[i]=rsmd.getColumnName(i+1);
				}
				//System.out.println(Arrays.toString(colsName));
				Method[] methods=c.getMethods();
				T t;
				String methodName=null;
				String colName=null;
				String typeName=null;
				Object val=null;
				while(rs.next()){
					t=(T) c.newInstance();//实例化一个对象
					for(int i=0;i<colsName.length;i++){
						colName=colsName[i];
						val=rs.getObject(colName);
						for(Method md:methods){
							methodName=md.getName();
							if(("set"+colName).equalsIgnoreCase(methodName)&& val!=null){
								//获取返回值的类型
								typeName=val.getClass().getName();
								if("java.math.BigDecimal".equals(typeName)){
									md.invoke(t, rs.getInt(colName));
								}else{
									md.invoke(t, rs.getString(colName));
								}
							}
						}
						
					}
					list.add(t);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return list;
			
			
		}
}
