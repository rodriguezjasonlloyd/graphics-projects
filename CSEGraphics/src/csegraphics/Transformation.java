package csegraphics;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Transformation extends Frame {
    private final int windowHeight;

    Transformation(int height) {
        windowHeight = height;
    }

    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        AffineTransform flip = graphics2D.getTransform();
        flip.setToScale(1, -1);

        AffineTransform translate = new AffineTransform();
        translate.setToTranslation(40, windowHeight - 50);

        flip.preConcatenate(translate);

        graphics2D.transform(flip);
        graphics2D.setStroke(new BasicStroke(5.0f));

        Rectangle2D.Double rectangle = new Rectangle2D.Double(180, 124, 100, 60);
        graphics2D.draw(rectangle);

        AffineTransform scaling = new AffineTransform();
        scaling.setToScale(2, 0.5);

        AffineTransform rotation = new AffineTransform();
        rotation.setToRotation(Math.PI / 4);

        AffineTransform shear = new AffineTransform();
        shear.setToShear(1, 0);

        AffineTransform translation = new AffineTransform();
        translation.setToTranslation(140, 80);

        graphics2D.setStroke(
                new BasicStroke(
                        5.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_BEVEL,
                        8.0f,
                        new float[] {50.0f, 10.0f},
                        4.0f));
        graphics2D.draw(scaling.createTransformedShape(rectangle));
        graphics2D.draw(rotation.createTransformedShape(rectangle));
        graphics2D.draw(shear.createTransformedShape(rectangle));
        graphics2D.draw(translation.createTransformedShape(rectangle));

        graphics2D.setStroke(new BasicStroke(1.0f));
        drawSimpleCoordinateSystem(800, 400, graphics2D);
    }

    public static void drawSimpleCoordinateSystem(int xMax, int yMax, Graphics2D graphics2D) {
        int xOffset = 0;
        int yOffset = 0;
        int step = 20;

        String string;
        Font font = graphics2D.getFont();
        int fontSize = 13;
        Font fontCoordinateSystem = new Font("serif", Font.PLAIN, fontSize);

        AffineTransform flip = new AffineTransform();
        flip.setToScale(1, -1);

        AffineTransform lift = new AffineTransform();
        lift.setToTranslation(0, fontSize);
        flip.preConcatenate(lift);

        Font fontUpsideDown = fontCoordinateSystem.deriveFont(flip);

        graphics2D.setFont(fontUpsideDown);

        graphics2D.drawLine(xOffset, yOffset, xMax, yOffset);

        for (int index = xOffset + step; index <= xMax; index = index + step) {
            graphics2D.drawLine(index, yOffset - 2, index, yOffset + 2);
            graphics2D.drawString(String.valueOf(index), index - 7, yOffset - 30);
        }

        graphics2D.drawLine(xOffset, yOffset, xOffset, yMax);

        string = "  ";

        for (int index = yOffset + step; index <= yMax; index = index + step) {
            graphics2D.drawLine(xOffset - 2, index, xOffset + 2, index);

            if (index > 99) {
                string = "";
            }

            graphics2D.drawString(string + String.valueOf(index), xOffset - 25, index - 20);
        }

        graphics2D.setFont(font);
    }
}
