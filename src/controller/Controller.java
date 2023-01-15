package controller;

import static model.IModel.ComponentType.Blue;
import static model.IModel.ComponentType.Green;
import static model.IModel.ComponentType.Intensity;
import static model.IModel.ComponentType.Luma;
import static model.IModel.ComponentType.Red;
import static model.IModel.ComponentType.Value;

import commands.BlurCommand;
import commands.BrightenCommand;
import commands.ComponentCommand;
import commands.FlipCommand;
import commands.GrayscaleCommand;
import commands.ICommand;
import commands.LoadCommand;
import commands.MosaickingCommand;
import commands.SaveCommand;
import commands.SepiaCommand;
import commands.SharpenCommand;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.IModel;
import view.IView;
import view.JFrameView;

/**
 * Controller for GUI.
 */
public class Controller {

  private final IModel model;

  private IView view;

  /**
   * Controller constructor for GUI takes in a model.
   *
   * @param model model given
   */
  public Controller(IModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Null parameter");
    }

    this.model = model;

  }

  /**
   * Sets the view and configures the ButtonListener.
   *
   * @param v the given view
   */
  public void setView(IView v) {
    this.view = v;
    configureButtonListener();
  }

  /**
   * Method that configures the ButtonListener and puts possible button clicked commands
   * in a hashmap.
   */
  private void configureButtonListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap<String, Runnable>();
    ButtonListener buttonListener = new ButtonListener();

    buttonClickedMap.put("Open File Button", () -> {
      //send text to the model
      final JFileChooser fchooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "Images", "jpg", "jpeg", "png", "ppm", "bmp");
      fchooser.setFileFilter(filter);

      int retvalue = fchooser.showOpenDialog(null);
      if (retvalue == JFileChooser.APPROVE_OPTION) {
        File f = fchooser.getSelectedFile();
        //ICommand loadCommand = new LoadCommand(f.toString(), view.getInputString());
        //loadCommand.apply(model);
        view.setOpenedFileLabel(f.toString());
      }
      view.resetFocus();

    });
    buttonClickedMap.put("Load Button", () -> {
      ICommand loadCommand = new LoadCommand(view.getOpenedFileLabel(), view.getInputString());
      loadCommand.apply(model);

      view.updateCombo(view.getInputString());
      view.updateImage(model.getMap().get(view.getInputString()));
      view.updateHistogram(model.getMap().get(view.getInputString()));

      //clear input textfield
      view.clearInputString();

      //set focus back to main frame so that keyboard events work
      view.resetFocus();

    });
    buttonClickedMap.put("Modifier Button", () -> {
      String[] options = {"Blur", "Brighten", "Red-Component", "Green-Component", "Blue-Component",
                          "Intensity-Component", "Value-Component", "Luma-Component",
                          "Flip-Vertical",
                          "Flip-Horizontal", "GrayScale", "Sepia", "Sharpen", "Mosaic"};
      JFrameView v = new JFrameView("FileChooser", this);

      int retvalue = JOptionPane.showOptionDialog(v, "Please choose number", "Options",
              JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[4]);
      view.setChosenOptionText(options[retvalue]);
      if (view.getChosenOptionText().equals("Brighten")) {
        view.setModInputEnabled(true);
        view.setModInputText("Enter increment here");
      }
      if (view.getChosenOptionText().equals("Mosaic")) {
        view.setModInputEnabled(true);
        view.setModInputText("Enter number of seeds here");
      } else {
        view.setModInputEnabled(false);
      }

      //clear input textfield
      view.clearInputString();

      //set focus back to main frame so that keyboard events work
      view.resetFocus();

    });
    buttonClickedMap.put("Do Mod Button", () -> {
      String selected = (String) (view.getComboBox().getSelectedItem());

      if (selected == null || view.getNewKeyText().equals("")) {
        return;
      }
      switch (view.getChosenOptionText()) {
        case "Blur":
          ICommand blurCommand = new BlurCommand(selected, view.getNewKeyText());
          blurCommand.apply(model);
          break;
        case "Brighten":
          try {
            int inc = Integer.parseInt(view.getModInputText());
            ICommand brightenCommand = new BrightenCommand(selected, view.getNewKeyText(), inc);
            brightenCommand.apply(model);
          } catch (NumberFormatException e) {
            //set warning to visible
            JOptionPane.showMessageDialog(null, "Brighten increment was "
                    + "not a valid number.", "Invalid Increment", JOptionPane.ERROR_MESSAGE);
            return;
          }
          break;
        case "Red-Component":
          ICommand redCommand = new ComponentCommand(selected, view.getNewKeyText(), Red);
          redCommand.apply(model);
          break;
        case "Green-Component":
          ICommand greenCommand = new ComponentCommand(selected, view.getNewKeyText(), Green);
          greenCommand.apply(model);
          break;
        case "Blue-Component":
          ICommand blueCommand = new ComponentCommand(selected, view.getNewKeyText(), Blue);
          blueCommand.apply(model);
          break;
        case "Intensity-Component":
          ICommand intCommand = new ComponentCommand(selected, view.getNewKeyText(), Intensity);
          intCommand.apply(model);
          break;
        case "Value-Component":
          ICommand valCommand = new ComponentCommand(selected, view.getNewKeyText(), Value);
          valCommand.apply(model);
          break;
        case "Luma-Component":
          ICommand lumaCommand = new ComponentCommand(selected, view.getNewKeyText(), Luma);
          lumaCommand.apply(model);
          break;
        case "Flip-Vertical":
          ICommand flipCommand = new FlipCommand(selected, view.getNewKeyText(), false);
          flipCommand.apply(model);
          break;
        case "Flip-Horizontal":
          ICommand flipCommand2 = new FlipCommand(selected, view.getNewKeyText(), true);
          flipCommand2.apply(model);
          break;
        case "GrayScale":
          ICommand grayCommand = new GrayscaleCommand(selected, view.getNewKeyText());
          grayCommand.apply(model);
          break;
        case "Sepia":
          ICommand sepiaCommand = new SepiaCommand(selected, view.getNewKeyText());
          sepiaCommand.apply(model);
          break;
        case "Sharpen":
          ICommand sharpenCommand = new SharpenCommand(selected, view.getNewKeyText());
          sharpenCommand.apply(model);
          break;
        case "Mosaic":
          try {
            int seeds = Integer.parseInt(view.getModInputText());
            ICommand mosaicCommand = new MosaickingCommand(selected, view.getNewKeyText(), seeds);
            mosaicCommand.apply(model);
          } catch (NumberFormatException e) {
            //set warning to visible
            JOptionPane.showMessageDialog(null, "Number of seeds was "
                    + "not a valid number.", "Invalid Number", JOptionPane.ERROR_MESSAGE);
            return;
          }
          break;
        default:
          throw new IllegalStateException("Invalid command");

      }


      //view.updateImage(model.getMap().get((String)(view.getComboBox().getSelectedItem())));
      view.updateCombo(view.getNewKeyText());
      view.updateImage(model.getMap().get(view.getNewKeyText()));
      view.updateHistogram(model.getMap().get(view.getNewKeyText()));


      //imageLabel[0].setIcon(new ImageIcon(images[0]));

      //clear input textfield
      //view.clearModInputString();
      view.setModInputEnabled(false);
      view.setNewKeyText("Enter new name here");

      //set focus back to main frame so that keyboard events work
      view.resetFocus();

    });
    buttonClickedMap.put("Save Button", () -> {
      final JFileChooser fchooser = new JFileChooser(".");

      int retvalue = fchooser.showSaveDialog(null);
      if (retvalue == JFileChooser.APPROVE_OPTION) {
        File f = fchooser.getSelectedFile();
        System.out.println(f.toString());
        String name = (String) (view.getComboBox().getSelectedItem());
        System.out.println(name);
        System.out.println(name.split("\\.")[name.split("\\.").length - 1]);
        ICommand saveCommand = new SaveCommand(f.toString(), name, f.toString()
                .split("\\.")[f.toString().split("\\.").length - 1]);
        saveCommand.apply(model);
      }

    });
    buttonClickedMap.put("Exit Button", () -> {
      System.exit(0);
    });

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.view.addActionListener(buttonListener);
  }

  /**
   * Gets the keys from the map and puts it into an ArrayList.
   *
   * @return ArrayList of keys
   */
  public ArrayList<String> getKeys() {
    ArrayList<String> keys = new ArrayList<String>();
    model.getMap().forEach((k, v) -> {
      keys.add(k);
    });
    return keys;
  }
}
