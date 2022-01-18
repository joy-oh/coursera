import edu.duke.*;
import java.io.*;

/**
 * Write a description of CountingCommon here.
 * 
 * @author (Joy) 
 * @version (7/2/2021)
 */
public class CountingCommon {
    public String[] getCommon(){
        FileResource fr = new FileResource();
        String[] common = new String [20];
        int index = 0;
        for (String s : fr.words()){
            common[index] = s;
            index += 1;
        }
        return common;
    }
    public int indexOf(String[] list, String word){
        for (int i = 0; i < list.length; i ++){
            if (list[i].equals(word)){
                return i;
            }
        }
        return -1;
    }
    public void countWords(FileResource fr, String[] common, int[] counts){
        for(String word: fr.words()){
            word = word.toLowerCase();
            int index = indexOf(common, word);
            if (index != -1){
                counts[index] += 1;
            }
        }
    }
    void countShakespeare(){
        String[] plays = {};
        String[] common = getCommon();
        int[] counts = new int[common.length];
        for (int i = 0 ; i < plays.length; i ++){
            FileResource fr = new FileResource();
            countWords(fr, common, counts);
            System.out.println("Done with " + plays[i]);
        }
        for (int i = 0 ; i < common.length; i ++){
            System.out.println(common[i] + "\t" + counts[i]);
        }
    }
}
