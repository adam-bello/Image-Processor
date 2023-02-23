package model;

import model.Pixel;

/**
 * Class to represent a type of image file with a height, width, and
 * maximum color value of all of its contained pixels.
 */
public interface Image {

  /**
   * Gets the height of the image.
   * @return the height of the image as an integer
   */
  int getHeight();

  /**
   * Gets the width of the image.
   * @return the width of the image as an integer
   */
  int getWidth();

  /**
   * Gets the Pixel object located at a given row and column location in the image.
   * @param row the row location of the pixel
   * @param col the height location of the pixel
   * @return the Pixel object at the given location
   */
  Pixel getPixelAt(int row, int col);
}
