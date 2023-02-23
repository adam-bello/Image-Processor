package controller.commands;

import java.io.IOException;

import model.ImageEditorModel;
import view.ImageEditorView;

/**
 * Represents the visualize blue method in the model. This can create a greyscale image based
 * on the blue component of the image.
 */
public class VisualizeBlue implements ImageEditCommand {
  private final String filename;
  private final String destination;
  private final ImageEditorView view;

  /**
   * Constructs a visualize blue object with a filename, detination, and view for displaying
   * error messages.
   * @param filename the filename for the file being edited.
   * @param destination the destination for the image being created.
   * @param view used for displaying error messages.
   */
  public VisualizeBlue(String filename, String destination, ImageEditorView view) {
    this.filename = filename;
    this.destination = destination;
    this.view = view;
  }

  @Override
  public void execute(ImageEditorModel model) throws IllegalStateException {
    try {
      model.visualizeBlue(this.filename, this.destination);
    } catch (IllegalArgumentException e) {
      try {
        view.renderMessage(e.getMessage());
      } catch (IOException ie) {
        throw new IllegalStateException("Unable to render error message");
      }
    }
  }
}
