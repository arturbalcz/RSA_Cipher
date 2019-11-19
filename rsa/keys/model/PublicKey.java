package rsa.keys.model;

import java.math.BigInteger;

public class PublicKey {
    private final BigInteger numberE; 
    private final BigInteger numberN; 

    public PublicKey(BigInteger e, BigInteger n) {
        numberE = e; 
        numberN = n; 
    }

    public BigInteger getE() {
        return numberE; 
    }

    public BigInteger getN() {
        return numberN; 
    }
}