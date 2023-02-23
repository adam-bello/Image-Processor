package view;

import java.io.IOException;

/**
 * An interface for the view of the image editor program. It has one method - renderMessage which
 * renders the given message.
 */
public interface ImageEditorView {

  void renderMessage(String message) throws IOException;
}
