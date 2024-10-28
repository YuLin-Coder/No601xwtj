package com.bean;

/**
 * 新闻管理
 * 
 */
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.util.Constant;
import com.util.DBO;

public class NewsBean {

	private String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	private List list;
	private ResultSet rs = null;
	
	
	//不置顶新闻分页
	private int EVERYPAGENUM = 2;
	private int count = -1;
	private int qq = 0;
	private String sql="select count(*) from fz_news";
	private String sql2="select * from fz_news order by id desc";
	
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
                    list2.add(rs.getString(1));
    				list2.add(rs.getString(2));
    				list2.add(rs.getString(3));
    				list2.add(rs.getString(4));
    				list2.add(rs.getString(5));
    				list2.add(rs.getString(6));
    				list2.add(rs.getString(7));
    				list2.add(rs.getString(8));
    				list.add(list2);
                } else {
                    break; //减少空循环的时间
                }
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return list;
        } finally {
            dbo.close();
        }
    }
   
	//add news
	public int addNews(String title,String fenlei,String content,String adder,String gjz){
		String sql = "insert into fz_news (title,fenlei,content,addtime,adder,visit,gjz) " +
				"values ('"+title+"','"+fenlei+"','"+content+"','"+date+"','"+adder+"','0','"+gjz+"')";
		DBO dbo = new DBO();
		dbo.open();
		try{
			
			int i = dbo.executeUpdate(sql);
			if(i == 1){
				return Constant.SUCCESS;
			}
			else{
				return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	
	//update news
	public int updateNews(int id,String title,String fenlei,String content,String adder,String gjz){
		String sql = "update fz_news set title = '"+title+"',fenlei = '"+fenlei+"',content='"+content+"',addtime='"+date+"'," +
				"adder='"+adder+"',gjz='"+gjz+"' where id = '"+id+"' ";
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);
			if(i == 1){
				return Constant.SUCCESS;
			}
			else{
				return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}

	//delete news
	public int delNews(int id[]){
		DBO dbo = new DBO();
		dbo.open();
		try{
			for(int i = 0;i<id.length;i++){
				dbo.executeUpdate("delete from  fz_news  where  id = '"+id[i]+"'");				
			}
			return Constant.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	public void del(String filepath) {
		try{
			File f = new File(filepath);//定义文件路径        
			if(f.exists()){//判断是文件还是目录
			    f.delete();//递归调用
			}
		}catch(Exception e){
			
		}
	}
	
	//get one news to update
	public List getOneNews(int id){
		String sql = "select * from fz_news where id = '"+id+"'";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				list.add(rs.getString(1));//0id
				list.add(rs.getString(2));//1title
				list.add(rs.getString(3));//2content
				list.add(rs.getString(4));//3addtime
				list.add(rs.getString(5));//4adder
				list.add(rs.getString(6));//4adder
				list.add(rs.getString(7));//4adder
				list.add(rs.getString(8));//4adder
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
	
	//get news count
	public int getNewsCount(){
		String sql = "select count (*) from fz_news ";
		DBO dbo = new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			if(rs.next()){
				return rs.getInt(1);
			}
			else{
				return 0;
			}
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally{
			dbo.close();
		}
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	//首页10条新闻
	public List getIndexNews(){
		//String sql = "select id,type,title,addtime from news where  ifhide='1' order by id desc  limit 0,10";
		
		String sql = "select id,title,addtime from fz_news  order by id desc  limit 0,10";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);			
			while(rs.next()){
				List list2 = new ArrayList();
				list2.add(rs.getString("id"));
				list2.add(rs.getString("title"));
				list2.add(rs.getString("addtime"));
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
//	首页12条新闻
	public List getIndexNews12(){
		String sql = "select  id,title,addtime from fz_news where  ifhide='1' order by id desc  limit 0,13";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			for(int i=0;i<6;i++){
				rs.next();
			}
			while(rs.next()){
				List list2 = new ArrayList();
				list2.add(rs.getString("id"));
				list2.add(rs.getString("title"));
				list2.add(rs.getString("addtime"));
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

	//前台新闻页新闻列表
	public List getAllNews(){
		String sql = "select id,title,addtime  from fz_news   order by id desc ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				List list2 = new ArrayList();
				list2.add(rs.getString("id"));
				list2.add(rs.getString("title"));
				list2.add(rs.getString("addtime"));
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
	public List getaNews(){
		String sql = "select  * from fz_news order by id desc  limit 0,10";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				List list2 = new ArrayList();
				list2.add(rs.getString(1));//0id
				list2.add(rs.getString(2));//1title
				list2.add(rs.getString(3));//2content
				list2.add(rs.getString(4));//3addtime
				list2.add(rs.getString(5));//4adder
				list2.add(rs.getString(6));//4adder
				list2.add(rs.getString(7));//4adder
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
//	5条热门新闻
	public List get10HotNews(){
		String sql = "select  * from fz_news order by visit desc limit 0,10 ";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				List list2 = new ArrayList();
				list2.add(rs.getString(1));//0id
				list2.add(rs.getString(2));//1title
				list2.add(rs.getString(3));//2content
				list2.add(rs.getString(4));//3addtime
				list2.add(rs.getString(5));//4adder
				list2.add(rs.getString(6));//4adder
				list2.add(rs.getString(7));//4adder
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
//	更新点击率
	public int upVisit(int id){
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate("update  fz_news set visit=visit+1  where  id = '"+id+"'");		
			if(i==1){
				return Constant.SUCCESS;
			}
			else{
				return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	 
	public int addFenlei(String title){
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate("insert into fenlei(title,addtime) values('"+title+"','"+date+"')");		
			if(i==1){
				return Constant.SUCCESS;
			}
			else{
				return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	public int delFenlei(String id){
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate("delete from fenlei where id='"+id+"'");		
			if(i==1){
				return Constant.SUCCESS;
			}
			else{
				return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	public int delMethod(String sql){
		DBO dbo = new DBO();
		dbo.open();
		try{
			int i = dbo.executeUpdate(sql);		
			if(i==1){
				return Constant.SUCCESS;
			}
			else{
				return Constant.SYSTEM_ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	public List getFenlei(){
		String sql = "select* from fenlei  order by id asc";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				List list2=new ArrayList();
				list2.add(rs.getString(1));
				list2.add(rs.getString(2));
				list2.add(rs.getString(3));
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
	public List getGuoLv(){
		String sql = "select* from guolv  order by id asc";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				List list2=new ArrayList();
				list2.add(rs.getString(1));
				list2.add(rs.getString(2));
				list2.add(rs.getString(3));
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
	public List getPl(String nid){
		String sql = "select* from pl where nid='"+nid+"'  order by id desc";
		DBO dbo = new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				List list2=new ArrayList();
				list2.add(rs.getString(1));
				list2.add(rs.getString(2));
				list2.add(rs.getString(3));
				list2.add(rs.getString(4));
				list2.add(rs.getString(5));
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
	public int getMessageCountSs(String sql) { //得到信息总数
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
	    
	    public List getMessageSS(int page,String sql2,int jj) { //得到每页要显示的信息
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
	                    for(int mm=1;mm<=jj;mm++){
	                    	list2.add(rs.getString(mm));
	                    }
	    				list.add(list2);
	                } else {
	                    break; //减少空循环的时间
	                }
	            }
	            return list;
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return list;
	        } finally {
	            dbo.close();
	        }
	    }
	   
}

