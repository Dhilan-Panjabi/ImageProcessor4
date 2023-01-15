package view;

import static java.awt.image.BufferedImage.TYPE_BYTE_INDEXED;

import controller.Controller;
import image.IImage;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;

import java.awt.event.ActionListener;

/**
 * JFrame view for GUI.
 */
public class JFrameView extends JFrame implements IView {

  private final JLabel openedFileLabel;
  private final JLabel chosenModLabel;
  private final JButton loadButton;
  private final JButton openFileButton;
  private final JButton exitButton;
  private final JButton modifierButton;
  private final JButton doModButton;
  private final JButton saveButton;
  private final JTextField input;
  private final JTextField modInput;
  private final JTextField newKeyInput;

  private final JComboBox<String> combobox;

  private final JPanel imagePanel;

  private final JLabel[] imageLabel;

  /**
   * JFrame view constructor.
   * @param caption given caption
   * @param controller controller
   */
  public JFrameView(String caption, Controller controller) {
    super(caption);

    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // this.setResizable(false);
    // this.setMinimumSize(new Dimension(300,300));

    this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 25));

    //display = new JLabel("To be displayed");
    //label = new JLabel(new ImageIcon("Jellyfish.JPG"));

    openedFileLabel = new JLabel("No file selected");
    chosenModLabel = new JLabel("No Modification Selected");

    //this.add(openedFileLabel);

    //the textfield
    input = new JTextField(15);
    //this.add(input);

    //openFileButton
    openFileButton = new JButton("Open File Button");
    openFileButton.setActionCommand("Open File Button");
    //this.add(openFileButton);

    //loadButton
    loadButton = new JButton("Load Button");
    loadButton.setActionCommand("Load Button");
    //this.add(loadButton);

    JPanel topFrame = new JPanel();

    topFrame.add(openedFileLabel);
    topFrame.add(input);
    topFrame.add(openFileButton);
    topFrame.add(loadButton);
    pack();
    this.add(topFrame);
    topFrame.setVisible(true);

    //modifierButton
    modifierButton = new JButton("Modifier Button");
    modifierButton.setActionCommand("Modifier Button");
    this.add(modifierButton);
    this.add(chosenModLabel);

    //DoModButton
    doModButton = new JButton("Do Mod Button");
    doModButton.setActionCommand("Do Mod Button");
    this.add(doModButton);

    modInput = new JTextField(10);
    modInput.setVisible(false);
    this.add(modInput);

    newKeyInput = new JTextField(15);
    this.add(newKeyInput);

    JPanel middleFrame = new JPanel();
    middleFrame.add(modifierButton);
    middleFrame.add(chosenModLabel);
    middleFrame.add(doModButton);
    middleFrame.add(modInput);
    middleFrame.add(newKeyInput);
    this.add(middleFrame);
    middleFrame.setVisible(true);

    JPanel comboboxPanel = new JPanel();
    comboboxPanel.setBorder(BorderFactory.createTitledBorder("Image Selector"));
    comboboxPanel.setLayout(new BoxLayout(comboboxPanel, BoxLayout.PAGE_AXIS));
    this.add(comboboxPanel);

    JLabel comboboxDisplay = new JLabel("Which image are you modifying?");
    comboboxPanel.add(comboboxDisplay);
    combobox = new JComboBox<String>();
    ArrayList<String> options = controller.getKeys();
    //the event listener when an option is selected
    combobox.setActionCommand("Size options");
    //combobox.addActionListener(this);
    for (int i = 0; i < options.size(); i++) {
      combobox.addItem(options.get(i));
    }

    comboboxPanel.add(combobox);

    newKeyInput.setText("Enter new image name here");
    modInput.setText("Brighten increment here");
    input.setText("Enter image name here");

    //Image Panels
    imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    //imagePanel.setMaximumSize(null);
    this.add(imagePanel);

    //Showing Image
    imageLabel = new JLabel[2];
    JScrollPane[] imageScrollPane = new JScrollPane[2];

    imageLabel[0] = new JLabel();
    imageScrollPane[0] = new JScrollPane(imageLabel[0]);
    imageScrollPane[0].setPreferredSize(new Dimension(300, 300));
    imagePanel.add(imageScrollPane[0]);

    //Showing Histogram
    imageLabel[1] = new JLabel();
    imageScrollPane[1] = new JScrollPane(imageLabel[1]);
    imageScrollPane[1].setPreferredSize(new Dimension(300, 300));
    imagePanel.add(imageScrollPane[1]);

    //save button
    saveButton = new JButton("Save");
    saveButton.setActionCommand("Save Button");
    this.add(saveButton);

    //exit button
    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    this.add(exitButton);

    pack();

    setVisible(true);

  }

  @Override
  public void addActionListener(ActionListener actionListener) {
    openFileButton.addActionListener(actionListener);
    loadButton.addActionListener(actionListener);
    modifierButton.addActionListener(actionListener);
    doModButton.addActionListener(actionListener);
    saveButton.addActionListener(actionListener);
    exitButton.addActionListener(actionListener);
  }


  /*
      In order to make this frame respond to keyboard events, it must be within strong focus.
      Since there could be multiple components on the screen that listen to keyboard events,
      we must set one as the "currently focussed" one so that all keyboard events are
      passed to that component. This component is said to have "strong focus".

      We do this by first making the component focusable and then requesting focus to it.
      Requesting focus makes the component have focus AND removes focus from whoever had it
      before.
       */
  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void setOpenedFileLabel(String s) {
    openedFileLabel.setText(s);
  }

  @Override
  public String getInputString() {
    return input.getText();
  }

  @Override
  public String getOpenedFileLabel() {
    return openedFileLabel.getText();
  }

  @Override
  public void clearInputString() {
    input.setText("");
  }

  @Override
  public void clearOpenedFileLabel() {
    openFileButton.setText("No file selected");
  }

  @Override
  public void setChosenOptionText(String chosen) {
    chosenModLabel.setText(chosen);
  }

  @Override
  public String getChosenOptionText() {
    return chosenModLabel.getText();
  }

  @Override
  public String getNewKeyText() {
    return newKeyInput.getText();
  }

  @Override
  public String getModInputText() {
    return modInput.getText();
  }

  @Override
  public void setNewKeyText(String s) {
    newKeyInput.setText(s);
  }

  @Override
  public void setModInputText(String s) {
    modInput.setText(s);
  }

  @Override
  public void updateCombo(String s) {
    combobox.addItem(s);
  }

  @Override
  public void setModInputEnabled(boolean isEnabled) {
    modInput.setVisible(isEnabled);
  }

  @Override
  public JComboBox getComboBox() {
    return combobox;
  }

  @Override
  public void updateImage(IImage image) {

    BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(),
        TYPE_BYTE_INDEXED);

    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        int redColor = (int) (
            image.getPixelAt(row, col).getPixelColor().getRed() * 255);
        int greenColor = (int) (
            image.getPixelAt(row, col).getPixelColor().getGreen() * 255);
        int blueColor = (int) (
            image.getPixelAt(row, col).getPixelColor().getBlue() * 255);
        int a = 255;
        int rgb = (a << 24) | (redColor << 16) | (greenColor << 8) | blueColor;
        bufferedImage.setRGB(col, row, rgb);
      }
    }

    ImageIcon icon = new ImageIcon(bufferedImage);
    imageLabel[0].setIcon(icon);
  }

  @Override
  public void updateHistogram(IImage image) {
    imagePanel.remove(1);

    Histogram h = new Histogram(image);
    HistogramPanel histogramPanel = new HistogramPanel(h);
    imagePanel.add(histogramPanel);
    histogramPanel.setVisible(true);

  }

}
