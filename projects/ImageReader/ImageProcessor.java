import imagereader.*;

import java.awt.Image;
import java.awt.image.*;
import java.awt.Toolkit;
import java.io.*;

public class ImageProcessor implements IImageProcessor {

    /**
    * Show the RED chanel of an image,
    * return the processed image.
    */
    public Image showChanelR(Image sourceImage) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image image = kit.createImage(
                new FilteredImageSource(sourceImage.getSource(), new Filter(Filter.RED)));
        return image;
    }

    /**
    * Show the GREEN chanel of an image,
    * return the processed image.
    */
    public Image showChanelG(Image sourceImage) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image image = kit.createImage(
                new FilteredImageSource(sourceImage.getSource(), new Filter(Filter.GREEN)));
        return image;
    }

    /**
    * Show the BLUE chanel of an image,
    * return the processed image.
    */
    public Image showChanelB(Image sourceImage) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image image = kit.createImage(
                new FilteredImageSource(sourceImage.getSource(), new Filter(Filter.BLUE)));
        return image;
    }

    /**
    * Show the grayscale of an image,
    * return the processed image.
    */
    public Image showGray(Image sourceImage) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image image = kit.createImage(
                new FilteredImageSource(sourceImage.getSource(), new Filter(Filter.GRAY)));
        return image;
    }

    /**
    * This class works as a filter that can extract the RGB value
    * and make some operations as requirements.
    */
    public class Filter extends RGBImageFilter {
        public static final int GRAY = 0;
        public static final int RED = 1;
        public static final int GREEN = 2;
        public static final int BLUE = 3;
        public static final double RWEIGHT = 0.299;
        public static final double GWEIGHT = 0.587;
        public static final double BWEIGHT = 0.114;
        private int color;

        public Filter(int givenColor) {
            color = givenColor;
            canFilterIndexColorModel = true;
        }

        public int filterRGB(int x, int y, int rgb) {
            switch (color) {
                case 0:
                    int gray = (int)(((rgb & 0x00ff0000) >> 16) * RWEIGHT
                         + ((rgb & 0x0000ff00) >> 8) * GWEIGHT
                         + (rgb & 0x000000ff) * BWEIGHT);
                    return (int)(rgb & 0xff000000) + (gray << 16) + (gray << 8) + gray;
                case 1:
                    return (int)(rgb & 0xffff0000);
                case 2:
                    return (int)(rgb & 0xff00ff00);
                case 3:
                    return (int)(rgb & 0xff0000ff);
            }
            return 0;
        }
    }
}
