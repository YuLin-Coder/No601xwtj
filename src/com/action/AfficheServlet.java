package com.action;

/**
 * 网站公告
 * @author Administrator
 *
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.AfficheBean;
import com.bean.SystemBean;
import com.util.Constant;
import com.util.Filter;

public class AfficheServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AfficheServlet() {
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
				AfficheBean afficheBean = new AfficheBean();
				String method = request.getParameter("method").trim();
				if(method.equals("addAffiche")){//增加公告
					String title = Filter.escapeHTMLTags(request.getParameter("title").trim());
					String content = Filter.escapeHTMLTags(request.getParameter("content").trim());
					String adder = username2;
					String ifhide = Filter.escapeHTMLTags(request.getParameter("ifhide").trim());
					int flag = afficheBean.addAffiche(title, content, adder, ifhide);
					if(flag == Constant.SUCCESS){
						request.setAttribute("message", "操作成功！");
						request.getRequestDispatcher(sysdir+"/affiche/index.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "系统维护中请稍后再试！");
						request.getRequestDispatcher(sysdir+"/affiche/index.jsp").forward(request, response);
					}
				}
				else if(method.equals("editAffiche")){//修改公告
					String id=Filter.escapeHTMLTags(request.getParameter("id").trim());
					String title = Filter.escapeHTMLTags(request.getParameter("title").trim());
					String content = Filter.escapeHTMLTags(request.getParameter("content").trim());
					String adder = username2;
					String ifhide = Filter.escapeHTMLTags(request.getParameter("ifhide").trim());
					int flag = afficheBean.updateAffiche(Integer.parseInt(id), title, content, adder, ifhide);
					if(flag == Constant.SUCCESS){
						request.setAttribute("message", "操作成功！");
						request.getRequestDispatcher(sysdir+"/affiche/index.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "系统维护中请稍后再试！");
						request.getRequestDispatcher(sysdir+"/affiche/index.jsp").forward(request, response);
					}
				}
				else if(method.equals("hideAffiche")){
					String id = Filter.escapeHTMLTags(request.getParameter("id").trim());
					int flag = afficheBean.hideAffiche(Integer.parseInt(id));
					if(flag == Constant.SUCCESS){
						request.getRequestDispatcher(sysdir+"/affiche/index.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "系统维护中，请稍后再试！");
						request.getRequestDispatcher(sysdir+"/affiche/index.jsp").forward(request, response);
					}
				}
				else if(method.equals("delaffiche")){//删除公告
					String check[] = request.getParameterValues("checkit");
					if(check == null){
						request.setAttribute("message", "请选择要删除的记录！");
						request.getRequestDispatcher(sysdir+"/affiche/index.jsp").forward(request, response);
					}
					else{
						int id[]= new int[check.length];
						for(int i = 0;i<check.length;i++){
							int s = Integer.parseInt(check[i]);				
							id[i] = s;
						}
						int flag = afficheBean.delAffiche(id);
						if(flag == Constant.SUCCESS){
							request.getRequestDispatcher(sysdir+"/affiche/index.jsp").forward(request, response);
						}
						else{
							request.setAttribute("message", "系统维护中，请稍后再试！");
							request.getRequestDispatcher(sysdir+"/affiche/index.jsp").forward(request, response);
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
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
