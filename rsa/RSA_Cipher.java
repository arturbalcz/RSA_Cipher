package rsa;

import rsa.keys.KeyGenerator;
import rsa.keys.model.PrivateKey;
import rsa.keys.model.PublicKey;

public class RSA_Cipher {

    public static void main(String[] args) {
        KeyGenerator generator = new KeyGenerator(); 

        PrivateKey privateKey = generator.getPrivateKey(); 
        PublicKey publicKey = generator.getPublicKey(); 

        byte[] encrypted = Encrypter.encryptBlock(publicKey, "qwerty".getBytes());
        byte[] decrypted = Decrypter.decryptBlock(privateKey, encrypted); 
        
        System.out.println(new String(encrypted));
        System.out.println(encrypted.length);
        System.out.println(new String(decrypted));
        System.out.println(decrypted.length);
    }
}