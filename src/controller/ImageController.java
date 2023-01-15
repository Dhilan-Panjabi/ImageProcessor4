package controller;

import commands.BlurCommand;
import commands.GrayscaleCommand;
import commands.MosaickingCommand;
import commands.SepiaCommand;
import commands.SharpenCommand;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;

import commands.BrightenCommand;
import commands.ComponentCommand;
import commands.FlipCommand;
import commands.ICommand;
import commands.LoadCommand;
import commands.SaveCommand;
import model.IModel;
import view.ITextView;

/**
 * ImageController adds known commands to a hashmap and creates a new object depending on the input
 * from the user.
 */
public class ImageController implements IController {

  private final IModel model;

  private final ITextView view;
  private final Readable input;

  /**
   * Constructor for Image Controller.
   *
   * @param model The model that is being used.
   * @param input Type of input.
   */
  public ImageController(IModel model, ITextView view, Readable input) {
    if (model == null || input == null || view == null) {
      throw new IllegalArgumentException("Model or input is null");
    }
    this.model = model;
    this.view = view;
    this.input = input;
  }

  @Override
  public void run() {
    Scanner scan = new Scanner(this.input);
    Stack<ICommand> commands = new Stack<>();
    try {
      this.view.renderMessage("Begin entering commands:\n");
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }

    Map<String, Function<Scanner, ICommand>> knownCommands = new HashMap<>();
    knownCommands.put("load", (Scanner s) -> {
      return new LoadCommand(s.next(), s.next());
    });
    knownCommands.put("save", (Scanner s) -> {
      String filepath = s.next();
      String key = s.next();

      String filetype = filepath.substring(filepath.indexOf('.') + 1);

      return new SaveCommand(filepath, key, filetype);
    });
    knownCommands.put("blur", (Scanner s) -> {
      return new BlurCommand(s.next(), s.next());
    });
    knownCommands.put("sharpen", (Scanner s) -> {
      return new SharpenCommand(s.next(), s.next());
    });
    knownCommands.put("grayscale", (Scanner s) -> {
      return new GrayscaleCommand(s.next(), s.next());
    });
    knownCommands.put("sepia", (Scanner s) -> {
      return new SepiaCommand(s.next(), s.next());
    });
    knownCommands.put("component", (Scanner s) -> {
      String s1 = s.next();
      String s2 = s.next();
      String s3 = s.next();
      return new ComponentCommand(s1,
              s2, IModel.ComponentType.valueOf(
              s3.substring(0, 1).toUpperCase() + s3.substring(1).toLowerCase()));
    });
    knownCommands.put("flip", (Scanner s) -> {
      String s1 = s.next(); //v or h
      String s2 = s.next(); //filename
      String s3 = s.next(); //dest

      boolean isHorizontal = true;
      if (s1.equalsIgnoreCase("horizontal")) {
        isHorizontal = true;
      } else if (s1.equalsIgnoreCase("vertical")) {
        isHorizontal = false;
      }

      return new FlipCommand(s2,
              s3, isHorizontal);
    });
    knownCommands.put("brighten", (Scanner s) -> new BrightenCommand(s.next(),
            s.next(), s.nextInt()));
    knownCommands.put("mosaicking", (Scanner s) -> new MosaickingCommand(s.next(),
            s.next(), s.nextInt()));
    while (scan.hasNext()) {
      ICommand c;
      String in = scan.next();
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        try {
          this.view.renderMessage("Quitting program...\n");
        } catch (IOException e) {
          throw new IllegalStateException(e);
        }
        return;
      }
      Function<Scanner, ICommand> cmd = knownCommands.getOrDefault(in, null);
      if (cmd == null) {
        throw new IllegalArgumentException();
      } else {
        c = cmd.apply(scan);
        commands.add(c);
        c.apply(this.model);
        try {
          this.view.renderMessage(c.toString());
        } catch (IOException e) {
          throw new IllegalStateException(e);
        }
      }
    }
  }
}
