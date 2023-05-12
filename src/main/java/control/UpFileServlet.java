package control;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import entity.Student;
import model.StudentModel;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.model.Model;
import utils.ExcelHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdFile")
public class UpFileServlet extends HttpServlet {

    /**
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding(request.getCharacterEncoding());
        ExcelHelper helper = new ExcelHelper();
        StudentModel model = new StudentModel();
        try {
            List<FileItem> list = upload.parseRequest( request);
            for (int i = 0; i < list.size(); i++) {
                FileItem item = list.get(i);
                if (item.getName().endsWith(".xls") || item.getName().endsWith(".xlsx")) {
                    // 说明是文件,不过这里最好限制一下
                    //ExcelHelper.importXls(item.getInputStream());
                    ArrayList<Student> students = helper.importXlsx(item.getInputStream());

                    out.write("{\"result\":\"OK\"}");
                    for (Student student:students) {
                        //System.out.println(users.toString());
                        model.insert(student.getId(),student.getName(),student.sex,student.getAge(),student.getGrade(),student.getScore());
                    }
                    response.sendRedirect("/ListStudentServlet.do");
                } else {
                    // 说明文件格式不符合要求
                    out.write("{\"result\":\"Invalid\"}");
                }
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
};
