package com.bean;

/**
 * 
 * 管理员登陆 修改密码 登陆记录查询 通用类文件
 * 
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.util.Constant;
import com.util.DBO;

public class AdminBean {

	private List list;
	private ResultSet rs = null;
	private int EVERYPAGENUM = 2;
	private int count = -1;
	private int qq = 0;
	private String sql="select count(*) from fz_adminlog";
	private String sql2="select * from fz_adminlog order by id desc ";
	//声明时间变量
	String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	
	//分页查询登陆日志
	public void setEVERYPAGENUM(int EVERYPAGENUM){
    	this.EVERYPAGENUM=EVERYPAGENUM;
    }
    public int getMessageCount() { //得到信息总数
       DBO dbo=new DBO();
       dbo.open();
        try { 
            rs = dbo.executeQuery(sql);
            rs.next();
            count = rs.getInt(1);
            return count;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        } finally {
            dbo.close();
        }
    }
    public int getPageCount() { //得到共多少页（根据每页要显示几条信息）
        if (count % EVERYPAGENUM == 0) {
            return count / EVERYPAGENUM;
        } else {
            return count / EVERYPAGENUM + 1;
        }
    }
    public List getMessage(int page) { //得到每页要显示的信息
        DBO dbo=new DBO();
        dbo.open();
        List list = new ArrayList();
        try {
            rs = dbo.executeQuery(sql2);
            for (int i = 0; i < (page - 1) * EVERYPAGENUM; i++) {
                rs.next();
            }
            for (int t = 0; t < EVERYPAGENUM; t++) {
                if (rs.next()) {
                    qq++;
                    List list2=new ArrayList();
                    list2.add(rs.getString("id"));
    				list2.add(rs.getString("username"));
    				list2.add(rs.getString("password"));
    				list2.add(rs.getString("logintime"));
    				list2.add(rs.getString("loginip"));
    				list2.add(rs.getString("useros"));
    				list2.add(rs.getString("ok"));
    				list.add(list2);
                } else {
                    break; //减少空循环的时间
                }
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            dbo.close();
        }
    }
	//管理员登录 更新登录次数 写登录日志
	public int adminLogin(String username,String md5password,String password,String loginip,String useros){
		String sql = "select * from fz_admin where username = '"+username+"' and isuse='1'";
	//	String sql2 = "insert into fz_adminlog(username,password,logintime,loginip,useros,ok) values('"+username+"','"+md5password+"','"+date+"','"+loginip+"','"+useros+"','true')";
		//String sql3 = "insert into fz_adminlog(username,password,logintime,loginip,useros,ok) values('"+username+"','"+password+"','"+date+"','"+loginip+"','"+useros+"','false')";
		String sql4 = "update fz_admin set logintimes = logintimes+1 where username = '"+username+"' ";
		DBO dbo = new DBO();
		String pwd;
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			if(rs.next()){
				pwd = rs.getString("password");
				if(pwd.trim().equals(md5password)){
					//dbo.executeUpdate(sql2);
					dbo.executeUpdate(sql4);
					return Constant.SUCCESS;
				}
				else{
					//dbo.executeUpdate(sql3);
					return Constant.PASSWORD_ERROR;
				}
			}
			else{
				//dbo.executeUpdate(sql3);
				return Constant.NAME_ERROR;
			}			
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}	
	}
	
	//查询管理员信息
	public List getAdminInfo(String username){
		String sql = "select * from fz_admin where username='"+username+"' ";
		DBO dbo = new DBO();
		dbo.open();
		list = new ArrayList();
		try{
			rs = dbo.executeQuery(sql);
			rs.next();
			list.add(rs.getString("username"));
			list.add(rs.getString("flag"));
			list.add(rs.getString("logintimes"));
			list.add(date);
			list.add(rs.getString("quanxian"));
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
	
	//admin edit password
	public int editPassword(String username,String oldpwd,String newpwd){
		String sql="select * from fz_admin where username = '"+username+"' and password = '"+oldpwd+"'";
		String sql2="update fz_admin set password = '"+newpwd+"' where username = '"+username+"'";
		DBO dbo = new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			if(rs.next()){
				int i = dbo.executeUpdate(sql2);
				if(i == 1){
					return Constant.SUCCESS;
				}
				else{
					return Constant.SYSTEM_ERROR;
				}
			}
			else{
				return Constant.PASSWORD_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	
	//add manager
	public int addManager(String username,String password,String type,String isuse){
		String sql = "insert into fz_admin(username,password,creattime,flag,isuse,logintimes,quanxian) values('"+username+"','"+password+"','"+date+"','"+type+"','"+isuse+"','0','111')";
		String sql2 = "select * from fz_admin where username = '"+username+"'";
		DBO dbo = new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql2);
			if(rs.next()){
				return Constant.SAME_NAME;
			}
			else{
				int i = dbo.executeUpdate(sql);
				if(i == 1)return Constant.SUCCESS;
				else return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	
	//update manager
	public int updateManager(String username,String password,String type,String isuse){
		String sql;
		if(password.equals("")){
			sql = "update fz_admin set flag = '"+type+"' ,isuse = '"+isuse+"' where username = '"+username+"'";
		}
		else{
			sql = "update fz_admin set password = '"+password+"' ,flag = '"+type+"' ,isuse = '"+isuse+"' where username = '"+username+"'";
		}
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);
			if(i == 1)return Constant.SUCCESS;
			else return Constant.SYSTEM_ERROR;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	
	//get all manager
	public List getAllManager(){
		String sql = "select * from fz_admin where flag !='1' order by id asc";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				List list2 = new ArrayList();
				list2.add(rs.getString(1));
				list2.add(rs.getString(2));
				list2.add(rs.getString(4));
				list2.add(rs.getString(5));
				list2.add(rs.getString(6));
				list2.add(rs.getString(7));
				list.add(list2);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
	
	//delete manager
	public int delManager(int id){
		String sql = "delete from fz_admin where id = '"+id+"'";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);
			if(i == 1)return Constant.SUCCESS;
			else return Constant.SYSTEM_ERROR;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	
	//delete login note
	public int delLog(int id[]){
		DBO dbo=new DBO();
		dbo.open();
		try{
			for(int i = 0;i<id.length;i++){
				dbo.executeUpdate("delete from  fz_adminlog  where  id = '"+id[i]+"'");			
			}
			return Constant.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
} 

