package view;

import java.io.IOException;

/**
 * Represents a text view used for displaying messages through appending the Appendable.
 */
public class ImageEditorTextView implements ImageEditorView {
  private final Appendable destination;

  /**
   * Constructs a Text view with an Appendable that cannot be null.
   * @param destination The Appendable where the view will display information for the user.
   */
  public ImageEditorTextView(Appendable destination) {
    if (destination == null) {
      throw new IllegalArgumentException("Must provide Appendable");
    }
    this.destination = destination;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    destination.append(message + "\n");
  }
}
