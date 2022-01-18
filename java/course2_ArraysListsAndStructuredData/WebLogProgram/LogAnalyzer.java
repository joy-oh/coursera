
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()){
             LogEntry le= WebLogParser.parseEntry(line);
             records.add(le);
            }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)){
                uniqueIPs.add(ipAddr);
            }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num){
         for (LogEntry le: records){
            int statusCode = le.getStatusCode();
            if (statusCode > num){
                System.out.println(le);
            }
         }
     }
     
     public ArrayList uniqueIPVistOnDay(String someday){
         ArrayList<String> uniqueIPV = new ArrayList<String>();
         for (LogEntry le : records) {
             Date accessTime = le.getAccessTime();
             String at = accessTime.toString();
             String ipAddr = le.getIpAddress();
             if (at.contains(someday) && !uniqueIPV.contains(ipAddr)){
                 uniqueIPV.add(ipAddr);
             }
         }
         return uniqueIPV;
     } 
     
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> IPsInRange = new ArrayList<String>();
         for (LogEntry le: records){
             String ipAddr = le.getIpAddress();
             int status = le.getStatusCode();
             if (low <= status && status <= high &&!IPsInRange.contains(ipAddr)){
                 IPsInRange.add(ipAddr);
             }
         }
         return IPsInRange.size();
     }
     
     public HashMap<String,Integer> countVisitsPerIP(){
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         for (LogEntry le : records){
             String ipAddr = le.getIpAddress();
             if (!counts.containsKey(ipAddr)){
                 counts.put(ipAddr, 1);
             }
             else{
                 counts.put(ipAddr, counts.get(ipAddr) + 1);
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
         int most = 0;
         for (int count : counts.values()){
             if (count > most){
                most = count;
             }
         }
         return most;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts){
         int most = mostNumberVisitsByIP(counts);
         ArrayList<String> IPS = new ArrayList<String>();
         for (String key: counts.keySet()){
             if (counts.get(key) == most){
                IPS.add(key);
                }
         }
         return IPS;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays (){
         HashMap<String, ArrayList<String>> iPsForDays = new HashMap<String,ArrayList<String>>();
         for (LogEntry le: records){
             Date accessTime = le.getAccessTime();
             String AccessTime = accessTime.toString();
             String date = "";
             String IpAddr = le.getIpAddress();
             for (int i = 0 ; i < AccessTime.length() ; i ++){
                 if (Character.isDigit(AccessTime.charAt(i))){
                     date = AccessTime.substring(i-4, i+2);
                     break;
                 }
             }
             if (!iPsForDays.containsKey(date)){
                 ArrayList<String> IPs = new ArrayList<String>();
                 IPs.add(IpAddr);
                 iPsForDays.put(date,IPs);
             }
             else{
                 ArrayList<String> list = iPsForDays.get(date);
                 list.add(IpAddr);
                 iPsForDays.put(date, list);
             }
         }
         return iPsForDays;
     }
     
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> iPsForDays){
         String mostDay = "";
         int most = 0;
         for (String day : iPsForDays.keySet()){
             if (iPsForDays.get(day).size() > most){
                 most = iPsForDays.get(day).size();
                 mostDay = day;
             }
         }
         return mostDay;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> iPsForDays, String day){
         ArrayList<String> iPsMostVisitsOnDay = new ArrayList<String>();
         for (String key:iPsForDays.keySet()){
            if (key.equals(day)){
                HashMap<String,Integer> counts = new HashMap<String,Integer>();
                ArrayList<String> list = iPsForDays.get(key);
                for (String IP: list){
                    if (!counts.containsKey(IP)){
                        counts.put(IP, 1);
                    }
                    else{
                        counts.put(IP, counts.get(IP) + 1);
                    }
                }
                iPsMostVisitsOnDay = iPsMostVisits(counts); 
                break;
            }            
         }
         return iPsMostVisitsOnDay;
     }
}
