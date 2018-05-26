package login;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import student.Student;
import utils.DataSourceUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String passowrd = request.getParameter("password");

        Student student = null;
        try {
            student = login(username, passowrd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (student !=null) {
            //登录成功
            response.sendRedirect("/index.jsp");
        }else{
            request.setAttribute("loginInfo","用户名或密码错误 ！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

    public Student login(String username,String password) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT *FROM students where password=?";// username=? OR

        Student student = queryRunner.query(sql, new BeanHandler<Student>(Student.class)//,username
                    , password);
        return student;
    }
}
