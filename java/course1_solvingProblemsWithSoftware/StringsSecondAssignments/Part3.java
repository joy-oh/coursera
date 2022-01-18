import edu.duke.*;
/**
 * Write a description of Part3 here.
 * 
 * @author (Joy) 
 * @version (6/8/2021)
 */
public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex +3);
        while (currIndex != -1){
            int diff = currIndex - startIndex;
            if (diff % 3 == 0){
            return currIndex;
            }
            else{
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return "";
        }
        else {
            int taaIndex = findStopCodon(dna, startIndex, "TAA");
            int tagIndex = findStopCodon(dna, startIndex, "TAG");
            int tgaIndex = findStopCodon(dna, startIndex, "TGA");
            int minIndex = Math.min(taaIndex,Math.min(tagIndex,tgaIndex));
            
            if (minIndex == dna.length()){
                return "";              
            }
            else{
                String gene = dna.substring(startIndex, minIndex+3);
                return gene;
            }
        }
    }
    public void printAllGenes(String dna){
           
        while (true){
            String gene = findGene(dna);
            if (gene == ""){
                break;
            }
            else{
                System.out.println("Gene is "+gene);
                int startIndex = dna.indexOf(gene)+gene.length();
                dna = dna.substring(startIndex);
            }
        }
    }
    public int countGenes(String dna){
        int count = 0;
        while(true){
            String gene = findGene(dna);
            if (gene.isEmpty()){
                break;
            }
            else{
                count = count + 1;
                int newIndex = dna.indexOf(gene) + gene.length();
                dna = dna.substring(newIndex);
            }
        }
        return count;
    }
    public void testCountGenes(){
        URLResource ur = new URLResource("https://users.cs.duke.edu/~rodger/GRch38dnapart.fa");
        String dnaString = ur.asString();
        System.out.println(dnaString.length());
        int count = countGenes(dnaString);
        System.out.println(count);
        
        //count= countGenes("ATGTGAAAATGTAA");
        //System.out.println(count);
        
        //count = countGenes("ATGTT");
        //System.out.println(count);
    }
}
