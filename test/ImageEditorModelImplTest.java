import org.junit.Test;

import java.util.HashMap;

import model.Image;
import model.ImageEditorModel;
import model.ImageEditorModelImpl;
import model.ImageUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Tests for the PPM model containing tests involving loading, saving, and editing
 * a PPM image.
 */
public class ImageEditorModelImplTest {

  @Test (expected = IllegalArgumentException.class)
  public void testLoadFail() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.visualizeRed("Test-ppm", "Test-Red.ppm");
  }

  @Test
  public void testSavePPM() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "test", "ppm");
    model.load("Test-Save.ppm", "test-save", "ppm");
    model.load("Test-Save-Copy.ppm", "test-save-copy", "ppm");

    model.save("Test-Save.ppm", "test", "ppm");

    for (int i = 0; i < ImageUtil.getHeight("Test.ppm"); i++) {
      for (int j = 0; j < ImageUtil.getWidth("Test.ppm"); j++) {
        assertEquals(ImageUtil.getImagePixels("Test-Save.ppm")[i][j].getRed(),
                ImageUtil.getImagePixels("Test.ppm")[i][j].getRed());
        assertEquals(ImageUtil.getImagePixels("Test-Save.ppm")[i][j].getGreen(),
                ImageUtil.getImagePixels("Test.ppm")[i][j].getGreen());
        assertEquals(ImageUtil.getImagePixels("Test-Save.ppm")[i][j].getBlue(),
                ImageUtil.getImagePixels("Test.ppm")[i][j].getBlue());
      }
    }

    model.save("Test-Save.ppm", "test-save-copy", "ppm");
  }

  @Test
  public void testSaveImage() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("res/Test.jpg", "test-shoe", "jpg");

    model.save("Test-Save-Shoe.png", "test-shoe", "png");

    Image orgImage = ImageUtil.readImage("res/Test.jpg");
    Image newImage = ImageUtil.readImage("Test-Save-Shoe.png");

    for (int i = 0; i < orgImage.getHeight(); i++) {
      for (int j = 0; j < orgImage.getWidth(); j++) {
        assertEquals(orgImage.getPixelAt(i, j).getRed(), newImage.getPixelAt(i, j).getRed());
        assertEquals(orgImage.getPixelAt(i, j).getGreen(), newImage.getPixelAt(i, j).getGreen());
        assertEquals(orgImage.getPixelAt(i, j).getBlue(), newImage.getPixelAt(i, j).getBlue());
      }
    }
  }

  @Test
  public void testPPMToImage() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "test-save", "ppm");

    model.save("Test-Save.png", "test-save", "png");

    Image newImage = ImageUtil.readImage("Test-Save.png");

    for (int i = 0; i < ImageUtil.getHeight("Test.ppm"); i++) {
      for (int j = 0; j < ImageUtil.getWidth("Test.ppm"); j++) {
        assertEquals(ImageUtil.getImagePixels("Test.ppm")[i][j].getRed(),
                newImage.getPixelAt(i, j).getRed());
        assertEquals(ImageUtil.getImagePixels("Test.ppm")[i][j].getGreen(),
                newImage.getPixelAt(i, j).getGreen());
        assertEquals(ImageUtil.getImagePixels("Test.ppm")[i][j].getBlue(),
                newImage.getPixelAt(i, j).getBlue());
      }
    }
  }

  @Test
  public void testImageToPPM() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "test-ppm", "ppm");

    model.save("Test-Save.png", "test-ppm", "png");

    model.load("Test-Save.png", "test-png", "png");

    model.save("Test-Save.ppm", "test-png", "ppm");

    Image newImage = ImageUtil.readImage("Test-Save.png");

    for (int i = 0; i < newImage.getHeight(); i++) {
      for (int j = 0; j < newImage.getWidth(); j++) {
        assertEquals(ImageUtil.getImagePixels("Test-Save.ppm")[i][j].getRed(),
                newImage.getPixelAt(i, j).getRed());
        assertEquals(ImageUtil.getImagePixels("Test-Save.ppm")[i][j].getGreen(),
                newImage.getPixelAt(i, j).getGreen());
        assertEquals(ImageUtil.getImagePixels("Test-Save.ppm")[i][j].getBlue(),
                newImage.getPixelAt(i, j).getBlue());
      }
    }
  }

  @Test
  public void testSaveNewFile() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "test", "ppm");

    boolean doesFileExist = true;
    try {
      model.load("Save-New-File.ppm", "save-new-file", "jpg");
    } catch (IllegalArgumentException e) {
      doesFileExist = false;
    }

    model.save("Save-New-File.ppm", "test", "jpg");

    doesFileExist = true;
    try {
      model.load("Save-New-File.ppm", "save-new-file", "jpg");
    } catch (IllegalArgumentException e) {
      doesFileExist = false;
    }

    assertTrue(doesFileExist);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSaveEmptyFilePath() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "test", "ppm");

    model.save("", "test", "jpg");
  }

  @Test
  public void testVisualizeRed() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "Test.ppm", "ppm");

    assertEquals(4, ImageUtil.getWidth("Test.ppm"));

    assertEquals(101, ImageUtil.getImagePixels("Test.ppm")[0][0].getRed());
    assertEquals(90, ImageUtil.getImagePixels("Test.ppm")[0][0].getGreen());
    assertEquals(58, ImageUtil.getImagePixels("Test.ppm")[0][0].getBlue());

    model.visualizeRed("Test.ppm", "Test-Red.ppm");
    model.save("Test-Red.ppm", "Test-Red.ppm", "ppm");

  }

  @Test
  public void testVisualizeGreen() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "Test.ppm", "ppm");

    assertEquals(4, ImageUtil.getWidth("Test.ppm"));

    assertEquals(101, ImageUtil.getImagePixels("Test.ppm")[0][0].getRed());
    assertEquals(90, ImageUtil.getImagePixels("Test.ppm")[0][0].getGreen());
    assertEquals(58, ImageUtil.getImagePixels("Test.ppm")[0][0].getBlue());

    model.visualizeGreen("Test.ppm", "Test-Green.ppm");
    model.save("Test-Green.ppm", "Test-Green.ppm", "ppm");

    for (int i = 0; i < ImageUtil.getHeight("Test.ppm"); i++) {
      for (int j = 0; j < ImageUtil.getWidth("Test.ppm"); j++) {
        assertEquals(ImageUtil.getImagePixels("Test-Green.ppm")[i][j].getRed(),
                ImageUtil.getImagePixels("Test.ppm")[i][j].getGreen());
        assertEquals(ImageUtil.getImagePixels("Test-Green.ppm")[i][j].getGreen(),
                ImageUtil.getImagePixels("Test.ppm")[i][j].getGreen());
        assertEquals(ImageUtil.getImagePixels("Test-Green.ppm")[i][j].getBlue(),
                ImageUtil.getImagePixels("Test.ppm")[i][j].getGreen());
      }
    }
  }

  @Test
  public void testVisualizeBlue() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "Test.ppm", "ppm");

    assertEquals(4, ImageUtil.getWidth("Test.ppm"));

    assertEquals(101, ImageUtil.getImagePixels("Test.ppm")[0][0].getRed());
    assertEquals(90, ImageUtil.getImagePixels("Test.ppm")[0][0].getGreen());
    assertEquals(58, ImageUtil.getImagePixels("Test.ppm")[0][0].getBlue());

    model.visualizeBlue("Test.ppm", "Test-Blue.ppm");
    model.save("Test-Blue.ppm", "Test-Blue.ppm", "ppm");

    for (int i = 0; i < ImageUtil.getHeight("Test.ppm"); i++) {
      for (int j = 0; j < ImageUtil.getWidth("Test.ppm"); j++) {
        assertEquals(ImageUtil.getImagePixels("Test-Blue.ppm")[i][j].getRed(),
                ImageUtil.getImagePixels("Test.ppm")[i][j].getBlue());
        assertEquals(ImageUtil.getImagePixels("Test-Blue.ppm")[i][j].getGreen(),
                ImageUtil.getImagePixels("Test.ppm")[i][j].getBlue());
        assertEquals(ImageUtil.getImagePixels("Test-Blue.ppm")[i][j].getBlue(),
                ImageUtil.getImagePixels("Test.ppm")[i][j].getBlue());
      }
    }
  }

  @Test
  public void testVisualizeValue() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "Test.ppm", "ppm");

    assertEquals(4, ImageUtil.getWidth("Test.ppm"));

    assertEquals(103, ImageUtil.getImagePixels("Test.ppm")[0][1].getRed());
    assertEquals(92, ImageUtil.getImagePixels("Test.ppm")[0][1].getGreen());
    assertEquals(62, ImageUtil.getImagePixels("Test.ppm")[0][1].getBlue());

    model.visualizeValue("Test.ppm", "Test-Value.ppm");
    model.save("Test-Value.ppm", "Test-Value.ppm", "ppm");

    int maxValue;
    for (int i = 0; i < ImageUtil.getHeight("Test.ppm"); i++) {
      for (int j = 0; j < ImageUtil.getWidth("Test.ppm"); j++) {
        int maxRG = Math.max(ImageUtil.getImagePixels("Test.ppm")[i][j].getRed(),
                ImageUtil.getImagePixels("Test.ppm")[i][j].getGreen());
        maxValue = Math.max(ImageUtil.getImagePixels("Test.ppm")[i][j].getBlue(), maxRG);


        assertEquals(maxValue, ImageUtil.getImagePixels("Test-Value.ppm")[i][j].getRed());
        assertEquals(maxValue, ImageUtil.getImagePixels("Test-Value.ppm")[i][j].getGreen());
        assertEquals(maxValue, ImageUtil.getImagePixels("Test-Value.ppm")[i][j].getBlue());
      }
    }
  }

  @Test
  public void testVisualizeIntensity() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "Test.ppm", "ppm");

    assertEquals(4, ImageUtil.getWidth("Test.ppm"));

    assertEquals(103, ImageUtil.getImagePixels("Test.ppm")[0][1].getRed());
    assertEquals(92, ImageUtil.getImagePixels("Test.ppm")[0][1].getGreen());
    assertEquals(62, ImageUtil.getImagePixels("Test.ppm")[0][1].getBlue());

    model.visualizeIntensity("Test.ppm", "Test-Intensity.ppm");
    model.save("Test-Intensity.ppm", "Test-Intensity.ppm", "ppm");

    int averageValue;
    for (int i = 0; i < ImageUtil.getHeight("Test.ppm"); i++) {
      for (int j = 0; j < ImageUtil.getWidth("Test.ppm"); j++) {
        averageValue = (ImageUtil.getImagePixels("Test.ppm")[i][j].getRed() +
                ImageUtil.getImagePixels("Test.ppm")[i][j].getGreen() +
                ImageUtil.getImagePixels("Test.ppm")[i][j].getBlue()) / 3;


        assertEquals(averageValue,
                ImageUtil.getImagePixels("Test-Intensity.ppm")[i][j].getRed());
        assertEquals(averageValue,
                ImageUtil.getImagePixels("Test-Intensity.ppm")[i][j].getGreen());
        assertEquals(averageValue,
                ImageUtil.getImagePixels("Test-Intensity.ppm")[i][j].getBlue());
      }
    }
  }

  @Test
  public void visualizeLuma() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "Test.ppm", "ppm");

    assertEquals(4, ImageUtil.getWidth("Test.ppm"));

    assertEquals(103, ImageUtil.getImagePixels("Test.ppm")[0][1].getRed());
    assertEquals(92, ImageUtil.getImagePixels("Test.ppm")[0][1].getGreen());
    assertEquals(62, ImageUtil.getImagePixels("Test.ppm")[0][1].getBlue());

    model.visualizeLuma("Test.ppm", "Test-Luma.ppm");
    model.save("Test-Luma.ppm", "Test-Luma.ppm", "ppm");

    assertEquals(92, ImageUtil.getImagePixels("Test-Luma.ppm")[0][1].getRed());
    assertEquals(92, ImageUtil.getImagePixels("Test-Luma.ppm")[0][1].getGreen());
    assertEquals(92, ImageUtil.getImagePixels("Test-Luma.ppm")[0][1].getBlue());
  }

  @Test
  public void testFlipHorizontal() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "Test.ppm", "ppm");

    assertEquals(4, ImageUtil.getWidth("Test.ppm"));

    assertEquals(101, ImageUtil.getImagePixels("Test.ppm")[0][0].getRed());
    assertEquals(90, ImageUtil.getImagePixels("Test.ppm")[0][0].getGreen());
    assertEquals(58, ImageUtil.getImagePixels("Test.ppm")[0][0].getBlue());

    model.flipHorizontal("Test.ppm", "Test-Horizontal.ppm");
    model.save("Test-Horizontal.ppm", "Test-Horizontal.ppm", "ppm");

    assertEquals(104,
            ImageUtil.getImagePixels("Test-Horizontal.ppm")[0][0].getRed());
    assertEquals(91,
            ImageUtil.getImagePixels("Test-Horizontal.ppm")[0][0].getGreen());
    assertEquals(59,
            ImageUtil.getImagePixels("Test-Horizontal.ppm")[0][0].getBlue());
  }

  @Test
  public void testDoubleHorizontalFlip() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "Test.ppm", "ppm");

    assertEquals(4, ImageUtil.getWidth("Test.ppm"));

    assertEquals(101, ImageUtil.getImagePixels("Test.ppm")[0][0].getRed());
    assertEquals(90, ImageUtil.getImagePixels("Test.ppm")[0][0].getGreen());
    assertEquals(58, ImageUtil.getImagePixels("Test.ppm")[0][0].getBlue());

    model.flipHorizontal("Test.ppm", "Test-Horizontal.ppm");
    model.save("Test-Horizontal.ppm", "Test-Horizontal.ppm", "ppm");

    assertEquals(104,
            ImageUtil.getImagePixels("Test-Horizontal.ppm")[0][0].getRed());
    assertEquals(91,
            ImageUtil.getImagePixels("Test-Horizontal.ppm")[0][0].getGreen());
    assertEquals(59,
            ImageUtil.getImagePixels("Test-Horizontal.ppm")[0][0].getBlue());

    model.flipHorizontal("Test-Horizontal.ppm", "Test-Horizontal.ppm");
    model.save("Test-Horizontal.ppm", "Test-Horizontal.ppm", "ppm");

    assertEquals(101,
            ImageUtil.getImagePixels("Test-Horizontal.ppm")[0][0].getRed());
    assertEquals(90,
            ImageUtil.getImagePixels("Test-Horizontal.ppm")[0][0].getGreen());
    assertEquals(58,
            ImageUtil.getImagePixels("Test-Horizontal.ppm")[0][0].getBlue());
  }


  @Test
  public void testFlipVertical() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "Test.ppm", "ppm");

    assertEquals(4, ImageUtil.getWidth("Test.ppm"));

    assertEquals(101, ImageUtil.getImagePixels("Test.ppm")[0][0].getRed());
    assertEquals(90, ImageUtil.getImagePixels("Test.ppm")[0][0].getGreen());
    assertEquals(58, ImageUtil.getImagePixels("Test.ppm")[0][0].getBlue());

    model.flipVertical("Test.ppm", "Test-Vertical.ppm");
    model.save("Test-Vertical.ppm", "Test-Vertical.ppm", "ppm");

    assertEquals(106,
            ImageUtil.getImagePixels("Test-Vertical.ppm")[0][0].getRed());
    assertEquals(92,
            ImageUtil.getImagePixels("Test-Vertical.ppm")[0][0].getGreen());
    assertEquals(66,
            ImageUtil.getImagePixels("Test-Vertical.ppm")[0][0].getBlue());
  }

  @Test
  public void testDoubleVerticalFlip() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "Test.ppm", "ppm");

    assertEquals(4, ImageUtil.getWidth("Test.ppm"));

    assertEquals(101, ImageUtil.getImagePixels("Test.ppm")[0][0].getRed());
    assertEquals(90, ImageUtil.getImagePixels("Test.ppm")[0][0].getGreen());
    assertEquals(58, ImageUtil.getImagePixels("Test.ppm")[0][0].getBlue());

    model.flipVertical("Test.ppm", "Test-Vertical.ppm");
    model.save("Test-Vertical.ppm", "Test-Vertical.ppm", "ppm");

    assertEquals(106,
            ImageUtil.getImagePixels("Test-Vertical.ppm")[0][0].getRed());
    assertEquals(92,
            ImageUtil.getImagePixels("Test-Vertical.ppm")[0][0].getGreen());
    assertEquals(66,
            ImageUtil.getImagePixels("Test-Vertical.ppm")[0][0].getBlue());

    model.flipVertical("Test-Vertical.ppm", "Test-Vertical.ppm");
    model.save("Test-Vertical.ppm", "Test-Vertical.ppm", "ppm");

    assertEquals(101,
            ImageUtil.getImagePixels("Test-Vertical.ppm")[0][0].getRed());
    assertEquals(90,
            ImageUtil.getImagePixels("Test-Vertical.ppm")[0][0].getGreen());
    assertEquals(58,
            ImageUtil.getImagePixels("Test-Vertical.ppm")[0][0].getBlue());

  }

  @Test
  public void testBrighten() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "Test.ppm", "ppm");

    assertEquals(4, ImageUtil.getWidth("Test.ppm"));

    assertEquals(101, ImageUtil.getImagePixels("Test.ppm")[0][0].getRed());
    assertEquals(90, ImageUtil.getImagePixels("Test.ppm")[0][0].getGreen());
    assertEquals(58, ImageUtil.getImagePixels("Test.ppm")[0][0].getBlue());

    model.brighten(20,"Test.ppm", "Test-Brighten.ppm");
    model.save("Test-Brighten.ppm", "Test-Brighten.ppm", "ppm");

    for (int i = 0; i < ImageUtil.getHeight("Test.ppm"); i++) {
      for (int j = 0; j < ImageUtil.getWidth("Test.ppm"); j++) {
        assertEquals(ImageUtil.getImagePixels("Test-Brighten.ppm")[i][j].getRed(),
                ImageUtil.getImagePixels("Test.ppm")[i][j].getRed() + 20);
        assertEquals(ImageUtil.getImagePixels("Test-Brighten.ppm")[i][j].getGreen(),
                ImageUtil.getImagePixels("Test.ppm")[i][j].getGreen() + 20);
        assertEquals(ImageUtil.getImagePixels("Test-Brighten.ppm")[i][j].getBlue(),
                ImageUtil.getImagePixels("Test.ppm")[i][j].getBlue() + 20);
      }
    }
  }

  @Test
  public void testDarken() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "Test.ppm", "ppm");

    assertEquals(101, ImageUtil.getImagePixels("Test.ppm")[0][0].getRed());
    assertEquals(90, ImageUtil.getImagePixels("Test.ppm")[0][0].getGreen());
    assertEquals(58, ImageUtil.getImagePixels("Test.ppm")[0][0].getBlue());

    model.darken(20,"Test.ppm", "Test-Darken.ppm");
    model.save("Test-Darken.ppm", "Test-Darken.ppm", "ppm");

    for (int i = 0; i < ImageUtil.getHeight("Test.ppm"); i++) {
      for (int j = 0; j < ImageUtil.getWidth("Test.ppm"); j++) {
        assertEquals(ImageUtil.getImagePixels("Test-Darken.ppm")[i][j].getRed(),
                ImageUtil.getImagePixels("Test.ppm")[i][j].getRed() - 20);
        assertEquals(ImageUtil.getImagePixels("Test-Darken.ppm")[i][j].getGreen(),
                ImageUtil.getImagePixels("Test.ppm")[i][j].getGreen() - 20);
        assertEquals(ImageUtil.getImagePixels("Test-Darken.ppm")[i][j].getBlue(),
                ImageUtil.getImagePixels("Test.ppm")[i][j].getBlue() - 20);
      }
    }
  }

  @Test
  public void testBlur() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "Test.ppm", "ppm");

    assertEquals(4, ImageUtil.getWidth("Test.ppm"));

    assertEquals(101, ImageUtil.getImagePixels("Test.ppm")[0][0].getRed());
    assertEquals(90, ImageUtil.getImagePixels("Test.ppm")[0][0].getGreen());
    assertEquals(58, ImageUtil.getImagePixels("Test.ppm")[0][0].getBlue());

    model.blur("Test.ppm", "Test-Blur.ppm");
    model.save("Test-Blur.ppm", "Test-Blur.ppm", "ppm");

    assertEquals(57,
            ImageUtil.getImagePixels("Test-Blur.ppm")[0][0].getRed());
    assertEquals(51,
            ImageUtil.getImagePixels("Test-Blur.ppm")[0][0].getGreen());
    assertEquals(33,
            ImageUtil.getImagePixels("Test-Blur.ppm")[0][0].getBlue());
  }

  @Test
  public void testSharpen() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "Test.ppm", "ppm");

    assertEquals(4, ImageUtil.getWidth("Test.ppm"));

    assertEquals(101, ImageUtil.getImagePixels("Test.ppm")[0][0].getRed());
    assertEquals(90, ImageUtil.getImagePixels("Test.ppm")[0][0].getGreen());
    assertEquals(58, ImageUtil.getImagePixels("Test.ppm")[0][0].getBlue());

    model.sharpen("Test.ppm", "Test-Sharpen.ppm");
    model.save("Test-Sharpen.ppm", "Test-Sharpen.ppm", "ppm");

    assertEquals(112,
            ImageUtil.getImagePixels("Test-Sharpen.ppm")[0][0].getRed());
    assertEquals(101,
            ImageUtil.getImagePixels("Test-Sharpen.ppm")[0][0].getGreen());
    assertEquals(64,
            ImageUtil.getImagePixels("Test-Sharpen.ppm")[0][0].getBlue());
  }

  @Test
  public void testVisualizeSepia() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "Test.ppm", "ppm");

    assertEquals(4, ImageUtil.getWidth("Test.ppm"));

    assertEquals(101, ImageUtil.getImagePixels("Test.ppm")[0][0].getRed());
    assertEquals(90, ImageUtil.getImagePixels("Test.ppm")[0][0].getGreen());
    assertEquals(58, ImageUtil.getImagePixels("Test.ppm")[0][0].getBlue());

    model.visualizeSepia("Test.ppm", "Test-Sepia.ppm");
    model.save("Test-Sepia.ppm", "Test-Sepia.ppm", "ppm");

    assertEquals(119,
            ImageUtil.getImagePixels("Test-Sepia.ppm")[0][0].getRed());
    assertEquals(106,
            ImageUtil.getImagePixels("Test-Sepia.ppm")[0][0].getGreen());
    assertEquals(83,
            ImageUtil.getImagePixels("Test-Sepia.ppm")[0][0].getBlue());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testVisualizeRedNotLoadedFile() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.visualizeRed("Test.ppm", "test");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testVisualizeGreenNotLoadedFile() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.visualizeGreen("Test.ppm", "test");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testVisualizeBlueNotLoadedFile() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.visualizeBlue("Test.ppm", "test");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testVisualizeValueNotLoadedFile() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.visualizeValue("Test.ppm", "test");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testVisualizeIntensityNotLoadedFile() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.visualizeIntensity("Test.ppm", "test");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testVisualizeLumaNotLoadedFile() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.visualizeLuma("Test.ppm", "test");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testFlipHorizontalNotLoadedFile() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.flipHorizontal("Test.ppm", "test");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testFlipVerticalNotLoadedFile() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.flipVertical("Test.ppm", "test");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBrightenNotLoadedFile() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.brighten(20,"Test.ppm", "test");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testDarkenNotLoadedFile() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.darken(20,"Test.ppm", "test");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBlurNotLoadedFile() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.blur("Test.ppm", "test");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSharpenNotLoadedFile() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.sharpen("Test.ppm", "test");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSepiaNotLoadedFile() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.visualizeSepia("Test.ppm", "test");
  }

  private HashMap<Integer, Integer> initHistogram() {
    HashMap<Integer, Integer> histogram = new HashMap<Integer, Integer>();

    for (int i = 0; i < 256; i++) {
      histogram.put(i, 0);
    }

    return histogram;
  }

  @Test
  public void testCalculateRedHistogram() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "test", "ppm");
    HashMap<Integer, Integer> histogram = model.calculateRedHistogram("test",
            "test2");

    for (int i = 0; i < 256; i++) {
      assertEquals(histogram.get(i),
              model.calculateRedHistogram("test", "test2").get(i));
    }

  }

  @Test
  public void testCalculateGreenHistogram() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "test", "ppm");
    HashMap<Integer, Integer> histogram = model.calculateGreenHistogram("test",
            "test2");

    for (int i = 0; i < 256; i++) {
      assertEquals(histogram.get(i),
              model.calculateGreenHistogram("test", "test2").get(i));
    }

  }


  @Test
  public void testCalculateBlueHistogram() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "test", "ppm");
    HashMap<Integer, Integer> histogram = model.calculateBlueHistogram("test",
            "test2");

    for (int i = 0; i < 256; i++) {
      assertEquals(histogram.get(i),
              model.calculateBlueHistogram("test", "test2").get(i));
    }

  }

  @Test
  public void testCalculateIntensityHistogram() {
    ImageEditorModel model = new ImageEditorModelImpl();

    model.load("Test.ppm", "test", "ppm");
    HashMap<Integer, Integer> histogram = model.calculateIntensityHistogram("test",
            "test2");

    for (int i = 0; i < 256; i++) {
      assertEquals(histogram.get(i),
              model.calculateIntensityHistogram("test", "test2").get(i));
    }
  }
}