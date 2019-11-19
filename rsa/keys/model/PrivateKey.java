package rsa.keys.model; 

import java.math.BigInteger;

public class PrivateKey {
    private final BigInteger numberD; 
    private final BigInteger numberN;

    public PrivateKey(BigInteger d, BigInteger n) {
        numberD = d; 
        numberN = n; 
    }

    public BigInteger getD() {
        return numberD; 
    }

    public BigInteger getN() {
        return numberN; 
    }
}