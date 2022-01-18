import java.util.*;
import edu.duke.*;
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (Joy) 
 * @version (7/19/2021)
 */
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        System.out.println(myWords);
        FileResource fr = new FileResource();
        for ( String word : fr.words()){
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if (index == -1){
                myWords.add(word);
                myFreqs.add(1);
            }
            else{
                int value = myFreqs.get(index);
                myFreqs.set(index, value +  1);
            }
        }
    }
    public  int findIndexOfMax(){
        int max = 0;
        int indexOfMax = 0;
        for (int i = 0; i < myFreqs.size(); i ++){
            if (myFreqs.get(i) > max){
                max = myFreqs.get(i);
                indexOfMax = i;
            }
        }
        return indexOfMax;
    }
    public void tester(){
        findUnique();
        System.out.println("# of unique words: " + myWords.size());
        for (int i = 0; i < myWords.size(); i ++){
            System.out.println(myWords.get(i) + "\t" + myFreqs.get(i));
        }
        int indexOfMax = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " + myWords.get(indexOfMax) +" "+ myFreqs.get(indexOfMax));
    }
}
