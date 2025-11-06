import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

class MPFrame extends Frame {
    private final float AB = 100f, R = 40f, CD = 20f, EF = 100f;

    private GeneralPath makeCross(float startX, float startY) {
        GeneralPath cross = new GeneralPath();

        cross.moveTo(startX, startY);
        cross.lineTo(startX, startY - AB);
        cross.quadTo(startX, startY - AB - R, startX + R, startY - AB - R);
        cross.lineTo(startX + R + CD, startY - AB - R);
        cross.quadTo(startX + R + CD + R, startY - AB - R, startX + R + CD + R, startY - AB);
        cross.lineTo(startX + R + CD + R, startY);

        cross.lineTo(startX + R + CD + R + EF, startY);
        cross.quadTo(
                startX + R + CD + R + EF + R, startY, startX + R + CD + R + EF + R, startY + R);
        cross.lineTo(startX + R + CD + R + EF + R, startY + R + CD);
        cross.quadTo(
                startX + R + CD + R + EF + R,
                startY + R + CD + R,
                startX + R + CD + R + EF,
                startY + R + CD + R);
        cross.lineTo(startX + R + CD + R, startY + AB);

        cross.lineTo(startX + R + CD + R, startY + AB + EF);
        cross.quadTo(
                startX + R + CD + R, startY + AB + EF + R, startX + R + CD, startY + AB + EF + R);
        cross.lineTo(startX + R, startY + AB + EF + R);
        cross.quadTo(startX, startY + AB + EF + R, startX, startY + AB + EF);
        cross.lineTo(startX, startY + AB);

        cross.lineTo(startX - EF, startY + AB);
        cross.quadTo(startX - EF - R, startY + AB, startX - EF - R, startY + AB - R);
        cross.lineTo(startX - EF - R, startY + AB - R - CD);
        cross.quadTo(
                startX - EF - R, startY + AB - R - CD - R, startX - EF, startY + AB - R - CD - R);
        cross.lineTo(startX, startY);
        cross.closePath();

        return cross;
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        AffineTransform originalTransform = graphics2D.getTransform();
        int height = getHeight();
        int width = getWidth();
        int offset = 50;
        graphics2D.translate(offset, height - offset);
        graphics2D.scale(1, -1);

        GeneralPath path = makeCross(250, 280);
        graphics2D.setPaint(Color.white);
        graphics2D.fill(path);
        graphics2D.setPaint(Color.black);
        graphics2D.setStroke(new BasicStroke(2f));
        graphics2D.draw(path);

        Stroke dashed =
                new BasicStroke(
                        2f,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND,
                        10f,
                        new float[] {8f, 6f},
                        0f);

        AffineTransform scaleTransform = new AffineTransform();
        scaleTransform.scale(2f, 0.25);

        Shape scaled = scaleTransform.createTransformedShape(path);
        graphics2D.setStroke(dashed);
        graphics2D.setPaint(Color.blue);
        graphics2D.draw(scaled);

        AffineTransform rotateTransform = new AffineTransform();
        rotateTransform.rotate(Math.toRadians(30));

        Shape rotated = rotateTransform.createTransformedShape(path);
        graphics2D.setPaint(Color.green.darker());
        graphics2D.draw(rotated);

        AffineTransform shearTransform = new AffineTransform();
        shearTransform.shear(1f, 0.5);

        Shape sheared = shearTransform.createTransformedShape(path);
        graphics2D.setPaint(Color.magenta);
        graphics2D.draw(sheared);

        AffineTransform translateTransform = new AffineTransform();
        translateTransform.translate(100, 60);

        Shape translated = translateTransform.createTransformedShape(path);
        graphics2D.setPaint(Color.cyan.darker());
        graphics2D.draw(translated);

        graphics2D.setTransform(originalTransform);

        graphics2D.setStroke(new BasicStroke(1f));
        graphics2D.setPaint(Color.black);
        graphics2D.drawLine(offset, height - offset, width - offset, height - offset);
        graphics2D.drawLine(offset, height - offset, offset, offset);

        Font originalFont = graphics2D.getFont();
        graphics2D.setFont(originalFont.deriveFont(12f));

        for (int x = offset; x <= width - offset; x += 50) {
            graphics2D.drawLine(x, height - offset - 4, x, height - offset + 4);
            graphics2D.drawString(Integer.toString(x - offset), x - 10, height - offset + 16);
        }

        for (int y = offset; y <= height - offset; y += 50) {
            graphics2D.drawLine(offset - 4, y, offset + 4, y);
            graphics2D.drawString(Integer.toString(height - offset - y), offset - 30, y + 4);
        }

        graphics2D.setFont(originalFont);
    }
}

public class MP_Trans_Rodriguez {
    public static void main(String[] args) {
        MPFrame frame = new MPFrame();
        frame.setTitle("Machine Problem 3 - Rodriguez");
        frame.setSize(1100, 880);
        frame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent event) {
                        System.exit(0);
                    }
                });
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
