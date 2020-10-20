package nl.hva;

import java.math.BigInteger;
import java.util.Random;


public class Rsa {
    Random rand = new Random();

    public static final BigInteger INIT_NUMBER = new BigInteger("2");

    private final static BigInteger one = new BigInteger("1");
    private String calculateTime;
    private BigInteger p, q, n, e, d, phi;

    //
//    public Rsa(int bitLengt) {
//
//        bit_size = bitLengt;
//        long begin = System.currentTimeMillis();
//        p = generateRandomPrim();
//        q = generateRandomPrim();
//
//        // make sure they are different
//        while (p == q) {
//            q = generateRandomPrim();
//        }
//        calculateTime = System.currentTimeMillis() - begin;
//        n = p.multiply(q);
//        phi = p.subtract(one).multiply(q.subtract(one));
//        GenerateKey();
//    }
    public Rsa() {
    }

    // calculate P and Q
    public void step1Part1(String nInput) {
        this.n = new BigInteger(nInput);

        long begin = System.currentTimeMillis();
        //Initialise n and p
        BigInteger p = INIT_NUMBER;
        //For each prime p
        while (p.compareTo(n.divide(INIT_NUMBER)) <= 0) {
            //If we find p
            if (n.mod(p).equals(BigInteger.ZERO)) {
                //Calculate q
                BigInteger q = n.divide(p);
                //Displays the result

                this.p = p;
                this.q = q;
                calculateTime = String.valueOf(System.currentTimeMillis() - begin);
                //The end of the algorithm

                return;
            }
            //p = the next prime number
            p = p.nextProbablePrime();
        }
        calculateTime = "No solution exists";
    }


    // choose e < phi and co-prime to phi
    public void step2Part1() {
        phi = p.subtract(one).multiply(q.subtract(one));
        do {
            e = new BigInteger(phi.bitLength(), rand);
        } while (e.compareTo(one) <= 0 || e.compareTo(phi) >= 0 || !e.gcd(phi).equals(one));
        d = e.modInverse(phi);
        System.out.println(d + "    =d---e=     " + e);
    }


    public BigInteger step3part1(String message) {
        BigInteger convert = convertString(message);
        BigInteger cipherText = convert.modPow(e, n);
        System.out.println(convert + "             convertext");
        System.out.println(cipherText + "         ciphertext");
        return cipherText;
    }


    public void step1Part2(String inputE, String inputN) {
        e = new BigInteger(inputE);
        step1Part1(inputN);
        d = e.modInverse(phi);
        System.out.println(d + "    =d---e=     " + e);
    }

    public String step2Part2(String cipherText) {
        BigInteger mess = new BigInteger(cipherText).modPow(d, n);
        System.out.println(cipherText + "         ciphertext");
        System.out.println(mess + "         convertext");
        return recoverString(mess);
    }

    public BigInteger convertString(String message) {
        BigInteger bytes = new BigInteger(message.getBytes());
        System.out.println(new String(bytes.toByteArray()));
        return bytes;
    }

    public String recoverString(BigInteger bytes) {
        return new String(bytes.toByteArray());
    }


    // private key
    public BigInteger getD() {
        return d;
    }

    public String getCalculateTime() {
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

    @Override
    public String toString() {
        return "Public:\t" + this.e +
                "Private:\t" + this.d +
                "Modulus:\t" + this.n;
    }
}