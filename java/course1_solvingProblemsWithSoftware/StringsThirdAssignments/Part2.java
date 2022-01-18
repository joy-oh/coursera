import edu.duke.*;
/**
 * Write a description of Part2 here.
 * 
 * @author (Joy) 
 * @version (6/9/2021)
 */
public class Part2 {
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
        System.out.println("C count " + count);
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
        System.out.println("count " + count);
        return ratio ;
    }
    public int countCTG(String dna){
        int startIndex = 0;
        int times = 0;
        while (true){
            int found = dna.indexOf("CTG", startIndex);
            if (found == -1){
                break;
            }
            times += 1;
            startIndex = found + 3;
        }
        return times;
    }
    public void testOn(String dna){
        int count = countCTG(dna);
        System.out.println("CTG count is " + count);
        double ratio = cgRatio(dna);
        //System.out.println("cgRatio is " + ratio);
    }
    public void test(){
        URLResource ur = new URLResource("https://users.cs.duke.edu/~rodger/GRch38dnapart.fa");
        String stringUr = ur.asString();
        String dna = stringUr.toUpperCase();
        testOn(dna);
        //testOn("AACTGGCTGACTGCC");
    }
}
