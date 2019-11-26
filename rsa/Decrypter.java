package rsa;

import java.math.BigInteger;
import rsa.keys.model.PrivateKey;

public class Decrypter {

    public static byte[] decrypt(PrivateKey key, byte cryptogram[]) {
        BigInteger value = new BigInteger(cryptogram);  
        BigInteger result = value.modPow(key.getD(), key.getN()); 

        System.out.println(result.toString());

        return result.toByteArray(); 
    }
}