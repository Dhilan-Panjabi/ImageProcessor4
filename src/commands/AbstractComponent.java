package commands;

import image.Color;
import image.IImage;
import image.Image;
import model.IModel;
import model.IModel.ComponentType;

/**
 * AbstractComponent class for shared methods between grayscale and component classes.
 */
public abstract class AbstractComponent implements ICommand {

  /**
   * Performs grayscale function on an image given the type.
   *
   * @param type      of grayscale
   * @param model     the model that the command is run on.
   * @param imageName name of image in the map.
   * @return a new Image that is grayscaled
   */
  protected IImage doComponent(ComponentType type, IModel model, String imageName) {
    int width = model.getMap().get(imageName).getWidth();
    int height = model.getMap().get(imageName).getHeight();

    IImage modifiedImage = new Image(width, height);
    double avg;
    double max;
    double luma;

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {

        switch (type) {
          case Red:
            modifiedImage.getPixelAt(row, col).changePixelColor(
                    new Color(
                            model.getMap().get(imageName).getImageOfPixels()[row][col]
                                    .getPixelColor()
                                    .getRed(), 0.0, 0.0));
            break;
          case Green:
            modifiedImage.getPixelAt(row, col).changePixelColor(
                    new Color(0.0,
                            model.getMap().get(imageName).getImageOfPixels()[row][col]
                                    .getPixelColor()
                                    .getGreen(), 0.0));
            break;
          case Blue:
            modifiedImage.getPixelAt(row, col).changePixelColor(
                    new Color(0.0, 0.0,
                            model.getMap().get(imageName).getImageOfPixels()[row][col]
                                    .getPixelColor()
                                    .getBlue()));
            break;
          case Intensity:
            avg = model.getMap().get(imageName).getImageOfPixels()[row][col].getIntensity();
            modifiedImage.getPixelAt(row, col).changePixelColor(new Color(avg, avg, avg));
            break;
          case Value:
            max = model.getMap().get(imageName).getImageOfPixels()[row][col].getValue();
            modifiedImage.getPixelAt(row, col).changePixelColor(new Color(max, max, max));
            break;
          case Luma:
            luma = model.getMap().get(imageName).getImageOfPixels()[row][col].getLuma();
            modifiedImage.getPixelAt(row, col).changePixelColor(new Color(luma, luma, luma));
            break;
          default:
            throw new IllegalArgumentException("ComponentType passed in was invalid");
        }
      }
    }
    return modifiedImage;
  }

}
