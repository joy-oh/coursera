import edu.duke.*;
import java.util.*;
/**
 * Write a description of CodonCount here.
 * 
 * @author (Joy) 
 * @version (7/20/2021)
 */
public class CodonCount {
    private HashMap<String,Integer> codonMap;
    public CodonCount(){
        codonMap = new HashMap<String,Integer>();
    }
    public void buildCodonMap(int start, String dna){
        codonMap.clear();
        for (int i = start; i < dna.length(); i = i + 3){
            if (i+2 < dna.length()){
                String codon = dna.substring(i,i+3);
                if(codonMap.keySet().contains(codon)){
                    codonMap.put(codon, codonMap.get(codon) + 1);
                    }
                else{
                    codonMap.put(codon,1);
                    }
                
            }
        }
    }

    public String getMostCommonCodon(){
        int max = 0;
        String codon = "";
        for(String dna: codonMap.keySet()){
            if (codonMap.get(dna) > max){
                max = codonMap.get(dna);
                codon = dna;
            }
        }
        return codon;
    }
    public void printCodonCounts(int start, int end){
        for (String dna: codonMap.keySet()){
            if (start<=codonMap.get(dna) && codonMap.get(dna) <=end){
                System.out.println(dna + "\t" + codonMap.get(dna));
            }
        }
    }
    public void tester(){
        FileResource fr = new FileResource();
        String DNA = fr.asString().toUpperCase().trim();
        for (int i = 0; i < 3; i ++){
            buildCodonMap(i, DNA);
            String mostCommon = getMostCommonCodon();
            int start = 7;
            int end = 7;
            System.out.println("\t"+"Reading frame starting with "+ i +" results in "+ codonMap.size()+" unique codons");
            System.out.println("and most common codon is "+ mostCommon + " with count " + codonMap.get(mostCommon));
            System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
            printCodonCounts(start,end);
        }
    }
}
