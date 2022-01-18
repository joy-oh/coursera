import java.util.*;
import edu.duke.*;
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (Joy) 
 * @version (7/29/2021)
 */
public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> counts;
    public CharactersInPlay(){
        characters = new ArrayList<String>();
        counts = new ArrayList<Integer>();
        characters.clear();
        counts.clear();
    }
    public void update(String person){      
        if(characters.indexOf(person) == -1){
            characters.add(person);
            counts.add(1);
        }
        else{
            int index = characters.indexOf(person);
            int value = counts.get(index);
            counts.set(index, value + 1);
        }
    }
    public void findAllCharacters(){
        FileResource fr = new FileResource();
        for (String line: fr.lines()){
            if(line.contains(".")){
                int index = line.indexOf(".");
                String name = line.substring(0,index);
                update(name);
            }
        }
    }
    public void tester(){
        findAllCharacters();
        for (int i = 0 ; i < characters.size() ; i ++){
            if (counts.get(i) > 1){
                System.out.println(characters.get(i)+"\t"+counts.get(i));          
            }
        }
        //charactersWithNumParts(10, 15);
    }
    public void charactersWithNumParts(int num1, int num2){
        for (int i = 0 ; i < counts.size() ; i ++){
            if (num1 <= counts.get(i) && counts.get(i) <= num2){
                System.out.println(characters.get(i)+"\t"+counts.get(i));
            }
        }        
    }
}
