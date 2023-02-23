package controller.commands;

import java.io.IOException;

import model.ImageEditorModel;
import view.ImageEditorView;

/**
 * Represents the visualize value method in the model. This can be used to create a greyscale image
 * based on the maximum value of an RGB component in each pixel.
 */
public class VisualizeValue implements ImageEditCommand {
  private final String filename;
  private final String destination;
  private final ImageEditorView view;

  /**
   * Constructs a visualize value object with a filename, destination, and a view for displaying
   * error messages.
   * @param filename the filename for the image being edited
   * @param destination the destination for the image being created
   * @param view used for displaying error messages.
   */
  public VisualizeValue(String filename, String destination, ImageEditorView view) {
    this.filename = filename;
    this.destination = destination;
    this.view = view;
  }

  @Override
  public void execute(ImageEditorModel model) {
    try {
      model.visualizeValue(this.filename, this.destination);
    } catch (IllegalArgumentException e) {
      try {
        view.renderMessage(e.getMessage());
      } catch (IOException ie) {
        throw new IllegalStateException("Unable to render error message");
      }
    }
  }
}
