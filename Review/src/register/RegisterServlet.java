package register;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import student.Student;
import utils.DataSourceUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 将数据全部封装成一个类
         */
        request.setCharacterEncoding("UTF-8");
        Map<String, String[]> properties = request.getParameterMap();
        Student student = new Student();
        try {
            BeanUtils.populate(student,properties);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        student.setUid(UUID.randomUUID().toString());
        /**
         * 执行注册操作
         */
        register(student);
        response.sendRedirect("login.jsp");
    }

    public void register(Student student) {
        try {
            QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
            String sql = "INSERT INTO students values(?,?,?,?,?,?,?)";
            queryRunner.update(sql, student.getUid(), student.getUsername(), student.getPassword(), student.getName(),
                    student.getSex(), student.getTelephone(), student.getEmail());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        return student;
    }
}
