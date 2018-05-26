package Cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "LastAccessTimeServlet")
public class LastAccessTimeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss"
        );
        String currentTime = format.format(date);
        /**
         * 1. 创建Cookie 记录当前最新的访问时间
         */
        Cookie cookie = new Cookie("lastAccessTime", currentTime);
        cookie.setMaxAge(60 * 10 * 1000);//时间尽量设置长些，两次访问相隔时间可能会比较长
        response.addCookie(cookie);

        /**
         * 2.获得客户端携带的cookie记录  -lastAccessTime
         */
        String lasAccessTime = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie coo : cookies) {
                if ("lastAccessTime".equals(coo.getName())) {
                    lasAccessTime = coo.getValue();
                }
            }
        }
        response.setContentType("text/html;charset=UTF-8");
        if (lasAccessTime != null) {
           response.getWriter().write("您上次访问的时间是："+lasAccessTime);
        }else {
            response.getWriter().write("您是第一次访问");
        }
    }
}
