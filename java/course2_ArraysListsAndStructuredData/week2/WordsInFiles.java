import java.util.*;
import edu.duke.*;
import java.io.*;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (Joy) 
 * @version (7/21/2021)
 */
public class WordsInFiles {
    private HashMap<String, ArrayList > wordsMap;
    public WordsInFiles(){
        wordsMap = new HashMap<String, ArrayList>();
    }
    private void addWordsFromFile(File f){
        String fName = f.getName();
        FileResource file = new FileResource(f);
        for (String word: file.words()){
            if (wordsMap.containsKey(word)){                
                if (!wordsMap.get(word).contains(fName)){
                    wordsMap.get(word).add(fName);
                }
            }
            else{
                ArrayList<String> fNames = new ArrayList<String>();
                fNames.add(fName);
                wordsMap.put(word, fNames);
            }
        }
    }
    public void buildWordFileMap(){
        wordsMap.clear();
        DirectoryResource files = new DirectoryResource();
        for ( File f : files.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    public int maxNumber(){
        int maxNumber = 0;
        for (String word: wordsMap.keySet()){
            if (wordsMap.get(word).size()>maxNumber){
                maxNumber = wordsMap.get(word).size();
            }
        }
        return maxNumber;
    }
    public ArrayList wordsInNumberFiles(int number){
        ArrayList<String> words = new ArrayList<String>();
        for (String word: wordsMap.keySet()){
            if (wordsMap.get(word).size() == number){
                words.add(word);
            }
        }
        return words;
    }
    public void printFilesIn(String word){
        if (wordsMap.containsKey(word)){
            ArrayList<String> files = wordsMap.get(word);
            for (String file :  files){
                System.out.println(file);
            }
        }
    }
    public void tester(){
        buildWordFileMap();
        ArrayList Words = wordsInNumberFiles(4);
        int size = Words.size();
        System.out.println(size);
        //int max = maxNumber();
        //ArrayList<String> maxWords = wordsInNumberFiles(max);
        //for (String word : maxWords){
         //   System.out.println(word+"\t"+ wordsMap.get(word));
        //}
        //for (String word: wordsMap.keySet()){
        //    System.out.println(word+"\t" + wordsMap.get(word));
        //}
        //printFilesIn("red");
    }
}
