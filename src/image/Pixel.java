package image;

/**
 * Class to represents a pixel.
 */
public class Pixel {

  protected Color color;
  private final double value;
  private final double intensity;
  private final double luma;

  /**
   * Constructor for a pixel.
   *
   * @param color color of the pixel.
   */
  public Pixel(Color color) {
    if (color == null) {
      throw new IllegalArgumentException("Color is null");
    }

    this.color = color;
    this.value = Math.max(Math.max(color.getRed(), color.getBlue()), color.getGreen());
    this.intensity = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
    this.luma = (color.getRed() * .2126) + (color.getGreen() * .7152) + (color.getBlue() * .0722);
  }

  /**
   * Returns the pixel color.
   *
   * @return The color of the pixel.
   */
  public Color getPixelColor() {
    return this.color;
  }

  /**
   * Returns the intensity of the pixel.
   *
   * @return The intensity of the pixel.
   */
  public double getIntensity() {
    return this.intensity;
  }

  /**
   * Returns the luma of the pixel.
   *
   * @return The luma of the pixel.
   */
  public double getLuma() {
    return this.luma;
  }

  /**
   * Returns the value of the pixel.
   *
   * @return The value of the pixel.
   */
  public double getValue() {
    return this.value;
  }

  /**
   * Modifies the color of the pixel to the given color.
   *
   * @param color Color to set the pixel to.
   */
  public void changePixelColor(Color color) {
    this.color = color;
  }
}
