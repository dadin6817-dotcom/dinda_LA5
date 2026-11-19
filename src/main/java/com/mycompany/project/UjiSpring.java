/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

/**
 *
 * @author adind
 */
public class UjiSpring {

    public static void main(String[] args) {
        try {
            System.out.println("1. Spring menyiapkan Hibernate dan koneksi MySQL...");
            UserDao dao = SpringKonteks.ambil(UserDao.class);
            System.out.println("   Berhasil. Username 'adinda' ada di tabel: " + dao.usernameSudahAda("adinda"));

            System.out.println("2. Uji login adinda / cantik...");
            User user = dao.login("adinda", "cantik");
            System.out.println(user != null
                    ? "   Berhasil. Ditemukan: " + user
                    : "   Tidak cocok. Periksa isi tabel user atau passwordnya.");

            System.out.println("3. Hash SHA-256 dari 'cantik' di Java: " + UserDao.sha256("cantik"));
            System.out.println("   Nilai ini harus sama dengan kolom password milik adinda di phpMyAdmin.");
        } catch (RuntimeException e) {
            System.out.println("GAGAL: " + SpringKonteks.pesanGalat(e));
            e.printStackTrace();
        } finally {
            SpringKonteks.tutup();
        }
    }
}