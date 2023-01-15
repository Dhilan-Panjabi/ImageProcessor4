package commands;

import model.IModel;

/**
 * Interface for commands.
 */
public interface ICommand {

  /**
   * Apply method calls a command on an image.
   *
   * @param model given model
   */
  void apply(IModel model);

  String toString();

}
