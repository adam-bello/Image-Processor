package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Gets the width of a given ppm file.
   *
   * @param filename the name of the file to find the width of
   * @return the width of the given filename
   */
  public static int getWidth(String filename) {
    Scanner sc = initScanner(filename);
    return sc.nextInt();
  }

  /**
   * Gets the height of a given ppm file.
   *
   * @param filename the name of the file to find the height of
   * @return the height of the given filename
   */
  public static int getHeight(String filename) {
    Scanner sc = initScanner(filename);
    sc.next();
    return sc.nextInt();
  }

  /**
   * Gets the maximum color value of a given ppm file.
   *
   * @param filename the name of the file to find the maximum color value of
   * @return the maximum color value of the given filename
   */
  public static int getMaxValue(String filename) {
    Scanner sc = initScanner(filename);
    sc.next();
    sc.next();
    return sc.nextInt();
  }

  /**
   * Creates a 2d array of the pixels of a given ppm file.
   *
   * @param filename the name of the file to create a 2d array of pixels from
   * @return the complete 2d array of pixels from the given filename
   */
  public static Pixel[][] getImagePixels(String filename) {
    Scanner sc = initScanner(filename);
    int width = sc.nextInt();
    int height = sc.nextInt();
    sc.next();
    Pixel[][] result = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        Pixel p = new Pixel(r, g, b);
        result[i][j] = p;
      }
    }

    return result;
  }

  /**
   * Ensures that a given PPM file is in the proper format and returns
   * the scanner with remaining PPM file information.
   *
   * @param filename the name of the PPM file to look through
   * @return the remaining Scanner after the file has been confirmed to be in the PPM format
   */
  public static Scanner initScanner(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }

    return sc;
  }

  /**
   * Reads a PPMImage with a given pathname and returns an Image.
   * @param pathname the pathname of the file being read in.
   * @return returns an Image with the attributes from the PPMImage.
   */
  public static Image readPPM(String pathname) {
    Image newImage;
    try {
      int width = ImageUtil.getWidth(pathname);
      int height = ImageUtil.getHeight(pathname);
      Pixel[][] image = ImageUtil.getImagePixels(pathname);
      newImage = new GenericImage(width, height, image);

    } catch (NullPointerException e) {
      throw new IllegalArgumentException("The file " + pathname + " could not be found");
    }
    return newImage;

  }

  /**
   * Reads a conventional image file with a given pathname and returns an Image.
   * @param pathname the pathname of the file being read in.
   * @return an Image with the attributes from the conventional image.
   */
  public static Image readImage(String pathname) {
    BufferedImage input;
    FileInputStream fi;

    try {
      fi = new FileInputStream(pathname);
      input = ImageIO.read(fi);
      fi.close();
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File Not Found");
    } catch (IOException ie) {
      throw new IllegalStateException("IOException");
    }

    Pixel[][] imagePixels = new Pixel[input.getHeight()][input.getWidth()];
    for (int i = 0; i < input.getHeight(); i++) {
      for (int j = 0; j < input.getWidth(); j++) {
        int color = input.getRGB(j, i);
        Color c = new Color(color);
        imagePixels[i][j] = new Pixel(c.getRed(), c.getGreen(),c.getBlue());
      }
    }
    Image newImage = new GenericImage(input.getWidth(), input.getHeight(), imagePixels);
    return newImage;
  }

  /**
   * Writes a PPM file and saves it to either a given pathname, or creates
   * a new file if the name cannot be found.
   * @param pathname the pathname to save the file to
   * @param width the width component of the PPM file to be made
   * @param height the height component of the PPM file to be made
   * @param pixels the 2D array of pixels to be converted into PPM format
   * @throws IOException if the given file path name is invalid
   */
  public static void writePPM(String pathname, int width, int height, Pixel[][] pixels)
          throws IOException {

    OutputStream output;
    PrintStream str;
    try {
      output = new FileOutputStream(pathname);
      str = new PrintStream(output);
    } catch (FileNotFoundException e) {
      File newFile = new File(pathname);
      newFile.createNewFile();
      output = new FileOutputStream(newFile);
      str = new PrintStream(output);

    }

    str.append("P3\n");
    str.append(String.format("%s", width));
    str.append(" " + height + "\n");
    str.append(String.format("%s\n", 255));


    StringBuilder pixList = new StringBuilder();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {

        if (i == height - 1 && j == width - 1) {
          pixList.append(String.format("%s\n", pixels[i][j].getRed()));
          pixList.append(String.format("%s\n", pixels[i][j].getGreen()));
          pixList.append(String.format("%s", pixels[i][j].getBlue()));
        } else {
          pixList.append(String.format("%s\n", pixels[i][j].getRed()));
          pixList.append(String.format("%s\n", pixels[i][j].getGreen()));
          pixList.append(String.format("%s\n", pixels[i][j].getBlue()));
        }
      }
    }

    str.append(String.format("%s", pixList));
  }

  /**
   * Writes to an image file based on the attributes it is given from the model.
   * @param pathname the pathname of the file being written to.
   * @param width the width of the image.
   * @param height the height of the image.
   * @param pixels the pixels of the image.
   * @param filetype the type of file being created.
   * @throws IOException if the file fails to be written to.
   */
  public static void writeImage(String pathname, int width, int height, Pixel[][] pixels,
                                String filetype)
          throws IOException {
    BufferedImage newImage = new BufferedImage(width, height,
            BufferedImage.TYPE_INT_RGB);

    Pixel curPixel;
    int rgb;

    for (int i = 0; i < newImage.getHeight(); i++) {
      for (int j = 0; j < newImage.getWidth(); j++) {
        curPixel = pixels[i][j];
        rgb = new Color(curPixel.getRed(), curPixel.getGreen(), curPixel.getBlue()).getRGB();
        newImage.setRGB(j, i, rgb);
      }
    }

    OutputStream output;
    try {
      output = new FileOutputStream(pathname);
    } catch (FileNotFoundException e) {
      File newFile = new File(pathname);
      try {
        newFile.createNewFile();
        output = new FileOutputStream(newFile);
      } catch (IOException ie) {
        throw new IllegalStateException("Unable to create new file");
      }

    }
    ImageIO.write(newImage, filetype, output);
  }

  /**
   * Main method for giving arguments to load, save, and edit a PPM file.
   * @param args the list of arguments
   */
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "Koala.ppm";
    }

    ImageUtil.readPPM(filename);
  }
}

