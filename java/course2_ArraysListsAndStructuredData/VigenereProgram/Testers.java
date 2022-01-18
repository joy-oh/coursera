import edu.duke.*;
import java.util.*;
import java.io.*;
/**
 * Write a description of Testers here.
 * 
 * @author (Joy) 
 * @version (8/3/2021)
 */
public class Testers {
    public void testSliceString(){
        String message = "abcdefghijklm";
        VigenereBreaker vb = new VigenereBreaker();
        String ss = vb.sliceString(message, 1, 3);
        System.out.println(ss);
    }
    
    public void testCaesarCipher(){
        CaesarCipher cc = new CaesarCipher(4);
        FileResource fr = new FileResource();
        String input = fr.asString();
        String encrypt = cc.encrypt(input);
        System.out.println(encrypt);
        String decrypt = cc.decrypt(encrypt);
        System.out.println(decrypt);
    }
    
    public void testCaesarCracker(){
        CaesarCracker cc = new CaesarCracker('a');
        FileResource fr = new FileResource();
        String input = fr.asString();
        String decrypt = cc.decrypt(input);
        System.out.println(decrypt);
    }
    
    public void testVigenereCipher(){
        int[] key = {17,14,12,4};
        VigenereCipher vc = new VigenereCipher(key);
        FileResource fr = new FileResource();
        String input = fr.asString();
        String encrypt = vc.encrypt(input);
        System.out.println(encrypt);
    }
    
    public void testTryKeyLength(){
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        String input = fr.asString();
        int[] key = vb.tryKeyLength(input, 38, 'e');
        String k = Arrays.toString(key);
        System.out.println(k);
    }
    
    public void validWords(){
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        String input = fr.asString();
        int[] key = vb.tryKeyLength(input, 38, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(input);
        FileResource dic = new FileResource("dictionaries/english");
        HashSet<String> hs = vb.readDictionary(dic);
        int newCount = vb.countWords(decrypted, hs);
        System.out.println(newCount);
    }
    public void testDic(){
        FileResource fr = new FileResource("dictionaries/english");
        VigenereBreaker vb = new VigenereBreaker();
        HashSet<String> hs = vb.readDictionary(fr);
        System.out.println(hs.size());
    }
    
    public void testCount(){
        FileResource fr = new FileResource("dictionaries/english");
        VigenereBreaker vb = new VigenereBreaker();
        HashSet<String> hs = vb.readDictionary(fr);
        FileResource message = new FileResource();
        String Message = message.asString();
        int count = vb.countWords(Message, hs);
        System.out.println(count);
    }
    
    public void testBreakForLanguage(){
        FileResource fr = new FileResource();
        VigenereBreaker vb = new VigenereBreaker();
        HashSet<String> hs = vb.readDictionary(fr); 
        FileResource message = new FileResource();
        String Message = message.asString();
        String decrypted = vb.breakForLanguage(Message, hs);
        System.out.println(decrypted);
    }
    
    public void testCommomCharIn(){
        FileResource fr = new FileResource();
        VigenereBreaker vb = new VigenereBreaker();
        HashSet<String> hs = vb.readDictionary(fr);
        char mostChar = vb.mostCommonCharIn(hs);
        System.out.println(mostChar);
    }
}
