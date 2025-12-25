package com.sunbeam.quiz.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DButil {

    private static final String URL =
        "jdbc:mysql://localhost:3306/qms?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "manager";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
