import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import controller.ImageEditorController;
import controller.ImageEditorControllerImpl;
import model.ImageEditorModel;
import model.ImageEditorModelImpl;
import view.ImageEditorTextView;
import view.ImageEditorView;

import static org.junit.Assert.assertEquals;

/**
 * Represents a class for testing the controller of the program. This class tests that information
 * is accurately sent to the model and errors are correctly thrown or displayed by the view.
 */
public class ImageEditorControllerTest {
  private ImageEditorModel model;
  private ImageEditorView view;
  private ImageEditorController controller;
  private Appendable ap;
  private Readable readable;
  private MockImageEditModel mock;
  private StringBuilder log;

  @Before
  public void setup() {
    model = new ImageEditorModelImpl();
    ap = new StringBuilder();
    view = new ImageEditorTextView(ap);
    log = new StringBuilder();
    mock = new MockImageEditModel(log);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullModel() {
    new ImageEditorControllerImpl(null, view, readable);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullView() {
    new ImageEditorControllerImpl(model, null, readable);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullInput() {
    new ImageEditorControllerImpl(model, view, null);
  }

  @Test
  public void testLoad() {
    readable = new StringReader("load example.txt example");
    controller = new ImageEditorControllerImpl(mock, view, readable);
    controller.start();

    assertEquals("pathname: example.txt filename: example", log.toString());
  }

  @Test
  public void testSave() {
    readable = new StringReader("save example.txt example ppm");
    controller = new ImageEditorControllerImpl(mock, view, readable);
    controller.start();

    assertEquals("pathname: example.txt filename: example filetype: ppm", log.toString());
  }

  @Test
  public void testVisualizeRed() {
    readable = new StringReader("visualize-red example.txt example");
    controller = new ImageEditorControllerImpl(mock, view, readable);
    controller.start();

    assertEquals("filename: example.txt destination: example", log.toString());
  }

  @Test
  public void testVisualizeGreen() {
    readable = new StringReader("visualize-green example.txt example");
    controller = new ImageEditorControllerImpl(mock, view, readable);
    controller.start();

    assertEquals("filename: example.txt destination: example", log.toString());
  }

  @Test
  public void testVisualizeBlue() {
    readable = new StringReader("visualize-blue example.txt example");
    controller = new ImageEditorControllerImpl(mock, view, readable);
    controller.start();

    assertEquals("filename: example.txt destination: example", log.toString());
  }

  @Test
  public void testVisualizeValue() {
    readable = new StringReader("visualize-value example.txt example");
    controller = new ImageEditorControllerImpl(mock, view, readable);
    controller.start();

    assertEquals("filename: example.txt destination: example", log.toString());
  }

  @Test
  public void testVisualizeIntensity() {
    readable = new StringReader("visualize-intensity example.txt example");
    controller = new ImageEditorControllerImpl(mock, view, readable);
    controller.start();

    assertEquals("filename: example.txt destination: example", log.toString());
  }

  @Test
  public void testVisualizeLuma() {
    readable = new StringReader("visualize-luma example.txt example");
    controller = new ImageEditorControllerImpl(mock, view, readable);
    controller.start();

    assertEquals("filename: example.txt destination: example", log.toString());
  }

  @Test
  public void testFlipHorizontal() {
    readable = new StringReader("flip-horizontal example.txt example");
    controller = new ImageEditorControllerImpl(mock, view, readable);
    controller.start();

    assertEquals("filename: example.txt destination: example", log.toString());
  }

  @Test
  public void testFlipVertical() {
    readable = new StringReader("flip-vertical example.txt example");
    controller = new ImageEditorControllerImpl(mock, view, readable);
    controller.start();

    assertEquals("filename: example.txt destination: example", log.toString());
  }

  @Test
  public void testBrighten() {
    readable = new StringReader("brighten 10 example.txt example");
    controller = new ImageEditorControllerImpl(mock, view, readable);
    controller.start();

    assertEquals("scale: 10 filename: example.txt destination: example", log.toString());
  }

  @Test
  public void testDarken() {
    readable = new StringReader("darken 10 example.txt example");
    controller = new ImageEditorControllerImpl(mock, view, readable);
    controller.start();

    assertEquals("scale: 10 filename: example.txt destination: example", log.toString());
  }

  @Test
  public void testBlur() {
    readable = new StringReader("blur example.txt example");
    controller = new ImageEditorControllerImpl(mock, view, readable);
    controller.start();

    assertEquals("filename: example.txt destination: example", log.toString());
  }

  @Test
  public void testSharpen() {
    readable = new StringReader("sharpen example.txt example");
    controller = new ImageEditorControllerImpl(mock, view, readable);
    controller.start();

    assertEquals("filename: example.txt destination: example", log.toString());
  }

  @Test
  public void testVisualizeSepia() {
    readable = new StringReader("visualize-sepia example.txt example");
    controller = new ImageEditorControllerImpl(mock, view, readable);
    controller.start();

    assertEquals("filename: example.txt destination: example", log.toString());
  }

  @Test
  public void testInvalidCommand() {
    readable = new StringReader("load Test.ppm example flip-diagonal");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();

    assertEquals("Unknown Command. Try again!\n", ap.toString());
  }

  @Test
  public void testNotEnoughInputsLoad() {
    readable = new StringReader("load example.txt");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();
    assertEquals("Illegal arguments for load\n", ap.toString());
  }

  @Test
  public void testNotEnoughInputsVisualizeRed() {
    readable = new StringReader("load Test.ppm example visualize-red example");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();
    assertEquals("Illegal arguments for visualize-red\n", ap.toString());
  }

  @Test
  public void testNotEnoughInputsVisualizeGreen() {
    readable = new StringReader("load Test.ppm example visualize-green example");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();
    assertEquals("Illegal arguments for visualize-green\n", ap.toString());
  }

  @Test
  public void testNotEnoughInputsVisualizeBlue() {
    readable = new StringReader("load Test.ppm example visualize-blue example");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();
    assertEquals("Illegal arguments for visualize-blue\n", ap.toString());
  }

  @Test
  public void testNotEnoughInputsVisualizeValue() {
    readable = new StringReader("load Test.ppm example visualize-value example");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();
    assertEquals("Illegal arguments for visualize-value\n", ap.toString());
  }

  @Test
  public void testNotEnoughInputsVisualizeIntensity() {
    readable = new StringReader("load Test.ppm example visualize-intensity example");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();
    assertEquals("Illegal arguments for visualize-intensity\n", ap.toString());
  }

  @Test
  public void testNotEnoughInputsVisualizeLuma() {
    readable = new StringReader("load Test.ppm example visualize-luma example");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();
    assertEquals("Illegal arguments for visualize-luma\n", ap.toString());
  }

  @Test
  public void testNotEnoughInputsFlipHorizontal() {
    readable = new StringReader("load Test.ppm example flip-horizontal example");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();
    assertEquals("Illegal arguments for flip-horizontal\n", ap.toString());
  }

  @Test
  public void testNotEnoughInputsFlipVertical() {
    readable = new StringReader("load Test.ppm example flip-vertical example");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();
    assertEquals("Illegal arguments for flip-vertical\n", ap.toString());
  }

  @Test
  public void testNotEnoughInputsBrighten() {
    readable = new StringReader("load Test.ppm example brighten 10 example");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();
    assertEquals("Illegal arguments for brighten\n", ap.toString());
  }

  @Test
  public void testNotEnoughInputsDarken() {
    readable = new StringReader("load Test.ppm example darken 10 example");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();
    assertEquals("Illegal arguments for darken\n", ap.toString());
  }

  @Test
  public void testBrightenImageNotLoaded() {
    readable = new StringReader("brighten 10 koala koala-brighter");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();
    assertEquals("This file has not been loaded yet.\n", ap.toString());
  }

  @Test
  public void testDarkenImageNotLoaded() {
    readable = new StringReader("darken 10 koala koala-brighter");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();
    assertEquals("This file has not been loaded yet.\n", ap.toString());
  }

  @Test
  public void testVisualizeRedImageNotLoaded() {
    readable = new StringReader("visualize-red koala koala-brighter");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();
    assertEquals("This file has not been loaded yet.\n", ap.toString());
  }

  @Test
  public void testVisualizeGreenImageNotLoaded() {
    readable = new StringReader("visualize-green koala koala-brighter");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();
    assertEquals("This file has not been loaded yet.\n", ap.toString());
  }

  @Test
  public void testVisualizeBlueImageNotLoaded() {
    readable = new StringReader("visualize-blue koala koala-brighter");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();
    assertEquals("This file has not been loaded yet.\n", ap.toString());
  }

  @Test
  public void testVisualizeIntensityImageNotLoaded() {
    readable = new StringReader("visualize-intensity koala koala-brighter");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();
    assertEquals("This file has not been loaded yet.\n", ap.toString());
  }

  @Test
  public void testVisualizeLumaImageNotLoaded() {
    readable = new StringReader("visualize-luma koala koala-brighter");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();
    assertEquals("This file has not been loaded yet.\n", ap.toString());
  }

  @Test
  public void testVisualizeValueImageNotLoaded() {
    readable = new StringReader("visualize-value koala koala-brighter");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();
    assertEquals("This file has not been loaded yet.\n", ap.toString());
  }

  @Test
  public void testFlipHorizontalImageNotLoaded() {
    readable = new StringReader("flip-horizontal koala koala-brighter");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();
    assertEquals("This file has not been loaded yet.\n", ap.toString());
  }

  @Test
  public void testFlipVerticalImageNotLoaded() {
    readable = new StringReader("flip-vertical koala koala-brighter");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();
    assertEquals("This file has not been loaded yet.\n", ap.toString());
  }

  @Test
  public void testSaveImageNotLoaded() {
    readable = new StringReader("save koala koala-brighter jpg");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();
    assertEquals("This file has not been loaded yet.\n", ap.toString());
  }

  @Test
  public void testBrightenWrongArgs() {
    readable = new StringReader("brighten koala 10 koala-brighter");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();

    assertEquals("Illegal arguments for brighten\n" +
            "Unknown Command. Try again!\n" +
            "Unknown Command. Try again!\n" +
            "Unknown Command. Try again!\n", ap.toString());
  }

  @Test
  public void testDarkenWrongArgs() {
    readable = new StringReader("darken koala 10 koala-brighter");
    controller = new ImageEditorControllerImpl(model, view, readable);
    controller.start();

    assertEquals("Illegal arguments for darken\n" +
            "Unknown Command. Try again!\n" +
            "Unknown Command. Try again!\n" +
            "Unknown Command. Try again!\n", ap.toString());
  }

}
