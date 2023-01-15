package model;

import image.IImage;
import java.util.Map;

/**
 * Interface for a model.
 */
public interface IModel {

  /**
   * Enum for the possbile types of component modifications that can occur.
   */
  enum ComponentType { Red, Green, Blue, Intensity, Value, Luma }

  /**
   * Retrieves and returns the map.
   *
   * @return The map of Strings to IImages
   */
  Map<String, IImage> getMap();

  /**
   * Adds an image to the model map.
   *
   * @param image Image to add.
   * @param key   name of image in the map.
   */
  void addToMap(IImage image, String key);
}
