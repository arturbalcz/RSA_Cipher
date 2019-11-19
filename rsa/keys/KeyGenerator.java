package rsa.keys;

import java.math.BigInteger;
import java.util.Random;

import rsa.keys.model.PrivateKey;
import rsa.keys.model.PublicKey;

public class KeyGenerator {
    private static Random random = new Random(); 
    private static final int RANDOM_NUMBER_BIT_LENGTH = 20; 

    private final BigInteger productN; 
    private final BigInteger productPhi;
    
    private BigInteger coPrimeNumberE; 
    private BigInteger numberD;  

    public KeyGenerator() {
        BigInteger primeNumberP = getPrimeNumber(); 
        BigInteger primeNumberQ = getPrimeNumber(); 

        productN = primeNumberP.multiply(primeNumberQ); 
        productPhi = (primeNumberP.subtract(BigInteger.ONE)).multiply((primeNumberQ.subtract(BigInteger.ONE))); 
    }

    private static BigInteger getPrimeNumber() {
        BigInteger primeNumber = BigInteger.probablePrime(RANDOM_NUMBER_BIT_LENGTH, random); 

        while(!primeNumber.isProbablePrime(100)) {
            primeNumber = BigInteger.probablePrime(RANDOM_NUMBER_BIT_LENGTH, random);
        }

        return primeNumber; 
    }

    private static BigInteger getCoprimeNumber(BigInteger x) {
        BigInteger result = new BigInteger(RANDOM_NUMBER_BIT_LENGTH, random); 

        while(!result.gcd(x).equals(BigInteger.ONE)) {
            result = result.add(BigInteger.ONE).mod(x);  
        } 

        return result; 
    }

    public PrivateKey generatePrivateKey() {
        BigInteger randomNumber = new BigInteger(RANDOM_NUMBER_BIT_LENGTH, random); 

        numberD = (randomNumber.multiply(productPhi).add(BigInteger.ONE)).divide(coPrimeNumberE); 

        if(numberD.multiply(coPrimeNumberE).mod(productPhi).compareTo(BigInteger.ONE) != 0){
            throw new RuntimeException("Wrong D value"); 
        } 

        return new PrivateKey(numberD, productN); 
    }
    public PublicKey generatePublicKey() {
        coPrimeNumberE = getCoprimeNumber(productPhi);
        return new PublicKey(coPrimeNumberE, productN); 
    }
}