package image;

/**
 * Interface for IImage.
 */
public interface IImage {

  /**
   * Getter for the width of an image.
   *
   * @return the width of the image
   */
  int getWidth();

  /**
   * Getter for the height of an image.
   *
   * @return the height of the image
   */
  int getHeight();

  /**
   * Gets the Pixel at a certain position.
   *
   * @param xPos row number
   * @param yPos column number
   * @return a Pixel
   */
  Pixel getPixelAt(int xPos, int yPos);

  Pixel[][] getImageOfPixels();


}
