<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%>
<%@ page import="com.bean.*" %>
<jsp:useBean id="lb" scope="page" class="com.bean.AdminBean" />
<jsp:useBean id="sn" scope="page" class="com.bean.SystemBean" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String dir=sn.getDir();
%>
<HTML><HEAD><TITLE>后台操作区</TITLE>
<LINK href="<%=basePath %><%=dir %>/images/Admin_Style.css" type=text/css rel=stylesheet>
<LINK href="<%=basePath %><%=dir %>/images/style.css" type=text/css rel=stylesheet>
<SCRIPT language=JavaScript src="<%=basePath %><%=dir %>/images/Common.js"></SCRIPT>
<STYLE type=text/css>
BODY {
	MARGIN-LEFT: 0px; BACKGROUND-COLOR: #ffffff
}
.STYLE1 {color: #ECE9D8}
</STYLE>
</HEAD>
<%
String message = (String)request.getAttribute("message");
	if(message == null){
		message = "";
	}
	if (!message.trim().equals("")){
		out.println("<script language='javascript'>");
		out.println("alert('"+message+"');");
		out.println("</script>");
	}
	request.removeAttribute("message");
%>
<%
	String username=(String)session.getAttribute("user");
	if(username==null){
		response.sendRedirect(path+"/error.jsp");
	}
	else{
		List list=(List)session.getAttribute("list");
		List list2=lb.getAllManager();
		
%>
<BODY oncontextmenu="return false;" onselectstart="return false;" leftMargin=0 background=<%=basePath %><%=dir %>/images/MainBg.gif topMargin=0 scroll=no marginheight="0" marginwidth="0">
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=top >
	<form action="<%=basePath %>/AdminServlet?method=manager" method="post" name="form1" onSubmit="return checkUser()">
       <TABLE width="100%" border=0 align="center" cellPadding=3 cellSpacing=1 class=tablewidth>
		  <TBODY>
		  <TR align="center" class=head>
			<TD height=23>序号</TD>
			<TD>系统账号名</TD>
			<TD>创建时间</TD>
			<TD>状态</TD>
			<TD>登录次数</TD>
			<TD>删除</TD>
		  </TR>
		<%
		if(list2.size()!=0){
			for(int i = 0; i<list2.size(); i++){
				List list3 = (List)list2.get(i);
		%>
		  <TR onClick=OnclikeTable(this) align="center" bgColor=#ffffff>
			<TD width="30" id=map><%=i+1 %></TD>
			<TD id=map><%=list3.get(1).toString() %></TD>
			<TD id=map><%=list3.get(2).toString() %></TD>
			<TD id=map><%=Integer.parseInt(list3.get(4).toString())==1?"在用":"冻结" %></TD>
			<TD id=map><%=list3.get(5).toString() %></TD>
			<TD id=map><%if(Integer.parseInt(list3.get(0).toString())==1){out.print("删除");}else{%><a href="<%=basePath %>AdminServlet?method=delm&id=<%=list3.get(0).toString()%>">删除</a><%} %></TD>
		  </TR>
		<%}}else{%>	
		  <TR align="center" bgColor=#ffffff>
			<TD colspan=8 align=center id=map>当前还没有添加系统账号</TD>
		  </TR>
		<%} %>  
		  </TBODY>
	   </TABLE><br>
	   <TABLE width="40%" border=0 align="center" cellPadding=3 cellSpacing=1>
	   <TR><TD>
	   <TABLE width="100%" border=0 align="center" cellPadding=3 cellSpacing=1 class=tablewidth>
		  <TBODY>
		  <TR align="center" class=head>
			<TD height=23 colspan="2">增加/修改系统账号</TD>
		  </TR>
		  <TR  align="center" bgColor=#ffffff>
			<TD width="40%" align="right" id=map>系统账号名：</TD><input type=hidden name=ra value="">
			<TD width="60%" align="left" id=map><input type="text" name="username" value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>" maxlength="10" size="18" class=input  onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" 
                              onkeyup="value=value.replace(/[\W]/g,'')"/>&nbsp;字母或数字6-10位</TD>
		  </TR>
		  <TR align="center" bgColor=#ffffff>
			<TD align="right" id=map>登录密码：</TD>
			<TD align="left" id=map><input type="text" name="password" maxlength="16" size="18" class=input  onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" 
                              onkeyup="value=value.replace(/[\W]/g,'')"/>&nbsp;字母或数字6-16位</TD>
		  </TR>
		  <TR align="center" bgColor=#ffffff>
			<TD align="right" id=map>当前状态：</TD>
			<TD align="left" id=map><select name="isuse">
									<option value="">--请选择--</option>
									<option value="在用">在用</option>
									<option value="冻结">冻结</option>
									</select></TD>
		  </TR>
		  <TR align="center" bgColor=#ffffff>
			<TD align="right" id=map>操作类型：</TD>
			<TD align="left" id=map><input type="radio" name="add" value="add" checked>增加&nbsp;<input type="radio" name="update" value="update"  disabled=true>修改</TD>
		  </TR>
		  <TR align="center" bgColor=#ffffff>
			<TD colspan="2" align="center" id=map><input type="submit" value="确定">&nbsp;&nbsp;<input type="reset" value="重填"></TD>
			</TR>
		  </TBODY>
	   </TABLE>
	   </TD></TR></table><br>
	   <TABLE align="center" width="80%" border="0">
	    <TR align="center" valign="top">
		<TD align="left"><div align="left">注意：1、如增加系统账号，请直接输入系统账号名和登录密码，并选择管理级别和使用状态，选中“增加”按钮，确定即可。<br>
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、如修改系统账号信息，请用鼠标点击系统账号名，页面中部文本框会自动显示该系统账号信息，修改后注意保持“修改”按钮为选中状态，确定即可。<br>
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、如不修改登录密码，请保持密码框为空。<br>	
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4、如想删除某系统账号，直接用鼠标点击表格后的“删除”文字即可。	<br>
		  </div></TD>
       </TR>
	   </TABLE>
	  </form>
    </TD>
  </TR>
  </TBODY>
</TABLE>
</BODY>
<%} %>
</HTML>
