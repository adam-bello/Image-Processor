package controller;

import view.ImageEditorGUI;

/**
 * An interface containing all the features that are supported by ImageEditorGUI.
 */
public interface Features {
  public void load();

  public void save();

  public void visualizeRed();

  public void visualizeGreen();

  public void visualizeBlue();

  public void visualizeIntensity();

  public void visualizeValue();

  public void visualizeLuma();

  public void visualizeSepia();

  public void flipHorizontal();

  public void flipVertical();

  public void blur();

  public void sharpen();

  public void brighten();

  public void darken();

  public void setCurFile(String filename);

  public void setView(ImageEditorGUI v);
}
