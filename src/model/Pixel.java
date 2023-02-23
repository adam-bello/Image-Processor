package model;

/**
 * Represents a pixel with its color represented as an RGB value.
 */
public class Pixel {
  private int red;
  private int green;
  private int blue;

  /**
   * Creates a new pixel with a given Red value, Green value, and Blue value.
   *
   * @param red   red value of the pixel
   * @param green green value of the pixel
   * @param blue  blue value of the pixel
   * @throws IllegalArgumentException if any of the color values are less than 0 or
   *                                  greater than maximum value of 255
   */
  public Pixel(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Invalid Color Value. Must be between 0 and 255");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Gets the red color value of the pixel.
   *
   * @return the red value as an integer
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Gets the green color value of the pixel.
   *
   * @return the green value as an integer
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Gets the blue color value of the pixel.
   *
   * @return the blue value as an integer
   */
  public int getBlue() {
    return this.blue;
  }

  /**
   * Changes the red color component of the pixel to a given value.
   *
   * @param newColor the new color value to change the red component to
   * @throws IllegalArgumentException if the new color value is less than 0 or
   *                                  greater than the maximum color value of 255
   */
  public void changeRed(int newColor) throws IllegalArgumentException {
    if (newColor > 255) {
      red = 255;
    } else if (newColor < 0) {
      red = 0;
    } else {
      red = newColor;
    }
  }

  /**
   * Changes the green color component of the pixel to a given value.
   *
   * @param newColor the new color value to change the green component to
   * @throws IllegalArgumentException if the new color value is less than 0 or
   *                                  greater than the maximum color value of 255
   */
  public void changeGreen(int newColor) throws IllegalArgumentException {
    if (newColor > 255) {
      green = 255;
    } else if (newColor < 0) {
      green = 0;
    } else {
      green = newColor;
    }
  }

  /**
   * Changes the blue color component of the pixel to a given value.
   *
   * @param newColor the new color value to change the blue component to
   * @throws IllegalArgumentException if the new color value is less than 0 or
   *                                  greater than the maximum color value of 255
   */
  public void changeBlue(int newColor) throws IllegalArgumentException {
    if (newColor > 255) {
      blue = 255;
    } else if (newColor < 0) {
      blue = 0;
    } else {
      blue = newColor;
    }
  }
}
