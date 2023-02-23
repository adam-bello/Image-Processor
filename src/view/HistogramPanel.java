package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.util.HashMap;

import javax.swing.JPanel;

import model.ImageEditorModel;

/**
 * Class representing a Histogram Panel by extending JPanel. This panel's only purpose is
 * to display the histogram through the paintComponent method.
 */
public class HistogramPanel extends JPanel {
  private HashMap<Integer, Integer> redHistogram;
  private HashMap<Integer, Integer> greenHistogram;
  private HashMap<Integer, Integer> blueHistogram;
  private HashMap<Integer, Integer> intensityHistogram;

  /**
   * Constructs a Histogram Panel by taking in a model and initializing the four histograms.
   * @param model The model for which to get histogram information from.
   */
  public HistogramPanel(ImageEditorModel model) {
    super();
    this.redHistogram = new HashMap<Integer, Integer>();
    this.greenHistogram = new HashMap<Integer, Integer>();
    this.blueHistogram = new HashMap<Integer, Integer>();
    this.intensityHistogram = new HashMap<Integer, Integer>();
    this.setPreferredSize(new Dimension(300, 600));
  }

  protected void setHistogram(HashMap<Integer, Integer> redHistogram,
                              HashMap<Integer, Integer> greenHistogram,
                              HashMap<Integer, Integer> blueHistogram,
                              HashMap<Integer, Integer> intensityHistogram) {
    this.redHistogram = redHistogram;
    this.greenHistogram = greenHistogram;
    this.blueHistogram = blueHistogram;
    this.intensityHistogram = intensityHistogram;

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    int xCount = 40;

    g.setColor(Color.RED);

    for (int i = 1; i < 256; i++) {
      if (redHistogram.containsKey(i)) {
        g.drawLine(xCount,
                600 - (int)(Math.pow(this.redHistogram.get(i - 1), 1 / 1.8)),
                xCount + 2,
                600 - (int)(Math.pow(this.redHistogram.get(i), 1 / 1.8)));
        xCount += 2;
      }
    }
    xCount = 40;

    g.setColor(Color.GREEN);

    for (int i = 1; i < 256; i++) {
      if (greenHistogram.containsKey(i)) {
        g.drawLine(xCount,
                600 - (int)(Math.pow(this.greenHistogram.get(i - 1), 1 / 1.8)),
                xCount + 2,
                600 - (int)(Math.pow(this.greenHistogram.get(i), 1 / 1.8)));
        xCount += 2;
      }
    }
    xCount = 40;

    g.setColor(Color.BLUE);

    for (int i = 1; i < 256; i++) {
      if (blueHistogram.containsKey(i)) {
        g.drawLine(xCount,
                600 - (int)(Math.pow(this.blueHistogram.get(i - 1), 1 / 1.8)),
                xCount + 2,
                600 - (int)(Math.pow(this.blueHistogram.get(i), 1 / 1.8)));
        xCount += 2;
      }
    }
    xCount = 40;

    g.setColor(Color.YELLOW);

    for (int i = 1; i < 256; i++) {
      if (intensityHistogram.containsKey(i)) {
        g.drawLine(xCount,
                600 - (int)(Math.pow(this.intensityHistogram.get(i - 1), 1 / 1.8)),
                xCount + 2,
                600 - (int)(Math.pow(this.intensityHistogram.get(i), 1 / 1.8)));
        xCount += 2;
      }
    }
    xCount = 40;

  }
}
