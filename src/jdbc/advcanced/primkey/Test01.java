package jdbc.advcanced.primkey;

import jdbc.advcanced.projo.UserMoney;
import org.junit.Test;

import java.sql.*;

public class Test01 {
    //主键回显
    /*在执行新增操作时候，主键列为自动增长，可以在表中直观的看到，但是在java中，我们执行完新程序之后
    * 只能得到收影响的行数，无法得知当前新增数据的主键值。
    * 在java程序中获取数据库中插入新数据后的主键值，并赋值给java对象，这就叫做主键回显*/
    @Test
    public void test()throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:mysql:///usersql", "root", "1025");
    String sql="insert into account (username,money) values (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        //给我返回生成的数据库主键值
        UserMoney userMoney = new UserMoney(null, "田七", 1200);
        preparedStatement.setString(1,userMoney.getUserName());
        preparedStatement.setInt(2,userMoney.getMoney());
        int i = preparedStatement.executeUpdate();
        ResultSet set=null;
        if(i>0){
            System.out.println("成功插入");
            //获取当前新增数据的主键列，回显到usermoney对象的id属性上
             set = preparedStatement.getGeneratedKeys();
            if(set.next()){
                int userid = set.getInt(1);
                userMoney.setID(userid);
            }
            System.out.println(userMoney);
        }else {
            System.out.println("失败");
        }
        if(set!=null){
            set.close();
        }

        preparedStatement.close();
        connection.close();
    }

    @Test
    public void test03()throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:mysql:///usersql", "root", "1025");

        String sql="insert into account (username,money) values (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        //获取当前时间
        long start = System.currentTimeMillis();
        //插入多条数据
        for (int i =0;i<10000;i++){
            preparedStatement.setString(1,"marry"+i);
            preparedStatement.setInt(2,1000+i);
            preparedStatement.executeUpdate();
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testBatch()throws Exception{
        //批量操作 在mysql的后面加上？rewriteBatchedStatements=true设置为true，允许批量操作
        //新增sql必须要用values
        //调用addbatch方法，将sql语句批量添加
        //最后调用excutebatch
        Connection connection =
                DriverManager.getConnection("jdbc:mysql:///usersql?rewriteBatchedStatements=true",
                "root",
                "1025");

        String sql="insert into account (username,money) values (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        //获取当前时间
        long start = System.currentTimeMillis();
        //插入多条数据
        for (int i =0;i<10000;i++){
            preparedStatement.setString(1,"Andy"+i);
            preparedStatement.setInt(2,1000+i);
            preparedStatement.addBatch();
            //添加批处理
        }
        preparedStatement.executeBatch();
        //批量提交
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        preparedStatement.close();
        connection.close();
    }
    }

