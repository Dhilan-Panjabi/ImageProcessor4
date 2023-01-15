package view;

import java.io.IOException;

/**
 * ITextView interface defines render message method in the TextView class.
 */
public interface ITextView {
  void renderMessage(String message) throws IOException;
}
