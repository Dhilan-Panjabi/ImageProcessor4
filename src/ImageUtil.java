import controller.Controller;
import controller.IController;
import controller.ImageController;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import model.Model;
import model.IModel;
import view.ITextView;
import view.IView;
import view.JFrameView;
import view.TextView;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Main method.
   *
   * @param args input arguments.
   */
  //demo main
  public static void main(String[] args) {
    IModel model = new Model();
    if (args.length > 0) {
      ITextView view1 = new TextView(model, System.out);
      for (int i = 0; i < args.length; i++) {
        if (args[i].equals("-file")) {
          IController controller1;
          try {
            controller1 = new ImageController(model, view1, new FileReader(args[i + 1]));
          } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found.");
          }
          controller1.run();
          break;
        } else if (args[i].equals("-text")) {
          ITextView view2 = new TextView(model, System.out);
          IController controller2 = new ImageController(model, view2,
                  new InputStreamReader(System.in));
          controller2.run();
        }
      }
    } else {
      Controller controller = new Controller(model);
      IView view = new JFrameView("Image Program", controller);
      controller.setView(view);
    }

    //Controller controller = new Controller(model);
    //IView view = new JFrameView("Image Program", controller);
    //controller.setView(view);

  }
}

