package commands;

import image.IImage;
import image.Image;
import model.IModel;

/**
 * LoadCommand class defines the constructor and apply method to be able to load an image properly.
 */
public class LoadCommand implements ICommand {

  private final String filename;

  private final String key;

  /**
   * LoadCommand constructor takes in name of the image from the hashmap and creates a new Image for
   * the program to use.
   *
   * @param filename the given filename
   */
  public LoadCommand(String filename, String key) {
    if (filename == null || key == null) {
      throw new IllegalArgumentException("Parameter is null");
    }

    this.filename = filename;
    this.key = key;
  }

  @Override
  public void apply(IModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    }

    load(this.filename, this.key, model);
  }

  /**
   * Load an image from the specified path and refer it to henceforth in the program by the given
   * image name.
   *
   * @param filename filename of ppm.
   * @param key      key to put in map.
   * @param model    model that the image is being loaded into.
   */
  private void load(String filename, String key, IModel model) {
    IImage i = new Image(filename);
    model.addToMap(i, key);
  }

  @Override
  public String toString() {
    return "Load command was completed successfully.\n";
  }
}
