package com.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.util.Constant;
import com.util.DBO;

/**
 * 
 * 网站后台管理注册会员 查询 冻结 删除会员
 */	

public class MemberManageBean {

	private List list;
	private ResultSet rs = null;
	private int EVERYPAGENUM = 2;
	private int count = -1;
	private int qq = 0;
	private String sql="select count(*) from member where type='person'";
	private String sql2="select * from member where type='person' order by id desc ";
	//声明时间变量
	String date1=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
	String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	
	//分页查询所有个人会员
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
    				list2.add(rs.getString("regtime"));
    				list2.add(rs.getString("ifuse"));
    				list2.add(rs.getString("logintimes"));
    				list2.add(rs.getString("lasttime"));
    				list2.add(rs.getString("lastip"));
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
    
    ///所有企业会员////////////////////
    public int getMessageCountCO() { //得到信息总数
        DBO dbo=new DBO();
        dbo.open();
         try { 
             rs = dbo.executeQuery("select count(*) from member where type='co'");
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
     public List getMessageCO(int page) { //得到每页要显示的信息
         DBO dbo=new DBO();
         dbo.open();
         List list = new ArrayList();
         try {
             rs = dbo.executeQuery("select * from member where type='co' order by id desc ");
             for (int i = 0; i < (page - 1) * EVERYPAGENUM; i++) {
                 rs.next();
             }
             for (int t = 0; t < EVERYPAGENUM; t++) {
                 if (rs.next()) {
                     qq++;
                    List list2=new ArrayList();
                    list2.add(rs.getString("id"));
     				list2.add(rs.getString("username"));
     				list2.add(rs.getString("regtime"));
     				list2.add(rs.getString("ifuse"));
     				list2.add(rs.getString("logintimes"));
     				list2.add(rs.getString("lasttime"));
     				list2.add(rs.getString("lastip"));
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
     
     //所有在用会员
     public int getMessageCountUS() { //得到信息总数
         DBO dbo=new DBO();
         dbo.open();
          try { 
              rs = dbo.executeQuery("select count(*) from member where ifuse='1'");
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
      public List getMessageUS(int page) { //得到每页要显示的信息
          DBO dbo=new DBO();
          dbo.open();
          List list = new ArrayList();
          try {
              rs = dbo.executeQuery("select * from member where ifuse='1' order by id desc ");
              for (int i = 0; i < (page - 1) * EVERYPAGENUM; i++) {
                  rs.next();
              }
              for (int t = 0; t < EVERYPAGENUM; t++) {
                  if (rs.next()) {
                      qq++;
                     List list2=new ArrayList();
                     list2.add(rs.getString("id"));
      				list2.add(rs.getString("username"));
      				list2.add(rs.getString("regtime"));
      				list2.add(rs.getString("ifuse"));
      				list2.add(rs.getString("logintimes"));
      				list2.add(rs.getString("lasttime"));
      				list2.add(rs.getString("lastip"));
      				list2.add(rs.getString("type"));
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
//    所有冻结会员
      public int getMessageCountCL() { //得到信息总数
          DBO dbo=new DBO();
          dbo.open();
           try { 
               rs = dbo.executeQuery("select count(*) from member where ifuse='0'");
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
       public List getMessageCL(int page) { //得到每页要显示的信息
           DBO dbo=new DBO();
           dbo.open();
           List list = new ArrayList();
           try {
               rs = dbo.executeQuery("select * from member where ifuse='0' order by id desc ");
               for (int i = 0; i < (page - 1) * EVERYPAGENUM; i++) {
                   rs.next();
               }
               for (int t = 0; t < EVERYPAGENUM; t++) {
                   if (rs.next()) {
                       qq++;
                      List list2=new ArrayList();
                      list2.add(rs.getString("id"));
       				list2.add(rs.getString("username"));
       				list2.add(rs.getString("regtime"));
       				list2.add(rs.getString("ifuse"));
       				list2.add(rs.getString("logintimes"));
       				list2.add(rs.getString("lasttime"));
       				list2.add(rs.getString("lastip"));
       				list2.add(rs.getString("type"));
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
//     今日注册会员
       public int getMessageCountTODAY() { //得到信息总数
           DBO dbo=new DBO();
           dbo.open();
            try { 
                rs = dbo.executeQuery("select count(*) from member where regtime between '"+date1+"' and '"+date+"'");
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
        public List getMessageTODAY(int page) { //得到每页要显示的信息
            DBO dbo=new DBO();
            dbo.open();
            List list = new ArrayList();
            try {
                rs = dbo.executeQuery("select * from member where regtime between '"+date1+"' and '"+date+"' order by id desc ");
                for (int i = 0; i < (page - 1) * EVERYPAGENUM; i++) {
                    rs.next();
                }
                for (int t = 0; t < EVERYPAGENUM; t++) {
                    if (rs.next()) {
                        qq++;
                       List list2=new ArrayList();
                       list2.add(rs.getString("id"));
        				list2.add(rs.getString("username"));
        				list2.add(rs.getString("regtime"));
        				list2.add(rs.getString("ifuse"));
        				list2.add(rs.getString("logintimes"));
        				list2.add(rs.getString("lasttime"));
        				list2.add(rs.getString("lastip"));
        				list2.add(rs.getString("type"));
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
   
    /*********************************************************************************************************************************
     * 删除 冻结会员
     * @param id
     * @return
     *********************************************************************/
    //  删除会员
	public int delMember(int id[]){
		DBO dbo=new DBO();
		dbo.open();
		try{
			for(int i = 0;i<id.length;i++){
				dbo.executeUpdate("delete from  member where  id = '"+id[i]+"'");	
				dbo.executeUpdate("delete from  pmember where  mid = '"+id[i]+"'");
				dbo.executeUpdate("delete from  cmember where  mid = '"+id[i]+"'");
			}
			return Constant.SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SYSTEM_ERROR;
		}finally{
			dbo.close();
		}
	}
	//冻结会员
	public int closeMember(int id){
		String sql = "select ifuse from member where id='"+id+"' ";
		String sql2 = "update member set ifuse='0' where id='"+id+"'";
		String sql3 = "update member set ifuse='1' where id='"+id+"'";
		DBO dbo=new DBO();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			if(rs.next()){
				int ifuse=rs.getInt(1);
				if(ifuse == 1){
					int j = dbo.executeUpdate(sql2);
					if(j == 1)
						return Constant.SUCCESS;
					else
						return Constant.SYSTEM_ERROR;
				}
				else{
					int j = dbo.executeUpdate(sql3);
					if(j == 1)
						return Constant.SUCCESS;
					else
						return Constant.SYSTEM_ERROR;
				}
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
	/**********************************************************************
     * 后台查询单条个人 企业会员信息 查询会员类型
     * @param id
     * @return
     *********************************************************************/
    //以id为条件查询会员类型
    public String getType(int id){
    	String sql = "select type from member where id='"+id+"'";
    	DBO dbo=new DBO();
    	dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			if(rs.next()){
				String type = rs.getString(1);
				return type;
			}
			else
				return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			dbo.close();
		}
    }
    //以id为条件 单条会员登陆信息
    public List getMemberLogin(int id){
		String sql = "select * from member where id='"+id+"'";
		DBO dbo=new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			rs.next();
			list.add(rs.getString("username"));
			list.add(rs.getString("type"));
			list.add(rs.getString("regtime"));
			list.add(rs.getString("ifuse"));
			list.add(rs.getString("logintimes"));
			list.add(rs.getString("lasttime"));
			list.add(rs.getString("lastip"));
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
	//以id为条件 单条个人会员信息
	public List getPerSonMember(int id){
		String sql = "select * from pmember where mid='"+id+"'";
		DBO dbo=new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			rs.next();
			list.add(rs.getString("realname"));
			list.add(rs.getString("sex"));
			list.add(rs.getString("bir"));
			list.add(rs.getString("sheng"));
			list.add(rs.getString("city"));
			list.add(rs.getString("telphone"));
			list.add(rs.getString("email"));
			list.add(rs.getString("question"));
			list.add(rs.getString("answer"));
			list.add(rs.getString("address"));
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
//	以id为条件 单条企业会员信息
	public List getCoMember(int id){
		String sql = "select * from cmember where mid='"+id+"'";
		DBO dbo=new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			rs.next();
			list.add(rs.getString("coname"));
			list.add(rs.getString("address"));
			list.add(rs.getString("postnum"));
			list.add(rs.getString("tel"));
			list.add(rs.getString("email"));
			list.add(rs.getString("question"));
			list.add(rs.getString("answer"));
			list.add(rs.getString("intro"));
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}finally{
			dbo.close();
		}
	}
	//////////////以用户名为条件查询///////////////
//	以用户名为条件查询会员类型
    public String getType(String name){
    	String sql = "select type from member where username='"+name+"'";
    	DBO dbo=new DBO();
    	dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			if(rs.next()){
				String type = rs.getString(1);
				return type;
			}
			else
				return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			dbo.close();
		}
    }
    //以用户名为条件查询用户id，然后用id来查询会员信息 跳转到//以id为条件查询会员类型
    public int getID(String name){
    	String sql = "select id from member where username='"+name+"'";
    	DBO dbo=new DBO();
    	dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			if(rs.next()){
				int id = rs.getInt(1);
				return id;
			}
			else
				return 0;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally{
			dbo.close();
		}
    }
//////////////以用户名 id为条件查询///////////////
//	以用户名 id为条件查询会员类型，然后用id来查询会员信息 跳转到//以id为条件查询会员
    public String getType(int id,String name){
    	String sql = "select type from member where id='"+id+"' and username='"+name+"'";
    	DBO dbo=new DBO();
    	dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			if(rs.next()){
				String type = rs.getString(1);
				return type;
			}
			else
				return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			dbo.close();
		}
    }
    
	/////////////////////按注册时间查询/////////////////////
    public List getMemberByTime(String stime,String etime){
    	String sql = "select * from member where regtime between '"+stime+"' and '"+etime+" 23:59:59' ";
    	DBO dbo=new DBO();
		list = new ArrayList();
		dbo.open();
		try{
			rs = dbo.executeQuery(sql);
			while(rs.next()){
				List list2=new ArrayList();
                list2.add(rs.getString("id"));
 				list2.add(rs.getString("username"));
 				list2.add(rs.getString("regtime"));
 				list2.add(rs.getString("ifuse"));
 				list2.add(rs.getString("logintimes"));
 				list2.add(rs.getString("lasttime"));
 				list2.add(rs.getString("lastip"));
 				list2.add(rs.getString("type"));
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
}
