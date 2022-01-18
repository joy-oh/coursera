import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.lang.*;
/**
 * Write a description of ParsingWeatherData here.
 * 
 * @author (Joy) 
 * @version (6/11/2021)
 */
public class ParsingWeatherData {
    public CSVRecord getLowestDataRowOfTwo(CSVRecord currentRow, CSVRecord lowestDataRow, String stringOfInterest){
        if (lowestDataRow == null ){
            if (lowestDataRow == null){
                lowestDataRow = currentRow;
            }
        }
        else if (currentRow.get("Humidity") == "N/A"){
            lowestDataRow = lowestDataRow;
        }
        else if (Double.parseDouble(currentRow.get("TemperatureF")) == -9999.0){
            lowestDataRow = lowestDataRow;
        }
            
        else {
            double lowest = Double.parseDouble(lowestDataRow.get(stringOfInterest));
            double current = Double.parseDouble(currentRow.get(stringOfInterest));
            if(current < lowest){
                lowestDataRow = currentRow; 
            }
        }
        return lowestDataRow;
    }
    public CSVRecord coldestTempInFile(CSVParser parser){
        CSVRecord coldestTempRow = null;
        for (CSVRecord record : parser){
            CSVRecord currentRow = record;
            coldestTempRow = getLowestDataRowOfTwo(currentRow, coldestTempRow,"TemperatureF");
        }
        return coldestTempRow;
    }
    public void testColdestTemp(){
        FileResource fr = new FileResource();
        CSVRecord coldestTempRow = coldestTempInFile(fr.getCSVParser());
        System.out.println(coldestTempRow.get("TemperatureF"));
    }
    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        String fileName = null;
        CSVRecord coldestTempRow = null;        
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestTempInFile(fr.getCSVParser());
            coldestTempRow = getLowestDataRowOfTwo(currentRow, coldestTempRow,"TemperatureF"); 
            if (coldestTempRow == currentRow){
                fileName = f.getName();
            }
        }
        return fileName;
    }
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestTempRow = coldestTempInFile(parser);
        String coldestTime = coldestTempRow.get("TimeEST");
        System.out.println("\n" + coldestTime);
    }
    public void testFileWithColdestTemperature(){
        String fileName = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + fileName);
        File f = new File("nc_weather/2014/" + fileName);
        FileResource fr = new FileResource(f);
        CSVRecord coldestTempRow = coldestTempInFile(fr.getCSVParser());
        double coldestTemp = Double.parseDouble(coldestTempRow.get("TemperatureF"));
        System.out.println("Coldest temperature on that day was " + coldestTemp);
        System.out.println("All the temperature on the coldest day were:");
        for (CSVRecord record : fr.getCSVParser()){
            System.out.print(record.get("DateUTC")+": ");
            System.out.println(record.get("TemperatureF"));
        }  
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumidityRow = null;
        for (CSVRecord currentRow : parser){
             lowestHumidityRow = getLowestDataRowOfTwo(currentRow, lowestHumidityRow, "Humidity");
        }
        return lowestHumidityRow;
    }
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("\nLowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidityRowInMany = null;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestHumidityRowInMany = getLowestDataRowOfTwo(currentRow, lowestHumidityRowInMany, "Humidity");
        }
        return lowestHumidityRowInMany;
    }
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowestHumidityRowInMany = lowestHumidityInManyFiles();
        System.out.println("\nLowest Humidity was "+ lowestHumidityRowInMany.get("Humidity") + " at " + lowestHumidityRowInMany.get("DateUTC"));
    }
    public double averageTemperatureInFile(CSVParser parser){
        double totalTemp = 0;
        double totalRow = 0;
        for (CSVRecord currentRow : parser){
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            totalTemp = totalTemp + currentTemp;
            totalRow += 1;
        }
        double averageTemp = totalTemp/totalRow;
        return averageTemp;
    }      
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        double averTemp = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("\nAverage temperature in file is " + averTemp);
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double totalTemp = 0;
        double totalCount = 0;
        for (CSVRecord currentRow : parser){
            if (Double.parseDouble(currentRow.get("Humidity"))>= value){
                totalTemp += Double.parseDouble(currentRow.get("TemperatureF"));
                totalCount += 1;
            }
        }   
        double averTempWithSuchHumi = totalTemp/totalCount;
        return averTempWithSuchHumi;
    }
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        double averTempWSuchHumi = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (Double.isNaN(averTempWSuchHumi)){
            System.out.println("No temperature with that humidity");
        }
        else{
            System.out.println(averTempWSuchHumi);
        }
    }
}

