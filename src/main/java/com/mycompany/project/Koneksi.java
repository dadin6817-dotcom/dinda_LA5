/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author adind
 */
public class Koneksi {

    private static final String URL =
            "jdbc:mysql://localhost:3306/projek?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Koneksi() {
    }

    public static Connection getKoneksi() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}