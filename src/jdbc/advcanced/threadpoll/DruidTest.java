package jdbc.advcanced.threadpoll;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidTest {
    @Test
    /*
    * @硬编码
    * */
    public void Test01() throws Exception{
      //就是将连接池的配置信息和java代码耦合在一起
      //1.创建一个DruidDataSourece连接池对象
      /*2.设置连接池的配置信息（必须/非必须）
      * 3.通过连接池获取连接对象
      * 4.回收连接，给其他线程复用*/
        DruidDataSource druidDataSource=new DruidDataSource();
        //设置连接信息
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //设置连接池名称
        druidDataSource.setUrl("jdbc:mysql:///usersql");
        //设置连接的数据库
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("1025");
        //设置用户和密码

        //非必要设置的设置
        druidDataSource.setInitialSize(10);
        //默认初始化10个连接对象
        druidDataSource.setMaxActive(20);
        //设置最大20个连接对象
        Connection connection = druidDataSource.getConnection();
        //通过连接池获取连接对象

        //基于connection进行Crud
        System.out.println(connection);
        connection.close();
        //回收连接
    }


    @Test
    /*
    * 软编码
    * */
    public void Test02()throws Exception{
        Properties properties = new Properties();
        //创建properties的配置文件,用于存储外部配置文件的key和value
        InputStream inputStream = DruidTest.class.getClassLoader().getResourceAsStream("db.properties");
        //利用反射获取类加载器，用类加载器获取资源并作为流处理

        //读取外部配置文件，获取输入流，加载到properties集合中
        properties.load(inputStream);
        //基于properties集合构建DuridDAtaSource连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        //通过连接池获取连接对象
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        //开发CRUD
        connection.close();
        //回收连接
        /*11月 18, 2024 4:27:43 下午 com.alibaba.druid.pool.DruidDataSource info
        信息: {dataSource-1} inited
        com.mysql.cj.jdbc.ConnectionImpl@37fb0bed*/
    }
}
