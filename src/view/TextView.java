package view;

import java.io.IOException;
import model.IModel;

/**
 * TextView class renders messages to the user as needed.
 */
public class TextView implements ITextView {

  private final Appendable output;

  /**
   * TextView constructor.
   * @param model given model
   * @param output given appendable for output
   */
  public TextView(IModel model, Appendable output) {
    if (model == null || output == null) {
      throw new IllegalArgumentException("Null parameter.");
    }

    this.output = output;
  }

  /**
   * Renders a certain message to the user.
   * @param message message to be displayed
   * @throws IOException when something goes wrong while displaying message
   */
  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.output.append(message);
    } catch (IOException e) {
      throw new IOException("Something went wrong while writing to the appendable.");
    }
  }
}
