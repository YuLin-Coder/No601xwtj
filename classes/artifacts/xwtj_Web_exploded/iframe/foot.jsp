<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312" %>
<jsp:useBean id="cb6" scope="page" class="com.bean.ComBean"/> 
<TABLE class=dragTable cellSpacing=0 cellPadding=0 width="100%" height=50 align=center border=0>
  <TBODY>
  <TR>
    <TD><br>
       <%List ttflist=cb6.getCom("select * from lj order by id desc",3);if(!ttflist.isEmpty()){for(int i=0;i<ttflist.size();i++){List list2=(List)ttflist.get(i); %>
    <a href="http://<%=list2.get(2).toString() %>" target="_blank"><%=list2.get(1).toString() %></a>
    <%}} %>
	</TD>
   </TR>
  </TBODY>
</TABLE>
<TABLE id=footer cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
<TBODY>
  <TR>
    <TD align=middle>
	<DIV align=center>新闻发布系统</DIV>
    </TD>
 </TR>
</TBODY>
</TABLE>
<SCRIPT language=JavaScript>
<!--//目的是为了做风格方便
document.write('</div>');
//-->
</SCRIPT>

<SCRIPT language=JavaScript>
<!--
clickEdit.init();
//-->
</SCRIPT>
 
