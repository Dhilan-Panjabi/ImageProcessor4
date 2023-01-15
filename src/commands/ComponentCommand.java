package commands;

import image.IImage;
import model.IModel;
import model.IModel.ComponentType;

/**
 * ComponentCommand class defines the constructor and apply method modify an image.
 */
public class ComponentCommand extends AbstractComponent {

  private final String imageName;
  private final String destName;
  private final ComponentType type;

  /**
   * Constructor for a ComponentCommand.
   *
   * @param imageName name of the original image.
   * @param destName  name of the new image.
   * @param type      type of component alteration.
   */
  public ComponentCommand(String imageName, String destName, ComponentType type) {
    if (imageName == null || destName == null || type == null) {
      throw new IllegalArgumentException("Parameter is null");
    }

    this.imageName = imageName;
    this.destName = destName;
    this.type = type;
  }

  @Override
  public void apply(IModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    }

    IImage image = doComponent(type, model, this.imageName);
    model.addToMap(image, this.destName);
  }

  @Override
  public String toString() {
    return "Component command was completed successfully.\n";
  }
}
