package controller.commands;

import java.io.IOException;

import model.ImageEditorModel;
import view.ImageEditorView;

/**
 * Represents the visualize green method in the model. This can create a greyscale image based
 * on the green component of the image.
 */
public class VisualizeGreen implements ImageEditCommand {
  private final String filename;
  private final String destination;
  private final ImageEditorView view;

  /**
   * Constructs a visualize green object with a filename, destination, and view for dispalying
   * error messages.
   * @param filename the filename for the image being edited.
   * @param destination the destination for the image being created.
   * @param view used for displaying error messages.
   */
  public VisualizeGreen(String filename, String destination, ImageEditorView view) {
    this.filename = filename;
    this.destination = destination;
    this.view = view;
  }

  @Override
  public void execute(ImageEditorModel model) {
    try {
      model.visualizeGreen(this.filename, this.destination);
    } catch (IllegalArgumentException e) {
      try {
        view.renderMessage(e.getMessage());
      } catch (IOException ie) {
        throw new IllegalStateException("Unable to render error message");
      }
    }
  }
}
