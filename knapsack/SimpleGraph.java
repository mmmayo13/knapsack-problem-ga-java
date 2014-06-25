package knapsack;

/**
 * @filename:       SimpleGraph.java
 * @author:         Matthew Mayo
 * @modified:       2014-04-08
 * @description:    Creates a SimpleGraph object based on supplied ArrayList
 *                  of data points; draws graph, adds points, lines, appropriate
 *                  hatch marks; must supply ArrayList of data points to plot 
 *                  and title of graph to display
 * @usage:          java SimpleGraph <data_points> <graph_title>
 * @note:           Inspiration for, and adapted code, comes from:
 *                  http://stackoverflow.com/questions/8693342/drawing-a-simple-line-graph-in-java
 */


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimpleGraph extends JPanel {

    private int width = 800;
    private int heigth = 400;
    private int padding = 25;
    private int label_padding = 25;
    private int point_width = 6;
    private int number_y_divisions = 0;
    private Color line_color = new Color(44, 102, 230, 180);
    private Color point_color = Color.BLACK;
    private Color grid_color = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private String graph_title = "";
    private ArrayList<Double> data_points;



    /**
     * Main method (for testing directly from this class)
     */
    public static void main(String[] args) {

        // Create an ArrayList<Double> of data_points
        ArrayList<Double> test_data = new ArrayList<Double>();

        // Add points to data_points
        test_data.add(1.0);
        test_data.add(9.2);
        test_data.add(5.7);
        test_data.add(7.9);
        test_data.add(2.4);
        test_data.add(11.5);

        // Set a graph title
        String test_title = "Graph title goes here";

        // Pass data_points and graph_title to SimpleGraph constructor
        SimpleGraph test = new SimpleGraph(test_data, test_title);

    }


    /**
     * Default constructor
     */
    public SimpleGraph(ArrayList<Double> data_points, String graph_title) {

        // Set data points data set and graph title
        this.data_points = data_points;
        this.graph_title = graph_title;

        // Set number of y divisions by fidning difference between
        // max and min data points
        number_y_divisions = getMaxDataPoint() - getMinDataPoint();

        // Set preferred size of panel
        this.setPreferredSize(new Dimension(800, 600));

        // Create content frame, add to panel
        JFrame frame = new JFrame(graph_title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    /**
     * Creates and draws graph to specification
     * @param Graphics - What to be drawn
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON);

	// Set scales
        double xScale = ((double) getWidth() - (2 * padding) - label_padding) 
            / (data_points.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - label_padding) 
            / (getMaxDataPoint() - getMinDataPoint());

	// Create array of Point objects from passed in array of Doubles
        ArrayList<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < data_points.size(); i++) {
            int x1 = (int) (i * xScale + padding + label_padding);
            int y1 = (int) ((getMaxDataPoint() - data_points.get(i)) * yScale 
                + padding);
            graphPoints.add(new Point(x1, y1));
        }

        // Draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + label_padding, padding, getWidth() - (2 * padding) 
            - label_padding, getHeight() - 2 * padding - label_padding);
        g2.setColor(Color.BLACK);

        // Create hatch marks and grid lines for y axis
        for (int i = 0; i < number_y_divisions + 1; i++) {
            if(number_y_divisions == 0) {
		number_y_divisions = number_y_divisions + 1;
	    }
	    int x0 = padding + label_padding;
            int x1 = point_width + padding + label_padding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 
                - label_padding)) / number_y_divisions + padding + label_padding);
            int y1 = y0;
            if (data_points.size() > 0) {
                g2.setColor(grid_color);
                g2.drawLine(padding + label_padding + 1 + point_width, y0, 
                    getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) (getMinDataPoint() + (getMaxDataPoint() - getMinDataPoint()) * 
                    ((i * 1.0) / number_y_divisions))) + "   ";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 
                    + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        // Create hatch marks and grid lines for x axis
        for (int i = 0; i < data_points.size(); i++) {
            if (data_points.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - label_padding) 
                    / (data_points.size() - 1) + padding + label_padding;
                int x1 = x0;
                int y0 = getHeight() - padding - label_padding;
                int y1 = y0 - point_width;
                if ((i % ((int) ((data_points.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(grid_color);
                    g2.drawLine(x0, getHeight() - padding - label_padding - 1 
                        - point_width, x1, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = (i + 1) + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 
                        + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }

        // Create x and y axes
        g2.drawLine(padding + label_padding, getHeight() - padding - label_padding, 
            padding + label_padding, padding);
        g2.drawLine(padding + label_padding, getHeight() - padding - label_padding, 
            getWidth() - padding, getHeight() - padding - label_padding);

        // Draw lines
        Stroke oldStroke = g2.getStroke();
        g2.setColor(line_color);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        // Draw points
        g2.setStroke(oldStroke);
        g2.setColor(point_color);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - point_width / 2;
            int y = graphPoints.get(i).y - point_width / 2;
            int ovalW = point_width;
            int ovalH = point_width;
            g2.fillOval(x, y, ovalW, ovalH);
        }

    }


    /**
     * Returns minimum data point in data_points set
     * @return int - Minimum data point in set
     */
    private int getMinDataPoint() {
        int min_data_point = Integer.MAX_VALUE;
        Integer dp_conv = 0;
        for (Double data_point : data_points) {
	    dp_conv = (int) data_point.doubleValue();
            min_data_point = Math.min(min_data_point, dp_conv);
        }
        return min_data_point;
    }


    /**
     * Returns maximum data point in data_points set
     * @return int - Maximum data point in set
     */
    private int getMaxDataPoint() {
        int max_data_point = Integer.MIN_VALUE;
        Integer dp_conv = 0;
        for (Double data_point : data_points) {
            dp_conv = (int) data_point.doubleValue() + 1;
            max_data_point = Math.max(max_data_point, dp_conv);
        }
        return max_data_point;
    }

} // SimpleGraph

