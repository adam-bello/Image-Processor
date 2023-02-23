import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import view.ImageEditorTextView;
import view.ImageEditorView;

import static org.junit.Assert.assertEquals;

/**
 * Represents a class for testing the View of the program. It tests that messages are accurately
 * appended to the Appendable and IOExceptions are handled correctly.
 */
public class ImageEditorViewTest {
  private ImageEditorView view1;
  private ImageEditorView view2;
  private Appendable ap1;
  private Appendable ap2;

  @Before
  public void setup() {
    ap1 = new StringBuilder();
    ap2 = new StringBuilder();
    view1 = new ImageEditorTextView(ap1);
    view2 = new ImageEditorTextView(ap2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    new ImageEditorTextView(null);
  }

  @Test
  public void testRenderMessage() {
    try {
      view1.renderMessage("Hello");
      view2.renderMessage("Command executed successfully!");
    } catch (IOException ie) {
      throw new IllegalStateException("Unable to render message");
    }
    assertEquals("Hello\n", ap1.toString());
    assertEquals("Command executed successfully!\n", ap2.toString());
  }

  @Test (expected = IllegalStateException.class)
  public void testRenderMessageIOException() {
    Appendable errorAppendable = new MockAppendable();
    ImageEditorView errorView = new ImageEditorTextView(errorAppendable);
    try {
      errorView.renderMessage("HelloWorld");
    } catch (IOException ie) {
      throw new IllegalStateException("Unable to render message");
    }

  }
}
