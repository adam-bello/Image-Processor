import org.junit.Before;
import org.junit.Test;

import controller.commands.Blur;
import controller.commands.Brighten;
import controller.commands.Darken;
import controller.commands.FlipHorizontal;
import controller.commands.FlipVertical;
import controller.commands.ImageEditCommand;
import controller.commands.Load;
import controller.commands.Save;
import controller.commands.Sharpen;
import controller.commands.VisualizeBlue;
import controller.commands.VisualizeGreen;
import controller.commands.VisualizeIntensity;
import controller.commands.VisualizeLuma;
import controller.commands.VisualizeRed;
import controller.commands.VisualizeSepia;
import controller.commands.VisualizeValue;
import view.ImageEditorTextView;
import view.ImageEditorView;

import static org.junit.Assert.assertEquals;

/**
 * A class used for testing the different ImageEditCommand implementations. Each test is for
 * a different command and ensures that information is being accurately passed to the model
 * when the command calls execute.
 */
public class ImageEditCommandTest {

  private MockImageEditModel mock;
  private StringBuilder log;
  private ImageEditorView view;

  @Before
  public void setup() {
    log = new StringBuilder();
    mock = new MockImageEditModel(log);
    view = new ImageEditorTextView(new StringBuilder());
  }

  @Test
  public void testLoad() {
    ImageEditCommand load = new Load("test.ppm", "test");
    load.execute(mock);

    assertEquals("pathname: test.ppm filename: test", log.toString());
  }

  @Test
  public void testSave() {
    ImageEditCommand save = new Save("test.ppm", "test", "jpg", view);
    save.execute(mock);

    assertEquals("pathname: test.ppm filename: test filetype: jpg", log.toString());
  }

  @Test
  public void testBrighten() {
    ImageEditCommand brighten = new Brighten(10,"test.ppm", "test", view);
    brighten.execute(mock);

    assertEquals("scale: 10 filename: test.ppm destination: test", log.toString());
  }

  @Test
  public void testDarken() {
    ImageEditCommand darken = new Darken(10,"test.ppm", "test", view);
    darken.execute(mock);

    assertEquals("scale: 10 filename: test.ppm destination: test", log.toString());
  }

  @Test
  public void testVisualizeRed() {
    ImageEditCommand vred = new VisualizeRed("test.ppm", "test", view);
    vred.execute(mock);

    assertEquals("filename: test.ppm destination: test", log.toString());
  }

  @Test
  public void testVisualizeGreen() {
    ImageEditCommand vgreen = new VisualizeGreen("test.ppm", "test", view);
    vgreen.execute(mock);

    assertEquals("filename: test.ppm destination: test", log.toString());
  }

  @Test
  public void testVisualizeBlue() {
    ImageEditCommand vblue = new VisualizeBlue("test.ppm", "test", view);
    vblue.execute(mock);

    assertEquals("filename: test.ppm destination: test", log.toString());
  }

  @Test
  public void testVisualizeValue() {
    ImageEditCommand value = new VisualizeValue("test.ppm", "test", view);
    value.execute(mock);

    assertEquals("filename: test.ppm destination: test", log.toString());
  }

  @Test
  public void testVisualizeIntensity() {
    ImageEditCommand intensity = new VisualizeIntensity("test.ppm", "test", view);
    intensity.execute(mock);

    assertEquals("filename: test.ppm destination: test", log.toString());
  }

  @Test
  public void testVisualizeLuma() {
    ImageEditCommand luma = new VisualizeLuma("test.ppm", "test", view);
    luma.execute(mock);

    assertEquals("filename: test.ppm destination: test", log.toString());
  }

  @Test
  public void testFlipHorizontal() {
    ImageEditCommand fh = new FlipHorizontal("test.ppm", "test", view);
    fh.execute(mock);

    assertEquals("filename: test.ppm destination: test", log.toString());
  }

  @Test
  public void testFlipVertical() {
    ImageEditCommand fv = new FlipVertical("test.ppm", "test", view);
    fv.execute(mock);

    assertEquals("filename: test.ppm destination: test", log.toString());
  }

  @Test
  public void testBlur() {
    ImageEditCommand blur = new Blur("test.ppm", "test", view);
    blur.execute(mock);

    assertEquals("filename: test.ppm destination: test", log.toString());
  }

  @Test
  public void testSharpen() {
    ImageEditCommand sharpen = new Sharpen("test.ppm", "test", view);
    sharpen.execute(mock);

    assertEquals("filename: test.ppm destination: test", log.toString());
  }

  @Test
  public void testVisualizeSepia() {
    ImageEditCommand vsepia = new VisualizeSepia("test.ppm", "test", view);
    vsepia.execute(mock);

    assertEquals("filename: test.ppm destination: test", log.toString());
  }
}
