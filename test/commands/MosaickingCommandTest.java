package commands;

import org.junit.Test;

import java.io.StringReader;
import java.util.Random;

import controller.IController;
import controller.ImageController;
import image.IImage;
import image.Image;
import model.IModel;
import model.Model;
import view.ITextView;
import view.TextView;

import static org.junit.Assert.assertEquals;

/**
 * The test class for the mosaicking command.
 */
public class MosaickingCommandTest {


  IImage image1;

  IModel model;
  MosaickingCommand mosaic;

  StringReader reader;

  IController controller;

  ITextView view;

  @Test
  public void init() {
    this.image1 = new Image("res/cat.png");
    this.model = new Model();
    assertEquals(this.image1, this.image1);
  }

  @Test
  public void testMosaicickingCommand() {
    init();
    this.model.getMap().put("cat image", this.image1);
    assertEquals(0.6313725490196078,
            this.image1.getPixelAt(0, 0).getPixelColor().getRed(), 0.01);
    assertEquals(0.5529411764705883,
            this.image1.getPixelAt(0, 0).getPixelColor().getGreen(), 0.01);
    assertEquals(0.41568627450980394,
            this.image1.getPixelAt(0, 0).getPixelColor().getBlue(), 0.01);


    this.mosaic =
            new MosaickingCommand("cat image", "res/cat.png", 800,
                    new Random(1));
    //All pixels changing after mosaicking
    assertEquals(0.611764705882353,
            this.model.getMap().get("cat image")
                    .getPixelAt(1, 1).getPixelColor().getRed(), 0.01);
    assertEquals(0.5411764705882353,
            this.model.getMap().get("cat image")
                    .getPixelAt(1, 1).getPixelColor().getGreen(), 0.01);
    assertEquals(0.4,
            this.model.getMap().get("cat image")
                    .getPixelAt(1, 1).getPixelColor().getBlue(), 0.01);
    //The pixel value color changing by given position
    assertEquals(0.2980392156862745,
            this.model.getMap().get("cat image")
                    .getPixelAt(100, 100).getPixelColor().getRed(), 0.01);
    assertEquals(0.25882352941176473,
            this.model.getMap().get("cat image")
                    .getPixelAt(100, 100).getPixelColor().getGreen(), 0.01);
    assertEquals(0.1607843137254902,
            this.model.getMap().get("cat image")
                    .getPixelAt(100, 100).getPixelColor().getBlue(), 0.01);
    //Different green value in a part of the image
    assertEquals(0.21568627450980393,
            this.model.getMap().get("cat image")
                    .getPixelAt(224, 200).getPixelColor().getGreen(), 0.01);
    //Different red value in a part of the image
    assertEquals(0.5215686274509804,
            this.model.getMap().get("cat image")
                    .getPixelAt(123, 234).getPixelColor().getRed(), 0.01);
    //Different blue value in a part of the image
    assertEquals(0.09803921568627451,
            this.model.getMap().get("cat image")
                    .getPixelAt(122, 110).getPixelColor().getBlue(), 0.01);
    //Blue value is identical across pixels within the same area
    assertEquals(0.4117647058823529, this.model.getMap().get("cat image")
            .getPixelAt(239, 304).getPixelColor().getBlue(), 0);
    assertEquals(0.4117647058823529, this.model.getMap().get("cat image")
            .getPixelAt(239, 305).getPixelColor().getBlue(), 0);
    assertEquals(0.4, this.model.getMap().get("cat image").getPixelAt(238, 303)
            .getPixelColor().getBlue(), 0);

  }

  @Test
  public void controllerMosaicTest() {
    init();
    this.model = new Model();
    assertEquals(0.6313725490196078,
            this.model.getMap().get("cat image").getPixelAt(0, 0).getPixelColor()
                    .getRed(), 0.01);
    this.view = new TextView(model, new StringBuilder());
    this.reader = new StringReader("load res/cat.png cat "
            + "mosaicking cat res/cat.png" + 1500
            + "save cat image res/cat.png");
    this.controller = new ImageController(this.model, this.view, this.reader);
    this.controller.run();
    assertEquals(0.611764705882353,
            this.model.getMap().get("cat image")
                    .getPixelAt(1, 1).getPixelColor().getRed(), 0.01);
  }

}