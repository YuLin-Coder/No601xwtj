package com.bean;

/**
 * 系统核心设置
 * @author Administrator
 *
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.Constant;
import com.util.DBO;


public class SystemBean {

	private List list;
	private ResultSet rs;
	
	//update system infomation
	public int updateSystem(String sql){
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);
			if(i == 1)
				return Constant.SUCCESS;
			else
				return Constant.SYSTEM_ERROR;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	
	//get site infomation
	public List getSystem(){
		String sql = "select * from fz_system ";
		DBO dbo = new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			rs.next();
			list = new ArrayList();
			list.add(rs.getString(2));
			list.add(rs.getString(3));
			list.add(rs.getString(4));
			list.add(rs.getString(5));
			list.add(rs.getString(6));
			list.add(rs.getString(7));
			list.add(rs.getString(8));
			list.add(rs.getString(9));
			list.add(rs.getString(10));
			list.add(rs.getString(11));
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
	
	//get site name
	public List getSiteInfo(){
		String sql = "select * from fz_system ";
		DBO dbo = new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			rs.next();
			list = new ArrayList();
			list.add(rs.getString("sitename")); //0
			list.add(rs.getString("url")); //1
			list.add(rs.getString("keyword")); //2
			list.add(rs.getString("description")); //3
			list.add(rs.getString("email")); //4
			list.add(rs.getString("state")); //5
			list.add(rs.getString("reasons")); //6
			list.add(rs.getString("record")); //7
			list.add(rs.getString("copyright")); //8
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
	
	//get system dir
	public String getDir(){
		String sql = "select dir from fz_system ";
		DBO dbo = new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			rs.next();
			String dir = rs.getString(1);
			return dir;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			dbo.close();
		}
	}
	 
}
