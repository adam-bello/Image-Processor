package controller.commands;

import java.io.IOException;

import model.ImageEditorModel;
import view.ImageEditorView;

/**
 * Represents the sharpen command which can sharpen an image by manipulating all the pixels' color
 * values by filtering through a kernel.
 */
public class Sharpen implements ImageEditCommand {
  private final String filename;
  private final String destination;
  private final ImageEditorView view;

  /**
   * Constructs a sharpen object which takes a filename, destination, and view. The view
   * is used to display an error message if necessary and the rest are used as arguments for the
   * model's sharpen method.
   * @param filename the file to be edited
   * @param destination the final image that has been sharpened
   * @param view used for sending error messages.
   */
  public Sharpen(String filename, String destination, ImageEditorView view) {
    this.filename = filename;
    this.destination = destination;
    this.view = view;
  }

  @Override
  public void execute(ImageEditorModel model) {
    try {
      model.sharpen(filename, destination);
    } catch (IllegalArgumentException e) {
      try {
        view.renderMessage(e.getMessage());
      } catch (IOException ie) {
        throw new IllegalStateException("Unable to render error message");
      }
    }
  }
}
