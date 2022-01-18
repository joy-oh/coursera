import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder(message);
        StringBuilder partial = new StringBuilder();
        for(int i = whichSlice ; i < message.length() ; i += totalSlices){
          char c = sb.charAt(i);
          partial.append(c);
        }
        return partial.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for(int i = 0 ; i < key.length; i ++){
            String sS = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            int theKey = cc.getKey(sS);
            key[i] = theKey;
        }        
        return key;
    }

    public void breakVigenere() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        HashMap<String, HashSet<String>> dics = new HashMap<String, HashSet<String>>();
        String[] names = new String[]{"English", "Spanish","Danish", "German", "Portuguese", "Italian", "Dutch", "French"};
        for (String name : names){
            HashSet<String> dic = readDictionary(new FileResource("dictionaries/" + name));
            dics.put(name, dic);
        }
        breakForAllLangs(message, dics);        
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> hs = new HashSet<String>();
        for (String line: fr.lines()){
            String lineLower = line.toLowerCase();
            hs.add(lineLower);
        }
        return hs;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        String messageLower = message.toLowerCase();
        for (String word: messageLower.split("\\W")){
            if(dictionary.contains(word)){
                count += 1;
            }
        }
        return count;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character, Integer> counts = new HashMap<Character, Integer>();
        int max = 0;
        Character mostC = null;
        for (String s : dictionary){
            for (int i = 0; i < s.length(); i ++){
                char c  = s.charAt(i);
                if (!counts.containsKey(c)){
                    counts.put(c, 1);
                }
                counts.put(c, counts.get(c) + 1);
            }
        }
        for (Character c : counts.keySet()){
            if (counts.get(c) > max){
                max = counts.get(c);
                mostC = c;
            }
        }
        return mostC;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        String decrypt = "";
        int count = 0;
        int keyLength = 1;
        char mostC = mostCommonCharIn(dictionary);
        for (int i = 1 ; i <= 100 ; i++){
            int[] tryKey = tryKeyLength(encrypted, i , mostC); 
            VigenereCipher vc = new VigenereCipher(tryKey);
            String decrypted = vc.decrypt(encrypted);
            int newCount = countWords(decrypted, dictionary);
            if (newCount > count){
                count = newCount;
                decrypt = decrypted;
                keyLength = i;
            }
        }
        System.out.println(count);
        return decrypt;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages){
        for (String lang : languages.keySet()){
            String decrypted = breakForLanguage(encrypted, languages.get(lang));
            System.out.println(lang);
            System.out.println(decrypted);
        }
    }
}
