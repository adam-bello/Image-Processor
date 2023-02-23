package controller.commands;

import java.io.IOException;


import model.ImageEditorModel;
import view.ImageEditorView;

/**
 * Represents the save method in the model. This can save an image from the program to a
 * given pathname.
 */
public class Save implements ImageEditCommand {
  private final String pathname;
  private final String filename;
  private final String filetype;
  private final ImageEditorView view;

  /**
   * Constructs a save object with a pathname, filename, and a view for displaying error messages.
   * @param pathname the pathaname for the file to be edited
   * @param filename filename for created image
   * @param view used for displaying errors
   */
  public Save(String pathname, String filename, String filetype, ImageEditorView view) {
    this.pathname = pathname;
    this.filename = filename;
    this.filetype = filetype;
    this.view = view;
  }

  @Override
  public void execute(ImageEditorModel model) {
    try {
      model.save(pathname, filename, filetype);
    } catch (IllegalArgumentException e) {
      try {
        view.renderMessage(e.getMessage());
      } catch (IOException ie) {
        throw new IllegalStateException("Unable to render error message");
      }
    }
  }
}
