package jdbc.base;

import java.sql.*;
import java.util.Scanner;

public class JDBCPre {
    public static void main(String[] args) throws Exception{
        String url="jdbc:mysql:///usersql";
        String username="root";
        String password="1025";
        Connection connection = DriverManager.getConnection(url, username, password);


        PreparedStatement preparedStatement = connection.prepareStatement("select *from account where username=?");
        System.out.println("请输入员工姓名");
        Scanner scanner = new Scanner(System.in);
        String name1 = scanner.nextLine();
        //给？占位符赋值
        preparedStatement.setString(1,name1);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id=resultSet.getInt("id");
            String name=resultSet.getString("username");
            int mon=resultSet.getInt("money");
            System.out.println(id+"\t"+name+"\t"+mon+"\t");


        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
