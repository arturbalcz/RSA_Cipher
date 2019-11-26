package rsa;

import java.math.BigInteger;
import rsa.keys.model.PrivateKey;

public class Decrypter {

    private static final int INPUT_BLOCK_SIZE_IN_BYTES = 7; 

    private static byte[] getBlock(byte[] byteArray) {
        int blockIterator = INPUT_BLOCK_SIZE_IN_BYTES; 
        byte[] block = new byte[INPUT_BLOCK_SIZE_IN_BYTES]; 

        for (int i = byteArray.length - 1; blockIterator > 0; i--) {
            block[--blockIterator] = byteArray[i]; 
        }

        return block; 
    }


    public static byte[] decryptBlock(PrivateKey key, byte b[]) {
        BigInteger value = new BigInteger(b);  
        BigInteger result = value.modPow(key.getD(), key.getN()); 

        System.out.println(result.toString());

        // for (byte c : result.toByteArray()) {
        //     System.out.println(c);
        // }

        return result.toByteArray(); 
    }

    public static String decrypt(PrivateKey key, String text) {


        return ""; 
    }
}