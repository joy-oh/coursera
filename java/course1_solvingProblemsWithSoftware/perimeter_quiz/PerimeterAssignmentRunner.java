import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // start with totalPoint = 0
        int totalPoint = 0;
        // iterate each point in the shape
        for (Point p : s.getPoints()) {
            //add p point to totalPoint
           totalPoint = totalPoint + 1;
        }
        return totalPoint;
    }

    public double getAverageLength(Shape s) {
        // get perimeter
        double perimeter = getPerimeter(s);
        // get total points
        int points = getNumPoints(s);
        // devide perimeter with total points
        double averageLength = perimeter/points;
        return averageLength;
    }

    public double getLargestSide(Shape s) {
        // get largest side
        double largestSide = 0.0;
        // Start wth prevPt = the last point 
        Point prePt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()){
            // Find distance from prevPt point to currPt
            double currDist = prePt.distance(currPt);
            System.out.println(currDist);
            // check current distance is bigger than assigned largestSide value,
            if (currDist > largestSide){
            // update to largestSide value
            largestSide = currDist;
            }
            prePt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // initializing largestX to a last point
        Point lastPoint = s.getLastPoint();
        double largestX = lastPoint.getX();
        // for each point in shape,
        for (Point a : s.getPoints()){
            // find x that is largest
            double currentX = a.getX();
            //check if currentX is bigger than largestX
            if (currentX > largestX){
            //update currentX to largestX
            largestX = currentX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // create a DirectoryResource
        DirectoryResource dr = new DirectoryResource();
        // set a largestPerimeter
        double largestPerimeter = 0.0;
        //for files in selectedFiles,
        for (File f: dr.selectedFiles()){
            // create a file resource
            FileResource fr = new FileResource(f);
            // make a shape from the resource
            Shape s = new Shape(fr);
            // get perimeter from the shape
            double currPeri = getPerimeter(s);
            // compare largestPerimeter and current perimeter of the shape
            System.out.println(currPeri);
            if (currPeri > largestPerimeter){
                //update largestPerimeter
            largestPerimeter = currPeri;            
        }
        }             
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // create a directory
        DirectoryResource dr = new DirectoryResource();
        // set a largest perimeter
        double largestPerimeter = 0.0;
        File temp = null;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            //create a shape
            Shape s = new Shape(fr);
            // get perimeter of the shape
            double currPeri = getPerimeter(s);
            if (currPeri > largestPerimeter){
            // update file temp
            largestPerimeter = currPeri;
            temp = f;
            }
        }       
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int numPoints = getNumPoints(s);
        System.out.println("Number" + numPoints);
        double averageLength = getAverageLength(s);
        System.out.println("Ave"+ averageLength);
        double largestSide = getLargestSide(s);
        System.out.println("Longest" + largestSide);
        double largestX = getLargestX(s);
        System.out.println("Lgst X" + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // call getLargestPerimeterMultipleFiles
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println(largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        // print file name that has largest perimeter
        String fWLPeri = getFileWithLargestPerimeter();
        System.out.println("file with largest perimeter" + fWLPeri);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.printFileNames();
        //pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
