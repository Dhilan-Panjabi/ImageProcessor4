package commands;

import static java.awt.image.BufferedImage.TYPE_BYTE_INDEXED;

import image.Color;
import image.Pixel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.IModel;

/**
 * AbstractUtils implements writePPm and writeImage methods as well as readPPM and readPNG methods.
 */
public abstract class AbstractUtils implements ICommand {
  /**
   * writePPM method writes the ppm data of an Image to a new file.
   *
   * @param filepath given filepath
   * @param filename name of the new file
   * @param model    given model
   * @throws IOException if not written correctly
   */
  public void writePPM(String filepath, String filename, IModel model) throws IOException {
    FileOutputStream output;

    File file = new File(filepath);

    try {
      output = new FileOutputStream(file, true);
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      throw new RuntimeException(e);
    }

    StringBuilder sb = new StringBuilder();

    sb.append("P3\n").append(model.getMap().get(filename).getWidth()).append(" ")
            .append(model.getMap().get(filename).getHeight()).append("\n255\n");

    for (int row = 0; row < model.getMap().get(filename).getHeight(); row++) {
      for (int col = 0; col < model.getMap().get(filename).getWidth(); col++) {
        sb.append(
                (int) (model.getMap().get(filename).getPixelAt(row, col).getPixelColor().getRed()
                        * 255));
        sb.append("\n");
        sb.append(
                (int) (model.getMap().get(filename).getPixelAt(row, col).getPixelColor().getGreen()
                        * 255));
        sb.append("\n");
        sb.append(
                (int) (model.getMap().get(filename).getPixelAt(row, col).getPixelColor().getBlue()
                        * 255));
        sb.append("\n");
      }
    }

    String s = sb.toString();
    byte[] byteArray = s.getBytes();

    try {
      output.write(byteArray);
    } catch (IOException e) {
      System.out.println("Didn't write correctly.");
      throw new RuntimeException("Didn't write correctly.");
    }
  }

  /**
   * Writes the data of any common image type to a new file.
   *
   * @param filepath given filepath
   * @param filename name of the new file
   * @param filetype file extension to be saves as
   * @param model    given model
   * @throws IOException when image is nto written correctly
   */
  public void writeImage(String filepath, String filename, String filetype, IModel model)
          throws IOException {
    BufferedImage bufferedImage = new BufferedImage(model.getMap().get(filename).getWidth(),
            model.getMap().get(filename).getHeight(), TYPE_BYTE_INDEXED);
    File file = new File(filepath);

    for (int row = 0; row < model.getMap().get(filename).getHeight(); row++) {
      for (int col = 0; col < model.getMap().get(filename).getWidth(); col++) {
        int redColor = (int) (
                model.getMap().get(filename).getPixelAt(row, col).getPixelColor().getRed() * 255);
        int greenColor = (int) (
                model.getMap().get(filename).getPixelAt(row, col).getPixelColor().getGreen() * 255);
        int blueColor = (int) (
                model.getMap().get(filename).getPixelAt(row, col).getPixelColor().getBlue() * 255);
        int a = 255;
        int rgb = (a << 24) | (redColor << 16) | (greenColor << 8) | blueColor;
        bufferedImage.setRGB(col, row, rgb);
      }
    }

    try {
      ImageIO.write(bufferedImage, filetype.toUpperCase(), file);
    } catch (IOException e) {
      throw new IllegalStateException("Didn't write correctly");
    }
  }

  /**
   * readPPM was given to us and reads in a file.
   *
   * @param fileName PPM file we want to read
   * @return a 2D array of Pixels representing an image
   */
  public static Pixel[][] readPPM(String fileName) {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(fileName));
    } catch (FileNotFoundException e) {
      System.out.println("File " + fileName + " not found!");
      throw new IllegalArgumentException("Invalid filename");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();

    //System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    //System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    //System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    Pixel[][] pixelArray = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        double r = sc.nextInt() / 255.0;
        double g = sc.nextInt() / 255.0;
        double b = sc.nextInt() / 255.0;

        Color c = new Color(r, g, b);
        Pixel p = new Pixel(c);
        pixelArray[i][j] = p;
      }
    }
    return pixelArray;
  }

  /**
   * A non ppm was given to us and reads in a file.
   *
   * @param filename file we want to read
   * @return a 2D array of Pixels representing an image
   */
  public static Pixel[][] readPNG(String filename) {

    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(filename));
    } catch (IOException e) {
      throw new IllegalStateException("Didn't read png correctly");
    }

    int height = img.getHeight();
    int width = img.getWidth();

    Pixel[][] pixelArray = new Pixel[height][width];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int color = img.getRGB(col, row);

        double r = ((color & 0xff0000) >> 16) / 255.0;
        double g = ((color & 0xff00) >> 8) / 255.0;
        double b = (color & 0xff) / 255.0;

        pixelArray[row][col] = new Pixel(new Color(r, g, b));

      }
    }
    return pixelArray;
  }
}
