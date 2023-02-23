package controller.commands;

import java.io.IOException;

import model.ImageEditorModel;
import view.ImageEditorView;

/**
 * Represents the sepia command which can visualize sepia on an image by editing the pixels
 * with a color transformation.
 */
public class VisualizeSepia implements ImageEditCommand {
  private final String filename;
  private final String destination;
  private final ImageEditorView view;

  /**
   * Constructs a sepia object with a filename, destination, and the view used for rendering
   * error messages.
   * @param filename the file to be edited
   * @param destination the final image which has been sepia'd
   * @param view used for sending error messages.
   */
  public VisualizeSepia(String filename, String destination, ImageEditorView view) {
    this.filename = filename;
    this.destination = destination;
    this.view = view;
  }

  @Override
  public void execute(ImageEditorModel model) {
    try {
      model.visualizeSepia(filename, destination);
    } catch (IllegalArgumentException e) {
      try {
        view.renderMessage(e.getMessage());
      } catch (IOException ie) {
        throw new IllegalStateException("Unable to render error message");
      }
    }
  }
}
