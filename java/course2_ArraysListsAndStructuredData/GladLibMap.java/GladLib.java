import edu.duke.*;
import java.util.*;

public class GladLib {
    private HashMap<String,ArrayList> myMap;    
    private ArrayList<String> usedList;
    private ArrayList<String> usedKeys;
    private Random myRandom;
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";
    
    public GladLib(){
        myMap = new HashMap<String,ArrayList>();
        initializeFromSource(dataSourceDirectory);
        usedList = new ArrayList<String>();
        usedKeys = new ArrayList<String>();
        myRandom = new Random();
    }
    
    public GladLib(String source){
        initializeFromSource(source);
        usedList = new ArrayList<String>();
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String [] catagories = {"country", "noun", "animal", "adjective", "fruit","name","verb","color", "timeframe"};
        for (String s: catagories){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s, list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (myMap.containsKey(label)){
            if (!usedKeys.contains(label)){
                usedKeys.add(label);
            }            
            return randomFrom(myMap.get(label));            
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while (usedList.contains(sub)){
           sub = getSubstitute(w.substring(first+1,last));
        }
        usedList.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    public int totalWordsInMap(){
        int total = 0;
        for (String key: myMap.keySet()){
            total += myMap.get(key).size();
        }
        return total;
    }
    public int totalWordsConsidered(){
        int total = 0;
        for (String key: usedKeys){
            total += myMap.get(key).size();
        }
        return total;
    }
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("datalong/madtemplate2.txt");
        printOut(story, 60);
        int totalPossible = totalWordsInMap();
        int totalConsidered = totalWordsConsidered();
        System.out.println("\n"+ "The total number of words that were replaced is " + usedList.size());
        System.out.println("The total number of words that were possible to pick from: " + totalPossible);
        System.out.println("The total number of words considered: " + totalConsidered);
    }
}
