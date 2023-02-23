package view;

import java.util.HashMap;

import controller.Features;
import model.Pixel;

/**
 * An interface containing all the methods used for correctly displaying the GUI and interacting
 * with the controller.
 */
public interface ImageEditorGUI extends ImageEditorView {

  void renderMessage(String message);

  String openFile();

  String chooseFileName();

  void addImage(String filename, Features features);

  String getScale();

  void updateImage(int width, int height, Pixel[][] pixels);

  public void updateHistogram(HashMap<Integer, Integer> redHistogram,
                              HashMap<Integer, Integer> greenHistogram,
                              HashMap<Integer, Integer> blueHistogram,
                              HashMap<Integer, Integer> intensityHistogram);

  String getFileType();

  String savePath();

  void addFeatures(Features features);
}
