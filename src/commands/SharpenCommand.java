package commands;

import image.Color;
import image.IImage;
import image.Image;
import model.IModel;

/**
 * SharpenCommand class defines the constructor and apply method modify an image.
 */
public class SharpenCommand extends AbstractFilter {

  private final String imageName;
  private final String destName;

  /**
   * Constructor for a SharpenCommand.
   *
   * @param imageName name of the original image.
   * @param destName  name of the new image.
   */
  public SharpenCommand(String imageName, String destName) {
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

    IImage image = sharpen(model);
    model.addToMap(image, this.destName);

  }

  /**
   * Increases the sharpness aspect of the image.
   *
   * @param model the model that the command is run on.
   *
   * @return the sharpened image.
   */
  private IImage sharpen(IModel model) {
    int width = model.getMap().get(this.imageName).getWidth();
    int height = model.getMap().get(this.imageName).getHeight();

    IImage modifiedImage = new Image(width, height);

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        modifiedImage.getPixelAt(row, col)
            .changePixelColor(this.sharpenPixel(row, col, width, height, model));
      }
    }

    return modifiedImage;
  }

  private Color sharpenPixel(int row, int col, int width, int height, IModel model) {
    int sharpenedR = cap((int) (
        this.calculateKernelValue("red", -2, -2, row, col,
                this.calculateSharpenEdges(row, col, width, height), model, this.imageName) * 255));
    int sharpenedG = cap((int) (
        this.calculateKernelValue("green", -2, -2, row, col,
                this.calculateSharpenEdges(row, col, width, height), model, this.imageName) * 255));
    int sharpenedB = cap((int) (
        this.calculateKernelValue("blue", -2, -2, row, col,
                this.calculateSharpenEdges(row, col, width, height), model, this.imageName) * 255));

    return new Color(sharpenedR / 255.0, sharpenedG / 255.0, sharpenedB / 255.0);
  }

  private double[] calculateSharpenEdges(int row, int col, int width, int height) {
    double[] factors = {-1 / 8.0, -1 / 8.0, -1 / 8.0, -1 / 8.0, -1 / 8.0,
        -1 / 8.0, 1 / 4.0, 1 / 4.0, 1 / 4.0, -1 / 8.0,
        -1 / 8.0, 1 / 4.0, 1.0, 1 / 4.0, -1 / 8.0,
        -1 / 8.0, 1 / 4.0, 1 / 4.0, 1 / 4.0, -1 / 8.0,
        -1 / 8.0, -1 / 8.0, -1 / 8.0, -1 / 8.0, -1 / 8.0};

    for (int r = -2; r <= 2; r++) {
      for (int c = -2; c <= 2; c++) {
        if (!(row + r >= 0 && row + r < height) || !(col + c >= 0
            && col + c < width)) { //not within bounds
          factors[(r + 2) * 5 + (c + 2)] = 0.0;
        }
      }
    }
    return factors;
  }

  @Override
  public String toString() {
    return "Sharpen command was completed successfully.\n";
  }
}