package cn.itcast.web.download;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1 获取请求参数，文件名称
        String filename = request.getParameter("filename");//getParameter()    根据参数名称获取参数值

        //2 使用字节输入流加载文件进内存
        //2.1 找到文件服务器路径
        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("/img/"+filename);

        //2.2 用字节流关联
        FileInputStream fis = new FileInputStream(realPath);//FileInputStream:字节输入流，read方法用于从输入流读取数据的下一个字节



        //3.设置response的响应头，作用:设置打开方式
        //3.1 设置响应头类型
        String mimeType = servletContext.getMimeType(filename);//设置获取文件的类型为Mime类型
        response.setHeader("cintent-type",mimeType);

        //3.2 设置响应头打开方式
        response.setHeader("conten-disposition", "attachment;filename"+filename);



        //4 将输入流的数据写到输出流中
        ServletOutputStream sos = response.getOutputStream();  //getOutputStream()   获取字节输出流
        byte[] buff = new byte[1024 * 8];
        int len = 0;
        while ((len = fis.read(buff)) != -1){
            sos.write(buff, 0,len);
        }

        fis.close();


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}
