
/**
 * Write a description of Part2 here.
 * 
 * @author (Joy) 
 * @version (6/8/2021)
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        int occure = 0;
        while (true){
            int found = stringb.indexOf(stringa);
            if (found != -1){
                occure = occure + 1;
                int newIndex = found + stringa.length();
                stringb = stringb.substring(newIndex);
            }
            else{
                break;
            }
        }
        return occure;
    }
    public void testHowMany(){
        int occure = howMany("AA", "ATAAAA");
        System.out.println(occure);
        
        occure = howMany("TAG", "TAGTTTABTAGTTAG");
        System.out.println(occure);
        
        occure = howMany("TTT", "AAAAGGG");
        System.out.println(occure);
    }
}
