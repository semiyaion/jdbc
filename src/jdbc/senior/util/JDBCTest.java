package jdbc.senior.util;

import org.junit.Test;

import java.sql.Connection;

public class JDBCTest {
    @Test
    public void test()throws Exception{
        Connection connection = JDBCUtil.getConnection();
        System.out.println(connection);

        JDBCUtil.release(connection);
    }
}
