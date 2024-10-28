<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,java.util.*,com.util.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:useBean id="yzm" scope="page" class="com.util.CheckCode"/>
<jsp:useBean id="sdir" scope="page" class="com.bean.SystemBean"/>
<%
List list = sdir.getSiteInfo();
String str = list.get(0).toString();
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>网站后台管理登录---<%=str %></title>
<style type="text/css">
<!--
.font1 {  font-family: "宋体"; font-size: 12px; line-height: 130%}
a {  font-family: "宋体"; font-size: 12px}
a:link {  font-family: "宋体"; font-size: 12px; color: #CFD1E8; text-decoration: underline}
a:hover {  font-family: "宋体"; font-size: 12px; color: #FFCC00; text-decoration: none}
a:visited {  font-family: "宋体"; font-size: 12px; color: #CFD1E8; text-decoration: underline}
.input {  font-family: "宋体"; font-size: 12px; color: #FFFFFF; border: #4047A4; border-style: solid; border-top-width: 1px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px; background-color: #000077}
-->
</style>
</head>
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
 	String code=yzm.getCheckCode();
 	String dir=sdir.getDir();
%>
<body  background="<%=basePath %><%=dir %>/images/bg1.jpg">
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr>
    <td valign="middle" align="center"> 
      <table width="360" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td align="center"> </td>
        </tr>
      </table>
      <table width="280" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="30">&nbsp;</td>
        </tr>
        <tr>
          <td bgcolor="#4047A4" height="1"></td>
        </tr>
      </table>
      <table width="280" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td bgcolor="#4047A4" width="1"></td>
          <td> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="36">&nbsp;</td>
              </tr>
            </table><form action="<%=basePath %>AdminServlet" name=form1 method=post onSubmit="return checkform(form1)" autocomplete="off">
            <table width="160" border="0" cellspacing="0" cellpadding="0" align="center">
		      
              <tr> 
                <td colspan="3" class="news" height="5"><input type=hidden name=method value="one" /></td>
              </tr>
			 
              <tr> 
                <td width="5" class="nwes" height="36"></td>
                <td width="56" class="font1" height="36"><font color="#ffffff">用户名</font></td>
                <td> 
		            <input type="text" name="username"  size="15" class=input  onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" 
                              onkeyup="value=value.replace(/[\W]/g,'')"/>
                </td>
              </tr>
              <tr> 
                <td class="nwes" height="36">&nbsp; </td>
                <td class="font1" height="36"><font color="#ffffff">口　令</font></td>
                <td> 
		            <input type="password" name="password" value="" size="15" class=input onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" 
                              onkeyup="value=value.replace(/[\W]/g,'')">
                </td>
              </tr>
              
              <tr> 
                <td height="5" colspan="3"></td>
              </tr>
              <tr> 
                <td>&nbsp; </td>
                <td align="center">&nbsp; </td>
                <td> 
                  <input type="image" border="0" name="imageField" src="<%=basePath %><%=dir %>/images/bt_login.gif" width="70" height="16">
                </td>
              </tr>
			  
            </table></form>	
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="36">&nbsp;</td>
              </tr>
            </table>
          </td>
          <td bgcolor="#4047A4" width="1"></td>
        </tr>
      </table>
      <table width="280" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td bgcolor="#4047A4" height="1"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
<script language=Javascript>
	
	form1.username.focus()	

	function checkform(form)
	{
		var flag=true;
		if(form("username").value==""){alert("请输入用户名!");form("username").focus();return false};
		if(form("password").value==""){alert("请输入口令!");form("password").focus();return false}; 
		return flag;
	}
</script>
