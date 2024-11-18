package jdbc.advcanced.threadpoll;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class HikariDemo {
    @Test
    public void Test01() throws Exception{
        /*
        * 硬编码
        * */
        //必须
        HikariDataSource hikariDataSource=new HikariDataSource();
        hikariDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariDataSource.setJdbcUrl("jdbc:mysql:///usersql");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("1025");
        //非必须
        hikariDataSource.setMinimumIdle(10);
        hikariDataSource.setMaximumPoolSize(20);

        //通过连接池获取连接对象
        Connection connection = hikariDataSource.getConnection();
        System.out.println(connection);

        //回收连接
        connection.close();
        //HikariProxyConnection@1345900725 wrapping com.mysql.cj.jdbc.ConnectionImpl@5f9edf14
    }

    @Test
    public void Test02()throws Exception{
        Properties properties = new Properties();
        InputStream inputStream = HikariDemo.class.getClassLoader().getResourceAsStream("hikari.properties");
        properties.load(inputStream);
        //创建一个HikariConfig连接池配置对象，将properties集合传进去,即将配置文件中的数据传入Cinfig‘中
        HikariConfig hikariConfig=new HikariConfig(properties);

        //基于连接池配置对象，构建hikaridatasourece
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        //利用hikaricongfig中的参数创建hikaridatasource

        //获取连接
        Connection connection = hikariDataSource.getConnection();
        System.out.println(connection);
        //释放连接
        connection.close();
    }
}
