package controller.commands;

import java.io.IOException;

import model.ImageEditorModel;
import view.ImageEditorView;

/**
 * Represents the visualize luma method in the model. This can be used to create a greyscale
 * image based on the formula for luma.
 */
public class VisualizeLuma implements ImageEditCommand {
  private final String filename;
  private final String destination;
  private final ImageEditorView view;

  /**
   * Constructs a visualize luma object with a filename, destination, and a view for displaying
   * error messages.
   * @param filename the filename for the image being edited.
   * @param destination the destination for the image being created.
   * @param view used for displaying error messages.
   */
  public VisualizeLuma(String filename, String destination, ImageEditorView view) {
    this.filename = filename;
    this.destination = destination;
    this.view = view;
  }

  @Override
  public void execute(ImageEditorModel model) throws IllegalStateException {
    try {
      model.visualizeLuma(this.filename, this.destination);
    } catch (IllegalArgumentException e) {
      try {
        view.renderMessage(e.getMessage());
      } catch (IOException ie) {
        throw new IllegalStateException("Unable to render error message");
      }
    }
  }
}
