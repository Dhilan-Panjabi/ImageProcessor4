package model;

import image.IImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a collection of images.
 */
public class Model implements IModel {

  private final Map<String, IImage> colOfImages;

  public Model() {
    this.colOfImages = new HashMap<>();

  }

  public Map<String, IImage> getMap() {
    return this.colOfImages;
  }

  @Override
  public void addToMap(IImage image, String key) {
    if (image == null) {
      throw new IllegalArgumentException("null image passed in");
    }

    this.colOfImages.put(key, image);
  }
}
