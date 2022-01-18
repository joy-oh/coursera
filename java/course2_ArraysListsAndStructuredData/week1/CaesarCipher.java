import edu.duke.*;
import java.io.*;
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (Joy) 
 * @version (6/30/2021)
 */
public class CaesarCipher {    
    public String encrypt(String input, int key){
        String upInput = input.toUpperCase();
        StringBuilder encrypted = new StringBuilder(upInput);
        String UpAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedUpAlphabet = UpAlphabet.substring(key) + UpAlphabet.substring(0,key);
        for (int i = 0; i < encrypted.length(); i ++){
            char currChar = encrypted.charAt(i);
            int idU = UpAlphabet.indexOf(currChar);
            char newChar = currChar;
            if (Character.isUpperCase(input.charAt(i))){
                newChar = shiftedUpAlphabet.charAt(idU);
            }
            else if(Character.isLowerCase(input.charAt(i))){
                newChar = Character.toLowerCase(shiftedUpAlphabet.charAt(idU));
            }
            encrypted.setCharAt(i, newChar);
        }
        return encrypted.toString();
    }

    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < input.length() ; i ++){
            char currChar = input.charAt(i);
            if (i % 2 == 0){
                String newStr = encrypt(Character.toString(currChar), key1);
                StringBuilder newChar = new StringBuilder(newStr);
                encrypted.append(newChar);
            }
            else {
                String newStr = encrypt(Character.toString(currChar), key2);
                StringBuilder newChar = new StringBuilder(newStr);
                encrypted.append(newChar);
            }
        }
        return encrypted.toString();
    }
    public void testCaesar(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 15;
        //String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypt = encrypt(message, key);
        
        System.out.println("Key is " + key + "\n" + "Encrypted is " + encrypt);
    }
    public void testerEncryptTwoKeys(){
        int key1 = 24;
        int key2 = 6;
        String input = "Top ncmy qkff vi vguv vbg ycpx";
        String encryptedTwoKeys = encryptTwoKeys(input, key1,key2);
        System.out.println(encryptedTwoKeys);
    }
}