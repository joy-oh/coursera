import edu.duke.*;
import java.io.*;
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (Joy) 
 * @version (7/13/2021)
 */
public class TestCaesarCipher {
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
    public void simpleTests(){
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipher cc = new CaesarCipher(15);
        String encrypted = cc.encrypt("Encrypted message is "+ message);
        System.out.println(encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(decrypted);
        String breakCC = breakCaesarCipher(encrypted);
        System.out.println("Break " + breakCC);
    }
    public String breakCaesarCipher(String input){
        int [] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dKey= maxDex - 4;
        if (maxDex < 4){
            dKey = 26-(4-maxDex);
        }        
        CaesarCipher cc = new CaesarCipher(26-dKey);
        return cc.encrypt(input);
    }
}
