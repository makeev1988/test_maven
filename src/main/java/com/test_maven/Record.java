package com.test_maven;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: Alexey
 * Date: 28.02.13
 * Time: 14:09
 * To change this template use File | Settings | File Templates.
 */
public class Record {
    private int id;
    private Timestamp date;
    private String message;

    Record (int id, Timestamp date, String message){
        this.id = id;
        this.date = date;
        this.message = message;
    }

    public void setId (int id){
        this.id = id;
    }

    public void setDate (Timestamp date){
        this.date = date;
    }

    public void setMessage (String message){
        this.message = message;
    }

    public String getRecordAsStream (){
        String s = id + "\t" + date + "\t" + message;
        return s;
    }

}
