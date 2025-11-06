import java.awt.*;
import java.awt.geom.*;

public class StringAndGeometry extends Frame {
    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        BasicStroke stroke = new BasicStroke(10.0f);
        graphics2D.setStroke(stroke);
        graphics2D.setFont(new Font("Arial", Font.BOLD, 40));

        // 1. Draw the string
        graphics2D.drawString("First Drawing in Java - RODRIGUEZ", 100, 130);

        // 2. Draw the Line
        Line2D.Double line = new Line2D.Double(80, 150, 180, 300);
        graphics2D.draw(line);

        // 3. Draw a Quadratic Curve
        QuadCurve2D.Double quadCurve = new QuadCurve2D.Double(80, 150, 100, 400, 180, 300);
        graphics2D.setPaint(Color.MAGENTA);
        graphics2D.draw(quadCurve);

        // 4. Draw Cubic Curve
        CubicCurve2D.Double cubicCurve =
                new CubicCurve2D.Double(180, 300, 250, 440, 350, 150, 410, 300);
        graphics2D.setPaint(Color.cyan);
        graphics2D.draw(cubicCurve);

        // 5. Draw the rectangle or square
        Rectangle2D.Double rectangle = new Rectangle2D.Double(410, 300, 300, 250);
        graphics2D.setPaint(Color.RED);
        graphics2D.draw(rectangle);

        graphics2D.setPaint(Color.orange);
        graphics2D.fill(rectangle);

        // 6. Create the optimal square inside the rectangle in the most left side (fill green)
        Rectangle2D.Double square = new Rectangle2D.Double(410, 300, 250, 250);
        graphics2D.setPaint(Color.green);

        graphics2D.fill(square);
        graphics2D.draw(square);

        // 7. Draw the ellipse whose equation is (x - 400)^2 / 14400 + (y - 350)^2 / 8100 = 1
        // **Compute value of width and height first**
        // w = 2 * sqrt(14400) = 240
        // h = 2 * sqrt(8100) = 180

        // **Compute value of x and y**
        // x = 400 - 120 = 280
        // y = 350 - 90 = 260

        Ellipse2D.Double ellipse = new Ellipse2D.Double(280, 260, 240, 180);
        graphics2D.setPaint(Color.PINK);
        graphics2D.fill(ellipse);

        // 8. Draw an ARC
        Arc2D.Double arcPie = new Arc2D.Double(square, 20, 80, Arc2D.PIE);
        graphics2D.fill(arcPie);

        Arc2D.Double arcOpen = new Arc2D.Double(square, 110, 40, Arc2D.OPEN);
        graphics2D.fill(arcOpen);

        Arc2D.Double arcChord = new Arc2D.Double(square, 170, 45, Arc2D.CHORD);
        graphics2D.fill(arcChord);

        // 9. General Path
        GeneralPath path = new GeneralPath();
        path.moveTo(50, 50);
        path.lineTo(50, 200);
        path.quadTo(150, 500, 250, 200);
        path.curveTo(350, 100, 150, 150, 100, 100);
        path.lineTo(50, 50);
        graphics2D.setPaint(Color.GREEN);
        graphics2D.fill(path);

        // 9. Areas
        Ellipse2D.Double circle = new Ellipse2D.Double(150, 200, 200, 200);
        graphics2D.fill(circle);

        Area pathArea = new Area(path);
        Area circleArea = new Area(circle);

        // 10.1 Union
        pathArea.add(circleArea);
        graphics2D.fill(pathArea);

        // 10.2 Intersect
        pathArea.intersect(circleArea);
        graphics2D.fill(pathArea);
    }
}
