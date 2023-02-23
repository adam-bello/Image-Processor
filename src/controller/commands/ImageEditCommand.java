package controller.commands;

import model.ImageEditorModel;

/**
 * An interface for editing commands. Implementations of this interface represent commands that
 * can be by the model to edit an image. It has one method that executes the command that it
 * represents using the given model.
 */
public interface ImageEditCommand {

  /**
   * Executes this command using the given model.
   * @param model The model with which to execute the command.
   */
  void execute(ImageEditorModel model);
}
