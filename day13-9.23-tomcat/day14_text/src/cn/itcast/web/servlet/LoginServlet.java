package cn.itcast.web.servlet;
import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1设置编码
        req.setCharacterEncoding("utf-8");

        //2获取请求参数
        String username = req.getParameter("username");    //req.getParameter()   根据参数名称获取参数值
        String password = req.getParameter("password");

        //3 封装对象
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);

        //4 调用UserDao的login方法
        UserDao dao = new UserDao();
        User user = dao.login(loginUser);

        //5 判断user
        if (user == null){
            //登陆失败
            req.getRequestDispatcher("/failServlet").forward(req,resp);
        }else {
            //登陆成功
            //存储数据
            //转发
            req.setAttribute("user",user);//setAttribute 存储数据，用于共享
            req.getRequestDispatcher("/successServlet").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
