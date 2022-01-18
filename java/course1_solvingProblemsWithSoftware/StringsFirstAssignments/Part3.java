
/**
 * Write a description of Part3 here.
 * 
 * @author (Joy) 
 * @version (6/7/2021)
 */
public class Part3 {
    public boolean twoOccurences(String stringa, String stringb){
        int oneStringb = stringb.indexOf(stringa);
        if (oneStringb != -1){
            int twoStringb = stringb.indexOf(stringa, oneStringb + stringa.length());
            if (twoStringb != -1){
                return true;}
            else{
                return false;}}
        else{
            return false;}
        }
    public String lastPart(String stringa, String stringb){
        int inStringb = stringb.indexOf(stringa);
        String result = stringb;
        if(inStringb != -1){
            result = stringb.substring(inStringb+stringa.length());
        }
        return result;
    }
    public void testing(){
        String stringa = "a";
        //System.out.println("StringA is " + stringa);
        String stringb  = "banana";
        //System.out.println("StringB is " + stringb);
        //boolean twoOccure = twoOccurences(stringa , stringb);
        //System.out.println(twoOccure);
        String lastpart = lastPart(stringa, stringb);
        System.out.println("The part of the string after "+ stringa + " is " + lastpart);
        
        stringa = "by";
        //System.out.println("StringA is " + stringa);
        stringb = "A story by abby";
        //System.out.println("StringB is " + stringb);
        //twoOccure = twoOccurences(stringa , stringb);
        //System.out.println(twoOccure);
        lastpart = lastPart(stringa, stringb);
        System.out.println("The part of the string after "+ stringa + " is " + lastpart);
        
        stringa = "zoo";
        //System.out.println("StringA is " + stringa);
        stringb = "keeper";
        //System.out.println("StringB is " + stringb);
        //twoOccure = twoOccurences(stringa , stringb);
        //System.out.println(twoOccure);
        lastpart = lastPart(stringa, stringb);
        System.out.println("The part of the string after "+ stringa + " is " + lastpart);
    }
}
