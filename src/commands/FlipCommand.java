package commands;

import image.IImage;
import image.Image;
import model.IModel;

/**
 * FlipCommand class defines the constructor and apply method to be able to flip an image.
 */
public class FlipCommand implements ICommand {

  private final String imageName;
  private final String destName;
  private final boolean isHorizontal;

  /**
   * Constructor for a FlipCommand.
   *
   * @param imageName    name of the original image.
   * @param destName     name of the new image.
   * @param isHorizontal whether the image is being flipped horizontally or vertically.
   */
  public FlipCommand(String imageName, String destName, boolean isHorizontal) {
    if (imageName == null || destName == null) {
      throw new IllegalArgumentException("Parameter is null");
    }

    this.imageName = imageName;
    this.destName = destName;
    this.isHorizontal = isHorizontal;
  }

  @Override
  public void apply(IModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    }

    IImage image = flipHorizontally(isHorizontal, model);
    model.addToMap(image, this.destName);

  }

  /**
   * Flips an image either horizontally or vertically.
   *
   * @param isHorizontal boolean whether the image will be flipped horizontally or vertically
   * @param model        the model that the command is being used on.
   * @return new Image
   */
  private IImage flipHorizontally(boolean isHorizontal, IModel model) {
    int width = model.getMap().get(this.imageName).getWidth();
    int height = model.getMap().get(this.imageName).getHeight();

    //creates empty array of same size as this
    IImage modifiedImage = new Image(width, height);

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        if (isHorizontal) {
          modifiedImage.getPixelAt(row, col)
                  .changePixelColor(model.getMap().get(this.imageName)
                          .getImageOfPixels()[row][width - 1 - col].getPixelColor());
        } else { //Vertical flip
          modifiedImage.getPixelAt(row, col)
                  .changePixelColor(model.getMap().get(this.imageName)
                          .getImageOfPixels()[height - 1 - row][col].getPixelColor());
        }
      }
    }
    return modifiedImage;
  }

  @Override
  public String toString() {
    return "Flip command was completed successfully.\n";
  }
}
