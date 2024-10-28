package com.action;

/**
 * 
 * 网站后台管理注册会员 查询 冻结 删除会员
 */		


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.MemberManageBean;
import com.bean.SystemBean;
import com.util.Constant;
import com.util.Filter;
public class MemberManageAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MemberManageAction() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(Constant.CONTENTTYPE);
		request.setCharacterEncoding(Constant.CHARACTERENCODING);
		String sysdir = new SystemBean().getDir();
		HttpSession session = request.getSession();
		try{
			String username2 = (String)session.getAttribute("user");
			if(username2 == null){
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			else{
				String method = Filter.escapeHTMLTags(request.getParameter("method").trim());
				MemberManageBean mmBean = new MemberManageBean();
				if(method.equals("DELMEMBER")||method.equals("DELCO")||method.equals("DELTODAY")
						||method.equals("DELALLCLOSE")||method.equals("DELALLUSE")){//删除来自后台各个页面的会员
					String check[] = request.getParameterValues("checkit");
					if(check == null){
						if(method.equals("DELMEMBER")){//来自所有个人会员页面
							request.setAttribute("message", "请选择要删除的记录！");
							request.getRequestDispatcher(sysdir+"/member/person.jsp").forward(request, response);
						}
						else if(method.equals("DELCO")){//来自所有企业会员页面
							request.setAttribute("message", "请选择要删除的记录！");
							request.getRequestDispatcher(sysdir+"/member/co.jsp").forward(request, response);
						}
						else if(method.equals("DELTODAY")){//来自今日注册会员页面
							request.setAttribute("message", "请选择要删除的记录！");
							request.getRequestDispatcher(sysdir+"/member/today.jsp").forward(request, response);
						}
						else if(method.equals("DELALLCLOSE")){//来自所有冻结会员页面
							request.setAttribute("message", "请选择要删除的记录！");
							request.getRequestDispatcher(sysdir+"/member/close.jsp").forward(request, response);
						}
						else if(method.equals("DELALLUSE")){//来自所有在用会员页面
							request.setAttribute("message", "请选择要删除的记录！");
							request.getRequestDispatcher(sysdir+"/member/using.jsp").forward(request, response);
						}
					}
					else{
						int id[]= new int[check.length];
						for(int i = 0;i<check.length;i++){
							int s = Integer.parseInt(check[i]);				
							id[i] = s;
						}
						int flag = mmBean.delMember(id);
						if(flag == Constant.SUCCESS){
							if(method.equals("DELMEMBER")){//来自所有个人会员页面
								request.getRequestDispatcher(sysdir+"/member/person.jsp").forward(request, response);
							}
							else if(method.equals("DELCO")){//来自所有企业会员页面
								request.getRequestDispatcher(sysdir+"/member/co.jsp").forward(request, response);
							}
							else if(method.equals("DELTODAY")){//来自今日注册会员页面
								request.getRequestDispatcher(sysdir+"/member/today.jsp").forward(request, response);
							}
							else if(method.equals("DELALLCLOSE")){//来自所有冻结会员页面
								request.getRequestDispatcher(sysdir+"/member/close.jsp").forward(request, response);
							}
							else if(method.equals("DELALLUSE")){//来自所有在用会员页面
								request.getRequestDispatcher(sysdir+"/member/using.jsp").forward(request, response);
							}
						}
						else{
							if(method.equals("DELMEMBER")){//来自所有个人会员页面
								 request.getRequestDispatcher(sysdir+"/member/person.jsp").forward(request, response);
							}
							else if(method.equals("DELCO")){//来自所有企业会员页面
								 request.getRequestDispatcher(sysdir+"/member/co.jsp").forward(request, response);
							}
							else if(method.equals("DELTODAY")){//来自今日注册会员页面
								 request.getRequestDispatcher(sysdir+"/member/today.jsp").forward(request, response);
							}
							else if(method.equals("DELALLCLOSE")){//来自所有冻结会员页面
								 request.getRequestDispatcher(sysdir+"/member/close.jsp").forward(request, response);
							}
							else if(method.equals("DELALLUSE")){//来自所有在用会员页面
								 request.getRequestDispatcher(sysdir+"/member/using.jsp").forward(request, response);
							}
						}
					}
				}
				else if(method.equals("CLOSE")){
					String id=request.getParameter("id").trim();
					int flag=mmBean.closeMember(Integer.parseInt(id));
					if(flag==Constant.SUCCESS){
						request.setAttribute("message", "操作成功！");
						request.getRequestDispatcher("admin/member/person.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "系统维护中，请稍后再试！");
						request.getRequestDispatcher("admin/member/person.jsp").forward(request, response);
					}
				}
				else{
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			}
		}catch(Exception e){
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
