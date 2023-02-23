package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.Blur;
import controller.commands.Brighten;
import controller.commands.Darken;
import controller.commands.FlipHorizontal;
import controller.commands.FlipVertical;
import controller.commands.ImageEditCommand;
import controller.commands.Load;
import controller.commands.Save;
import controller.commands.Sharpen;
import controller.commands.VisualizeBlue;
import controller.commands.VisualizeGreen;
import controller.commands.VisualizeIntensity;
import controller.commands.VisualizeLuma;
import controller.commands.VisualizeRed;
import controller.commands.VisualizeSepia;
import controller.commands.VisualizeValue;
import model.ImageEditorModel;
import view.ImageEditorView;

/**
 * Represents an implementation of the ImageEditorController used for communicating between the user
 * and the model and telling the view what to do.
 */
public class ImageEditorControllerImpl implements ImageEditorController {
  private final ImageEditorModel model;
  private final ImageEditorView view;
  private final Readable input;

  /**
   * The Controller takes in a model, view, and input from the user in the form of a Readable.
   * @param model The model, the controller will be sending information to
   * @param view The view the controller will call to display information
   * @param input The input from the user.
   */
  public ImageEditorControllerImpl(ImageEditorModel model, ImageEditorView view, Readable input) {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("Must provide model, view, and Readable");
    }
    this.model = model;
    this.view = view;
    this.input = input;
  }

  private HashMap<String, Function<Scanner, ImageEditCommand>> initKnownCommands() {
    HashMap<String, Function<Scanner, ImageEditCommand>> knownCommands = new HashMap<>();
    knownCommands.put("load", sc -> new Load(sc.next(), sc.next()));
    knownCommands.put("save", sc -> new Save(sc.next(), sc.next(), sc.next(), this.view));
    knownCommands.put("visualize-red", sc -> new VisualizeRed(sc.next(), sc.next(), this.view));
    knownCommands.put("visualize-green", sc -> new VisualizeGreen(sc.next(), sc.next(), this.view));
    knownCommands.put("visualize-blue", sc -> new VisualizeBlue(sc.next(), sc.next(), this.view));
    knownCommands.put("visualize-value", sc -> new VisualizeValue(sc.next(), sc.next(), this.view));
    knownCommands.put("visualize-intensity", sc -> new VisualizeIntensity(sc.next(), sc.next(),
            this.view));
    knownCommands.put("visualize-luma", sc -> new VisualizeLuma(sc.next(), sc.next(), this.view));
    knownCommands.put("flip-horizontal", sc -> new FlipHorizontal(sc.next(), sc.next(), this.view));
    knownCommands.put("flip-vertical", sc -> new FlipVertical(sc.next(), sc.next(), this.view));
    knownCommands.put("brighten", sc -> new Brighten(sc.nextInt(), sc.next(), sc.next(),
            this.view));
    knownCommands.put("darken", sc -> new Darken(sc.nextInt(), sc.next(), sc.next(), this.view));
    knownCommands.put("blur", sc -> new Blur(sc.next(), sc.next(), this.view));
    knownCommands.put("sharpen", sc -> new Sharpen(sc.next(), sc.next(), this.view));
    knownCommands.put("visualize-sepia", sc -> new VisualizeSepia(sc.next(), sc.next(), this.view));

    return knownCommands;
  }

  /**
   * The method for starting the program. It will continue to run the program until the user inputs
   * q or quit to end it. Otherwise, it takes in commands as well as arguments for those commands
   * to send to the model.
   */
  public void start() {
    Scanner sc = new Scanner(this.input);
    String input;
    HashMap<String, Function<Scanner, ImageEditCommand>> knownCommands = initKnownCommands();

    while (sc.hasNext()) {
      ImageEditCommand c;
      input = sc.next();

      if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
        this.renderMessage("Successfully Quit");
        return;
      }
      Function<Scanner, ImageEditCommand> cmd =
              knownCommands.getOrDefault(input, null);
      if (cmd == null) {
        this.renderMessage("Unknown Command. Try again!");
      } else {
        try {
          c = cmd.apply(sc);
          c.execute(model);
        } catch (NoSuchElementException nse) {
          this.renderMessage("Illegal arguments for " + input);
        }
      }
    }
  }


  /**
   * Helper method used for rendering messages from the view.
   * @param message The message to be rendered
   * @throws IllegalStateException if the view cannot render the message.
   */
  private void renderMessage(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Unable to render message");
    }
  }
}