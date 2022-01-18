import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of ParsingExportData here.
 * 
 * @author (Joy) 
 * @version (6/10/2021)
 */
public class ParsingExportData {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //String CountryInfo = countryInfo(parser, "Nauru");
        //System.out.println(CountryInfo);
        
        parser = fr.getCSVParser();
        //listExportersTwoProducts(parser, "cotton", "flowers");
        
        parser = fr.getCSVParser();
        //int numOfExporters = numberOfExporters(parser, "cocoa");
        //System.out.println(numOfExporters);
        
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
    public String countryInfo(CSVParser parser, String countryOfInterest){
        String info = "";
        for ( CSVRecord record : parser){
            String country = record.get("Country");
            if (country.contains(countryOfInterest)){
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                //System.out.print(countryOfInterest +": ");
                //System.out.print(exports + ": ");
                //System.out.println(value);
                info = countryOfInterest + ": " + exports + ": " + value;
                break;
            }
            else{
                info = "NOT FOUND";
            }
        }
        return info;
    }
    public void listExportersTwoProducts(CSVParser parser, String ExportItem1, String ExportItem2){
        for (CSVRecord record : parser){
            String exports = record.get("Exports");
            if (exports.contains(ExportItem1) && exports.contains(ExportItem2)){
                
                    String country = record.get("Country");
                    System.out.println(country);
                              
            }
        }
    }
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for (CSVRecord record : parser){            
            String exports = record.get("Exports");
            if (exports.contains(exportItem)){
                count += 1;
            }
        }
        return count;
    }
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord record : parser){
            String value = record.get("Value (dollars)");
            int valueLength = value.length();
            int amountLength = amount.length();
            if ( valueLength > amountLength){
                String country = record.get("Country");
                System.out.println(country + value);
            }
        }
    }
}
