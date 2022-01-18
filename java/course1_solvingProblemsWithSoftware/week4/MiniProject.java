import edu.duke.*;
import java.io.File;
import org.apache.commons.csv.*;
/**
 * Write a description of MiniProject here.
 * 
 * @author (Joy) 
 * @version (6/22/2012)
 */
public class MiniProject {
    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalGirlNames = 0;
        int totalBoyNames = 0;
        int totalNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            totalNames += 1;
            if (rec.get(1).equals("F")){
                totalGirls += numBorn;
                totalGirlNames += 1;
            }
            else{
                totalBoys += numBorn;
                totalBoyNames += 1;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
        System.out.println("total names = " + totalNames);
        System.out.println("total girls' names = " + totalGirlNames);
        System.out.println("total boys' names = " + totalBoyNames);
    }
    public void testTtlBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    public int getRank(int year, String name, String gender){
        int count = 0;
        int rank = -1;
        boolean found = true;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        for (CSVRecord rec: fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)){
                count += 1;
                if (rec.get(0).equals(name)){
                    found = true;
                    break;
                }
                else {
                    found = false;
                }
            }                  
        }    
        if (found){
            rank = count;
        }
        return rank;
    }
    public void testGetRank(){
        int Rank = getRank(1971, "Frank", "M");
        System.out.println(Rank);
    }
    public String getName(int year, int rank, String gender){
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        int count = 0;
        String name = "NO NAME";
        boolean found = false;
        for (CSVRecord rec: fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)){
                count += 1;
                if (count == rank){
                    name = rec.get(0); 
                    break;
                }
            }
        }
        return name;
    }
    public void testGetName(){
        String Name = getName(1982, 450, "M");
        System.out.println(Name);
    }
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        String newName = "NO NAME";
        int count = 0;
        for (CSVRecord rec: fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)){
                count +=1;
                if (rec.get(0).equals(name)){
                    break;
                }
            }
        }
        FileResource nfr = new FileResource("us_babynames/us_babynames_by_year/yob"+newYear+".csv");
        int newCount = 0;
        for (CSVRecord rec: nfr.getCSVParser(false)){
            if (rec.get(1).equals(gender)){
                newCount += 1;
                if (newCount == count){
                    newName = rec.get(0);
                }
            }
        }
        System.out.println(name+ " born in " + year + " would be " + newName + " if she was born in " + newYear);
    }
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int year = -1;
        int rank = 0;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            int count = 0;
            boolean found = false;
            for (CSVRecord rec: fr.getCSVParser(false)){
                if (rec.get(1).equals(gender)){
                    count += 1;                    
                    if (rec.get(0).equals(name)){
                        found = true;
                        break;
                    }
                }                  
            }    
            if (found){
                if (rank == 0 || count < rank){
                    rank = count;
                    String fileName = f.getName();
                    int yearcovert = Integer.parseInt(fileName.substring(3, 7));
                    year = yearcovert;
                }            
            }
        }
        return year;
    }
    public void testYearOfHigestRank(){
        int yearOfHighestRank = yearOfHighestRank("Mich", "M");
        System.out.println("\n" + yearOfHighestRank);
    }
    public double getAverageRank(String name, String gender){
        double averageRank = -1.0;
        int numOfFiles = 0;
        double ranks = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            int count = 0;
            numOfFiles += 1;
            for (CSVRecord rec : fr.getCSVParser(false)){
                if (rec.get(1).equals(gender)){
                    count += 1;                   
                    if (rec.get(0).equals(name)){
                        ranks += count;
                        break;
                    }
                }                
            }
        }
        if (ranks > 0){
            averageRank = ranks/numOfFiles;
        }      
        return averageRank;
    }
    public void testGetAverageRank(){
        double AverageRank = getAverageRank("Robert", "M");
        System.out.println(AverageRank);
    }
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        FileResource fr = new FileResource();
        int totalNumBirths = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)){             
                if (rec.get(0).equals(name)){
                    break;
                }
                totalNumBirths += Integer.parseInt(rec.get(2));
            }            
        }
        return totalNumBirths;
    }
    public void testGetTotalBirthsRankedHihger(){
        int totalNumBirthsHigher = getTotalBirthsRankedHigher(1990, "Emily", "F");
        System.out.println(totalNumBirthsHigher);
    }
}