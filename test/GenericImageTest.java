import org.junit.Before;
import org.junit.Test;

import model.GenericImage;
import model.Pixel;
import static org.junit.Assert.assertEquals;

/**
 * Tests for a PPM Image involving construction and the different aspects
 * of the image such as width, height, and color values.
 */
public class GenericImageTest {

  private GenericImage image1;

  @Before
  public void setup() {
    Pixel[][] pixels1 = new Pixel[2][2];
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        pixels1[i][j] = new Pixel(10, 20, 30);
      }
    }

    image1 = new GenericImage(2, 2, pixels1);
  }

  @Test
  public void testConstructor() {
    Pixel p = new Pixel(10, 20, 30);
    assertEquals(2, image1.getHeight());
    assertEquals(2, image1.getWidth());

    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        assertEquals(p.getRed(), image1.getPixelAt(i, j).getRed());
        assertEquals(p.getGreen(), image1.getPixelAt(i, j).getGreen());
        assertEquals(p.getBlue(), image1.getPixelAt(i, j).getBlue());
      }
    }
  }


  @Test (expected = IllegalArgumentException.class)
  public void testNegativeRowGetPixel() {
    image1.getPixelAt(-1, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeColGetPixel() {
    image1.getPixelAt(1, -1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRowTooBigGetPixel() {
    image1.getPixelAt(2, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testColTooBigGetPixel() {
    image1.getPixelAt(1, 2);
  }
}
