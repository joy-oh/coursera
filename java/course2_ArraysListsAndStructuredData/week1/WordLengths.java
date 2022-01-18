import edu.duke.*;
import java.io.*;
/**
 * Write a description of WordLengths here.
 * 
 * @author (Joy) 
 * @version (7/7/2021)
 */
public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        for (String word: resource.words()){  
            char first = word.charAt(0);
            char last = word.charAt(word.length() -1);
            int n = 0;
            if (Character.isLetter(first)){
                if(Character.isLetter(last)){
                    n = word.length();
                }
                else{
                    n = word.length() -1;
                }
            }
            else{
                if(Character.isLetter(last)){
                    n = word.length() -1;
                }
                else{
                    n = word.length() -2;
                }
            }                   
            if (n > counts.length - 1){
                n = counts.length - 1;
            }
            if (n > 0){
                counts[n] += 1;
            }
        } 
        for (int i = 0 ; i < counts.length ; i ++){
            System.out.println(i + "\t" + counts[i]);
        }
        int maxIndex = indexOfMax(counts);
        System.out.println(maxIndex);
    }
    public void testCountLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int [31];
        countWordLengths(fr, counts);
    }
    public int indexOfMax(int[] values){
        int maxValue = 0;
        int maxIndex = 0;
        for (int i = 0 ; i < values.length; i ++){
            if (values[i] > maxValue){
                maxValue = values[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
