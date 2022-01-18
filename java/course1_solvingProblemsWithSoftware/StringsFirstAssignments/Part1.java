
/**
 * Write a description of Part1 here.
 * 
 * @author (Joy) 
 * @version (6/7/2021)
 */
public class Part1 {
    public String findSimpleGene (String dna){
        //index position of the start with "ATG"
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return "";
        }
        int stopIndex = dna.indexOf("TAA", startIndex+3);
        if (stopIndex == -1){
            return "";
        }
        if ((stopIndex - startIndex)%3 == 0){
           result = dna.substring(startIndex, stopIndex+3); 
        }
        return result;
    }
    
    public void testSimpleGene(){
        String dna = "ATGAAA";
        System.out.println("DNA stand is " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "AAAATAAAT";
        System.out.println("DNA stand is " +dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ATTTTATGTTT";
        System.out.println("DNA stand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "AAAAATGTTTAGTAGTAGTTAA";
        System.out.println("DNA stand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "AAATGTTTGGGAATAA";
        System.out.println("DNA stand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
    }
}
