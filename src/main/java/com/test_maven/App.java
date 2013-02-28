package com.test_maven;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException {

        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:mydatabase", "user1", "password1");

        Statement stat = connection.createStatement();
        stat.execute("CREATE TABLE posts (ID int, postDate date, postMessage varchar(255))");
        int i = stat.executeUpdate("INSERT INTO posts (ID, postDate, postMessage) VALUES (1 , curdate(), 'qqqqq')");
        stat.executeUpdate("INSERT INTO posts (ID, postDate, postMessage) VALUES (2 , curdate(), 'aaaaa')");

        PreparedStatement prStat = connection.prepareStatement("INSERT INTO posts (ID, postDate, postMessage) VALUES (?, ?, ?)");
        prStat.setLong(1, 3);
        prStat.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
        prStat.setString(3, "zzzzz");
        prStat.executeUpdate();

        ResultSet res = stat.executeQuery("SELECT * FROM posts ");

        while (res.next()){
            System.out.print(res.getString("ID"));
            System.out.print(" ");
            System.out.print(res.getString("postDate"));
            System.out.print(" ");
            System.out.println(res.getString("postMessage"));
        }

    }
}
