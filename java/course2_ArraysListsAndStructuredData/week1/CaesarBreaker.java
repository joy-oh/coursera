import java.io.*;
import edu.duke.*;
//import util.CaesarCiper;
/**
 * Write a description of AutoDecryptionOfCaesarCypher here.
 * 
 * @author (Joy) 
 * @version (7/6/2021)
 */
public class CaesarBreaker {
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - (4- maxDex);
        }
        return cc.encrypt(encrypted, 26-dkey);
    }
    public int[] countLetters(String encrypted){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i = 0 ; i < encrypted.length(); i ++){
            char ch = Character.toLowerCase(encrypted.charAt(i));
            int dex = alpha.indexOf(ch);
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    public int maxIndex(int[] freqs){
        int maxDex = 0;
        int frequency = 0;
        for (int i = 0; i < freqs.length; i++){
            if (freqs[i] >= frequency){
                frequency = freqs[i];
                maxDex = i;
            }
        }
        return maxDex;
    }
    public String halfOfString(String message, int start){
        //StringBuilder newChar = new StringBuilder();
        String answer = "";
        for (int i = start; i < message.length(); i = i + 2){
            answer = answer + message.charAt(i);
        }
        return answer;
    }
    public int getKey(String s){
        int counts[] = countLetters(s);
        int maxDex = maxIndex(counts);
        int dKey = maxDex - 4;
        if (dKey < 0){
            dKey = 26-(4-maxDex);
        }
        return dKey;
    }
    public String decryptTwoKeys(String encrypted){
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);
        int firstDKey = getKey(firstHalf);
        int secondDKey = getKey(secondHalf);
        System.out.println("The key to encrypt the every other character with the first character is " + firstDKey);
        System.out.println("The key to encrypt the every other character with the second character is " + secondDKey);
        CaesarCipher cc = new CaesarCipher();
        String decryptedTwoKeys = cc.encryptTwoKeys(encrypted, 26-firstDKey, 26-secondDKey);
        return decryptedTwoKeys;
    }
    public void testDecriptTwoKeys(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        //String encrypted = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        String decryptTwoKeys = decryptTwoKeys(encrypted);
        System.out.println(decryptTwoKeys);
    }
    public void testDecrypt(){
        String encrypt = "Yjhi p ithi higxcv lxiw adih du ttttttttttttttttth ";
        String decrypt = decrypt(encrypt);
        System.out.println("decrypted is " + decrypt);
    }
    public void testHalfOfString(){
        String message = "best friend, best company";
        String zeroString = halfOfString(message, 0);
        String oneString = halfOfString(message, 1);
        System.out.println(zeroString);
        System.out.println(oneString);
    }  
    public void testGetKey(){
        String message = "Yjhi p ithi higxcv lxiw adih du ttttttttttttttttth ";
        int dKey = getKey(message);
        System.out.println("Decrypt key is " + dKey);
    }
}

