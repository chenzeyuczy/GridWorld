import imagereader.*;

import java.awt.Image;
import java.awt.image.*;
import java.awt.Toolkit;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.*;

class ImageIOer implements IImageIO {

    /**
    * Read a bmp file at given path.
    * Return an image the program extract from the binary stream.
    */
    public Image myRead(String filePath) throws IOException {
        Image image;
        try {
            java.io.FileInputStream fin = new FileInputStream(filePath);

            byte[] bmpFile = new byte[14];
            fin.read(bmpFile, 0, 14);
            // Read bmp file header.
            byte[] bmpInfo = new byte[40];
            fin.read(bmpInfo, 0, 40);
            int biWidth = bytesToInt(bmpInfo, 4, 4);
            int biHeight = bytesToInt(bmpInfo, 8, 4);
            int biCount = bytesToInt(bmpInfo, 14, 2);
            int biSizeImage = bytesToInt(bmpInfo, 20, 4);
            // Read bmp info header.

            if (biCount == 24) {
                int skip = (biSizeImage / biHeight) - biWidth * 3;
                skip %= 4;
                int pixel[] = new int[biHeight * biWidth];
                byte bmpRGB[] = new byte[biSizeImage * 22];
                fin.read(bmpRGB, 0, (biWidth + skip) * 3 * biHeight);
                int index = 0;
                for (int i = biHeight - 1; i >= 0; i--) {
                    for (int j = 0; j < biWidth; j++) {
                        pixel[i * biWidth + j] = (255 & 0xff) << 24 | bytesToInt(bmpRGB, index, 3);
                        index += 3;
                    }
                    index += skip;
                }
                image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(
                     biWidth, biHeight, pixel, 0, biWidth));
            } else {
                image = (Image)null;
            }

            fin.close();
            return image;
        } catch(Exception e) {
            e.printStackTrace(System.out);
        }
        return (Image)null;
    }

    /**
    * Write an image into given path,
    * return the given image.
    */
    public Image myWrite(Image image, String filePath) throws IOException {
        try {
            File imgFile = new File(filePath + ".bmp");
            BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics2D graph = bi.createGraphics();
            graph.drawImage(image, 0, 0, null);
            graph.dispose();
            ImageIO.write(bi, "bmp", imgFile);
            return image;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return image;
    }

    /**
    * This function convey an array of byte into an integer.
    * Length and offset can specified.
    * Since the computer stores data in little-endian mode,
    * program cannot read the data directly from binary file.
    */
    public int bytesToInt(byte[] array, int offset, int length) {
        int target = 0;
        for (int i = 0; i < length; i++) {
            target += (int)((int)(array[offset + i] & 0xff) << (8 * i));
        }
        return target;
    }
}
