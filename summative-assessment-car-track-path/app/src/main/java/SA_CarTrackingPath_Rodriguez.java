import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class SA_CarTrackingPath_Rodriguez extends JFrame {
    private ArrayList<Point> track1Points = new ArrayList<>();
    private ArrayList<Point> track2Points = new ArrayList<>();
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Point car1Position;
    private Point car2Position;
    private double car1Angle = 0.0;
    private double car2Angle = 0.0;
    private double car1TargetAngle = 0.0;
    private double car2TargetAngle = 0.0;
    private int car1Target = 0;
    private int car2Target = 0;
    private double car1Progress = 0.0;
    private double car2Progress = 0.0;
    private boolean car1Reverse = false;
    private boolean car2Reverse = false;

    public SA_CarTrackingPath_Rodriguez() {
        setTitle("Car Tracking Path - Rodriguez");
        setSize(1600, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializePoints();
        car1Position = new Point(track1Points.get(0));
        car2Position = new Point(track2Points.get(0));
        JPanel panel =
                new JPanel() {
                    @Override
                    protected void paintComponent(Graphics graphics) {
                        super.paintComponent(graphics);
                        Graphics2D graphics2D = (Graphics2D) graphics;
                        graphics2D.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        Font font = graphics2D.getFont();
                        graphics2D.setFont(new Font(font.getName(), Font.BOLD, 18));

                        graphics2D.setColor(new Color(60, 60, 60));
                        graphics2D.setStroke(
                                new BasicStroke(50, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                        for (int index = 1; index < track1Points.size(); index++) {
                            Point start = track1Points.get(index - 1);
                            Point end = track1Points.get(index);
                            graphics2D.drawLine(start.x, start.y, end.x, end.y);
                        }
                        graphics2D.setColor(Color.WHITE);
                        for (int index = 0; index < track1Points.size(); index++) {
                            Point point = track1Points.get(index);
                            graphics2D.drawString(
                                    Character.toString(alphabet.charAt(index)),
                                    point.x - 4,
                                    point.y + 5);
                        }

                        graphics2D.setColor(new Color(60, 60, 60));
                        for (int index = 1; index < track2Points.size(); index++) {
                            Point start = track2Points.get(index - 1);
                            Point end = track2Points.get(index);
                            graphics2D.drawLine(start.x, start.y, end.x, end.y);
                        }

                        drawCar(graphics2D, car1Position.x, car1Position.y, car1Angle);
                        drawCar(graphics2D, car2Position.x, car2Position.y, car2Angle);
                    }
                };
        add(panel);
        setVisible(true);
        Timer timer =
                new Timer(
                        10,
                        event -> {
                            updateCar1();
                            updateCar2();
                            panel.repaint();
                        });
        timer.start();
    }

    private void initializePoints() {
        // Track 1 - Original
        track1Points.add(new Point(640, 710));
        track1Points.add(new Point(150, 710));
        track1Points.add(new Point(395, 560));
        track1Points.add(new Point(640, 410));
        track1Points.add(new Point(395, 250));
        track1Points.add(new Point(150, 85));
        track1Points.add(new Point(400, 85));
        track1Points.add(new Point(640, 85));

        // Track 2 - Square with X
        track2Points.add(new Point(950, 150));
        track2Points.add(new Point(1350, 150));
        track2Points.add(new Point(1350, 550));
        track2Points.add(new Point(950, 550));
        track2Points.add(new Point(950, 150));
        track2Points.add(new Point(1350, 550));
        track2Points.add(new Point(950, 550));
        track2Points.add(new Point(1350, 150));
        track2Points.add(new Point(950, 150));
    }

    private void updateCar1() {
        if (car1Target >= track1Points.size() || car1Target < 0) {
            return;
        }

        Point start, end;
        if (car1Reverse) {
            start = track1Points.get(car1Target);
            end = car1Target == 0 ? track1Points.get(0) : track1Points.get(car1Target - 1);
        } else {
            start = car1Target == 0 ? track1Points.get(0) : track1Points.get(car1Target - 1);
            end = track1Points.get(car1Target);
        }

        if (car1Progress == 0.0) {
            car1TargetAngle = Math.atan2(end.y - start.y, end.x - start.x);
        }

        car1Angle += (car1TargetAngle - car1Angle) * 0.1;

        double eased = easeInOut(car1Progress);
        car1Position.x = (int) (start.x + (end.x - start.x) * eased);
        car1Position.y = (int) (start.y + (end.y - start.y) * eased);
        car1Progress += 0.0025;
        if (car1Progress >= 1.0) {
            car1Progress = 0.0;
            if (car1Reverse) {
                car1Target--;
                if (car1Target <= 0) {
                    car1Target = 1;
                    car1Reverse = false;
                }
            } else {
                car1Target++;
                if (car1Target >= track1Points.size()) {
                    car1Target = track1Points.size() - 1;
                    car1Reverse = true;
                }
            }
        }
    }

    private void updateCar2() {
        if (car2Target >= track2Points.size() || car2Target < 0) {
            return;
        }

        Point start, end;
        if (car2Reverse) {
            start = track2Points.get(car2Target);
            end = car2Target == 0 ? track2Points.get(0) : track2Points.get(car2Target - 1);
        } else {
            start = car2Target == 0 ? track2Points.get(0) : track2Points.get(car2Target - 1);
            end = track2Points.get(car2Target);
        }

        if (car2Progress == 0.0) {
            car2TargetAngle = Math.atan2(end.y - start.y, end.x - start.x);
        }

        car2Angle += (car2TargetAngle - car2Angle) * 0.1;

        double eased = easeInOut(car2Progress);
        car2Position.x = (int) (start.x + (end.x - start.x) * eased);
        car2Position.y = (int) (start.y + (end.y - start.y) * eased);
        car2Progress += 0.0025;
        if (car2Progress >= 1.0) {
            car2Progress = 0.0;
            if (car2Reverse) {
                car2Target--;
                if (car2Target <= 0) {
                    car2Target = 1;
                    car2Reverse = false;
                }
            } else {
                car2Target++;
                if (car2Target >= track2Points.size()) {
                    car2Target = track2Points.size() - 1;
                    car2Reverse = true;
                }
            }
        }
    }

    private double easeInOut(double value) {
        return value < 0.5 ? 2 * value * value : 1 - Math.pow(-2 * value + 2, 2) / 2;
    }

    private void drawCar(Graphics2D graphics2D, int x, int y, double angle) {
        graphics2D = (Graphics2D) graphics2D.create();
        graphics2D.translate(x, y);
        graphics2D.rotate(angle + Math.PI);

        graphics2D.setColor(new Color(220, 20, 60));
        graphics2D.fillRoundRect(-15, -10, 30, 20, 4, 4);

        graphics2D.setColor(new Color(150, 200, 255));
        graphics2D.fillRect(-8, -6, 8, 12);

        graphics2D.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SA_CarTrackingPath_Rodriguez());
    }
}
