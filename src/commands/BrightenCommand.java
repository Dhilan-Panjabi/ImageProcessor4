package commands;

import image.Color;
import image.IImage;
import image.Image;
import model.IModel;

/**
 * BrightenCommand class defines the constructor and apply method modify an image.
 */
public class BrightenCommand implements ICommand {

  private final String imageName;
  private final String destName;

  private final int increment;

  /**
   * Constructor for a BrightenCommand.
   *
   * @param imageName name of the original image.
   * @param destName  name of the new image.
   * @param increment how much to increase or decrease the color values.
   */
  public BrightenCommand(String imageName, String destName, int increment) {
    if (imageName == null || destName == null) {
      throw new IllegalArgumentException("Parameter is null");
    }

    this.imageName = imageName;
    this.destName = destName;
    this.increment = increment;
  }

  @Override
  public void apply(IModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    }

    //IImage image = model.getMap().get(this.imageName).brighten(increment);
    IImage image = brighten(increment, model);
    model.addToMap(image, this.destName);

  }


  /**
   * Brighten the image by the given increment to create a new image, referred to henceforth by the
   * given destination name.
   *
   * @param increment the factor that the image should be brightened by.
   * @param model     The model that the command is run on.
   * @return a new Image.IImage that is brightened.
   */
  private IImage brighten(int increment, IModel model) {
    int width = model.getMap().get(this.imageName).getWidth();
    int height = model.getMap().get(this.imageName).getHeight();

    IImage modifiedImage = new Image(width, height);

    int rValue;
    int gValue;
    int bValue;

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {

        rValue = (int) (255 * model.getMap().get(this.imageName)
                .getImageOfPixels()[row][col].getPixelColor().getRed()) + increment;
        gValue = (int) (255 * model.getMap().get(this.imageName)
                .getImageOfPixels()[row][col].getPixelColor().getGreen()) + increment;
        bValue = (int) (255 * model.getMap().get(this.imageName)
                .getImageOfPixels()[row][col].getPixelColor().getBlue()) + increment;

        rValue = cap(rValue);
        gValue = cap(gValue);
        bValue = cap(bValue);

        Color color = new Color(rValue / 255.0, gValue / 255.0, bValue / 255.0);
        modifiedImage.getPixelAt(row, col).changePixelColor(color);

      }
    }
    return modifiedImage;
  }

  /**
   * Returns the capped value.
   *
   * @param colorValue Color to cap.
   * @return the capped value.
   */
  private static int cap(int colorValue) {
    if (colorValue > 255) {
      return 255;
    } else {
      return Math.max(colorValue, 0);
    }
  }

  @Override
  public String toString() {
    return "Brighten command was completed successfully.\n";
  }
}
