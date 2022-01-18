
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene (String dna, int startCodon, int stopCodon){
        //index position of the start with "ATG"
        String result = "";
        if (startCodon == -1){
            return "";
        }
        
        if (stopCodon == -1){
            return "";
        }
        
        if ((stopCodon - startCodon)%3 == 0){
           result = dna.substring(startCodon, stopCodon+3); 
        }
        return result;
    }
    
    public void testSimpleGene(){
        String dna = "ATGaaa";
        String upDna = dna.toUpperCase();
        System.out.println("DNA stand is " + upDna); 
        int startCodon = upDna.indexOf("ATG");
        int stopCodon = upDna.indexOf("TAA", startCodon+3);
        String gene = findSimpleGene(upDna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);
        
        dna = "AAAATAAAT";
        System.out.println("DNA stand is " +dna);
        startCodon = dna.indexOf("ATG");
        stopCodon = dna.indexOf("TAA", startCodon+3);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);
        
        dna = "ATTTTATGTTT";
        System.out.println("DNA stand is " + dna);
        startCodon = dna.indexOf("ATG");
        stopCodon = dna.indexOf("TAA", startCodon+3);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);
        
        dna = "AAAAATGTTTAGTAGTAGTTAA";
        System.out.println("DNA stand is " + dna);
        startCodon = dna.indexOf("ATG");
        stopCodon = dna.indexOf("TAA", startCodon+3);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);
        
        dna = "AAATGTTTGGGAATAA";
        System.out.println("DNA stand is " + dna);
        startCodon = dna.indexOf("ATG");
        stopCodon = dna.indexOf("TAA", startCodon+3);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);
    }
}
