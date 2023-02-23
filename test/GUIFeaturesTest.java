import org.junit.Before;
import org.junit.Test;

import controller.Features;
import controller.GUIController;
import view.ImageEditorGUI;

import static org.junit.Assert.assertEquals;

/**
 * Class for testing the GUI by testing the features it provides.
 */
public class GUIFeaturesTest {
  private Features controller;
  private ImageEditorGUI view;
  private StringBuilder modelLog;
  private StringBuilder viewLog;

  @Before
  public void setup() {
    modelLog = new StringBuilder();
    viewLog = new StringBuilder();
    MockImageEditModel model = new MockImageEditModel(modelLog);
    view = new MockGUIView(viewLog);
    controller = new GUIController(model);
    controller.setView(view);
    controller.setCurFile("curFile");
  }

  @Test
  public void testLoad() {
    controller.load();
    assertEquals("features added image updated histogram updated image updated" +
                    " histogram updated image added: curFile",
            viewLog.toString());
    assertEquals("pathname: pathname filename: curFile", modelLog.toString());
  }

  @Test
  public void testSave() {
    controller.save();
    assertEquals("features added image updated histogram updated ", viewLog.toString());
    assertEquals("pathname: pathname  filename: curFile filetype: filetype ",
            modelLog.toString());
  }

  @Test
  public void testSetView() {
    controller.setView(view);
    assertEquals("features added image updated histogram updated features added ",
            viewLog.toString());
  }

  @Test
  public void testSetCurFile() {
    controller.setCurFile("TEST");
    controller.visualizeRed();
    assertEquals("filename: TEST destination: TEST", modelLog.toString());
  }

  @Test
  public void testVisualizeRed() {
    controller.visualizeRed();
    assertEquals("filename: curFile destination: curFile", modelLog.toString());
  }

  @Test
  public void testVisualizeGreen() {
    controller.visualizeGreen();
    assertEquals("filename: curFile destination: curFile", modelLog.toString());
  }

  @Test
  public void testVisualizeBlue() {
    controller.visualizeBlue();
    assertEquals("filename: curFile destination: curFile", modelLog.toString());
  }

  @Test
  public void testVisualizeValue() {
    controller.visualizeValue();
    assertEquals("filename: curFile destination: curFile", modelLog.toString());
  }

  @Test
  public void testVisualizeIntensity() {
    controller.visualizeIntensity();
    assertEquals("filename: curFile destination: curFile", modelLog.toString());
  }

  @Test
  public void testVisualizeLuma() {
    controller.visualizeLuma();
    assertEquals("filename: curFile destination: curFile", modelLog.toString());
  }

  @Test
  public void testVisualizeSepia() {
    controller.visualizeSepia();
    assertEquals("filename: curFile destination: curFile", modelLog.toString());
  }

  @Test
  public void testBlur() {
    controller.blur();
    assertEquals("filename: curFile destination: curFile", modelLog.toString());
  }

  @Test
  public void testSharpen() {
    controller.sharpen();
    assertEquals("filename: curFile destination: curFile", modelLog.toString());
  }

  @Test
  public void testFlipHorizontal() {
    controller.flipHorizontal();
    assertEquals("filename: curFile destination: curFile", modelLog.toString());
  }

  @Test
  public void testFlipVertical() {
    controller.flipVertical();
    assertEquals("filename: curFile destination: curFile", modelLog.toString());
  }

  @Test
  public void testBrighten() {
    controller.brighten();
    assertEquals("scale: 0 filename: curFile destination: curFile", modelLog.toString());
  }

  @Test
  public void testDarken() {
    controller.darken();
    assertEquals("scale: 0 filename: curFile destination: curFile", modelLog.toString());
  }

}
