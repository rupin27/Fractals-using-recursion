package fractal;
import fractal.Turtle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 * A class that implements some basic fractal drawing methods using recursion.
 * These methods include the Koch curve, tree, Sierpinski triangle and carpet.
 */
public class Fractal {
  private Graphics2D g2d = null; // a Graphics2D object as canvas for drawing
  private int width = 0;
  private int height = 0; // image width and height
  private int maxRecursionLevel = 0; // maximum recursion level
  private String fractalType = "Koch Curve"; // type of fractal
  private Color color = Color.green; // draw color

  /**
   * setGraphics.
   * 
   * @param g graph
   * @param w width
   * @param h height
   */
  public void setGraphics(Graphics g, int w, int h) {
    g2d = (Graphics2D) g;
    width = w;
    height = h;
  }

  public void setFractalType(String t) {
    fractalType = t;
  }

  public void setColor(Color c) {
    color = c;
  }

  public void setMaxRecursion(int n) {
    maxRecursionLevel = n;
  }

  // Recursive method for drawing the Koch curve given two points and the
  // recursion level
  private void drawKochCurve(Point2D p1, Point2D p2, int level) {
    if (level == 0) { // base case
      drawLine(p1, p2);
      return;
    }
    // Koch subdivision rule: ___ -> _/\_
    Turtle samp = new Turtle(p1, p2);
    samp.move(p1.distance(p2) / 3);
    Point2D p3 = samp.getPosition();
    samp.turnLeft(60);
    samp.move(p1.distance(p2) / 3);
    Point2D p4 = samp.getPosition();
    samp.turnRight(120);
    samp.move(p1.distance(p2) / 3);
    Point2D p5 = samp.getPosition();
    drawKochCurve(p1, p3, level - 1);
    drawKochCurve(p3, p4, level - 1);
    drawKochCurve(p4, p5, level - 1);
    drawKochCurve(p5, p2, level - 1);
  }

  // Recursive method for drawing a fractal Tree given two points and the
  // recursion level
  private void drawTree(Point2D p1, Point2D p2, int level) {
    if (level == 0) { // base case
      drawLine(p1, p2);
      return;
    }
    Turtle samp = new Turtle(p1, p2);
    samp.move(p1.distance(p2) / 3);
    Point2D p3 = samp.getPosition();
    drawTree(p1, p3, 0);
    samp.turnRight(15);
    samp.move(2 * p1.distance(p2) / 3);
    Point2D p4 = samp.getPosition();
    drawTree(p3, p4, level - 1);
    samp = new Turtle(p3, p2);
    samp.turnLeft(60);
    samp.move(2 * p1.distance(p2) / 3);
    drawTree(p3, samp.getPosition(), level - 1);
  }

  // Recursive method for drawing the Sierpinski Triangle given the three points
  // that define the triangle and the recursion level
  private void drawSierpinskiTriangle(Point2D p1, Point2D p2, Point2D p3, int level) {
    if (level == 0) { // base case
      drawTriangle(p1, p2, p3);
      return;
    }
    Turtle samp = new Turtle(p1, p2);
    samp.move(p1.distance(p2) / 2);
    Point2D p4 = samp.getPosition();
    samp = new Turtle(p1, p3);
    samp.move(p1.distance(p3) / 2);
    Point2D p5 = samp.getPosition();
    samp = new Turtle(p2, p3);
    samp.move(p2.distance(p3) / 2);
    Point2D p6 = samp.getPosition();
    drawSierpinskiTriangle(p1, p4, p5, level - 1);
    drawSierpinskiTriangle(p2, p4, p6, level - 1);
    drawSierpinskiTriangle(p3, p5, p6, level - 1);
  }

  // Recursive method for drawing the Sierpinski Carpet given the lower-left corner
  // of the square (p), the side length (a) of the square, and the recursion level
  private void drawSierpinskiCarpet(Point2D p, double a, int level) {
    if (level == 0) { // base case
      drawRectangle(p, a, a);
      return;
    }
    Point2D p1 = new Point2D.Double(p.getX() + a / 3, p.getY());
    Point2D p2 = new Point2D.Double(p.getX() + 2 * a / 3, p.getY());
    Point2D p3 = new Point2D.Double(p.getX(), p.getY() + a / 3);
    Point2D p4 = new Point2D.Double(p.getX(), p.getY() + 2 * a / 3);
    Point2D p5 = new Point2D.Double(p.getX() + 2 * a / 3, p.getY() + a / 3);
    Point2D p6 = new Point2D.Double(p.getX() + 2 * a / 3, p.getY() + 2 * a / 3);
    Point2D p7 = new Point2D.Double(p.getX() + a / 3, p.getY() + 2 * a / 3);
    drawSierpinskiCarpet(p, a / 3, level - 1);
    drawSierpinskiCarpet(p1, a / 3, level - 1);
    drawSierpinskiCarpet(p2, a / 3, level - 1);
    drawSierpinskiCarpet(p3, a / 3, level - 1);
    drawSierpinskiCarpet(p4, a / 3, level - 1);
    drawSierpinskiCarpet(p5, a / 3, level - 1);
    drawSierpinskiCarpet(p6, a / 3, level - 1);
    drawSierpinskiCarpet(p7, a / 3, level - 1);
  }

  // This method is left for you to experiment with creative fractals
  // designed by yourself. You will NOT be graded on this method
  void drawMyFractal(Point2D p1, Point2D p2, int level) {
    if (level == 0) { // base case
      drawLine(p1, p2);
      return;
    }
    Turtle samp = new Turtle(p1, p2);
    samp.move(p1.distance(p2));
    Point2D p3 = samp.getPosition();
    drawMyFractal(p1, p3, 0);
    samp.turnLeft(30);
    samp.move(p1.distance(p2) / 1.46);
    Point2D p4 = samp.getPosition();
    drawLine(p3, p4);
    drawMyFractal(p3, p4, level - 1);
    samp.turnRight(120);
    samp.move(p1.distance(p2) / 1.46);
    Point2D p5 = samp.getPosition();
    drawLine(p3, p5);
    drawMyFractal(p3, p5, level - 1);
  }

  // The code below provides utility methods.
  public void draw() {
    if (g2d == null || width == 0 || height == 0) {
      return;
    }
    g2d.setBackground(Color.black);
    g2d.clearRect(0, 0, width, height);
    g2d.setColor(color);
    if (fractalType.equalsIgnoreCase("Koch Curve")) {
      drawKochCurve(new Point2D.Double(0.0, 0.4), new Point2D.Double(1.0, 0.4), maxRecursionLevel);
    } else if (fractalType.equalsIgnoreCase("Snowflake")) {
      double r = 0.5;
      Point2D p1 = new Point2D.Double(r * Math.cos(Math.toRadians(150)) + 0.5,
          r * Math.sin(Math.toRadians(150)) + 0.5);
      Point2D p2 = new Point2D.Double(r * Math.cos(Math.toRadians(30)) + 0.5,
          r * Math.sin(Math.toRadians(30)) + 0.5);
      Point2D p3 = new Point2D.Double(r * Math.cos(Math.toRadians(-90)) + 0.5,
          r * Math.sin(Math.toRadians(-90)) + 0.5);
      // Snowflake is made of three Koch curves segments
      //  p1____p2
      //    \  /
      //     \/
      //     p3
      drawKochCurve(p1, p2, maxRecursionLevel);
      drawKochCurve(p2, p3, maxRecursionLevel);
      drawKochCurve(p3, p1, maxRecursionLevel);
    } else if (fractalType.equalsIgnoreCase("Tree")) {
      // double the maximum recursion level because tree subdivides very slowly
      drawTree(new Point2D.Double(0.6, 0.1), new Point2D.Double(0.6, 0.9),
          maxRecursionLevel * 2);
    } else if (fractalType.equalsIgnoreCase("Sp Triangle")) {
      double r = 0.5;
      Point2D p1 = new Point2D.Double(r * Math.cos(Math.toRadians(90)) + 0.5,
          r * Math.sin(Math.toRadians(90)) + 0.5);
      Point2D p2 = new Point2D.Double(r * Math.cos(Math.toRadians(-150)) + 0.5,
          r * Math.sin(Math.toRadians(-150)) + 0.5);
      Point2D p3 = new Point2D.Double(r * Math.cos(Math.toRadians(-30)) + 0.5,
          r * Math.sin(Math.toRadians(-30)) + 0.5);
      drawSierpinskiTriangle(p1, p2, p3, maxRecursionLevel);
    } else if (fractalType.equalsIgnoreCase("Sp Carpet")) {
      drawSierpinskiCarpet(new Point2D.Double(0, 0), 1, maxRecursionLevel);
    } else {
      drawMyFractal(new Point2D.Double(0.5, 0.04), new Point2D.Double(0.5, 0.34),
          maxRecursionLevel * 2);
    }
  }

  /**
   * Draw a straight line between two points P1 and P2.
   * The given x and y values of p1 and p2 are assumed to be within [0, 1] (i.e. normalized).
   * This allows our fractals to be resolution-independent. The method below
   * converts the normalized coordinates to integer coordinates based on the actual image resolution.
   */
  private void drawLine(Point2D p1, Point2D p2) {
    int x1 = (int) (p1.getX() * width);
    int x2 = (int) (p2.getX() * width);
    // flip the Y coordinate
    // because screen Y axis is flipped from mathematical Y axis
    int y1 = (int) ((1.0 - p1.getY()) * height);
    int y2 = (int) ((1.0 - p2.getY()) * height);
    g2d.drawLine(x1, y1, x2, y2);
  }

  // Draw a solid rectangle given its lower left corner and its size
  private void drawRectangle(Point2D p, double w, double h) {
    int x0 = (int) (p.getX() * width);
    int y0 = (int) ((1.0 - p.getY()) * height);
    int x1 = (int) ((p.getX() + w) * width);
    int y1 = (int) ((1.0 - (p.getY() + h)) * height);
    int[] xpoints = { x0, x0, x1, x1 };
    int[] ypoints = { y0, y1, y1, y0 };
    g2d.fillPolygon(xpoints, ypoints, 4);
  }

  // Draw a solid triangle given its three vertices
  private void drawTriangle(Point2D p1, Point2D p2, Point2D p3) {
    int[] xpoints = { (int) (p1.getX() * width),
        (int) (p2.getX() * width),
        (int) (p3.getX() * width) };
    int[] ypoints = { (int) ((1.0 - p1.getY()) * height),
        (int) ((1.0 - p2.getY()) * height),
        (int) ((1.0 - p3.getY()) * height) };
    g2d.fillPolygon(xpoints, ypoints, 3);
  }
}
