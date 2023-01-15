package commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.awt.Point;
import java.util.List;


import image.Color;
import image.IImage;
import image.Image;
import model.IModel;

/**
 * MosaickingCommand class defines the constructor and apply method modify an image.
 */
public class MosaickingCommand implements ICommand {

  private final String imageName;

  private final String destName;

  private static int seeds;

  Random random;


  /**
   * Constructor for a MosaickingCommand which takes in a random which is only used for testing.
   *
   * @param imageName the image name
   * @param destName  the destination name
   * @param seeds     the number of seeds
   * @param random    random number generator
   */
  public MosaickingCommand(String imageName, String destName, int seeds, Random random) {
    Objects.requireNonNull(imageName);
    Objects.requireNonNull(destName);
    if (seeds < 0) {
      throw new IllegalArgumentException("Must have above one seed");
    }
    this.imageName = imageName;
    this.destName = destName;
    this.seeds = seeds;
    this.random = random;
  }

  /**
   * Constructor for a MosaickingCommand.
   *
   * @param imageName name of the original image.
   * @param destName  name of the new image.
   * @param seeds     number of seeds.
   */
  public MosaickingCommand(String imageName, String destName, int seeds) {
    Objects.requireNonNull(imageName);
    Objects.requireNonNull(destName);
    if (seeds < 0) {
      throw new IllegalArgumentException("Must have above one seed");
    }
    this.imageName = imageName;
    this.destName = destName;
    this.seeds = seeds;
  }

  @Override
  public void apply(IModel model) {
    Objects.requireNonNull(model);
    IImage mosaickingImage = mosaicImage(seeds, model);
    model.addToMap(mosaickingImage, this.destName);
  }

  //The mosaic image method which takes in the number of seeds and the model given by the user.
  //It then creates a new image and then creates a list of points which are the seeds.
  //It then creates a map of the seeds and the colors of the seeds.
  //It then goes through the image and finds the clOSest seed to the pixel and
  // then changes the color of the pixel to the color of the seed it is clOSest to
  // and creates the mosaic effect
  //It then returns the new image.
  private IImage mosaicImage(int seeds, IModel model) {
    int width = model.getMap().get(this.imageName).getWidth();
    int height = model.getMap().get(this.imageName).getHeight();
    IImage modifiedImage = new Image(width, height);
    List<Point> lOS = this.getRandomSeeds(seeds, width, height);
    Map<Integer, Color> seedColorMap = this.makeSeedColorMap(lOS, model);
    List<Integer> seedColorList = new ArrayList<>(seedColorMap.keySet());
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        modifiedImage.getPixelAt(row, col)
                .changePixelColor(seedColorMap.get(seedColorList.get(this.findClOSestSeed(lOS,
                        new Point(col, row)))));
      }
    }
    return modifiedImage;
  }

  //This method takes in the number of seeds and the width and height of the image and
  // then creates a list of random points in the image and returns the list.
  private List<Point> getRandomSeeds(int seeds, int width, int height) {
    List<Point> lOS = new ArrayList<>();
    Random rand = new Random();
    for (int i = 0; i < seeds; i++) {
      Point seed = new Point(rand.nextInt(width), rand.nextInt(height));
      lOS.add(seed);
    }
    return lOS;
  }

  //takes in the list of seeds and the model and creates a map of the color of the seeds.
  private Map<Integer, Color> makeSeedColorMap(List<Point> lOS, IModel model) {
    Map<Integer, Color> seedColorMap = new HashMap<>();
    for (int i = 0; i < lOS.size(); i++) {
      seedColorMap.put(i, model.getMap().get(this.imageName)
              .getPixelAt(lOS.get(i).y, lOS.get(i).x).getPixelColor());
    }
    return seedColorMap;
  }

  //Takes in the list of seeds and the point and finds clOSet seed to the point and
  // returns the index
  private int findClOSestSeed(List<Point> lOS, Point pixel) {
    int clOSestSeed = 0;
    int minDistance = Integer.MAX_VALUE;
    for (int i = 0; i < lOS.size(); i++) {
      int distance = Math.abs(pixel.x - lOS.get(i).x) + Math.abs(pixel.y - lOS.get(i).y);
      if (distance < minDistance) {
        minDistance = distance;
        clOSestSeed = i;
      }
    }
    return clOSestSeed;
  }

  @Override
  public String toString() {
    return "Mosaic command was completed successfully.\n";
  }
}

