package model;

/**
 * Represents an Image created from a PPM file with its collection of
 * pixels represented as a 2D array as well as a maximum color value of
 * all the pixels in the image.
 */
public class GenericImage implements Image {
  private final Pixel[][] image;
  private final int height;
  private final int width;
  private int maxValue;

  /**
   * Creates a PPM Image with a given width, height, maximum color component value,
   * and the collection of RGB values represented as a 2D array of pixels.
   * @param width the width of the PPM image
   * @param height the height of the PPM image
   * @param image the 2D array of pixels in the PPM image
   * @throws IllegalArgumentException if the maximum color value is less than 0 or greather
   *                                  than 255
   */
  public GenericImage(int width, int height, Pixel[][] image)
          throws IllegalArgumentException {
    if (maxValue < 0 || maxValue > 255) {
      throw new IllegalArgumentException("Illegal maxValue. Must be between 0 and 255");
    }

    if (image == null) {
      throw new IllegalArgumentException("Pixel array cannot be null");
    }
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
    this.image = image;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public Pixel getPixelAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row > this.height - 1 || col < 0 || col > this.width - 1) {
      throw new IllegalArgumentException("Invalid row or column");
    }
    return image[row][col];
  }

}
