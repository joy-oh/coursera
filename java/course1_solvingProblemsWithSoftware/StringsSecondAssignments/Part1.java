
/**
 * Write a description of Part1 here.
 * 
 * @author (Joy) 
 * @version (6/8/2021)
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
    public void testFindGene(){
        //                  v  v  v  v  v 
        String dna = "AATTATTTTATAGGGTAGGAA";
        System.out.println("DNA stand is " + dna);
        String gene = findGene(dna);
        System.out.println("The gene is " + gene);
        //       v  v  v  v  v  v  v
        dna = "TAATGTTTGATATGGGTTAGTT";
        System.out.println("DNA stand is " + dna);
        gene = findGene(dna);
        System.out.println("The gene is " + gene);
        //        v  v  v  v  v  v  v
        dna = "GGTATGTTGTGAATGTAA";
        System.out.println("DNA stand is " + dna);
        gene = findGene(dna);
        System.out.println("The gene is " + gene);
        //       v  v  v  v   
        dna = "TTTTGATGTTATAT";
        System.out.println("DNA stand is " + dna);
        gene = findGene(dna);
        System.out.println("The gene is " + gene);
        //            v  v  v  v  v  
        dna = "GAGATAGATGGGTTTGAATTAA";
        System.out.println("DNA stand is " + dna);
        gene = findGene(dna);
        System.out.println("The gene is " + gene);
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
    public void testOn(String dna){
        System.out.println("Testing printAllGenes on " + dna);
        printAllGenes(dna);
    }
    public void test(){
        //         ATGv  v  TAAv          ATGv  TGAvATGv  v
        testOn("GAGATGAGATGGTAAGATAGTAATTTATGTTGTGAAATGGGA");
        testOn("");
        //        ATGv  TAAv  ATGv  TGAv
        testOn("TTATGGGGTAAVVVATGAAATGAAA");
    }
    public void testFindStopCodon(){
        //             v  v  v  v  v  v
        String dna = "AATGATGTTGTGATAATAG";
        System.out.println("DNA stand is "+ dna);
        String gene = findGene(dna);
        System.out.println("The gene is "+gene);
        }
    }
