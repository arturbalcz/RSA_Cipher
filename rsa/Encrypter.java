package rsa;

import java.math.BigInteger;

import rsa.keys.model.PublicKey;

public class Encrypter {

    private static final int BLOCK_SIZE_IN_BYTES = 1; 

    private static byte[] getBlock(byte[] byteArray) {
        int blockIterator = BLOCK_SIZE_IN_BYTES; 
        byte[] block = new byte[BLOCK_SIZE_IN_BYTES]; 

        for (int i = byteArray.length - 1; blockIterator > 0; i--) {
            block[--blockIterator] = byteArray[i]; 
        }

        return block; 
    }


    public static byte[] encryptBlock(PublicKey key, byte b[]) {
        BigInteger value = new BigInteger(b);  
        BigInteger result = value.modPow(key.getE(), key.getN()); 

        System.out.println(result.toString());

        // for (byte c : result.toByteArray()) {
        //     System.out.println(c);
        // }

        return result.toByteArray(); 
    }

    public static String encrypt(PublicKey key, String text) {


        return ""; 
    }
}