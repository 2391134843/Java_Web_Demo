# 实现原理图：
![](http://p8i28834i.bkt.clouddn.com/%E6%98%BE%E7%A4%BA%E4%B8%8A%E6%AC%A1%E8%AE%BF%E9%97%AE%E7%9A%84%E6%97%B6%E9%97%B4.png)

# 效果图：
![](http://p8i28834i.bkt.clouddn.com/%E8%BF%94%E5%9B%9E%E6%97%B6%E9%97%B4%E6%A0%B7%E4%BE%8B.png)
# 实现过程：
## 1.利用从客户端获得的所有Cookie

## 2.从所有Cookie中查找指定名称的Cookie

## 3.判断是是不是第一次访问：
+ 如果是： 显示现在的您为第一次访问。
+ 如果不是：显示上次访问的时间

## 4. 记录当前时间，并利用Cookie将时间写回到浏览器端。

# 代码实现：
```java
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
        //设置时间格式
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

```
