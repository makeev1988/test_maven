package com.test_maven;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alexey
 * Date: 27.02.13
 * Time: 20:14
 * To change this template use File | Settings | File Templates.
 */
public interface GuestBookController {
    void addRecord (String message) throws SQLException;

    List<Record> getRecords() throws SQLException;

    public void closeConnection () throws SQLException;
}
