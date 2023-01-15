package image;

import static commands.AbstractUtils.readPNG;
import static commands.AbstractUtils.readPPM;

/**
 * Class that represents an Image.
 */
public class Image implements IImage {

  private final int width;
  private final int height;
  private final Pixel[][] imageOfPixels;

  /**
   * Constructor for image class.
   *
   * @param fileName Name of the ppm file that is being converted to an image.
   */
  public Image(String fileName) {
    // initialize imageOfPixels 2D array here
    String filetype = fileName.split("\\.")[fileName.split("\\.").length - 1];

    switch (filetype) {
      case "ppm":
        this.imageOfPixels = readPPM(fileName);
        break;
      case "png":
      case "jpg":
      case "jpeg":
      case "bmp":
        this.imageOfPixels = readPNG(fileName);
        break;
      default:
        throw new IllegalArgumentException("Unsupported filetype");
    }
    this.width = this.imageOfPixels[0].length;
    this.height = this.imageOfPixels.length;
  }

  /**
   * Constructor that creates an empty image of the given size.
   *
   * @param width  width of image.
   * @param height height of the image.
   */
  public Image(int width, int height) {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Invalid dimensions.");
    }

    // initialize imageOfPixels 2D array here
    this.imageOfPixels = new Pixel[height][width];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        this.imageOfPixels[row][col] = new Pixel(new Color(0.0, 0.0, 0.0));
      }
    }
    this.width = width;
    this.height = height;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public Pixel[][] getImageOfPixels() {
    return this.imageOfPixels;
  }

  @Override
  public Pixel getPixelAt(int xPos, int yPos) {
    return imageOfPixels[xPos][yPos];
  }
}
     