<%@ page language="java" import="java.util.*,com.util.*" contentType="text/html;charset=gb2312"%>
<jsp:useBean id="sn" scope="page" class="com.bean.SystemBean" />
<%
List list = sn.getSiteInfo();
String str = list.get(0).toString();
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Frameset//EN">
<HTML><HEAD><TITLE>网站后台管理---<%=str %></TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META content="MSHTML 6.00.2900.3243" name=GENERATOR></HEAD>
<%
	String username=(String)session.getAttribute("user");
	if(username==null){
		response.sendRedirect(path+"/error.jsp");
	}
	else{
		String dir=sn.getDir();		
%>
<FRAMESET border=0 frameSpacing=0 rows=65,*,33 frameBorder=0>
<FRAME name=FrameTop src="<%=basePath %><%=dir %>/iframe/top.jsp" frameBorder=no noResize scrolling=no>
<FRAMESET border=0 name=FrameMain frameSpacing=0 frameBorder=0 cols=165,*>
<FRAME name=LeftFrame marginWidth=0 marginHeight=0 src="<%=basePath %><%=dir %>/iframe/left.jsp" frameBorder=no scrolling=no>
<FRAMESET border=0 frameSpacing=0 rows=*,26 frameBorder=0>
<FRAME name=MainFrame marginWidth=0 marginHeight=0 src="<%=basePath %><%=dir %>/iframe/main.jsp" frameBorder=no noResize scrolling=yes>
<FRAME name=BottomFrame marginWidth=0 marginHeight=0 src="<%=basePath %><%=dir %>/iframe/help.jsp" frameBorder=no scrolling=no>
</FRAMESET>
</FRAMESET>
<FRAME name=FrameBottom marginWidth=0 marginHeight=0 src="<%=basePath %><%=dir %>/iframe/foot.jsp" frameBorder=no noResize scrolling=no>
</FRAMESET>
<%} %>
</HTML>
