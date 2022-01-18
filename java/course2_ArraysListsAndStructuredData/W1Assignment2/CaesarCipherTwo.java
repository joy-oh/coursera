
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (Joy) 
 * @version (7/13/2021)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    public CaesarCipherTwo(int key1, int key2){
        mainKey1 = key1;
        mainKey2 = key2;
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
    }
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < input.length(); i ++){
            char currChar = input.charAt(i);
            char CurrChar = Character.toLowerCase(currChar);
            int idx = alphabet.indexOf(CurrChar);
            char newChar = currChar;
            if (idx != -1){
                if (i % 2 == 0){                
                    newChar = shiftedAlphabet1.charAt(idx);                
                }
                else{
                    newChar = shiftedAlphabet2.charAt(idx);
                }
            }
            if (Character.isUpperCase(currChar)){
                newChar = Character.toUpperCase(newChar);
            }
            encrypted.append(newChar);
        }
        return encrypted.toString();
    }
    public String decrypt(String input){
        CaesarCipherTwo cct = new CaesarCipherTwo(26-mainKey1, 26-mainKey2);
        return cct.encrypt(input);
    }
}
