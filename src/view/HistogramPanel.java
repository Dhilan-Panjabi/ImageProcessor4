package view;

import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JPanel;

/**
 * Histogram Panel class extends JPanel and defined where histograms will be displayed.
 */
public class HistogramPanel extends JPanel {

  private final Histogram histogram;

  /**
   * Histogram panel constructor.
   * @param histogram given histogram
   */
  public HistogramPanel(Histogram histogram) {
    this.histogram = histogram;
  }

  /**
   * drawHistogram method draws a histogram of one component and is given the graphics class,
   * an array of data, and a color.
   * @param g graphics class
   * @param component given array of data
   * @param c color that will be shown
   */
  public void drawHistogram(Graphics g, int[] component, Color c) {
    g.setColor(c);
    for (int i = 0; i < 256; i++) {
      g.drawLine(i, 300, i, component[i]);
    }
  }

  @Override
  protected void paintComponent(final Graphics g) {
    super.paintComponent(g);

    drawHistogram(g, histogram.intensity, Color.YELLOW);
    drawHistogram(g, histogram.red, Color.RED);
    drawHistogram(g, histogram.green, Color.GREEN);
    drawHistogram(g, histogram.blue, Color.BLUE);

    repaint();
  }
}
