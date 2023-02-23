package controller.commands;

import model.ImageEditorModel;

/**
 * Represents the load method in the model. This can load an image from a pathname into the program
 * for editing.
 */
public class Load implements ImageEditCommand {
  private final String pathname;
  private final String filename;

  /**
   * Constructs a load object with a pathname and a filename.
   * @param pathname the path from where to load a file.
   * @param filename the filename for the program to reference later for editing.
   */
  public Load(String pathname, String filename) {
    this.pathname = pathname;
    this.filename = filename;
  }

  @Override
  public void execute(ImageEditorModel model) {
    String filetype = pathname.substring(pathname.length() - 3);
    model.load(pathname, filename, filetype);
  }
}
