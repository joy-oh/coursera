
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer lz = new LogAnalyzer();
        lz.readFile("short-test_log");
        lz.printAll();
    }
    
    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int unique = la.countUniqueIPs();
        System.out.println("There are "+ unique +" unique IPs");
    }
    
    public void testHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqIPVistOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList unique = la. uniqueIPVistOnDay("Sep 24");
        System.out.println(unique.size());
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int countUniqueIPsInRange = la.countUniqueIPsInRange(200,299);
        System.out.println(countUniqueIPsInRange);
    }
    
    public void testMostNumberVBIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        int most = la.mostNumberVisitsByIP(counts);
        System.out.println(most);
    }
    
    public void testIPsMostVists(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        ArrayList<String>IPs = la.iPsMostVisits(counts);
        System.out.println(IPs);
    }
    
    public void testIPsOnDays(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> iPsForDays = la.iPsForDays();
        System.out.println(iPsForDays);
    }
    
    public void testDayWithMostIPs(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> iPsForDays = la.iPsForDays();
        String dayWithMostIPs = la.dayWithMostIPVisits(iPsForDays);
        System.out.println(dayWithMostIPs);
    }
    
    public void testiPsWithMostVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> iPsForDays = la.iPsForDays();
        ArrayList<String> iPsMostVisitsOnDay = la.iPsWithMostVisitsOnDay(iPsForDays, "Sep 30");
        System.out.println(iPsMostVisitsOnDay);
    }
}
