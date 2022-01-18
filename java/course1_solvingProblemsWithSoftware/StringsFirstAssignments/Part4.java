import edu.duke.*;
/**
 * find youtube links.
 * 
 * @author (Joy) 
 * @version (6/7/2021)
 */
public class Part4 {
    public String youtubeLink(){
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        System.out.println("Here again");
        for(String word : ur.words()){
            String lowerword = word.toLowerCase();
            int indexyoutube = lowerword.indexOf("youtube.com");
            if (indexyoutube == -1){
                continue;
            }
            int begIndex = word.lastIndexOf("\"",indexyoutube);
            int endIndex = word.indexOf("\"", indexyoutube +1);
            String link = word.substring(begIndex +1, endIndex);
            System.out.println(link);                   
        }
        return "";
    }
    public void test(){
        String youTubeLinks = youtubeLink();
        System.out.println(youTubeLinks + "\n");
    }
}
