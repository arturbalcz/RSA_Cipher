package rsa;

import java.math.BigInteger;
import java.util.ArrayList;

import rsa.keys.model.PublicKey;

public class Encrypter {
    
    private static final int INPUT_BLOCK_SIZE_IN_BYTES = 3; 
    private static final int OUTPUT_BLOCK_SIZE_IN_BYTES = 7; 

    private static byte[] getBlock(byte[] byteArray, int startIndedx) {
        byte[] block = new byte[INPUT_BLOCK_SIZE_IN_BYTES]; 

        for (int i = 0; i < block.length && startIndedx + i < byteArray.length; i++) {
            block[i] = byteArray[startIndedx + i]; 
        }

        return block; 
    }

    private static byte[] fillBlock(byte[] byteArray) {
        if(byteArray.length == OUTPUT_BLOCK_SIZE_IN_BYTES) return byteArray; 
        
        byte[] result = new byte[OUTPUT_BLOCK_SIZE_IN_BYTES]; 

        int resultIterator = result.length; 
        for (int i = byteArray.length - 1; i > 0; i--) {
            result[--resultIterator] = byteArray[i]; 
        }

        return result; 
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

    public static byte[] encrypt(PublicKey key, byte[] text) {
        ArrayList<Byte> result = new ArrayList<>(); 

        for (int i = 0; i < text.length; i+=INPUT_BLOCK_SIZE_IN_BYTES) {
            byte[] block = getBlock(text, i); 
            byte[] encryptedBlock = encryptBlock(key, block);
            byte[] readyBlock = fillBlock(encryptedBlock); 
            for (byte b : readyBlock) {
                result.add(b); 
            }
        }

        return null; 
        // return result.stream().collect(); 
    }
}