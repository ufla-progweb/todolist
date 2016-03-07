/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.security.MessageDigest;

/**
 *
 * @author Paulo
 */
public class SenhaHashing {

    public static String hash(String senha) {
        String hashSenha = senha;
        try {
            String charset = "UTF-8";
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(senha.getBytes(charset));
            hashSenha = new String(messageDigest, charset);
        } finally {
            return hashSenha;
        }
    }
}
