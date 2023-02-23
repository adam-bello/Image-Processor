import java.util.HashMap;

import controller.Features;
import model.Pixel;
import view.ImageEditorGUI;

/**
 * Class representing a mock GUIView. This class is used only for testing and adds to the log
 * as its methods are called.
 */
public class MockGUIView implements ImageEditorGUI {
  private StringBuilder log;

  public MockGUIView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void renderMessage(String message) {
    log.append("message: " + message);
  }

  @Override
  public String openFile() {
    return "pathname";
  }

  @Override
  public String chooseFileName() {
    return "curFile";
  }

  @Override
  public void addImage(String filename, Features features) {
    log.append("image added: " + filename);
  }

  @Override
  public String getScale() {
    return "0";
  }

  @Override
  public void updateImage(int width, int height, Pixel[][] pixels) {
    log.append("image updated ");
  }

  @Override
  public void updateHistogram(HashMap<Integer, Integer> redHistogram,
                              HashMap<Integer, Integer> greenHistogram,
                              HashMap<Integer, Integer> blueHistogram,
                              HashMap<Integer, Integer> intensityHistogram) {
    log.append("histogram updated ");
  }

  @Override
  public String getFileType() {
    return "filetype ";
  }

  @Override
  public String savePath() {
    return "pathname ";
  }

  @Override
  public void addFeatures(Features features) {
    log.append("features added ");
  }
}
