import imagereader.Runner;

/**
* This class runs a program that execute the image reader,
* a class that can read and write bmp files
* as well as extracting color chanels and changing into grayscale.
*/
public class ImageRunner {
    public static void main(String[] args) {
        ImageIOer imageioer = new ImageIOer();
        ImageProcessor processor = new ImageProcessor();
        Runner.run(imageioer, processor);
    }
}
