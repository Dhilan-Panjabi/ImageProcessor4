package commands;

import image.IImage;
import model.IModel;
import model.IModel.ComponentType;

/**
 * GrayscaleCommand class defines the constructor and apply method modify an image.
 */
public class GrayscaleCommand extends AbstractComponent {

  private final String imageName;
  private final String destName;

  /**
   * Constructor for a GrayScaleCommand.
   *
   * @param imageName name of the original image.
   * @param destName  name of the new image.
   */
  public GrayscaleCommand(String imageName, String destName) {
    if (imageName == null || destName == null) {
      throw new IllegalArgumentException("Parameter is null");
    }

    this.imageName = imageName;
    this.destName = destName;
  }

  @Override
  public void apply(IModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    }

    IImage image = doComponent(ComponentType.Luma, model, this.imageName);
    model.addToMap(image, this.destName);

  }

  @Override
  public String toString() {
    return "GrayScale command was completed successfully.\n";
  }
}