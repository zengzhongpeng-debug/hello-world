package cn.itcast.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置request编码
        request.setCharacterEncoding("utf0-8");

        //2.获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");

        //3.先获取生成的验证码
        HttpSession session = request.getSession();
        String checkCode_sessiom = (String) session.getAttribute("checkCode_sessiom");

        //删除session中存储的共享验证码，防止恶意重复使用验证码
        session.removeAttribute("checkCode_session");

        //4.判断验证码是否正确
        if (checkCode_sessiom!=null && checkCode_sessiom.equalsIgnoreCase(checkCode)){
            if ("zhangsan".equals(username) && "123".equals(password)){
                //登陆成功
                //存储用户信息
                session.setAttribute("user",username);
                //重定向到success.jsp
                response.sendRedirect(request.getContextPath()+"success.jsp");
                //request本身是由服务器根据资源创建的对象，所以包含了大量资源，所以使用getContextPath方法获取相关路径


            }else {
                //登陆失败
                //存储提示信息到request
                request.setAttribute("login_error","用户名或密码错误");
                //转发到登陆页面
                request.getRequestDispatcher("/login.jsp").forward(request,response);
                //获得一个RequestDispatcher对象，调用forward方法，完成转发，转发到路径说的地址上

            }

        }else{
            //验证码不一致
            //存储提示信息到request
            request.setAttribute("cc_error","验证码错误");
            //转发到登陆页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
