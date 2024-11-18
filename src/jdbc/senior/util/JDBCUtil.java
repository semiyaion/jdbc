package jdbc.senior.util;

/*
* JDBC工具类
* 1.维护一个连接池对象
* 2.对外提供在连接池中获取连接的方法
* 3.对外提供回收连接的方法
*     工具类仅对外提供共性的功能代码，所以方法均为静态方法*/

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
    /*创建一个连接池引用，因为要提供给当前项目的全局使用，所以创建为静态的*/
    private static DataSource dataSource;

    //在项目启动时，即创建连接池对象，赋值给dataSource
    //静态代码块，在创建时一定会先执行一次
    static {
        Properties properties=new Properties();
        InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            //创建连接池
            properties.load(inputStream);
            dataSource= DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //对外提供在连接池中获取连接的方法--*--通过连接池获取连接对象
    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //对外提供回收连接的方法
    public static void release(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
