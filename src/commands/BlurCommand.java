package commands;

import image.Color;
import image.IImage;
import image.Image;
import model.IModel;

/**
 * BlurCommand class defines the constructor and apply method modify an image.
 */
public class BlurCommand extends AbstractFilter {

  private final String imageName;
  private final String destName;

  /**
   * Constructor for a BlurCommand.
   *
   * @param imageName name of the original image.
   * @param destName  name of the new image.
   */
  public BlurCommand(String imageName, String destName) {
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

    IImage image = blur(model);
    model.addToMap(image, this.destName);

  }

  /**
   * Adds a blurred effect to the Image.
   *
   * @param model The model that the command is performed on.
   * @return the blurred image.
   */
  private IImage blur(IModel model) {
    int width = model.getMap().get(this.imageName).getWidth();
    int height = model.getMap().get(this.imageName).getHeight();

    IImage modifiedImage = new Image(width, height);

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        modifiedImage.getPixelAt(row, col)
                .changePixelColor(this.blurPixel(row, col, width, height, model));
      }
    }

    return modifiedImage;
  }

  private Color blurPixel(int row, int col, int width, int height, IModel model) {
    int blurredR = cap((int) (
            calculateKernelValue("red", -1, -1, row, col,
                    this.calculateBlurEdges(row, col, width, height), model, this.imageName)
                    * 255));
    int blurredG = cap((int) (
            calculateKernelValue("green", -1, -1, row, col,
                    this.calculateBlurEdges(row, col, width, height), model, this.imageName)
                    * 255));
    int blurredB = cap((int) (
            this.calculateKernelValue("blue", -1, -1, row, col,
                    this.calculateBlurEdges(row, col, width, height), model, this.imageName)
                    * 255));

    return new Color(blurredR / 255.0, blurredG / 255.0, blurredB / 255.0);
  }

  private double[] calculateBlurEdges(int row, int col, int width, int height) {
    double[] factors = {1 / 16.0, 1 / 8.0, 1 / 16.0, 1 / 8.0, 1 / 4.0, 1 / 8.0, 1 / 16.0, 1 / 8.0,
                        1 / 16.0};

    for (int r = -1; r <= 1; r++) {
      for (int c = -1; c <= 1; c++) {
        if (!(row + r >= 0 && row + r < height) || !(col + c >= 0
                && col + c < width)) { //not within bounds
          factors[(r + 1) * 3 + (c + 1)] = 0.0;
        }
      }
    }
    return factors;
  }

  @Override
  public String toString() {
    return "Blur command was completed successfully.\n";
  }


}