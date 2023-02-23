import java.io.IOException;

/**
 * Class for creating mock appendables which always throw IOExceptions
 * when the append method is called.
 */
public class MockAppendable implements Appendable {

  public Appendable append(CharSequence c) throws IOException {
    throw new IOException("Error during append");
  }

  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Error during append");
  }

  public Appendable append(char c) throws IOException {
    throw new IOException("Error during append");
  }
}