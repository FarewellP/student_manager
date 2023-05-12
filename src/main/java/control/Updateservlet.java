package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.StudentModel;
import entity.Student;

public class Updateservlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Updateservlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
       this.doPost(request, response);
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    request.setCharacterEncoding("gbk");
		 		
	 			// �õ����������ѧ��ID
			    String id = request.getParameter("id");
			if (null==id)
			{
				request.setAttribute("error", "û�и�ѧ����");
				request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
				
				return ;
			}
			Integer studentId = Integer.valueOf(id);
			// ���ò�ѯ�������õ�ѧ������
     		StudentModel model = new StudentModel();

			Student student = model.load(studentId);
			if (null==student)
			{
				request.setAttribute("error", "û��ָ����ŵ�ѧ����¼");
				request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
				return ;
			}
			// ������Ա���ݱ��浽request��
			System.out.println(student.imgUrl);
			request.setAttribute("student", student);
			// ת����student.jsp
			request.getRequestDispatcher("/jsp/studentupdate.jsp").forward(request, response);
	
	}

}
