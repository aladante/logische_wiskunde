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
    private BigInteger p, q, n, e, d, phi;

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
        n = p.multiply(q);
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
        return acc.modPow(publicKey, n);
    }


    // choose e < phi and co-prime to phi
    public void GenerateKey() {
        do {
            e = new BigInteger(phi.bitLength(), rand);
        } while (e.compareTo(one) <= 0 || e.compareTo(phi) >= 0 || !e.gcd(phi).equals(one));
        d = e.modInverse(phi);
        publicKey = e;
        privateKey = d;
    }

    public String decrypt(String encrypted) {
        BigInteger mess = new BigInteger(encrypted).modPow(privateKey, n);
        return recoverString(mess);
    }

    public String decrypt(String encrypted, BigInteger e) {
        BigInteger mess = new BigInteger(encrypted).modPow(e, n);
        return recoverString(mess);
    }

    // with mod(n) en e (pub)
    public BigInteger getD(BigInteger e_input, BigInteger n_input) {
//        BigInteger d_calculated = e_input.pow(-1)
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

    // pub key
    public BigInteger getE() {
        return e;
    }

    // N = p * q
    public BigInteger getN() {
        return n;
    }



    @Override
    public String toString() {
        return "Public:\t" + this.publicKey +
                "Private:\t" + this.privateKey +
                "Modulus:\t" + this.n;
    }
}