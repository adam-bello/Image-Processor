import java.util.HashMap;

import model.GenericImage;
import model.Image;
import model.ImageEditorModelImpl;
import model.Pixel;

/**
 * Mock for the PPMModel for testing the controller. Stores
 * given commands to a log to ensure that each command is processed
 * correctly.
 */
public class MockImageEditModel extends ImageEditorModelImpl {
  private final StringBuilder log;

  public MockImageEditModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public Image getImage(String key) {
    Pixel[][] pixels = new Pixel[1][1];
    pixels[0][0] = new Pixel(10, 10, 10);
    return new GenericImage(1, 1, pixels);
  }

  public void load(String pathname, String filename, String filetype) {
    log.append("pathname: " + pathname + " filename: " + filename);
  }

  public void save(String pathname, String filename, String filetype) {
    log.append("pathname: " + pathname + " filename: " + filename + " filetype: " + filetype);
  }

  public void visualizeRed(String filename, String destination) {
    log.append("filename: " + filename + " destination: " + destination);
  }

  public void visualizeGreen(String filename, String destination) {
    log.append("filename: " + filename + " destination: " + destination);
  }

  public void visualizeBlue(String filename, String destination) {
    log.append("filename: " + filename + " destination: " + destination);
  }

  public void visualizeValue(String filename, String destination) {
    log.append("filename: " + filename + " destination: " + destination);
  }

  public void visualizeIntensity(String filename, String destination) {
    log.append("filename: " + filename + " destination: " + destination);
  }

  public void visualizeLuma(String filename, String destination) {
    log.append("filename: " + filename + " destination: " + destination);
  }

  public void flipHorizontal(String filename, String destination) {
    log.append("filename: " + filename + " destination: " + destination);
  }

  public void flipVertical(String filename, String destination) {
    log.append("filename: " + filename + " destination: " + destination);
  }

  public void brighten(int scale, String filename, String destination) {
    log.append("scale: " + scale + " filename: " + filename + " destination: " + destination);
  }

  public void darken(int scale, String filename, String destination) {
    log.append("scale: " + scale + " filename: " + filename + " destination: " + destination);
  }

  public void blur(String filename, String destination) {
    log.append("filename: " + filename + " destination: " + destination);
  }

  public void sharpen(String filename, String destination) {
    log.append("filename: " + filename + " destination: " + destination);
  }

  public void visualizeSepia(String filename, String destination) {
    log.append("filename: " + filename + " destination: " + destination);
  }

  public HashMap<Integer, Integer> calculateRedHistogram(String filename, String destination) {
    return new HashMap<Integer, Integer>();
  }

  public HashMap<Integer, Integer> calculateGreenHistogram(String filename, String destination) {
    return new HashMap<Integer, Integer>();
  }

  public HashMap<Integer, Integer> calculateBlueHistogram(String filename, String destination) {
    return new HashMap<Integer, Integer>();
  }

  public HashMap<Integer, Integer> calculateIntensityHistogram(
          String filename, String destination) {
    return new HashMap<Integer, Integer>();
  }

}
