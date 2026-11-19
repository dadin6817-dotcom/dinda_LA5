/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author adind
 */
public final class SpringKonteks {

    private static AnnotationConfigApplicationContext konteks;

    private SpringKonteks() {
    }

    public static synchronized <T> T ambil(Class<T> kelas) {
        if (konteks == null) {
            konteks = new AnnotationConfigApplicationContext(AppConfig.class);
        }
        return konteks.getBean(kelas);
    }

    public static synchronized void tutup() {
        if (konteks != null) {
            konteks.close();
            konteks = null;
        }
    }

    public static String pesanGalat(Throwable galat) {
        Throwable akar = galat;
        for (int i = 0; i < 20 && akar.getCause() != null && akar.getCause() != akar; i++) {
            akar = akar.getCause();
        }
        String pesan = akar.getMessage();
        return (pesan == null || pesan.isEmpty()) ? akar.getClass().getSimpleName() : pesan;
    }
}