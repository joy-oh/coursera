
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (Joy) 
 * @version (7/13/2021)
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key){
        mainKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key)+alphabet.substring(0,key);
    }
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder();
        String upInput = input.toUpperCase();
        for (int i = 0; i < input.length(); i ++){
            char currChar = upInput.charAt(i);
            int index = alphabet.indexOf(currChar);
            char newChar = currChar;
            if (index != -1){
                newChar = shiftedAlphabet.charAt(index);
                if (Character.isLowerCase(input.charAt(i))){
                    newChar = Character.toLowerCase(newChar);
                }
            }        
            encrypted.append(newChar);
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26- mainKey);
        return cc.encrypt(input);
    }
}
