import edu.duke.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (Joy) 
 * @version (6/9/2021)
 */
public class Part1 {
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
    public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();   
        while (true){
            String gene = findGene(dna);
            if (gene == ""){
                break;
            }
            else{
                geneList.add(gene);
                int startIndex = dna.indexOf(gene)+gene.length();
                dna = dna.substring(startIndex);
            }
        }
        return geneList;
    }
    public void testOn(String dna){
        System.out.println("Testing getAllGenes on "+ dna);
        StorageResource geneList = getAllGenes(dna);
        for (String gene : geneList.data()){
            System.out.println(gene);
        }
    }
    public void test(){
        testOn("GAGATGAGATGGTAAGATAGTAATTTATGTTGTGAAATGGGA");
        testOn("");
        //        ATGv  TAAv  ATGv  TGAv
        testOn("TTATGGGGTAAVVVATGAAATGAAA");
    }
}