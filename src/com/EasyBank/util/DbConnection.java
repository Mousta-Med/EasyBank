package com.EasyBank.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    static Connection connection;
    public static Connection createConnection(){
        try{
            String url = "jdbc:postgresql://localhost:5432/easyBank";
            String user = "postgres";
            String password = "mousta";
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
        }catch(Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
