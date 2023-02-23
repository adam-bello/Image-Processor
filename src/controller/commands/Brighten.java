package controller.commands;

import java.io.IOException;

import model.ImageEditorModel;
import view.ImageEditorView;

/**
 * Represents the brighten command which can brighten an image by increasing the all the pixels'
 * color values.
 */
public class Brighten implements ImageEditCommand {
  private final int scale;
  private final String filename;
  private final String destination;
  private final ImageEditorView view;

  /**
   * Constructs a brighten object which takes a scale, filename, destination, and view. The view
   * is used to display an error message if necessary and the rest are used as arguments for the
   * model's brighten method.
   * @param scale the scale by which to brighten the image.
   * @param filename the filename of the image to brighten
   * @param destination the destination for new image to be placed in
   * @param view used for displaying an error message.
   */
  public Brighten(int scale, String filename, String destination, ImageEditorView view) {
    this.scale = scale;
    this.filename = filename;
    this.destination = destination;
    this.view = view;
  }

  @Override
  public void execute(ImageEditorModel model) throws IllegalStateException {
    try {
      model.brighten(scale, filename, destination);
    } catch (IllegalArgumentException e) {
      try {
        view.renderMessage(e.getMessage());
      } catch (IOException ie) {
        throw new IllegalStateException("Unable to render error message");
      }
    }
  }
}
