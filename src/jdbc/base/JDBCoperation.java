package jdbc.base;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class JDBCoperation {
    @Test
    /*单行单列数据的测试*/
    public void test()throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:mysql:///usersql","root","1025");

        PreparedStatement preparedStatement = connection.prepareStatement("select count(*) as count1 from account");
            //统计account中数据数量
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int i=resultSet.getInt("count1");
            System.out.println(i);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
    @Test
    //单行多列
    public void test2()throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:mysql:///usersql","root","1025");
//        PreparedStatement preparedStatement = connection.prepareStatement("select *from score where id='1'");
        PreparedStatement preparedStatement = connection.prepareStatement("select *from score where id=?");
//        System.out.println("请查询三位同学的成绩，按id查询");
//        Scanner scanner = new Scanner(System.in);
//        int i = scanner.nextInt();
//        preparedStatement.setInt(1,i);
        preparedStatement.setInt(1,2);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id =resultSet.getInt("id");
            String name=resultSet.getString("stuname");
            int math =resultSet.getInt("math");
            int english=resultSet.getInt("english");
            int chinese=resultSet.getInt("chinese");
            System.out.println(id+"\t"+name+"\t"+math+"\t"+english+"\t"+chinese+"\t");
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    //多行多列
    public void test3()throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:mysql:///usersql", "root", "1025");
        PreparedStatement preparedStatement = connection.prepareStatement("select *from score where chinese>?");
        preparedStatement.setInt(1,80);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id =resultSet.getInt("id");
            String name=resultSet.getString("stuname");
            int math =resultSet.getInt("math");
            int english=resultSet.getInt("english");
            int chinese=resultSet.getInt("chinese");
            System.out.println(id+"\t"+name+"\t"+math+"\t"+english+"\t"+chinese+"\t");
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    //增加
    public void add()throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:mysql:///usersql", "root", "1025");
        PreparedStatement preparedStatement = connection.prepareStatement("insert into account(username,money) values (?,?)");
        preparedStatement.setString(1,"田七");
        preparedStatement.setInt(2,1500);
        int result = preparedStatement.executeUpdate();
        //根据受影响行数做判断，得到成功或失败
        if(result>0){
            System.out.println("成功插入");
        }else {
            System.out.println("插入失败");
        }
        preparedStatement.close();
        connection.close();
    }

    @Test
    //修改
    public void upadte()throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:mysql:///usersql", "root", "1025");
        PreparedStatement preparedStatement = connection.prepareStatement("update account set money=? where username=?");
        preparedStatement.setInt(1,3000);
        preparedStatement.setString(2,"张三");
        int i = preparedStatement.executeUpdate();
        System.out.println(i);
        preparedStatement.close();
        connection.close();
    }

    @Test
    //删除
    public void drop()throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:mysql:///usersql", "root", "1025");
        PreparedStatement preparedStatement = connection.prepareStatement("delete from account where id=?");
        preparedStatement.setInt(1,3);
        int i = preparedStatement.executeUpdate();
        System.out.println(i);
        preparedStatement.close();
        connection.close();
    }
}
