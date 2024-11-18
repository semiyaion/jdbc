package jdbc.advcanced;

import jdbc.advcanced.projo.UserMoney;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ORMDemo {
    //对象到数据库中的映射
    //就是将面向对象的概念和数据库中表的概念对应起来，以面向对象的角度操作数据库中的数据
    //即以面向对象的角度操作数据库中的数据，即一张表对应一个类，一行数据对应一个对象，一个列对应一个属性
    @Test
    public void testORm()throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:mysql:///usersql", "root", "1025");
        PreparedStatement preparedStatement = connection.prepareStatement("select *from account where id=?");
        preparedStatement.setInt(1,1);
        ResultSet resultSet = preparedStatement.executeQuery();
        UserMoney userMoney=null;
        if(resultSet.next()){
            userMoney=new UserMoney();
            int id=resultSet.getInt("id");
            String name=resultSet.getString("username");
            int mon=resultSet.getInt("money");
            userMoney.setID(id);
            userMoney.setUserName(name);
            userMoney.setMoney(mon);
        }
        System.out.println(userMoney);
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
    @Test
    public void test02 ()throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:mysql:///usersql", "root", "1025");
        PreparedStatement preparedStatement = connection.prepareStatement("select *from account ");
        ResultSet resultSet = preparedStatement.executeQuery();

        UserMoney userMoney=null;
        ArrayList<UserMoney> userlist = new ArrayList<>();
        while (resultSet.next()){
            userMoney=new UserMoney();
            int id=resultSet.getInt("id");
            String name=resultSet.getString("username");
            int mon=resultSet.getInt("money");
            userMoney.setID(id);
            userMoney.setUserName(name);
            userMoney.setMoney(mon);
            //将每次循环封装完一行数据的对象存储在集合里
            userlist.add(userMoney);
        }
        for (UserMoney money : userlist) {
            System.out.println(money);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        //UserMoney{ID=1, UserName='张三', Money=3000}
        //UserMoney{ID=2, UserName='李四', Money=2000}
    }


}
