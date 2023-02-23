package controller.commands;

import java.io.IOException;

import model.ImageEditorModel;
import view.ImageEditorView;

/**
 * Represents the darken command which can darken an image by decreasing the all the pixels'
 * color values.
 */
public class Darken implements ImageEditCommand {
  private final int scale;
  private final String filename;
  private final String destination;
  private final ImageEditorView view;

  /**
   * Constructs a darken object which takes a scale, filename, destination, and view. The view
   * is used to display an error message if necessary and the rest are used as arguments for the
   * model's darken method.
   * @param scale the scale by which to brighten the image.
   * @param filename the filename of the image to brighten
   * @param destination the destination for new image to be placed in
   * @param view used for displaying an error message.
   */
  public Darken(int scale, String filename, String destination, ImageEditorView view) {
    this.scale = scale;
    this.filename = filename;
    this.destination = destination;
    this.view = view;
  }

  @Override
  public void execute(ImageEditorModel model) {
    try {
      model.darken(scale, filename, destination);
    } catch (IllegalArgumentException e) {
      try {
        view.renderMessage(e.getMessage());
      } catch (IOException ie) {
        throw new IllegalStateException("Unable to render error message");
      }
    }
  }
}
