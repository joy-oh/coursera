
/**
 * Write a description of BatchGrayScale here.
 * 
 * @author (Joy) 
 * @version (6/25/2021)
 */
import edu.duke.*;
import java.io.*;
public class BatchGrayScale {    
    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen());
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return outImage;
    }
    public  void BatchGrayScale(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeGray(inImage);
            String fname = inImage.getFileName();
            String newName = "copy-"+fname;
            gray.setFileName(newName);
            gray.draw();
            gray.save();
        }
    }
}
