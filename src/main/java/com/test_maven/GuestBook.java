package com.test_maven;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alexey
 * Date: 28.02.13
 * Time: 17:24
 * To change this template use File | Settings | File Templates.
 */
public class GuestBook {
    public static void main(String[] args){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        GuestBookController controller = null;
        try {
            controller = new ControllerRecords();
            String s;
            boolean exit = true;
            while(exit &&((s = in.readLine()) != null)){
                s = s.trim();
                String [] st = s.split(" ");
                String command = st[0].toUpperCase();
                String message = s.substring(command.length(), s.length());
                switch (command){
                    case "ADD":
                        controller.addRecord(message);
                        break;
                    case "LIST":
                        System.out.println("id:\tДата:\t\t\t\t\t Сообщение:");
                        List<Record> listRecords = controller.getRecords();
                        for (Record r: listRecords){
                            System.out.println(r.getRecordAsStream());
                        }
                        break;
                    case "EXIT":
                        exit = false;
                        break;
                    default:
                        System.out.println("Неизвестная команда");
                }
            }
        } catch (ClassNotFoundException | SQLException | IOException  e) {
            e.printStackTrace();
        } finally {
            try {
                controller.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
