import java.awt.*;
import java.awt.geom.*;

class GeometryDrawingAWT extends Frame {

    public GeometryDrawingAWT() {
        setTitle("MP1");
        setSize(700, 650);
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        Path2D.Double polygon = new Path2D.Double();
        polygon.moveTo(80, 475);
        polygon.lineTo(500, 125);
        polygon.lineTo(300, 300);
        polygon.lineTo(450, 500);
        polygon.lineTo(200, 375);
        polygon.closePath();

        graphics2D.setStroke(new BasicStroke(2.0f));
        graphics2D.setPaint(Color.BLACK);
        graphics2D.draw(polygon);
        graphics2D.drawString("Lines", 50, 460);

        QuadCurve2D.Double quadCurve = new QuadCurve2D.Double(175, 500, 200, 300, 400, 550);
        graphics2D.setPaint(Color.GREEN);
        graphics2D.draw(quadCurve);
        graphics2D.setPaint(Color.BLACK);
        graphics2D.drawString("QuadCurve", 300, 520);

        CubicCurve2D.Double cubicCurve =
                new CubicCurve2D.Double(375, 300, 450, 550, 550, 450, 575, 350);
        graphics2D.setPaint(Color.BLUE);
        graphics2D.draw(cubicCurve);
        graphics2D.setPaint(Color.BLACK);
        graphics2D.drawString("CubicCurve", 420, 360);

        Rectangle2D.Double rectangle = new Rectangle2D.Double(100, 100, 150, 200);
        graphics2D.setPaint(Color.RED);
        graphics2D.draw(rectangle);
        graphics2D.setPaint(Color.BLACK);
        graphics2D.drawString("Rectangle", 110, 95);

        Ellipse2D.Double ellipse = new Ellipse2D.Double(80, 60, 40, 60);
        graphics2D.setPaint(Color.YELLOW);
        graphics2D.fill(ellipse);
        graphics2D.setPaint(Color.BLACK);
        graphics2D.draw(ellipse);
        graphics2D.drawString("Ellipse", 100, 55);

        Arc2D.Double arc = new Arc2D.Double(300, 50, 100, 75, 15, 75, Arc2D.PIE);
        graphics2D.setPaint(Color.ORANGE);
        graphics2D.fill(arc);
        graphics2D.setPaint(Color.BLACK);
        graphics2D.draw(arc);
        graphics2D.drawString("Arc", 340, 45);

        graphics2D.setPaint(Color.BLACK);
        graphics2D.setFont(new Font("SansSerif", Font.BOLD, 14));
        graphics2D.drawString("Jason Lloyd T. Rodriguez", 30, 50);
        graphics2D.drawString("4CSE", 30, 70);
    }
}

public class MP1Rodriguez {

    public static void main(String[] args) {
        GeometryDrawingAWT frame = new GeometryDrawingAWT();
        frame.setVisible(true);

        frame.addWindowListener(
                new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
    }
}
