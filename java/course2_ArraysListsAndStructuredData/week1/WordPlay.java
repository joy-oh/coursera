import edu.duke.*;
import java.io.*;
/**
 * Write a description of WordPlay here.
 * 
 * @author (Joy) 
 * @version (6/30/2021)
 */
public class WordPlay {
    public boolean isVowel(char ch){
        //switch (ch) {
          //  case 'a':
          //  case 'e':
          //  case 'i':
          //  case 'o':
          //  case 'u':
          //      return true;
          //  default:
          //      return false;
        //}
        char lowerCh = Character.toLowerCase(ch);
        if (lowerCh == 'a' || lowerCh == 'e' || lowerCh == 'i' || lowerCh == 'o'|| lowerCh == 'u'){
            return true;
        }
        else{
            return false;
        }
    }
    public void testerIsVowel(){
        char chc = 'o';
        boolean isVowel = isVowel(chc);
        System.out.println("Letter " + chc + " is vowel?:");
        System.out.println(isVowel);
        
        char cha = 'B';
        isVowel = isVowel(cha);
        System.out.println("Letter " + cha + " is vowel?:");
        System.out.println(isVowel);        
    }
    public String replaceVowels(String phrase, char ch){
        StringBuilder newPhrase = new StringBuilder(phrase);
        for (int i = 0; i < newPhrase.length(); i ++){
            char currChar = newPhrase.charAt(i);
            boolean isVowel = isVowel(currChar);
            if (isVowel){
                newPhrase.setCharAt(i, ch);
            }
        } 
        return newPhrase.toString();
    }
    public void testerReplaceVowels(){
        String phrase = "A hot summer";
        char ch = '$';
        String replaceVowels = replaceVowels(phrase, ch);
        System.out.println(replaceVowels);
    }
    public String emphasize(String phrase, char ch){
        StringBuilder newPhrase = new StringBuilder(phrase);
        char lowerCh = Character.toLowerCase(ch);
        for (int i = 0; i < newPhrase.length(); i ++){
            char lowerCurrChar = Character.toLowerCase(newPhrase.charAt(i));
            if(lowerCurrChar == lowerCh){
                if (i % 2 == 0){    
                    newPhrase.setCharAt(i, '*');
                }
                else{
                    newPhrase.setCharAt(i, '+');
                }
            }            
        }
        return newPhrase.toString();
    }
    public void testerEmphasize(){
        String phrase = "AGSDaESAGE SAG aDG";
        char ch = 'a';
        String emphasize = emphasize(phrase, ch);
        System.out.println(emphasize);
    }
}
