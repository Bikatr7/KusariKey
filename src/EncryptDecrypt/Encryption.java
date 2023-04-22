package EncryptDecrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class Encryption {

    public static String encrypt(String plaintext, String key) throws Exception 
    {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);

        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        byte[] plaintextBytes = plaintext.getBytes(StandardCharsets.UTF_8);
        byte[] ciphertextBytes = cipher.doFinal(plaintextBytes);
        
        return Base64.getEncoder().encodeToString(ciphertextBytes);
    }

}