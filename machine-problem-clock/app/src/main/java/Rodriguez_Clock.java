import java.awt.*;
import java.awt.geom.*;
import java.time.*;

import javax.swing.*;

public class Rodriguez_Clock extends JFrame {

    private class ClockPanel extends JPanel {
        private void drawClockHands(Graphics2D graphics2D, int hour, int minute, int second) {
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;

            int hourLength = 80;
            int minuteLength = 140;
            int secondLength = 150;
            int tailLength = 20;

            double hourAngle = (hour % 12 + minute / 60.0) * 30.0 - 90.0;
            double minuteAngle = (minute + second / 60.0) * 6.0 - 90.0;
            double secondAngle = second * 6.0 - 90.0;

            graphics2D.setColor(Color.WHITE);
            drawHand(graphics2D, centerX, centerY, hourLength, 6, hourAngle, tailLength);
            drawHand(graphics2D, centerX, centerY, minuteLength, 4, minuteAngle, tailLength);

            graphics2D.setColor(Color.RED);
            drawHand(graphics2D, centerX, centerY, secondLength, 2, secondAngle, tailLength);

            graphics2D.setColor(Color.BLACK);
            graphics2D.fillOval(centerX - 10, centerY - 10, 20, 20);
        }

        private void drawDigitalDisplay(Graphics2D graphics2D, LocalTime now) {
            int rectangleWidth = 150;
            int rectangleHeight = 50;
            int rectangleX = (getWidth() - rectangleWidth) / 2;

            int blackSize = 400;
            int yBlackBottom = (getHeight() - blackSize) / 2 + blackSize;
            int rectangleY = yBlackBottom - rectangleHeight + 60;

            graphics2D.setColor(new Color(50, 50, 50));
            graphics2D.fillRect(rectangleX, rectangleY, rectangleWidth, rectangleHeight);

            String timeText =
                    String.format(
                            "%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond());

            graphics2D.setColor(Color.WHITE);
            graphics2D.setFont(new Font("Monospaced", Font.BOLD, 24));

            FontMetrics fontMetrics = graphics2D.getFontMetrics();
            int textX = rectangleX + (rectangleWidth - fontMetrics.stringWidth(timeText)) / 2;
            int textY =
                    rectangleY
                            + ((rectangleHeight - fontMetrics.getHeight()) / 2)
                            + fontMetrics.getAscent();

            graphics2D.drawString(timeText, textX, textY);
        }

        private void drawHand(
                Graphics2D graphics2D,
                int x,
                int y,
                int length,
                int thickness,
                double angle,
                int tail) {
            AffineTransform originalTransform = graphics2D.getTransform();
            graphics2D.rotate(Math.toRadians(angle), x, y);
            graphics2D.setStroke(
                    new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            graphics2D.drawLine(x - tail, y, x + length, y);
            graphics2D.setTransform(originalTransform);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            Graphics2D graphics2D = (Graphics2D) graphics;

            graphics2D.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int whiteBorderSize = 620;
            int xWhiteBorder = (getWidth() - whiteBorderSize) / 2;
            int yWhiteBorder = (getHeight() - whiteBorderSize) / 2;

            graphics2D.setColor(Color.WHITE);
            graphics2D.fillOval(xWhiteBorder, yWhiteBorder, whiteBorderSize, whiteBorderSize);

            int greySize = 600;
            int xGrey = (getWidth() - greySize) / 2;
            int yGrey = (getHeight() - greySize) / 2;

            graphics2D.setColor(Color.GRAY);
            graphics2D.fillOval(xGrey, yGrey, greySize, greySize);

            int blackSize = 400;
            int xBlack = (getWidth() - blackSize) / 2;
            int yBlack = (getHeight() - blackSize) / 2;

            graphics2D.setColor(Color.BLACK);
            graphics2D.fillOval(xBlack, yBlack, blackSize, blackSize);

            int shapeWidth = 150;
            int shapeHeight = 200;

            int xCenter = (getWidth() - shapeWidth) / 2;
            int yCenter = (getHeight() - shapeHeight * 2) / 2;

            graphics2D.setColor(new Color(173, 217, 110));

            int arcHeight = shapeHeight;

            graphics2D.fillArc(xCenter, yCenter + 10, shapeWidth, arcHeight, 0, -180);
            graphics2D.fillArc(xCenter, yCenter + shapeHeight - 10, shapeWidth, arcHeight, 0, 180);

            int panelCenter = getWidth() / 2;
            int radius = greySize / 2;
            int tickRadius = radius - 10;
            int hourTickLength = 20;
            int minuteTickLength = 10;

            AffineTransform originalTransform = graphics2D.getTransform();
            graphics2D.setColor(Color.LIGHT_GRAY);
            graphics2D.translate(panelCenter, panelCenter);

            for (int i = 0; i < 60; i++) {

                if (i % 5 == 0) {
                    graphics2D.setStroke(new BasicStroke(4));
                    graphics2D.drawLine(0, -tickRadius, 0, -(tickRadius - hourTickLength));
                } else {
                    graphics2D.setStroke(new BasicStroke(2));
                    graphics2D.drawLine(0, -tickRadius, 0, -(tickRadius - minuteTickLength));
                }

                graphics2D.rotate(Math.toRadians(6));
            }

            graphics2D.setTransform(originalTransform);

            LocalTime now = LocalTime.now();
            drawDigitalDisplay(graphics2D, now);
            drawClockHands(graphics2D, now.getHour(), now.getMinute(), now.getSecond());
        }
    }

    public Rodriguez_Clock() {
        setTitle("Jason's Ben 10 Analog Clock");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ClockPanel clockPanel = new ClockPanel();
        add(clockPanel);

        Timer timer =
                new Timer(
                        5,
                        _ -> {
                            clockPanel.repaint();
                        });

        timer.start();

        setVisible(true);
    }

    public static void main(String[] args) {
        new Rodriguez_Clock();
    }
}
