package jdbc.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCInjection {
    public static void main(String[] args) throws Exception{
        String url="jdbc:mysql:///usersql";
        String username="root";
        String password="1025";
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        System.out.println("请输入员工姓名");
        Scanner scanner = new Scanner(System.in);
        String name1 = scanner.nextLine();
        String sql="select *from account where username='"+name1+"'";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            int id=resultSet.getInt("id");
            String name=resultSet.getString("username");
            int mon=resultSet.getInt("money");
            System.out.println(id+"\t"+name+"\t"+mon+"\t");


        }
        resultSet.close();
        statement.close();
        connection.close();
    }

}
