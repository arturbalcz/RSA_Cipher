package rsa;

import java.math.BigInteger;
import java.util.ArrayList;

import rsa.keys.model.PublicKey;

public class Encrypter {

    public static byte[] encrypt(PublicKey key, byte text[]) {
        BigInteger value = new BigInteger(text);  
        BigInteger result = value.modPow(key.getE(), key.getN()); 

        return result.toByteArray(); 
    }
}