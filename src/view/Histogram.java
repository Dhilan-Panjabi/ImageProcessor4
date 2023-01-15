package view;

import image.IImage;

/**
 * Histogram class generates data for histograms.
 */
public class Histogram {
  private IImage image;
  int[] red;
  int[] green;
  int[] blue;
  int[] intensity;

  /**
   * Histogram constructor takes in an image and generates arrays pf data.
   * @param image given image
   */
  public Histogram(IImage image) {
    this.image = image;
    this.generateArrays();
  }

  /**
   * generateArrays method creates four new arrays and fills them with the frequency of how many
   * times that color component is seen in an image.
   */
  public void generateArrays() {
    red = new int[256];
    green = new int[256];
    blue = new int[256];
    intensity = new int[256];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int redIndex = (int) (image.getPixelAt(i, j).getPixelColor().getRed() * 255);
        int greenIndex = (int) (image.getPixelAt(i, j).getPixelColor().getGreen() * 255);
        int blueIndex = (int) (image.getPixelAt(i, j).getPixelColor().getBlue() * 255);
        int intensityIndex = (int) (image.getPixelAt(i, j).getIntensity() * 255);

        red[redIndex] = red[redIndex] + 1;
        green[greenIndex] = green[greenIndex] + 1;
        blue[blueIndex] = blue[blueIndex] + 1;
        intensity[intensityIndex] = intensity[intensityIndex] + 1;
      }
    }
  }
}
