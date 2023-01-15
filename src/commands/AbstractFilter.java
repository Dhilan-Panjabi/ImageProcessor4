package commands;

import model.IModel;

/**
 * AbstractFilter class implements all methods shared by filter command classes.
 */
public abstract class AbstractFilter implements ICommand {

  protected double calculateKernelValue(String type, int rIncStart, int cIncStart, int row, int col,
                                        double[] factors, IModel model, String imageName) {
    double newValue = 0.0;
    int rowInc = rIncStart;
    int colInc = cIncStart;

    for (double factor : factors) {
      if (factor != 0) {
        switch (type) {
          case "red":
            newValue += (factor * model.getMap().get(imageName).getImageOfPixels()[row + rowInc][col
                    + colInc].getPixelColor()
                    .getRed());
            break;
          case "green":
            newValue += (factor * model.getMap().get(imageName).getImageOfPixels()[row + rowInc][col
                    + colInc].getPixelColor()
                    .getGreen());
            break;
          case "blue":
            newValue += (factor * model.getMap().get(imageName).getImageOfPixels()[row + rowInc][col
                    + colInc].getPixelColor()
                    .getBlue());
            break;
          default:
            throw new IllegalArgumentException("Invalid color.");
        }
      }

      if (colInc == (-1 * cIncStart)) {
        rowInc++;
        colInc = cIncStart;
      } else {
        colInc++;
      }
    }

    return newValue;
  }

  /**
   * Returns the capped value.
   *
   * @param colorValue Color to cap.
   * @return the capped value.
   */
  protected static int cap(int colorValue) {
    if (colorValue > 255) {
      return 255;
    } else {
      return Math.max(colorValue, 0);
    }
  }


}
