package controller.commands;

import java.io.IOException;

import model.ImageEditorModel;
import view.ImageEditorView;

/**
 * Represents the flip vertical method. It can be used to flip an image vertically through the
 * model.
 */
public class FlipVertical implements ImageEditCommand {
  private final String filename;
  private final String destination;
  private final ImageEditorView view;

  /**
   * Constructs an object for flipping an image vertically. It takes a filename, destination, and
   * view for displaying error messages.
   * @param filename the filename for the file that will be flipped.
   * @param destination the destination for the flipped image.
   * @param view used for displaying error messages.
   */
  public FlipVertical(String filename, String destination, ImageEditorView view) {
    this.filename = filename;
    this.destination = destination;
    this.view = view;
  }

  @Override
  public void execute(ImageEditorModel model) {
    try {
      model.flipVertical(filename, destination);
    } catch (IllegalArgumentException e) {
      try {
        view.renderMessage(e.getMessage());
      } catch (IOException ie) {
        throw new IllegalStateException("Unable to render error message");
      }
    }
  }
}
