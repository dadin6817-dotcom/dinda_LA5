/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.project;

/**
 *
 * @author adind
 */
public class Project {

    public static void main(String[] args) {
        // Memanggil dan menampilkan Form Login saat program dijalankan
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }
}