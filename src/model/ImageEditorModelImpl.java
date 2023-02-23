package model;

import java.io.IOException;
import java.util.HashMap;

/**
 * The model of the program which does the editing of images through their pixels. Images that
 * have been loaded are stored in a hashmap with their name.
 */
public class ImageEditorModelImpl implements ImageEditorModel {
  protected HashMap<String, Image> images;

  public ImageEditorModelImpl() {
    images = new HashMap<String, Image>();
  }

  public Image getImage(String key) {
    return images.get(key);
  }

  @Override
  public void load(String pathname, String filename, String filetype)
          throws IllegalArgumentException {
    Image newImage;
    if (filetype.equalsIgnoreCase("ppm")) {
      newImage = ImageUtil.readPPM(pathname);
    } else {
      newImage = ImageUtil.readImage(pathname);
    }

    images.put(filename, newImage);
  }

  @Override
  public void save(String pathname, String filename, String filetype)
          throws IllegalArgumentException {
    checkFile(filename);
    if (pathname.length() == 0) {
      throw new IllegalArgumentException("Save path cannot be empty");
    }

    Image saveImage = images.get(filename);
    Pixel[][] imagePix = this.copyPixels(saveImage);
    if (filetype.equalsIgnoreCase("ppm")) {
      try {
        ImageUtil.writePPM(pathname, saveImage.getWidth(), saveImage.getHeight(), imagePix);
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      try {
        ImageUtil.writeImage(pathname, saveImage.getWidth(), saveImage.getHeight(),
                imagePix, filetype);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private Image startVisualize(String filename) {
    checkFile(filename);
    Image oldImage = images.get(filename);
    Image curImage = copyImage(oldImage);
    return curImage;
  }

  private void visualizeColor(String filename, String destination, String col) {
    Image curImage = startVisualize(filename);
    int newColor;
    for (int i = 0; i < curImage.getHeight(); i++) {
      for (int j = 0; j < curImage.getWidth(); j++) {
        Pixel curPixel = curImage.getPixelAt(i, j);
        if (col.equals("red")) {
          newColor = curPixel.getRed();
        }
        else if (col.equals("green")) {
          newColor = curPixel.getGreen();
        }
        else {
          newColor = curPixel.getBlue();
        }

        curPixel.changeRed(newColor);
        curPixel.changeGreen(newColor);
        curPixel.changeBlue(newColor);
      }
    }

    images.put(destination, curImage);
  }

  @Override
  public void visualizeRed(String filename, String destination) {
    visualizeColor(filename, destination, "red");
  }

  @Override
  public void visualizeGreen(String filename, String destination)
          throws IllegalArgumentException {

    visualizeColor(filename, destination, "green");
  }

  @Override
  public void visualizeBlue(String filename, String destination)
          throws IllegalArgumentException {

    visualizeColor(filename, destination, "blue");
  }

  @Override
  public void visualizeValue(String filename, String destination)
          throws IllegalArgumentException {

    Image curImage = startVisualize(filename);

    for (int i = 0; i < curImage.getHeight(); i++) {
      for (int j = 0; j < curImage.getWidth(); j++) {
        Pixel curPixel = curImage.getPixelAt(i, j);
        int maxRG = Math.max(curPixel.getRed(), curPixel.getGreen());
        int max = Math.max(curPixel.getBlue(), maxRG);

        curPixel.changeRed(max);
        curPixel.changeGreen(max);
        curPixel.changeBlue(max);

      }
    }

    images.put(destination, curImage);
  }

  @Override
  public void visualizeIntensity(String filename, String destination)
          throws IllegalArgumentException {

    Image curImage = startVisualize(filename);

    for (int i = 0; i < curImage.getHeight(); i++) {
      for (int j = 0; j < curImage.getWidth(); j++) {
        Pixel curPixel = curImage.getPixelAt(i, j);
        int averageBrightness = ((curPixel.getRed()) + (curPixel.getBlue())
                + (curPixel.getGreen())) / 3;
        curPixel.changeRed(averageBrightness);
        curPixel.changeGreen(averageBrightness);
        curPixel.changeBlue(averageBrightness);
      }
    }

    images.put(destination, curImage);
  }

  @Override
  public void visualizeLuma(String filename, String destination)
          throws IllegalArgumentException {

    double[][] lumaFilter = new double[3][3];
    for (int i = 0; i < lumaFilter.length; i++) {
      lumaFilter[i][0] = 0.2126;
      lumaFilter[i][1] = 0.7152;
      lumaFilter[i][2] = 0.0722;
    }
    colorTransformation(lumaFilter, filename, destination);
  }

  @Override
  public void flipHorizontal(String filename, String destination)
          throws IllegalArgumentException {
    Image curImage = startVisualize(filename);
    Pixel[][] tempImage = new Pixel[curImage.getHeight()][curImage.getWidth()];

    for (int i = 0; i < curImage.getHeight(); i++) {
      for (int j = 0; j < curImage.getWidth(); j++) {
        Pixel curPixel = new Pixel(curImage.getPixelAt(i, j).getRed(),
                curImage.getPixelAt(i, j).getGreen(), curImage.getPixelAt(i, j).getBlue());
        tempImage[i][(curImage.getWidth() - 1) - j] = curPixel;
      }
    }

    curImage = new GenericImage(curImage.getWidth(), curImage.getHeight(), tempImage);

    images.put(destination, curImage);
  }

  @Override
  public void flipVertical(String filename, String destination)
          throws IllegalArgumentException {

    Image curImage = startVisualize(filename);
    Pixel[][] tempImage = new Pixel[curImage.getHeight()][curImage.getWidth()];

    for (int i = 0; i < curImage.getHeight(); i++) {
      for (int j = 0; j < curImage.getWidth(); j++) {
        Pixel curPixel = new Pixel(curImage.getPixelAt(i, j).getRed(),
                curImage.getPixelAt(i, j).getGreen(), curImage.getPixelAt(i, j).getBlue());
        tempImage[(curImage.getHeight() - 1) - i][j] = curPixel;
      }
    }

    curImage = new GenericImage(curImage.getWidth(), curImage.getHeight(), tempImage);

    images.put(destination, curImage);
  }

  @Override
  public void brighten(int scale, String filename, String destination) {
    Image curImage = startVisualize(filename);

    for (int i = 0; i < curImage.getHeight(); i++) {
      for (int j = 0; j < curImage.getWidth(); j++) {
        Pixel curPixel = curImage.getPixelAt(i, j);

        if (curPixel.getRed() >= 255 - scale) {
          curPixel.changeRed(255);
        } else {
          curPixel.changeRed(curPixel.getRed() + scale);
        }

        if (curPixel.getGreen() >= 255 - scale) {
          curPixel.changeGreen(255);
        } else {
          curPixel.changeGreen(curPixel.getGreen() + scale);
        }

        if (curPixel.getBlue() >= 255 - scale) {
          curPixel.changeBlue(255);
        } else {
          curPixel.changeBlue(curPixel.getBlue() + scale);
        }
      }
    }

    images.put(destination, curImage);
  }

  @Override
  public void darken(int scale, String filename, String destination) {

    Image curImage = startVisualize(filename);

    for (int i = 0; i < curImage.getHeight(); i++) {
      for (int j = 0; j < curImage.getWidth(); j++) {
        Pixel curPixel = curImage.getPixelAt(i, j);

        if (curPixel.getRed() <= scale) {
          curPixel.changeRed(0);
        } else {
          curPixel.changeRed(curPixel.getRed() - scale);
        }

        if (curPixel.getGreen() <= scale) {
          curPixel.changeGreen(0);
        } else {
          curPixel.changeGreen(curPixel.getGreen() - scale);
        }

        if (curPixel.getBlue() <= scale) {
          curPixel.changeBlue(0);
        } else {
          curPixel.changeBlue(curPixel.getBlue() - scale);
        }
      }
    }

    images.put(destination, curImage);
  }

  /**
   * Creates a copy of a given Image.
   * @param old original image to be copied
   * @return a newly copied image
   */
  private Image copyImage(Image old) {
    Pixel[][] pixels = this.copyPixels(old);

    Image newImage = new GenericImage(old.getWidth(), old.getHeight(), pixels);
    return newImage;
  }

  private Pixel[][] copyPixels(Image old) {
    Pixel[][] pixels = new Pixel[old.getHeight()][old.getWidth()];
    for (int i = 0; i < old.getHeight(); i++) {
      for (int j = 0; j < old.getWidth(); j++) {
        Pixel oldPixel = old.getPixelAt(i, j);
        pixels[i][j] = new Pixel(oldPixel.getRed(), oldPixel.getGreen(), oldPixel.getBlue());
      }
    }
    return pixels;
  }

  protected void checkFile(String filename) throws IllegalArgumentException {
    if (!images.containsKey(filename)) {
      throw new IllegalArgumentException("This file has not been loaded yet.");
    }
  }

  @Override
  public void blur(String filename, String destination) {
    Image curImage = startVisualize(filename);

    double sumRed = 0;
    double sumGreen = 0;
    double sumBlue = 0;


    for (int i = 0; i < curImage.getHeight(); i++) {
      for (int j = 0; j < curImage.getWidth(); j++) {
        sumRed = 0;
        sumGreen = 0;
        sumBlue = 0;

        sumRed += 1.0 * curImage.getPixelAt(i, j).getRed() * (1.0 / 4.0);
        sumGreen += 1.0 * curImage.getPixelAt(i, j).getGreen() * (1.0 / 4.0);
        sumBlue += 1.0 * curImage.getPixelAt(i, j).getBlue() * (1.0 / 4.0);
        if (i != 0) {
          sumRed += 1.0 * curImage.getPixelAt(i - 1, j).getRed() * (1.0 / 8.0);
          sumGreen += 1.0 * curImage.getPixelAt(i - 1, j).getGreen() * (1.0 / 8.0);
          sumBlue += 1.0 * curImage.getPixelAt(i - 1, j).getBlue() * (1.0 / 8.0);

          if (j != 0) {
            sumRed += 1.0 * curImage.getPixelAt(i - 1, j - 1).getRed() * (1.0 / 16.0);
            sumGreen += 1.0 * curImage.getPixelAt(i - 1, j - 1).getGreen() * (1.0 / 16.0);
            sumBlue += 1.0 * curImage.getPixelAt(i - 1, j - 1).getBlue() * (1.0 / 16.0);
          }

          if (j != curImage.getWidth() - 1) {
            sumRed += 1.0 * curImage.getPixelAt(i - 1, j + 1).getRed() * (1.0 / 16.0);
            sumGreen += 1.0 * curImage.getPixelAt(i - 1, j + 1).getGreen() * (1.0 / 16.0);
            sumBlue += 1.0 * curImage.getPixelAt(i - 1, j + 1).getBlue() * (1.0 / 16.0);
          }
        }

        if (j != 0) {
          sumRed += 1.0 * curImage.getPixelAt(i, j - 1).getRed() * (1.0 / 8.0);
          sumGreen += 1.0 * curImage.getPixelAt(i, j - 1).getGreen() * (1.0 / 8.0);
          sumBlue += 1.0 * curImage.getPixelAt(i, j - 1).getBlue() * (1.0 / 8.0);
        }

        if (j != curImage.getWidth() - 1) {
          sumRed += 1.0 * curImage.getPixelAt(i, j + 1).getRed() * (1.0 / 8.0);
          sumGreen += 1.0 * curImage.getPixelAt(i, j + 1).getGreen() * (1.0 / 8.0);
          sumBlue += 1.0 * curImage.getPixelAt( i, j + 1).getBlue() * (1.0 / 8.0);
        }

        if (i != curImage.getHeight() - 1) {
          sumRed += 1.0 * curImage.getPixelAt(i + 1, j).getRed() * (1.0 / 8.0);
          sumGreen += 1.0 * curImage.getPixelAt(i + 1, j).getGreen() * (1.0 / 8.0);
          sumBlue += 1.0 * curImage.getPixelAt( i + 1, j).getBlue() * (1.0 / 8.0);

          if (j != 0) {
            sumRed += 1.0 * curImage.getPixelAt(i + 1, j - 1).getRed() * (1.0 / 16.0);
            sumGreen += 1.0 * curImage.getPixelAt(i + 1, j - 1).getGreen() * (1.0 / 16.0);
            sumBlue += 1.0 * curImage.getPixelAt(i + 1, j - 1).getBlue() * (1.0 / 16.0);
          }

          if (j != curImage.getWidth() - 1) {
            sumRed += 1.0 * curImage.getPixelAt(i + 1, j + 1).getRed() * (1.0 / 16.0);
            sumGreen += 1.0 * curImage.getPixelAt(i + 1, j + 1).getGreen() * (1.0 / 16.0);
            sumBlue += 1.0 * curImage.getPixelAt( i + 1, j + 1).getBlue() * (1.0 / 16.0);
          }
        }

        curImage.getPixelAt(i, j).changeRed((int) sumRed);
        curImage.getPixelAt(i, j).changeGreen((int) sumGreen);
        curImage.getPixelAt(i, j).changeBlue((int) sumBlue);
      }
    }

    images.put(destination, curImage);

  }

  @Override
  public void sharpen(String filename, String destination) {
    Image curImage = startVisualize(filename);

    double sumRed = 0;
    double sumGreen = 0;
    double sumBlue = 0;

    for (int i = 0; i < curImage.getHeight(); i++) {
      for (int j = 0; j < curImage.getWidth(); j++) {
        sumRed = 0;
        sumGreen = 0;
        sumBlue = 0;

        sumRed += 1.0 * curImage.getPixelAt(i, j).getRed();
        sumGreen += 1.0 * curImage.getPixelAt(i, j).getGreen();
        sumBlue += 1.0 * curImage.getPixelAt(i, j).getBlue();
        if (j >= 1) {
          sumRed += 1.0 * curImage.getPixelAt(i, j - 1).getRed() * (1.0 / 4.0);
          sumGreen += 1.0 * curImage.getPixelAt(i, j - 1).getGreen() * (1.0 / 4.0);
          sumBlue += 1.0 * curImage.getPixelAt( i, j - 1).getBlue() * (1.0 / 4.0);
        }

        if (j >= 2) {
          sumRed += 1.0 * curImage.getPixelAt(i, j - 2).getRed() * (-1.0 / 8.0);
          sumGreen += 1.0 * curImage.getPixelAt(i, j - 2).getGreen() * (-1.0 / 8.0);
          sumBlue += 1.0 * curImage.getPixelAt( i, j - 2).getBlue() * (-1.0 / 8.0);
        }

        if (j < curImage.getWidth() - 1) {
          sumRed += 1.0 * curImage.getPixelAt(i, j + 1).getRed() * (1.0 / 4.0);
          sumGreen += 1.0 * curImage.getPixelAt(i, j + 1).getGreen() * (1.0 / 4.0);
          sumBlue += 1.0 * curImage.getPixelAt( i, j + 1).getBlue() * (1.0 / 4.0);
        }

        if (j < curImage.getWidth() - 2) {
          sumRed += 1.0 * curImage.getPixelAt(i, j + 2).getRed() * (-1.0 / 8.0);
          sumGreen += 1.0 * curImage.getPixelAt(i, j + 2).getGreen() * (-1.0 / 8.0);
          sumBlue += 1.0 * curImage.getPixelAt( i, j + 2).getBlue() * (-1.0 / 8.0);
        }

        if (i >= 1) {
          sumRed += 1.0 * curImage.getPixelAt(i - 1, j).getRed() * (1.0 / 4.0);
          sumGreen += 1.0 * curImage.getPixelAt(i - 1, j).getGreen() * (1.0 / 4.0);
          sumBlue += 1.0 * curImage.getPixelAt( i - 1, j).getBlue() * (1.0 / 4.0);

          if (j >= 1) {
            sumRed += 1.0 * curImage.getPixelAt(i - 1, j - 1).getRed() * (1.0 / 4.0);
            sumGreen += 1.0 * curImage.getPixelAt(i - 1, j - 1).getGreen() * (1.0 / 4.0);
            sumBlue += 1.0 * curImage.getPixelAt( i - 1, j - 1).getBlue() * (1.0 / 4.0);
          }

          if (j >= 2) {
            sumRed += 1.0 * curImage.getPixelAt(i - 1, j - 2).getRed() * (-1.0 / 8.0);
            sumGreen += 1.0 * curImage.getPixelAt(i - 1, j - 2).getGreen() * (-1.0 / 8.0);
            sumBlue += 1.0 * curImage.getPixelAt( i - 1, j - 2).getBlue() * (-1.0 / 8.0);
          }

          if (j < curImage.getWidth() - 1) {
            sumRed += 1.0 * curImage.getPixelAt(i - 1, j + 1).getRed() * (1.0 / 4.0);
            sumGreen += 1.0 * curImage.getPixelAt(i - 1, j + 1).getGreen() * (1.0 / 4.0);
            sumBlue += 1.0 * curImage.getPixelAt( i - 1, j + 1).getBlue() * (1.0 / 4.0);
          }

          if (j < curImage.getWidth() - 2) {
            sumRed += 1.0 * curImage.getPixelAt(i - 1, j + 2).getRed() * (-1.0 / 8.0);
            sumGreen += 1.0 * curImage.getPixelAt(i - 1, j + 2).getGreen() * (-1.0 / 8.0);
            sumBlue += 1.0 * curImage.getPixelAt( i - 1, j + 2).getBlue() * (-1.0 / 8.0);
          }
        }

        if (i >= 2) {
          sumRed += 1.0 * curImage.getPixelAt(i - 2, j).getRed() * (-1.0 / 8.0);
          sumGreen += 1.0 * curImage.getPixelAt(i - 2, j).getGreen() * (-1.0 / 8.0);
          sumBlue += 1.0 * curImage.getPixelAt( i - 2, j).getBlue() * (-1.0 / 8.0);

          if (j >= 1) {
            sumRed += 1.0 * curImage.getPixelAt(i - 2, j - 1).getRed() * (-1.0 / 8.0);
            sumGreen += 1.0 * curImage.getPixelAt(i - 2, j - 1).getGreen() * (-1.0 / 8.0);
            sumBlue += 1.0 * curImage.getPixelAt( i - 2, j - 1).getBlue() * (-1.0 / 8.0);
          }

          if (j >= 2) {
            sumRed += 1.0 * curImage.getPixelAt(i - 2, j - 2).getRed() * (-1.0 / 8.0);
            sumGreen += 1.0 * curImage.getPixelAt(i - 2, j - 2).getGreen() * (-1.0 / 8.0);
            sumBlue += 1.0 * curImage.getPixelAt( i - 2, j - 2).getBlue() * (-1.0 / 8.0);
          }

          if (j < curImage.getWidth() - 1) {
            sumRed += 1.0 * curImage.getPixelAt(i - 2, j + 1).getRed() * (-1.0 / 8.0);
            sumGreen += 1.0 * curImage.getPixelAt(i - 2, j + 1).getGreen() * (-1.0 / 8.0);
            sumBlue += 1.0 * curImage.getPixelAt( i - 2, j + 1).getBlue() * (-1.0 / 8.0);
          }

          if (j < curImage.getWidth() - 2) {
            sumRed += 1.0 * curImage.getPixelAt(i - 2, j + 2).getRed() * (-1.0 / 8.0);
            sumGreen += 1.0 * curImage.getPixelAt(i - 2, j + 2).getGreen() * (-1.0 / 8.0);
            sumBlue += 1.0 * curImage.getPixelAt( i - 2, j + 2).getBlue() * (-1.0 / 8.0);
          }
        }

        if (i < curImage.getHeight() - 1) {
          sumRed += 1.0 * curImage.getPixelAt(i + 1, j).getRed() * (1.0 / 4.0);
          sumGreen += 1.0 * curImage.getPixelAt(i + 1, j).getGreen() * (1.0 / 4.0);
          sumBlue += 1.0 * curImage.getPixelAt( i + 1, j).getBlue() * (1.0 / 4.0);

          if (j >= 1) {
            sumRed += 1.0 * curImage.getPixelAt(i + 1, j - 1).getRed() * (1.0 / 4.0);
            sumGreen += 1.0 * curImage.getPixelAt(i + 1, j - 1).getGreen() * (1.0 / 4.0);
            sumBlue += 1.0 * curImage.getPixelAt( i + 1, j - 1).getBlue() * (1.0 / 4.0);
          }

          if (j >= 2) {
            sumRed += 1.0 * curImage.getPixelAt(i + 1, j - 2).getRed() * (-1.0 / 8.0);
            sumGreen += 1.0 * curImage.getPixelAt(i + 1, j - 2).getGreen() * (-1.0 / 8.0);
            sumBlue += 1.0 * curImage.getPixelAt( i + 1, j - 2).getBlue() * (-1.0 / 8.0);
          }

          if (j < curImage.getWidth() - 1) {
            sumRed += 1.0 * curImage.getPixelAt(i + 1, j + 1).getRed() * (1.0 / 4.0);
            sumGreen += 1.0 * curImage.getPixelAt(i + 1, j + 1).getGreen() * (1.0 / 4.0);
            sumBlue += 1.0 * curImage.getPixelAt( i + 1, j + 1).getBlue() * (1.0 / 4.0);
          }

          if (j < curImage.getWidth() - 2) {
            sumRed += 1.0 * curImage.getPixelAt(i + 1, j + 2).getRed() * (-1.0 / 8.0);
            sumGreen += 1.0 * curImage.getPixelAt(i + 1, j + 2).getGreen() * (-1.0 / 8.0);
            sumBlue += 1.0 * curImage.getPixelAt( i + 1, j + 2).getBlue() * (-1.0 / 8.0);
          }
        }

        if (i < curImage.getHeight() - 2) {
          sumRed += 1.0 * curImage.getPixelAt(i + 2, j).getRed() * (-1.0 / 8.0);
          sumGreen += 1.0 * curImage.getPixelAt(i + 2, j).getGreen() * (-1.0 / 8.0);
          sumBlue += 1.0 * curImage.getPixelAt( i + 2, j).getBlue() * (-1.0 / 8.0);

          if (j >= 1) {
            sumRed += 1.0 * curImage.getPixelAt(i + 2, j - 1).getRed() * (-1.0 / 8.0);
            sumGreen += 1.0 * curImage.getPixelAt(i + 2, j - 1).getGreen() * (-1.0 / 8.0);
            sumBlue += 1.0 * curImage.getPixelAt( i + 2, j - 1).getBlue() * (-1.0 / 8.0);
          }

          if (j >= 2) {
            sumRed += 1.0 * curImage.getPixelAt(i + 2, j - 2).getRed() * (-1.0 / 8.0);
            sumGreen += 1.0 * curImage.getPixelAt(i + 2, j - 2).getGreen() * (-1.0 / 8.0);
            sumBlue += 1.0 * curImage.getPixelAt( i + 2, j - 2).getBlue() * (-1.0 / 8.0);
          }

          if (j < curImage.getWidth() - 1) {
            sumRed += 1.0 * curImage.getPixelAt(i + 2, j + 1).getRed() * (-1.0 / 8.0);
            sumGreen += 1.0 * curImage.getPixelAt(i + 2, j + 1).getGreen() * (-1.0 / 8.0);
            sumBlue += 1.0 * curImage.getPixelAt( i + 2, j + 1).getBlue() * (-1.0 / 8.0);
          }

          if (j < curImage.getWidth() - 2) {
            sumRed += 1.0 * curImage.getPixelAt(i + 2, j + 2).getRed() * (-1.0 / 8.0);
            sumGreen += 1.0 * curImage.getPixelAt(i + 2, j + 2).getGreen() * (-1.0 / 8.0);
            sumBlue += 1.0 * curImage.getPixelAt( i + 2, j + 2).getBlue() * (-1.0 / 8.0);
          }
        }

        curImage.getPixelAt(i, j).changeRed((int) sumRed);
        curImage.getPixelAt(i, j).changeGreen((int) sumGreen);
        curImage.getPixelAt(i, j).changeBlue((int) sumBlue);
      }
    }

    images.put(destination, curImage);
  }

  private void colorTransformation(double[][] filter, String filename, String destination) {
    Image curImage = startVisualize(filename);

    int newRed;
    int newGreen;
    int newBlue;

    Pixel curPixel;

    for (int i = 0; i < curImage.getHeight(); i++) {
      for (int j = 0; j < curImage.getWidth(); j++) {
        curPixel = curImage.getPixelAt(i, j);
        newRed = (int) (filter[0][0] * curPixel.getRed() + filter[0][1] * curPixel.getGreen() +
                filter[0][2] * curPixel.getBlue());
        newGreen = (int) (filter[1][0] * curPixel.getRed() + filter[1][1] * curPixel.getGreen() +
                filter[1][2] * curPixel.getBlue());
        newBlue = (int) (filter[2][0] * curPixel.getRed() + filter[2][1] * curPixel.getGreen() +
                filter[2][2] * curPixel.getBlue());

        curImage.getPixelAt(i, j).changeRed(newRed);
        curImage.getPixelAt(i, j).changeGreen(newGreen);
        curImage.getPixelAt(i, j).changeBlue(newBlue);
      }
    }

    images.put(destination, curImage);
  }

  @Override
  public void visualizeSepia(String filename, String destination) {

    double[][] sepiaFilter = new double[3][3];
    sepiaFilter[0][0] = 0.393;
    sepiaFilter[0][1] = 0.769;
    sepiaFilter[0][2] = 0.189;
    sepiaFilter[1][0] = 0.349;
    sepiaFilter[1][1] = 0.686;
    sepiaFilter[1][2] = 0.168;
    sepiaFilter[2][0] = 0.272;
    sepiaFilter[2][1] = 0.534;
    sepiaFilter[2][2] = 0.131;

    colorTransformation(sepiaFilter, filename, destination);

  }

  private HashMap<Integer, Integer> initHistogram() {
    HashMap<Integer, Integer> histogram = new HashMap<Integer, Integer>();

    for (int i = 0; i < 256; i++) {
      histogram.put(i, 0);
    }

    return histogram;
  }

  @Override
  public HashMap<Integer, Integer> calculateRedHistogram(String filename, String destination) {
    HashMap<Integer, Integer> histogram = initHistogram();
    Image curImage = startVisualize(filename);
    Pixel curPixel;
    int redVal;

    for (int i = 0; i < curImage.getHeight(); i++) {
      for (int j = 0; j < curImage.getWidth(); j++) {
        curPixel = curImage.getPixelAt(i, j);
        redVal = curPixel.getRed();
        int oldFrequency = histogram.get(redVal);
        histogram.put(redVal, oldFrequency + 1);
      }
    }
    return histogram;
  }

  @Override
  public HashMap<Integer, Integer> calculateGreenHistogram(String filename, String destination) {
    HashMap<Integer, Integer> histogram = initHistogram();
    Image curImage = startVisualize(filename);
    Pixel curPixel;
    int greenVal;

    for (int i = 0; i < curImage.getHeight(); i++) {
      for (int j = 0; j < curImage.getWidth(); j++) {
        curPixel = curImage.getPixelAt(i, j);
        greenVal = curPixel.getGreen();
        int oldFrequency = histogram.get(greenVal);
        histogram.put(greenVal, oldFrequency + 1);
      }
    }
    return histogram;
  }

  @Override
  public HashMap<Integer, Integer> calculateBlueHistogram(String filename, String destination) {
    HashMap<Integer, Integer> histogram = initHistogram();
    Image curImage = startVisualize(filename);
    Pixel curPixel;
    int blueVal;

    for (int i = 0; i < curImage.getHeight(); i++) {
      for (int j = 0; j < curImage.getWidth(); j++) {
        curPixel = curImage.getPixelAt(i, j);
        blueVal = curPixel.getBlue();
        int oldFrequency = histogram.get(blueVal);
        histogram.put(blueVal, oldFrequency + 1);
      }
    }
    return histogram;
  }

  @Override
  public HashMap<Integer, Integer> calculateIntensityHistogram(
          String filename, String destination) {
    HashMap<Integer, Integer> histogram = initHistogram();
    Image curImage = startVisualize(filename);
    Pixel curPixel;
    int averageVal;

    for (int i = 0; i < curImage.getHeight(); i++) {
      for (int j = 0; j < curImage.getWidth(); j++) {
        curPixel = curImage.getPixelAt(i, j);
        averageVal = (curPixel.getRed() + curPixel.getGreen() + curPixel.getBlue()) / 3;
        int oldFrequency = histogram.get(averageVal);
        histogram.put(averageVal, oldFrequency + 1);
      }
    }
    return histogram;
  }


}
