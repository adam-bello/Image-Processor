package controller;

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
import model.Image;
import model.ImageEditorModel;
import model.Pixel;
import view.ImageEditorGUI;

/**
 * Class representing the controller for the GUI version of the program. This implements the
 * Features interface which offers methods for all the actions the user has access to in the GUI.
 */
public class GUIController implements Features {
  private ImageEditorGUI view;
  private ImageEditorModel model;
  private String curFile;

  public GUIController(ImageEditorModel model) {
    this.model = model;
    this.curFile = "";
  }

  /**
   * Sets the controller view to the view given to the method. It also adds all the features
   * to the view.
   * @param v The view that will be used when the program is ran.
   */
  public void setView(ImageEditorGUI v) {
    view = v;
    //provide view with all the callbacks
    view.addFeatures(this);
  }

  public void setCurFile(String filename) {
    curFile = filename;
    updateView();
  }

  @Override
  public void load() {
    String pathname = view.openFile();

    if (pathname.length() > 0) {
      curFile = view.chooseFileName();
      ImageEditCommand cmd = new Load(pathname, curFile);
      cmd.execute(model);
      this.updateView();
      view.addImage(curFile, this);
    }
  }

  @Override
  public void save() {
    String filetype = view.getFileType();
    String pathname = view.savePath();

    if (pathname.length() > 0) {
      ImageEditCommand cmd = new Save(pathname, curFile, filetype, view);
      cmd.execute(model);
    }
  }

  private void updateView() {
    Image curImage = model.getImage(curFile);
    Pixel[][] pixels = new Pixel[curImage.getHeight()][curImage.getWidth()];
    for (int i = 0; i < curImage.getHeight(); i++) {
      for (int j = 0; j < curImage.getWidth(); j++) {
        Pixel oldPixel = curImage.getPixelAt(i, j);
        pixels[i][j] = new Pixel(oldPixel.getRed(), oldPixel.getGreen(), oldPixel.getBlue());
      }
    }

    view.updateImage(curImage.getWidth(), curImage.getHeight(), pixels);
    view.updateHistogram(model.calculateRedHistogram(curFile, curFile),
            model.calculateGreenHistogram(curFile, curFile),
            model.calculateBlueHistogram(curFile, curFile),
            model.calculateIntensityHistogram(curFile, curFile));
  }

  private void executeEdit(ImageEditCommand cmd) {
    try {
      cmd.execute(model);
      this.updateView();
    } catch (NullPointerException e) {
      return;
    }
  }

  @Override
  public void visualizeRed() {
    ImageEditCommand cmd = new VisualizeRed(curFile, curFile, this.view);
    executeEdit(cmd);
  }

  @Override
  public void visualizeGreen() {
    ImageEditCommand cmd = new VisualizeGreen(curFile, curFile, this.view);
    executeEdit(cmd);
  }

  @Override
  public void visualizeBlue() {
    ImageEditCommand cmd = new VisualizeBlue(curFile, curFile, this.view);
    executeEdit(cmd);
  }

  @Override
  public void visualizeIntensity() {
    ImageEditCommand cmd = new VisualizeIntensity(curFile, curFile, this.view);
    executeEdit(cmd);
  }

  @Override
  public void visualizeValue() {
    ImageEditCommand cmd = new VisualizeValue(curFile, curFile, this.view);
    executeEdit(cmd);
  }

  @Override
  public void visualizeLuma() {
    ImageEditCommand cmd = new VisualizeLuma(curFile, curFile, this.view);
    executeEdit(cmd);
  }

  @Override
  public void visualizeSepia() {
    ImageEditCommand cmd = new VisualizeSepia(curFile, curFile, this.view);
    executeEdit(cmd);
  }

  @Override
  public void flipHorizontal() {
    ImageEditCommand cmd = new FlipHorizontal(curFile, curFile, this.view);
    executeEdit(cmd);
  }

  @Override
  public void flipVertical() {
    ImageEditCommand cmd = new FlipVertical(curFile, curFile, this.view);
    executeEdit(cmd);
  }

  @Override
  public void blur() {
    ImageEditCommand cmd = new Blur(curFile, curFile, this.view);
    executeEdit(cmd);
  }

  @Override
  public void sharpen() {
    ImageEditCommand cmd = new Sharpen(curFile, curFile, this.view);
    executeEdit(cmd);
  }

  @Override
  public void brighten() {
    int scale;
    try {
      scale = Integer.parseInt(view.getScale());
      ImageEditCommand cmd = new Brighten(scale,curFile, curFile, this.view);
      executeEdit(cmd);
    } catch (NumberFormatException e) {
      view.renderMessage("Illegal input for scale");
    }
  }

  @Override
  public void darken() {
    int scale;
    try {
      scale = Integer.parseInt(view.getScale());
      ImageEditCommand cmd = new Darken(scale, curFile, curFile, this.view);
      executeEdit(cmd);
    } catch (NumberFormatException e) {
      view.renderMessage("Illegal input for scale");
    }
  }

}
