package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Features;
import model.ImageEditorModel;
import model.Pixel;

/**
 * A class representing the GUI view. It extends JFrame and creates a frame containing multiple
 * panels of displays.
 */
public class ImageEditorGUIView extends JFrame implements ImageEditorGUI {

  private JPanel imagePanel;
  private HistogramPanel histogramPanel;
  private JScrollPane imageScroll;
  private ButtonGroup buttonGroup;
  private JPanel radioPanel;

  private JButton loadButton;
  private JButton saveButton;
  private JButton blurButton;
  private JButton sharpenButton;
  private JButton brightenButton;
  private JButton darkenButton;
  private JButton flipHorizontalButton;
  private JButton flipVerticalButton;
  private JButton visualizeRedButton;
  private JButton visualizeGreenButton;
  private JButton visualizeBlueButton;
  private JButton visualizeIntensityButton;
  private JButton visualizeLumaButton;
  private JButton visualizeSepiaButton;
  private JButton visualizeValueButton;

  /**
   * The constructor for making the main frame of the GUI. It creates 4 panels - the button panel,
   * the radio button panel, the image panel, and the histogram panel.
   * @param title The title of the frame.
   * @param model The model which the GUI is displaying.
   */
  public ImageEditorGUIView(String title, ImageEditorModel model) {
    super(title);
    setSize(500, 500);
    this.setMinimumSize(new Dimension(500, 500));

    JPanel mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Add button panel
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBorder(BorderFactory.createTitledBorder("Image Operations"));
    buttonPanel.setLayout(new GridLayout(2, 8));
    mainPanel.add(buttonPanel);

    // Add individual buttons to button panel
    loadButton = new JButton("Load");
    loadButton.setActionCommand("Load");
    buttonPanel.add(loadButton);

    saveButton = new JButton("Save");
    saveButton.setActionCommand("Save");
    buttonPanel.add(saveButton);

    blurButton = new JButton("Blur");
    blurButton.setActionCommand("Blur");
    buttonPanel.add(blurButton);

    sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("Sharpen");
    buttonPanel.add(sharpenButton);

    brightenButton = new JButton("Brighten");
    brightenButton.setActionCommand("Brighten");
    buttonPanel.add(brightenButton);

    darkenButton = new JButton("Darken");
    darkenButton.setActionCommand("Darken");
    buttonPanel.add(darkenButton);

    flipHorizontalButton = new JButton("Flip Horizontal");
    flipHorizontalButton.setActionCommand("Flip Horizontal");
    buttonPanel.add(flipHorizontalButton);

    flipVerticalButton = new JButton("Flip Vertical");
    flipVerticalButton.setActionCommand("Flip Vertical");
    buttonPanel.add(flipVerticalButton);

    visualizeRedButton = new JButton("Visualize Red");
    visualizeRedButton.setActionCommand("Visualize Red");
    buttonPanel.add(visualizeRedButton);

    visualizeGreenButton = new JButton("Visualize Green");
    visualizeGreenButton.setActionCommand("Visualize Green");
    buttonPanel.add(visualizeGreenButton);

    visualizeBlueButton = new JButton("Visualize Blue");
    visualizeBlueButton.setActionCommand("Visualize Blue");
    buttonPanel.add(visualizeBlueButton);

    visualizeIntensityButton = new JButton("Visualize Intensity");
    visualizeIntensityButton.setActionCommand("Visualize Intensity");
    buttonPanel.add(visualizeIntensityButton);

    visualizeLumaButton = new JButton("Visualize Luma");
    visualizeLumaButton.setActionCommand("Visualize Luma");
    buttonPanel.add(visualizeLumaButton);

    visualizeValueButton = new JButton("Visualize Value");
    visualizeValueButton.setActionCommand("Visualize Value");
    buttonPanel.add(visualizeValueButton);

    visualizeSepiaButton = new JButton("Visualize Sepia");
    visualizeSepiaButton.setActionCommand("Visualize Sepia");
    buttonPanel.add(visualizeSepiaButton);

    //Add radio buttons
    radioPanel = new JPanel();
    radioPanel.setBorder(BorderFactory.createTitledBorder("Radio buttons"));
    radioPanel.setSize(new Dimension(500, 100));
    radioPanel.setLayout(new FlowLayout());
    buttonGroup = new ButtonGroup();
    mainPanel.add(radioPanel);

    // Add image panel
    imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image"));
    mainPanel.add(imagePanel);

    // Add histogram panel
    histogramPanel = new HistogramPanel(model);
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    JScrollPane histogramScroll = new JScrollPane(histogramPanel);
    mainPanel.add(histogramScroll);

    pack();
    setVisible(true);

  }

  @Override
  public void renderMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

  @Override
  public void addFeatures(Features features) {
    loadButton.addActionListener(evt -> features.load());
    saveButton.addActionListener(evt -> features.save());
    visualizeRedButton.addActionListener(evt -> features.visualizeRed());
    visualizeGreenButton.addActionListener(evt -> features.visualizeGreen());
    visualizeBlueButton.addActionListener(evt -> features.visualizeBlue());
    visualizeIntensityButton.addActionListener(evt -> features.visualizeIntensity());
    visualizeValueButton.addActionListener(evt -> features.visualizeValue());
    visualizeLumaButton.addActionListener(evt -> features.visualizeLuma());
    flipHorizontalButton.addActionListener(evt -> features.flipHorizontal());
    flipVerticalButton.addActionListener(evt -> features.flipVertical());
    visualizeSepiaButton.addActionListener(evt -> features.visualizeSepia());
    blurButton.addActionListener(evt -> features.blur());
    sharpenButton.addActionListener(evt -> features.sharpen());
    brightenButton.addActionListener(evt -> features.brighten());
    darkenButton.addActionListener(evt -> features.darken());
  }

  @Override
  public String openFile() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & GIF Images", "jpg", "gif");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(ImageEditorGUIView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getAbsolutePath();
    }
    return "";
  }

  @Override
  public String chooseFileName() {
    String input = JOptionPane.showInputDialog(null, "Enter filename");
    return input;
  }

  @Override
  public void addImage(String filename, Features features) {
    JRadioButton radioButton = new JRadioButton(filename);
    radioButton.setActionCommand(filename);
    radioButton.addActionListener(evt -> features.setCurFile(radioButton.getText()));
    buttonGroup.add(radioButton);
    radioPanel.add(radioButton);
    radioButton.doClick();
  }


  @Override
  public String getScale() {
    String input = JOptionPane.showInputDialog(null, "Enter scale");
    return input;
  }

  @Override
  public void updateImage(int width, int height, Pixel[][] pixels) {
    BufferedImage newImage = new BufferedImage(width, height,
            BufferedImage.TYPE_INT_RGB);

    Pixel curPixel;
    int rgb;

    for (int i = 0; i < newImage.getHeight(); i++) {
      for (int j = 0; j < newImage.getWidth(); j++) {
        curPixel = pixels[i][j];
        rgb = new Color(curPixel.getRed(), curPixel.getGreen(), curPixel.getBlue()).getRGB();
        newImage.setRGB(j, i, rgb);
      }
    }

    if (imageScroll != null) {
      imagePanel.remove(imageScroll);
    }
    JLabel imageLabel = new JLabel();
    imageScroll = new JScrollPane(imageLabel);
    imageLabel.setIcon(new ImageIcon(newImage));
    imageScroll.setPreferredSize(new Dimension(1000, 700));
    imagePanel.add(imageScroll);
    imagePanel.updateUI();
  }

  @Override
  public void updateHistogram(HashMap<Integer, Integer> redHistogram,
                              HashMap<Integer, Integer> greenHistogram,
                              HashMap<Integer, Integer> blueHistogram,
                              HashMap<Integer, Integer> intensityHistogram) {
    this.histogramPanel.setHistogram(redHistogram, greenHistogram,
            blueHistogram, intensityHistogram);
    this.repaint();
  }

  @Override
  public String getFileType() {
    String input = JOptionPane.showInputDialog(null, "Enter filetype");
    return input;
  }

  @Override
  public String savePath() {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(ImageEditorGUIView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getAbsolutePath();
    }
    return "";
  }

}
