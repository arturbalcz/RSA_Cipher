package rsa.keys;

import java.math.BigInteger;
import java.util.Random;

import rsa.keys.model.PrivateKey;
import rsa.keys.model.PublicKey;

public class KeyGenerator {
    private Random random = new Random(); 
    private static final int RANDOM_NUMBER_BIT_LENGTH = 24; 

    private final BigInteger productN; 
    private final BigInteger productPhi;
    
    private BigInteger coPrimeNumberE; 
    private BigInteger numberD;  

    public KeyGenerator() {
        BigInteger primeNumberP = generatePrimeNumber(); 
        System.out.println(primeNumberP);
        BigInteger primeNumberQ = generatePrimeNumber(); 

        while(primeNumberP.compareTo(primeNumberQ) == 0) {
            primeNumberQ = generatePrimeNumber(); 
        }
        System.out.println(primeNumberQ);
        productN = primeNumberP.multiply(primeNumberQ); 
        System.out.println(productN);
        productPhi = (primeNumberP.subtract(BigInteger.ONE)).multiply((primeNumberQ.subtract(BigInteger.ONE))); 
        System.out.println(productPhi);
        coPrimeNumberE = generatetCoprimeNumber(productPhi);
        System.out.println(coPrimeNumberE);
        numberD = generateDNumber(); 
        System.out.println(numberD);


 

    }

    private BigInteger generatePrimeNumber() {
        BigInteger primeNumber = BigInteger.probablePrime(RANDOM_NUMBER_BIT_LENGTH, random); 

        while(!primeNumber.isProbablePrime(100)) {
            primeNumber = BigInteger.probablePrime(RANDOM_NUMBER_BIT_LENGTH, random);
        }

        return primeNumber; 
    }

    private BigInteger generatetCoprimeNumber(BigInteger x) {
        BigInteger result = new BigInteger(RANDOM_NUMBER_BIT_LENGTH, random); 

        while(result.gcd(x).compareTo(BigInteger.ONE) != 0) {
            result = new BigInteger(RANDOM_NUMBER_BIT_LENGTH, random); 
            if(result.compareTo(BigInteger.ONE)==0) {
                result = result.add(BigInteger.ONE); 
            }   
        } 

        return result; 
    }


    private BigInteger generateDNumber() {
        BigInteger randomNumber = new BigInteger(RANDOM_NUMBER_BIT_LENGTH, random); 
        BigInteger d = (randomNumber.multiply(productPhi).add(BigInteger.ONE)).divide(coPrimeNumberE); 

        while(d.multiply(coPrimeNumberE).mod(productPhi).compareTo(BigInteger.ONE) != 0) {
            randomNumber = new BigInteger(RANDOM_NUMBER_BIT_LENGTH, random);
            d = (randomNumber.multiply(productPhi).add(BigInteger.ONE)).divide(coPrimeNumberE);
        } 

        return d; 
    }

    public PrivateKey getPrivateKey() {
        return new PrivateKey(numberD, productN); 
    }
    public PublicKey getPublicKey() {
        return new PublicKey(coPrimeNumberE, productN); 
    }
}