package commands;

import image.Color;
import image.IImage;
import image.Image;
import model.IModel;

/**
 * SepiaCommand class defines the constructor and apply method modify an image.
 */
public class SepiaCommand extends AbstractFilter {

  private final String imageName;
  private final String destName;

  /**
   * Constructor for a SepiaCommand.
   *
   * @param imageName name of the original image.
   * @param destName  name of the new image.
   */
  public SepiaCommand(String imageName, String destName) {
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

    IImage image = sepia(model);
    model.addToMap(image, this.destName);

  }

  /**
   * Adds a yellowish hue to the image.
   *
   * @param model the model that the command is run on.
   *
   * @return the modified image.
   */
  private IImage sepia(IModel model) {
    int width = model.getMap().get(this.imageName).getWidth();
    int height = model.getMap().get(this.imageName).getHeight();

    IImage modifiedImage = new Image(width, height);

    double[] factors = {0.393, 0.769, 0.189, 0.349, 0.686, 0.168, 0.272, 0.534, 0.131};

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        modifiedImage.getPixelAt(row, col)
            .changePixelColor(
                this.getTransformedColor(model.getMap().get(this.imageName)
                        .getImageOfPixels()[row][col].getPixelColor(), factors));
      }
    }

    return modifiedImage;
  }

  /**
   * Modifies the given color using the multiplication factors provided in the array.
   *
   * @param c       the color that is being modified.
   * @param factors a list representing the matrix to multiply with the r,g and b values.
   * @return the transformed color.
   */
  private Color getTransformedColor(Color c, double[] factors) {
    int transformedRed = cap((int) (
        ((c.getRed() * factors[0]) + (c.getGreen() * factors[1]) + (c.getBlue() * factors[2]))
            * 255));
    int transformedGreen = cap((int) (
        ((c.getRed() * factors[3]) + (c.getGreen() * factors[4]) + (c.getBlue() * factors[5]))
            * 255));
    int transformedBlue = cap((int) (
        ((c.getRed() * factors[6]) + (c.getGreen() * factors[7]) + (c.getBlue() * factors[8]))
            * 255));

    return new Color(transformedRed / 255.0, transformedGreen / 255.0, transformedBlue / 255.0);

  }

  @Override
  public String toString() {
    return "Sepia command was completed successfully.\n";
  }
}