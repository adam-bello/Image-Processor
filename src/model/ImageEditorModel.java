package model;

import java.util.HashMap;

/**
 * Represents the model of an image editor with the ability to load, save, and edit
 * different images.
 */
public interface ImageEditorModel {

  Image getImage(String key);

  /**
   * Loads a file from a given pathname and refer to it by a given image name.
   * @param pathname The path to get the file from
   * @param filename The name that the editor will refer to the file as
   */
  void load(String pathname, String filename, String filetype) throws IllegalArgumentException;

  /**
   * Saves the image with the given file name to the specified path.
   * @param pathname The name of the filepath to save the image to
   * @param filename The name of the image in the image editor to save.
   */
  void save(String pathname, String filename, String filetype);

  /**
   * Creates a greyscale image with the red-component of the image with the given name
   * and refers to it by a given file name.
   * @param filename The name of the image file to be edited.
   * @param destination The name to refer the newly edited file.
   */
  void visualizeRed(String filename, String destination);

  /**
   * Creates a greyscale image with the green-component of the image with the given name
   * and refers to it by a given file name.
   * @param filename The name of the image file to be edited.
   * @param destination The name to refer the newly edited file.
   */
  void visualizeGreen(String filename, String destination);

  /**
   * Creates a greyscale image with the blue-component of the image with the given name
   * and refers to it by a given file name.
   * @param filename The name of the image file to be edited.
   * @param destination The name to refer the newly edited file.
   */
  void visualizeBlue(String filename, String destination);

  /**
   * Creates a greyscale image with the maximum color of each pixel with the given name
   * and refers to it by a given file name.
   * @param filename The name of the image file to be edited.
   * @param destination The name to refer the newly edited file.
   */
  void visualizeValue(String filename, String destination);

  /**
   * Creates a greyscale image with the average color of each pixel with the given name
   * and refers to it by a given file name.
   * @param filename The name of the image file to be edited.
   * @param destination The name to refer the newly edited file.
   */
  void visualizeIntensity(String filename, String destination);

  /**
   * Creates a greyscale image with the luma color value of each pixel with the given name
   * and refers to it by a given file name.
   * @param filename The name of the image file to be edited.
   * @param destination The name to refer the newly edited file.
   */
  void visualizeLuma(String filename, String destination);

  /**
   * Creates an image flipped horizontally with the given file name and refers to it by the given
   * destination.
   * @param filename The name of the file to be edited
   * @param destination The name to refer the newly edited file
   */
  void flipHorizontal(String filename, String destination);

  /**
   * Creates an image flipped vertically with the given file name and refers to it by the given
   * destination.
   * @param filename The name of the file to be edited
   * @param destination The name to refer the newly edited file
   */
  void flipVertical(String filename, String destination);

  /**
   * Creates an image brightened by the given scale with the given file name
   * and refers to it by the given destination.
   * @param filename The name of the file to be edited
   * @param destination The name to refer the newly edited file
   */
  void brighten(int scale, String filename, String destination);

  /**
   * Creates an image darkened by the given scale with the given file name
   * and refers to it by the given destination.
   * @param filename The name of the file to be edited
   * @param destination The name to refer the newly edited file
   */
  void darken(int scale, String filename, String destination);

  void blur(String filename, String destination);

  void sharpen(String filename, String destination);

  void visualizeSepia(String filename, String destination);

  HashMap<Integer, Integer> calculateRedHistogram(String filename, String destination);

  HashMap<Integer, Integer> calculateGreenHistogram(String filename, String destination);

  HashMap<Integer, Integer> calculateBlueHistogram(String filename, String destination);

  HashMap<Integer, Integer> calculateIntensityHistogram(String filename, String destination);
}
