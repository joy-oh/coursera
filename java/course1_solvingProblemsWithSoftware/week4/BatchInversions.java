import edu.duke.*;
import java.io.*;
/**
 * Write a description of BatchInversions here.
 * 
 * @author (Joy) 
 * @version (6/28/2021)
 */
public class BatchInversions {
    public ImageResource makeInversion(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            pixel.setRed(255- inPixel.getRed());
            pixel.setGreen(255 - inPixel.getGreen());
            pixel.setBlue(255 - inPixel.getBlue());         
        }   
        return outImage;
    }
    public void BatchAndSave(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource inversion = makeInversion(inImage);
            String fileName = inImage.getFileName();
            String newName = "inverted-" + fileName; 
            inversion.setFileName(newName);           
            inversion.draw();
            inversion.save();
        }      
    }
}
