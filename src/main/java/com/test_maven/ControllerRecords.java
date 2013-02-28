package com.test_maven;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alexey
 * Date: 27.02.13
 * Time: 20:17
 * To change this template use File | Settings | File Templates.
 */
public class ControllerRecords implements GuestBookController {
    private Connection connection;

    ControllerRecords() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection("jdbc:h2:mem:mydatabase", "user1", "password1");

        Statement stat = connection.createStatement();
        stat.execute(
                "CREATE TABLE posts (" +
                        "id INT NOT NULL AUTO_INCREMENT," +
                        "postDate DATETIME," +
                        "postMessage VARCHAR(255)," +
                        "PRIMARY KEY (id)" +
                     ")");
    }

    @Override
    public void addRecord(String message) throws SQLException {
        PreparedStatement prStat = connection.prepareStatement("INSERT INTO posts (postDate, postMessage) VALUES (?, ?)");

        prStat.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
        prStat.setString(2, message);
        prStat.executeUpdate();
    }

    @Override
    public List<Record> getRecords() throws SQLException {
        ArrayList<Record> records= new ArrayList<>();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM posts ORDER BY postDate DESC");
        while (resultSet.next()){
            Record r = new Record(resultSet.getInt("ID"), resultSet.getTimestamp("postDate"), resultSet.getString("postMessage"));
            records.add(r);
        }
        return records;
    }

    public void closeConnection () throws SQLException {
        connection.close();
    }
}
