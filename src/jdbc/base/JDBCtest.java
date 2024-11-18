package jdbc.base;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCtest {
    public static void main(String[] args) throws Exception {
        //注册驱动:为了注册驱动程序，使得JDBC API能够识别并与特定的数据库进行交互
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        DriverManager.registerDriver(new Driver());
        //2.获取链接对象Connection 用于建立通信通道，Connection不为空。则表示一次数据库的链接
        //jdbc:mysql://端口号/数据库名
        String url="jdbc:mysql://localhost:3306/usersql";
        String username="root";
        String password="1025";
        Connection connection = DriverManager.getConnection(url, username, password);
        //3.获取执行sql语句的对象，statement用户执行sql语句的执行并与数据库进行交互
        Statement statement = connection.createStatement();
        //发送执行sql语句
        /*statement的子接口preparedStatement
        * 预编译SQL语句：在创建该对象时，就会预编译sql语句
        * 防止sql注入，将数据作为参数传递到SQL语句中，采用？占位符的方式，将传入的参数用一对单引号包裹起来，无论传递什么值都能有效传入关键字导致sql注入*/


        //4.编写sql并执行,接受返回的结果集resultset
        String sql="select *from student ";
        ResultSet resultSet = statement.executeQuery(sql);
        //resultset也是一个接口，用于表示从数据库中执行查询语句返回的结果集
        //next（）将游标移动到结果集的下一行，逐行遍历数据库查询的结果，结果返回布尔类型
        //获取的方法为getxxx，  xxx为对应数据库中的数据类型
        //5.处理结果：遍历resultset结果集
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String stuname = resultSet.getString("stuname");
            String stuno = resultSet.getString("stuno");
            System.out.println(id+"\t"+stuname+"\t"+stuno+"\t");
        }
        //6.释放资源
        resultSet.close();
        statement.close();
        connection.close();

    }
}
