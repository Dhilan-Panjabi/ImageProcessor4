package image;

/**
 * Class that represents a color.
 */
public class  Color {

  private double red;
  private double green;
  private double blue;

  /**
   * Constructor for Color class.
   *
   * @param red   red portion of color.
   * @param green green portion of color.
   * @param blue  blue portion of color.
   */
  public Color(double red, double green, double blue) {
    if (red < 0.0 || green < 0.0 || blue < 0.0) {
      throw new IllegalArgumentException("Negative color");
    } else if (red > 1.0 || green > 1.0 || blue > 1.0) {
      throw new IllegalArgumentException("Color too large");
    }

    this.red = red;
    this.green = green;
    this.blue = blue;
  }


  public double getRed() {
    return this.red;
  }

  public double getGreen() {
    return this.green;
  }

  public double getBlue() {
    return this.blue;
  }
}
