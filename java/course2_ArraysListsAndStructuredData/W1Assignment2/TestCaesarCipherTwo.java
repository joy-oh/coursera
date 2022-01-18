import edu.duke.*;
import java.io.*;
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (Joy) 
 * @version (7/13/2021)
 */
public class TestCaesarCipherTwo {
    private int[] countLetters(String encrypted){
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
    private int maxIndex(int[] freqs){
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
    private String halfOfString(String message, int start){
        String answer = "";
        for (int i = start; i < message.length(); i = i + 2){
            answer = answer + message.charAt(i);
        }
        return answer;
    }
    private int getKey(String s){
        int counts[] = countLetters(s);
        int maxDex = maxIndex(counts);
        int dKey = maxDex - 4;
        if (dKey < 0){
            dKey = 26-(4-maxDex);
        }
        return dKey;
    }
    public void simpleTests(){
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        //String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        //CaesarCipherTwo cct = new CaesarCipherTwo(14,24);
        //String encrypted = cct.encrypt(message);
        //System.out.println("Encrypted message with 2 keys is "+ encrypted);
        //String decrypted = cct.decrypt("Hfs cpwewloj loks cd Hoto kyg Cyy.");
       // System.out.println("Decrypted message with 2 keys is "+ decrypted);
        FileResource fr1 = new FileResource();
        String message1 = fr1.asString();
        String bcc = breakCaesarCipher(message1);
        System.out.println("Decrypted message with 2 unknown keys is " + bcc);
    }
    public String breakCaesarCipher(String input){
        String firstHalf = halfOfString(input, 0);
        String secondHalf = halfOfString(input, 1);
        int firstDKey = getKey(firstHalf);
        int secondDKey = getKey(secondHalf);
        System.out.println("The key to encrypt the every other character with the first character is " + firstDKey);
        System.out.println("The key to encrypt the every other character with the second character is " + secondDKey);
        CaesarCipherTwo cct = new CaesarCipherTwo(firstDKey, secondDKey);
        return cct.decrypt(input);
    }
}
