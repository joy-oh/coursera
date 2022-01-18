import edu.duke.*;
/**
 * Write a description of Part3 here.
 * 
 * @author (Joy) 
 * @version (6/9/2021)
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
    public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();   
        while (true){
            String gene = findGene(dna);
            if (gene.isEmpty()){
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
    public double cgRatio(String dna){
        int count = 0;
        double length = dna.length();
        int startIndexC = 0;
        while (true){
            int foundC = dna.indexOf("C", startIndexC);
            if (foundC == -1){
                break;
            }
            count += 1;
            startIndexC = foundC + 1;
        }       
        int startIndexG = 0;
        while (true){
            int foundG = dna.indexOf("G", startIndexG);
            if (foundG == -1){
                break;
            }
            count += 1;
            startIndexG = foundG + 1;
        }
        double ratio = count/length;
        return ratio ;
    }
    public void processGenes(StorageResource sr){
        int ratioCount = 0;
        int longest = 0;
        int longerThan60Count = 0;
        String longestGene = "";
        for (String gene : sr.data()){
            int currentLength = gene.length();
            if (currentLength > longest) {
                    longest = currentLength;
                }
            if ( currentLength > 60){
                System.out.println("string length that is longer than 60 :" + gene );
                System.out.println("String length of the string above is " + currentLength);
                longerThan60Count += 1;
                
            }
            double ratio = cgRatio(gene);
            if (ratio > 0.35){
                int higherRatioGene = gene.length();
                System.out.println(gene+ "is the string that has ratio higher than 0.35 ");
                System.out.println("The length of the string that has ration higher than 0.35 " + higherRatioGene);
                ratioCount += 1;
            }
        }
        System.out.println("The number of strings whose cg ration is higher than 0.35 is " + ratioCount);
        System.out.println("The number of strings that has longer than 60 is " + longerThan60Count);
        System.out.println("The length of the longest gene is " + longest + "\n");        
    }
    public void testProcessGenes(){
        //StorageResource sr = new StorageResource();
        //sr.add("TTATGGGATGTTGAGATGTTTGGAAAATAGG");
        //sr.add("TTATGTAAAATGTAG");
        //sr.add("ATGCCATGACCATGGGCCAATAGATGGCGCGCTAA");
        //sr.add("ATGTAAATGTGAATGTTTTAG");
        URLResource ur = new URLResource("https://users.cs.duke.edu/~rodger/GRch38dnapart.fa");
        String dnaString = ur.asString();
        System.out.println(dnaString.length());
        String dna = dnaString.toUpperCase();
        System.out.println("The DNA string is " + dna);
        StorageResource geneList = getAllGenes(dna);
        System.out.println("There are " +geneList.size() + "genes");
        processGenes(geneList);
    }
}
