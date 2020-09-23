package cn.itcast.util;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

//JDBC工具类,使用Druid连接池

public class JDBCutils {

    private static DataSource ds;
    
    static {
        try {
            Properties pro = new Properties();
            InputStream is = JDBCutils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);

            //初始化连接池对象
            ds = DruidDataSourceFactory.createDataSource(pro);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

    }

    //获取连接池对象
    public static DataSource getDataSource(){
        return ds;
        
    }

    //获取Connection对象
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
