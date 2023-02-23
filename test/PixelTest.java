import org.junit.Before;
import org.junit.Test;

import model.Pixel;
import static org.junit.Assert.assertEquals;

/**
 * Tests for a pixel involving constructing and changing the color
 * values of the pixel.
 */
public class PixelTest {
  private Pixel pixel1;
  private Pixel pixel2;

  @Before
  public void setup() {
    pixel1 = new Pixel(10, 100, 200);
    pixel2 = new Pixel(35, 125, 88);
  }

  @Test
  public void testConstructor() {
    Pixel example1 = new Pixel(10, 20, 30);
    Pixel example2 = new Pixel(100, 150, 200);

    assertEquals(10, example1.getRed());
    assertEquals(20, example1.getGreen());
    assertEquals(30, example1.getBlue());
    assertEquals(100, example2.getRed());
    assertEquals(150, example2.getGreen());
    assertEquals(200, example2.getBlue());

  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeRed() {
    new Pixel(-256, 10, 10);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeGreen() {
    new Pixel(10, -1, 10);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeBlue() {
    new Pixel(10, 10, -52);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testTooBigRed() {
    new Pixel(256, 10, 10);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testTooBigGreen() {
    new Pixel(10, 300, 10);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testTooBigBlue() {
    new Pixel(10, 10, 356);
  }

  @Test
  public void testGetRed() {
    assertEquals(10, pixel1.getRed());
    assertEquals(35, pixel2.getRed());
  }

  @Test
  public void testGetGreen() {
    assertEquals(100, pixel1.getGreen());
    assertEquals(125, pixel2.getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(200, pixel1.getBlue());
    assertEquals(88, pixel2.getBlue());
  }

  @Test
  public void testChangeRed() {
    assertEquals(10, pixel1.getRed());
    assertEquals(35, pixel2.getRed());

    pixel1.changeRed(11);
    pixel2.changeRed(255);

    assertEquals(11, pixel1.getRed());
    assertEquals(255, pixel2.getRed());
  }

  @Test
  public void testChangeGreen() {
    assertEquals(100, pixel1.getGreen());
    assertEquals(125, pixel2.getGreen());

    pixel1.changeGreen(11);
    pixel2.changeGreen(255);

    assertEquals(11, pixel1.getGreen());
    assertEquals(255, pixel2.getGreen());
  }

  @Test
  public void testChangeBlue() {
    assertEquals(200, pixel1.getBlue());
    assertEquals(88, pixel2.getBlue());

    pixel1.changeBlue(0);
    pixel2.changeBlue(132);

    assertEquals(0, pixel1.getBlue());
    assertEquals(132, pixel2.getBlue());
  }

  @Test
  public void testNegativeChangeRed() {
    pixel1.changeRed(-10);
    assertEquals(pixel1.getRed(), 0);
  }

  @Test
  public void testNegativeChangeGreen() {
    pixel1.changeGreen(-100);
    assertEquals(pixel1.getGreen(), 0);
  }

  @Test
  public void testNegativeChangeBlue() {
    pixel1.changeBlue(-65);
    assertEquals(pixel1.getBlue(), 0);
  }

  @Test
  public void testTooBigChangeRed() {
    pixel1.changeRed(1000);
    assertEquals(pixel1.getRed(), 255);
  }

  @Test
  public void testTooBigChangeGreen() {
    pixel1.changeGreen(256);
    assertEquals(pixel1.getGreen(), 255);
  }

  @Test
  public void testTooBigChangeBlue() {
    pixel1.changeBlue(312);
    assertEquals(pixel1.getBlue(), 255);
  }
}
