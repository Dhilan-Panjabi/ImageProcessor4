package view;

import image.IImage;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.JComboBox;

/**
 * The interface for our GUI view class.
 */
public interface IView {

  /**
   * Set the label that is showing what the model stores.
   *
   * @param s given string
   */
  void setOpenedFileLabel(String s);

  /**
   * Returns the opened file label.
   *
   * @return the label of the opened file
   */
  String getOpenedFileLabel();

  /**
   * Get the string from the text field and return it.
   *
   * @return the key input string
   */
  String getInputString();

  /**
   * Clear the text field. Note that a more general "setInputString" would work for this purpose but
   * would be incorrect. This is because the text field is not set programmatically in general but
   * input by the user.
   */
  void clearInputString();

  void clearOpenedFileLabel();

  /**
   * Reset the focus on the appropriate part of the view that has the keyboard listener attached to
   * it, so that keyboard events will still flow through.
   */
  void resetFocus();

  /**
   * this is to force the view to have a method to set up the keyboard. The name has been chosen
   * deliberately. This is the same method signature to add a key listener in Java Swing.
   *
   * <p>Thus our Swing-based implementation of this interface will already have such a method.
   *
   * @param listener key listener
   */
  void addKeyListener(KeyListener listener);

  /**
   * this is to force the view to have a method to set up actions for buttons. All the buttons must
   * be given this action listener
   *
   * <p>Thus our Swing-based implementation of this interface will already have such a method.
   *
   * @param listener action listener
   */

  void addActionListener(ActionListener listener);

  void setChosenOptionText(String chosen);

  String getChosenOptionText();

  String getNewKeyText();

  String getModInputText();

  void setNewKeyText(String s);

  void setModInputText(String s);

  void updateCombo(String s);

  void setModInputEnabled(boolean isEnabled);

  JComboBox getComboBox();

  void updateImage(IImage image);

  void updateHistogram(IImage image);
}
