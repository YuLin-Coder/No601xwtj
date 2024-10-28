<%@ page language="java" import="java.util.*" contentType="text/html;charset=gb2312" %>
<%@ include file="iframe/head.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<LINK href="images/default.css" type=text/css rel=stylesheet>
<LINK href="images/css.css" type=text/css rel=stylesheet>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
 
<META content="MSHTML 6.00.2900.3268" name=GENERATOR>
</HEAD>
<%
	String id=request.getParameter("id");if(id==null)id=(String)request.getAttribute("id");
	List list=news.getOneNews(Integer.parseInt(id));
	news.upVisit(Integer.parseInt(id));
 %>
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
<BODY text=#000000 bgColor=#ffffff leftMargin=0 topMargin=0>
 
     <TABLE class=dragTable cellSpacing=0 cellPadding=0 width="100%" border=0>
		<TBODY>
        <TR>
          <TD class=head colspan="2">
			<SPAN class=TAG><%=list.get(2).toString() %></SPAN> 
			
		  </TD>
		</TR>
		<TR>
		<TD  class=middle align="left" >
               <TABLE class=xsnr id=tb1_nr1  cellSpacing=0 cellPadding=0 width="100%" border=0>
                    <TBODY>
                    
        <TR>
          <TD  align=center>
		  <h3><%=list.get(1).toString() %></h3>
		   </TD>
		</TR>
		<TR>
          <TD  align=center>
		  发布时间：<%=list.get(4).toString().substring(0,10) %> 发布人：<%=list.get(5).toString() %> 点击率：<%=list.get(6).toString() %>
		   </TD>
		</TR>
		<TR>
          <TD  align=center>
		  <%=list.get(3).toString() %>
		   </TD>
		</TR>
				</TBODY>
			</TABLE>			
			</TD>
		</TR>
        </TBODY>
	  </TABLE>
<TABLE class=dragTable cellSpacing=0 cellPadding=0 width="100%" border=0>
		<TBODY>
        <TR>
          <TD class=head colspan="2">
			<SPAN class=TAG>评论信息</SPAN> 
			
		  </TD>
		</TR>
		<TR>
		<TD  class=middle align="left" > 
		<form name="fff" action="<%=basePath %>News.shtml?method=pl&nid=<%=id%>" method=post  onsubmit="return cc()">
		 <TABLE class=xsnr id=tb1_nr1  cellSpacing=0 cellPadding=0 width="100%" border=0>
                    <TBODY>           
        <TR>
          <TD  align=center>评论内容</TD>
          <TD  align=center>评论人</TD>
          <TD  align=center>评论时间</TD>
		</TR>
		<%List pllist=news.getPl(id); 
		if(!pllist.isEmpty()){for(int i=0;i<pllist.size();i++){List lists=(List)pllist.get(i);
		%>
		<TR>
          <TD  align=center><%=lists.get(2).toString() %></TD>
          <TD  align=center><%=lists.get(3).toString() %></TD>
          <TD  align=center><%=lists.get(4).toString() %></TD>
		</TR>
		
		<%}} %>
		<TR>
         
          <TD  align=center colspan=3><textarea name=content cols=40 rows=8></textarea><br>
          <input type=submit value="提交"></TD>
          
		</TR>
		</TBODY>
		</TABLE></form>
<SCRIPT language=JavaScript>
function cc()
{
	if(document.fff.content.value=="")
	{
		alert("请输入评论内容");
		document.fff.content.focus();
		return false;
	}
}
</SCRIPT>

 

<%@ include file="iframe/foot.jsp"%>