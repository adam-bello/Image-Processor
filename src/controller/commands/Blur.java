package controller.commands;

import java.io.IOException;

import model.ImageEditorModel;
import view.ImageEditorView;

/**
 * Represents the blur command which can blur an image by manipulating all the pixels' color
 * values by filtering through a kernel.
 */
public class Blur implements ImageEditCommand {
  private final String filename;
  private final String destination;
  private final ImageEditorView view;

  /**
   * Constructs a blur object which takes a filename, destination, and view. The view
   * is used to display an error message if necessary and the rest are used as arguments for the
   * model's blur method.
   * @param filename the file to be edited
   * @param destination the final image that has been blurred
   * @param view used for sending error messages.
   */
  public Blur(String filename, String destination, ImageEditorView view) {
    this.filename = filename;
    this.destination = destination;
    this.view = view;
  }

  @Override
  public void execute(ImageEditorModel model) {
    try {
      model.blur(filename, destination);
    } catch (IllegalArgumentException e) {
      try {
        view.renderMessage(e.getMessage());
      } catch (IOException ie) {
        throw new IllegalStateException("Unable to render error message");
      }
    }
  }
}
