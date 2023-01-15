package commands;

import java.io.IOException;
import model.IModel;

/**
 * SaveCommand class defines the constructor and apply method to be able to save an image to a
 * directory.
 */
public class SaveCommand extends AbstractUtils {

  private final String filepath;
  private final String filename;

  private final String filetype;

  /**
   * SaveCommand constructor takes in the filepath and name of the image from the hashmap.
   *
   * @param filepath path where image is saved
   * @param filename name of the file to be saved
   */
  public SaveCommand(String filepath, String filename, String filetype) {
    if (filepath == null || filename == null || filetype == null) {
      throw new IllegalArgumentException("Parameter is null");
    }

    this.filename = filename;
    this.filepath = filepath;
    this.filetype = filetype;
  }

  @Override
  public void apply(IModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    }

    save(this.filepath, this.filename, this.filetype, model);
  }

  /**
   * Save the image with the given name to the specified path which should include the name of the
   * file.
   *
   * @param filepath filepath to output ppm file to.
   * @param filename the name of the file that is being created.
   * @param filetype the filetype of the new image.
   * @param model the model that the save is pulling an image from.
   */
  private void save(String filepath, String filename, String filetype, IModel model) {
    try {
      switch (filetype) {
        case "ppm":
          this.writePPM(filepath, filename, model);
          break;
        case "png":
        case "jpg":
        case "jpeg":
        case "bmp":
          this.writeImage(filepath, filename, filetype, model);
          System.out.println(filepath);
          break;
        default:
          throw new IllegalArgumentException("Invalid filetype");
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String toString() {
    return "Save command was completed successfully.\n";
  }
}
