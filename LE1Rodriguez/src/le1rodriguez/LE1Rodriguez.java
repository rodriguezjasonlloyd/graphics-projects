package le1rodriguez;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

class LEFrame extends Frame {
    @Override
    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Point2D[] points1 = makePointsForImage1();
        Point2D[] points2 = makePointsForImage2();

        Polygon poly1 = pointsToPolygon(points1);
        GeneralPath path1 = pointsToGeneralPath(points1);

        Polygon poly2 = pointsToPolygon(points2);
        GeneralPath path2 = pointsToGeneralPath(points2);

        graphics2D.translate(180, 60);
        graphics2D.drawString("Basic Polygon", 10, 15);

        graphics2D.translate(-180, -60);
        graphics2D.setColor(Color.MAGENTA);
        graphics2D.fillPolygon(poly1);

        graphics2D.translate(660, 60);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawString("GeneralPath", 10, 15);

        graphics2D.translate(-180, -60);
        graphics2D.setColor(Color.MAGENTA);
        graphics2D.fill(path1);

        graphics2D.translate(250, 60);
        graphics2D.drawString("Basic Polygon", 10, 15);

        graphics2D.translate(-250, -60);
        graphics2D.setColor(Color.GREEN);
        graphics2D.fillPolygon(poly2);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawPolygon(poly2);

        graphics2D.translate(740, 60);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawString("GeneralPath", 10, 15);

        graphics2D.translate(-260, -60);
        graphics2D.setColor(Color.GREEN);
        graphics2D.fill(path2);
        graphics2D.setColor(Color.BLACK);
        graphics2D.draw(path2);

        Area area1 = new Area(path1);
        Area area2 = new Area(path2);

        Area relative = (Area) area1.clone();
        relative.subtract(area2);

        Area symmetricDifference = (Area) area1.clone();
        symmetricDifference.exclusiveOr(area2);

        graphics2D.setColor(new Color(30, 144, 255, 200));
        graphics2D.fill(relative);
        graphics2D.setColor(Color.BLACK);
        graphics2D.draw(relative);

        graphics2D.setStroke(
                new BasicStroke(
                        1.0f,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND,
                        0,
                        new float[] {4f, 4f},
                        0f));
        graphics2D.setColor(new Color(0, 0, 0, 80));
        graphics2D.draw(path1);
        graphics2D.draw(path2);

        graphics2D.translate(480, 0);
        graphics2D.setStroke(new BasicStroke(1.0f));
        graphics2D.setColor(new Color(255, 140, 0, 200));
        graphics2D.fill(symmetricDifference);
        graphics2D.setColor(Color.BLACK);
        graphics2D.draw(symmetricDifference);
    }

    private Polygon pointsToPolygon(Point2D[] points) {
        Polygon poly = new Polygon();

        for (Point2D point : points) {
            poly.addPoint((int) Math.round(point.getX()), (int) Math.round(point.getY()));
        }

        return poly;
    }

    private GeneralPath pointsToGeneralPath(Point2D[] points) {
        GeneralPath path = new GeneralPath();

        if (points.length == 0) {
            return path;
        }

        path.moveTo((float) points[0].getX(), (float) points[0].getY());

        for (int i = 1; i < points.length; i++) {
            path.lineTo((float) points[i].getX(), (float) points[i].getY());
        }

        path.closePath();

        return path;
    }

    private static Point2D[] makePointsForImage1() {
        ArrayList<Point2D> points = new ArrayList<>();
        double x = 100, y = 250;

        points.add(new Point2D.Double(x, y)); // Start
        points.add(new Point2D.Double(x += 50, y)); // AB
        points.add(new Point2D.Double(x, y -= 50)); // BC
        points.add(new Point2D.Double(x += 50, y)); // CD
        points.add(new Point2D.Double(x, y += 300)); // DE
        points.add(new Point2D.Double(x += 200, y)); // EF
        points.add(new Point2D.Double(x, y -= 300)); // FG
        points.add(new Point2D.Double(x += 50, y)); // GH
        points.add(new Point2D.Double(x, y += 50)); // HI
        points.add(new Point2D.Double(x += 50, y)); // IJ
        points.add(new Point2D.Double(x, y -= 100)); // JK
        points.add(new Point2D.Double(x = points.get(points.size() - 3).getX(), y -= 50)); // KL
        points.add(new Point2D.Double(x -= 300, y)); // LM
        points.add(new Point2D.Double(x = points.get(0).getX(), y += 50)); // MN
        points.add(new Point2D.Double(x, y += 100)); // NA

        return points.toArray(new Point2D[0]);
    }

    private static Point2D[] makePointsForImage2() {
        ArrayList<Point2D> points = new ArrayList<>();
        double x = 100, y = 350;

        points.add(new Point2D.Double(x, y)); // A
        points.add(new Point2D.Double(x += 50, y += 100)); // AB
        points.add(new Point2D.Double(x += 300, y)); // BC
        points.add(new Point2D.Double(x += 50, y -= 100)); // CD
        points.add(new Point2D.Double(x -= 195, y)); // DE
        points.add(new Point2D.Double(x -= 10, y)); // EF
        points.add(new Point2D.Double(x, y -= 200)); // AF
        points.add(new Point2D.Double(x += 10, y)); // GH
        points.add(new Point2D.Double(x, y += 50)); // HJ
        points.add(new Point2D.Double(x += 100, y));
        points.add(
                new Point2D.Double(
                        x = points.get(points.size() - 3).getX(),
                        y = points.get(points.size() - 3).getY()));
        points.add(new Point2D.Double(x, y += 200));

        return points.toArray(new Point2D[0]);
    }
}

public class LE1Rodriguez {
    public static void main(String[] args) {
        LEFrame frame = new LEFrame();
        frame.setSize(1080, 720);
        frame.setTitle("Long Exam 1");
        frame.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
