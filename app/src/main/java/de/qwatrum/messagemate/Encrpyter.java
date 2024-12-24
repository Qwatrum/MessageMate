package de.qwatrum.messagemate;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.MessageDigest;
import java.util.Base64;


public class Encrpyter {

    private static final String AES = "AES";
    private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    public String caeser(String message, int s) {
        StringBuilder newMessage = new StringBuilder();

        for (int i=0; i< message.length(); i++) {
            char c = message.charAt(i);

            if (Character.isLetter(c)) {
                char b = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char) ((c - b - s + 26) % 26 + b);
            }
            newMessage.append(c);
        }
        return newMessage.toString();
    }

    // AES

    private static SecretKey getKeyFromPassword(String password) throws  Exception {
        byte[] keyBytes = password.getBytes("UTF-8");

        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        keyBytes = sha.digest(keyBytes);
        byte[] key = new byte[16];
        System.arraycopy(keyBytes, 0, key, 0, 16);
        return new SecretKeySpec(key, AES);
    }

    public static String aes_encrypt(String message, String password) throws Exception {
        SecretKey key = getKeyFromPassword(password);

        byte[] iv = new byte[16];
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);

        byte[] encryptedBytes = cipher.doFinal(message.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);

    }

    public static String aes_decrypt(String message, String password) throws Exception {
        try {
            SecretKey key = getKeyFromPassword(password);

            byte[] iv = new byte[16];
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);

            byte[] encryptedByted = Base64.getDecoder().decode(message);
            byte[] decryptedBytes = cipher.doFinal(encryptedByted);

            return new String(decryptedBytes, "UTF-8");
        } catch (Exception e) {
            return "Decryption failed. Wrong password or corrupted data";
        }
    }





}
