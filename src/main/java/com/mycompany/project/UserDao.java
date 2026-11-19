/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author adind
 */
@Repository
@Transactional
public class UserDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public User login(String username, String password) {
        List<User> hasil = em.createQuery(
                "SELECT u FROM User u WHERE u.username = :username AND u.password = :password",
                User.class
        )
                .setParameter("username", username)
                .setParameter("password", sha256(password))
                .setMaxResults(1)
                .getResultList();

        return hasil.isEmpty() ? null : hasil.get(0);
    }

    @Transactional(readOnly = true)
    public boolean usernameSudahAda(String username) {
        Long jumlah = em.createQuery(
                "SELECT COUNT(u) FROM User u WHERE u.username = :username",
                Long.class
        )
                .setParameter("username", username)
                .getSingleResult();

        return jumlah > 0;
    }

    public User tambah(String username, String password) {
        User user = new User(username, sha256(password));
        em.persist(user);
        return user;
    }

    static String sha256(String teks) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return HexFormat.of().formatHex(md.digest(teks.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}