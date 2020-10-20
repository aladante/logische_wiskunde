package nl.hva;

import java.math.BigInteger;
import java.util.Random;


public class Rsa {
    Random rand = new Random();
    private static int bit_size = 120;
    private final static BigInteger one = new BigInteger("1");
    private final String encrypted = "";
    private final String message = "";
    private BigInteger publicKey;
    private BigInteger privateKey;
    private long calculateTime;
    private BigInteger p, q, modules, e, d, phi;

    public Rsa(int bitLengt) {

        bit_size = bitLengt;
        long begin = System.currentTimeMillis();
        p = generateRandomPrim();
        q = generateRandomPrim();

        // make sure they are different
        while (p == q) {
            q = generateRandomPrim();
        }
        calculateTime = System.currentTimeMillis() - begin;
        modules = p.multiply(q);
        phi = p.subtract(one).multiply(q.subtract(one));
        GenerateKey();
    }


    private BigInteger generateRandomPrim() {
        // Generates a prime, Changes it fails is smaller then to be struck by ligntning
        return BigInteger.probablePrime(bit_size, new Random());
    }

    public BigInteger convertString(String message) {
        return new BigInteger(message.getBytes());
    }

    public String recoverString(BigInteger bytes) {
        return new String(bytes.toByteArray());
    }

    public BigInteger encrypt(String message) {
        BigInteger acc = convertString(message);
        return acc.modPow(publicKey, modules);
    }

    public String decrypt(String encrypted) {
        BigInteger mess = new BigInteger(encrypted).modPow(privateKey, modules);
        return recoverString(mess);
    }

    public String decrypt(String encrypted, BigInteger pKey) {
        BigInteger mess = new BigInteger(encrypted).modPow(pKey, modules);
        ;
        return recoverString(mess);
    }


    public BigInteger getD() {
        return d;
    }

    public long getCalculateTime() {
        return calculateTime;
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getQ() {
        return q;
    }

    public BigInteger getE() {
        return e;
    }


    // choose e < phi and co-prime to phi
    public void GenerateKey() {
        do {
            e = new BigInteger(phi.bitLength(), rand);
        } while (e.compareTo(BigInteger.valueOf(1)) <= 0 || e.compareTo(phi) >= 0 || !e.gcd(phi).equals(BigInteger.valueOf(1)));
        d = e.modInverse(phi);
        publicKey = e;
        privateKey = d;
    }


    @Override
    public String toString() {
        return "Public:\t" + this.publicKey +
                "Private:\t" + this.privateKey +
                "Modulus:\t" + this.modules;
    }
}