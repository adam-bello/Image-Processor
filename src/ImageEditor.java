import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Scanner;

import controller.GUIController;
import controller.ImageEditorController;
import controller.ImageEditorControllerImpl;
import model.ImageEditorModel;
import model.ImageEditorModelImpl;
import view.ImageEditorGUIView;
import view.ImageEditorTextView;
import view.ImageEditorView;

/**
 * The main class for running the program. Creates a model, view, and the controller as
 * indicated by the user.
 */
public class ImageEditor {

  /**
   * The main method for running the program which can be ran in interactive mode, script mode,
   * or GUI mode.
   * @param args - The command line arguments for the program.
   */
  public static void main(String[] args) {
    ImageEditorModel model = new ImageEditorModelImpl();
    ImageEditorView view = new ImageEditorTextView(System.out);
    Readable readable = new InputStreamReader(System.in);
    if (args.length == 0) {
      GUIController controller = new GUIController(model);
      ImageEditorGUIView frame = new ImageEditorGUIView("Image Editor", model);
      controller.setView(frame);
    }
    else if (args[0].equalsIgnoreCase("-text")) {
      ImageEditorController controller = new ImageEditorControllerImpl(model, view, readable);
      controller.start();
    } else if (args[0].equalsIgnoreCase("-file")) {
      Scanner sc;
      try {
        sc = new Scanner(new FileInputStream(args[1]));
      } catch (FileNotFoundException e) {
        System.out.println("File " + args[1] + " not found!");
        return;
      }
      StringBuilder builder = new StringBuilder();
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        builder.append(s + System.lineSeparator());
      }
      readable = new StringReader(builder.toString());
      ImageEditorController controller = new ImageEditorControllerImpl(model, view, readable);
      controller.start();
    }
  }


}
