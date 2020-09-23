package cn.itcast.dao;

import cn.itcast.domain.User;
import cn.itcast.util.JDBCutils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {


    //声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCutils.getDataSource());


    //登陆方法                              loginUser是loginServlet中的一个User类对象
    public User login(User loginUser){
        try {
            //1 编写sql
            String sql = "select * from user where username = ? and password = ?";

            //2 调用query方法
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());


            return user;
        } catch (DataAccessException e) {          //没有查询到账号密码，就抛出异常返回null，而不是报错
            e.printStackTrace();
            return null;
        }
    }
}
