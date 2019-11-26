package rsa;

import rsa.keys.KeyGenerator;
import rsa.keys.model.PrivateKey;
import rsa.keys.model.PublicKey;

public class RSA_Cipher {

    public static void main(String[] args) {
        KeyGenerator generator = new KeyGenerator(); 

        PrivateKey privateKey = generator.getPrivateKey(); 
        PublicKey publicKey = generator.getPublicKey(); 

        byte[] encrypted = Encrypter.encrypt(publicKey, "qwe".getBytes());
        byte[] decrypted = Decrypter.decrypt(privateKey, encrypted); 
        
        System.out.println(new String(encrypted));
        System.out.println(encrypted.length);
        System.out.println(new String(decrypted));
        System.out.println(decrypted.length);
    }
}