package com.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.ComBean;
import com.util.Constant;

public class ComServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ComServlet() {
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
		HttpSession session = request.getSession();
		ComBean cBean = new ComBean();
		String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		String date2=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		String method = request.getParameter("method");
		 
		
		 if(method.equals("addlj")){ //  
			String mc = request.getParameter("mc");  
			String dz = request.getParameter("dz");   
				int flag=cBean.comUp("insert into lj(mc,dz  ) values('"+mc+"','"+dz+"'  ) ");
				if(flag == Constant.SUCCESS){ 
					request.setAttribute("message", "操作成功！");
					request.getRequestDispatcher("admin/lj/index.jsp").forward(request, response); 
				}
				else { 
					request.setAttribute("message", "操作失败！");
					request.getRequestDispatcher("admin/lj/index.jsp").forward(request, response);  
				}
			 
		} 
		else if(method.equals("uplj")){  // 
			String id = request.getParameter("id");  
			String mc = request.getParameter("mc");  
			String dz = request.getParameter("dz");   
			int flag=cBean.comUp("update lj set mc='"+mc+"',dz='"+dz+"' where id='"+id+"' ");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/lj/index.jsp").forward(request, response);  
			}
			else { 
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/lj/index.jsp").forward(request, response);  
			}
		}
		else if(method.equals("dellj")){   
			String id = request.getParameter("id");   
			int flag=cBean.comUp("delete from lj where id='"+id+"' ");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("admin/lj/index.jsp").forward(request, response);  
			}
			else { 
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("admin/lj/index.jsp").forward(request, response);  
			}
		} 
		else if(method.equals("delss")){   
			String id = request.getParameter("id");   
			int flag=cBean.comUp("delete from keyword where id='"+id+"' ");
			if(flag == Constant.SUCCESS){ 
				request.setAttribute("message", "操作成功！");
				request.getRequestDispatcher("member/ss/index.jsp").forward(request, response);  
			}
			else { 
				request.setAttribute("message", "操作失败！");
				request.getRequestDispatcher("member/ss/index.jsp").forward(request, response);  
			}
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
