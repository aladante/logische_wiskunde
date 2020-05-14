import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class Rsa {

    private final static SecureRandom random = new SecureRandom();
    private final static BigInteger one = new BigInteger("1");

    private int bitLength;

    private BigInteger modulus;

    private BigInteger publicKey;
    private BigInteger privateKey;
    private long calculateTime;
    
    
    public Rsa(int bitLength){
        this.bitLength = bitLength;

    }

    public void generatePandQ(){
        long begin = 0;
        long end =0;
        begin = System.currentTimeMillis();
        BigInteger p = BigInteger.probablePrime(bitLength / 2, new Random());
        BigInteger q = BigInteger.probablePrime(bitLength / 2, new Random());
        while(!p.isProbablePrime(100)) {
            p = BigInteger.probablePrime(bitLength / 2, new Random());
        }
        while (!q.isProbablePrime(100)) {
            q = BigInteger.probablePrime(bitLength / 2, new Random());
        }
        end = System.currentTimeMillis();
        this.calculateTime = end - begin;

        BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

        this.modulus = p.multiply(q);
        this.publicKey = new BigInteger("65537");
        this.privateKey = publicKey.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger message){
        return message.modPow(publicKey, modulus);
    }

    public BigInteger decrypt(BigInteger encrypted){
        return encrypted.modPow(privateKey, modulus);
    }

    public String encrypt(String message){
        System.out.print(message);
        return this.encrypt(new BigInteger(message)).toString();
    }

    public String decrypt(String message){
        return this.decrypt(new BigInteger(message)).toString();
    }

    public String getPublicKey(){
        return this.publicKey.toString() + this.modulus.toString();
    }

    public String getCalculateTime() {
        return String.valueOf(this.calculateTime) + "   in miliseconds";
    }

    public String getMod() {
        return String.valueOf(this.modulus);
    }

    public String getPrivateKey(){
        return String.valueOf(this.privateKey) + "   This is private Key";
    }
    
    @Override
    public String toString(){
        return 	"Public:\t" + this.publicKey +
                "Private:\t" + this.privateKey +
                "Modulus:\t" + this.modulus;
    }
}