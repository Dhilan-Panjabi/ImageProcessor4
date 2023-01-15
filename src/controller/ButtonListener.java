package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * ButtonListener class implements the ActionListener.
 */
public class ButtonListener implements ActionListener {
  Map<String, Runnable> buttonClickedActions;

  /**
   * Empty default constructor.
   */
  public ButtonListener() {
    //empty
  }

  /**
   * Set the map for key typed events. Key typed events in Java Swing are characters.
   */

  public void setButtonClickedActionMap(Map<String, Runnable> map) {
    buttonClickedActions = map;
  }

  /**
   * Action performed method performs the action needed and runs the action command.
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (buttonClickedActions.containsKey(e.getActionCommand())) {

      buttonClickedActions.get(e.getActionCommand()).run();
    }
  }
}
