package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.StudentModel;

public class Deleteservlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Deleteservlet() {
		super();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
       this.doPost(request, response);
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     		request.setCharacterEncoding("gbk");
     		int id=Integer.parseInt(request.getParameter("id"));
     		StudentModel model = new StudentModel();
	 	    model.delete(id);
	 	    response.sendRedirect("ListStudentServlet.do");
	}

}
