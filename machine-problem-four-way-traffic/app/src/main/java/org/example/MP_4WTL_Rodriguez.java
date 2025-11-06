package org.example;

import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.Timer;

public class MP_4WTL_Rodriguez extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> {
                    MP_4WTL_Rodriguez frame = new MP_4WTL_Rodriguez();
                    frame.setTitle("4-Lane Intersection + Traffic Lights");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(800, 800);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                });
    }

    public MP_4WTL_Rodriguez() {
        IntersectionPanel panel = new IntersectionPanel();

        add(panel);

        Timer lightTimer =
                new Timer(
                        1000,
                        event -> {
                            panel.updateLights();
                            panel.repaint();
                        });

        lightTimer.start();

        Timer carTimer =
                new Timer(
                        10,
                        event -> {
                            panel.updateCars();
                            panel.repaint();
                        });

        carTimer.start();
    }

    static class IntersectionPanel extends JPanel {
        final int centerX = 400;
        final int centerY = 400;
        final int laneWidth = 40;
        final int intersectionSize = 120;

        final TrafficLight northLight = new TrafficLight(6, 2, 8);
        final TrafficLight southLight = new TrafficLight(6, 2, 8);
        final TrafficLight eastLight = new TrafficLight(6, 2, 8);
        final TrafficLight westLight = new TrafficLight(6, 2, 8);

        final List<Car> northCars = new ArrayList<>();
        final List<Car> southCars = new ArrayList<>();
        final List<Car> eastCars = new ArrayList<>();
        final List<Car> westCars = new ArrayList<>();

        final Random random = new Random();
        int tick = 0;

        IntersectionPanel() {
            setBackground(Color.DARK_GRAY);

            // Start with North-South green, East-West red
            northLight.state = TrafficLight.State.GREEN;
            northLight.remainingSeconds = 6;
            southLight.state = TrafficLight.State.GREEN;
            southLight.remainingSeconds = 6;

            spawnCar(northCars, Direction.NORTH);
            spawnCar(southCars, Direction.SOUTH);
            spawnCar(eastCars, Direction.EAST);
            spawnCar(westCars, Direction.WEST);
        }

        private void spawnCar(List<Car> cars, Direction dir) {
            // Don't spawn if there's a car too close to spawn position
            if (!cars.isEmpty()) {
                Car lastCar = cars.get(cars.size() - 1);
                double minDistance = 100;

                switch (dir) {
                    case NORTH -> {
                        if (lastCar.y > 800 - minDistance) return;
                    }
                    case SOUTH -> {
                        if (lastCar.y < minDistance) return;
                    }
                    case EAST -> {
                        if (lastCar.x < minDistance) return;
                    }
                    case WEST -> {
                        if (lastCar.x > 800 - minDistance) return;
                    }
                }
            }

            double x, y, velocity = 1.5;

            switch (dir) {
                case NORTH -> {
                    x = centerX - laneWidth / 2.0;
                    y = 800;
                    velocity = -1.5;
                }
                case SOUTH -> {
                    x = centerX + laneWidth / 2.0;
                    y = 0;
                }
                case EAST -> {
                    x = 0;
                    y = centerY - laneWidth / 2.0;
                }
                case WEST -> {
                    x = 800;
                    y = centerY + laneWidth / 2.0;
                    velocity = -1.5;
                }
                default -> {
                    x = 0;
                    y = 0;
                }
            }

            cars.add(new Car(x, y, velocity, 25.0, 15.0, dir, random));
        }

        public void updateLights() {
            // Synchronize North-South lights
            northLight.update(1000);
            southLight.state = northLight.state;
            southLight.remainingSeconds = northLight.remainingSeconds;

            // East-West lights are opposite of North-South
            switch (northLight.state) {
                case GREEN -> {
                    eastLight.state = TrafficLight.State.RED;
                    eastLight.remainingSeconds = northLight.remainingSeconds;
                    westLight.state = TrafficLight.State.RED;
                    westLight.remainingSeconds = northLight.remainingSeconds;
                }
                case YELLOW -> {
                    eastLight.state = TrafficLight.State.RED;
                    eastLight.remainingSeconds = northLight.remainingSeconds;
                    westLight.state = TrafficLight.State.RED;
                    westLight.remainingSeconds = northLight.remainingSeconds;
                }
                case RED -> {
                    if (northLight.remainingSeconds > 6) {
                        eastLight.state = TrafficLight.State.RED;
                        eastLight.remainingSeconds = northLight.remainingSeconds - 6;
                        westLight.state = TrafficLight.State.RED;
                        westLight.remainingSeconds = northLight.remainingSeconds - 6;
                    } else if (northLight.remainingSeconds > 2) {
                        eastLight.state = TrafficLight.State.GREEN;
                        eastLight.remainingSeconds = northLight.remainingSeconds - 2;
                        westLight.state = TrafficLight.State.GREEN;
                        westLight.remainingSeconds = northLight.remainingSeconds - 2;
                    } else {
                        eastLight.state = TrafficLight.State.YELLOW;
                        eastLight.remainingSeconds = northLight.remainingSeconds;
                        westLight.state = TrafficLight.State.YELLOW;
                        westLight.remainingSeconds = northLight.remainingSeconds;
                    }
                }
            }
        }

        public void updateCars() {
            tick++;

            if (tick % 200 == 0) spawnCar(northCars, Direction.NORTH);
            if (tick % 180 == 20) spawnCar(southCars, Direction.SOUTH);
            if (tick % 220 == 40) spawnCar(eastCars, Direction.EAST);
            if (tick % 190 == 60) spawnCar(westCars, Direction.WEST);

            updateLane(northCars, northLight, centerY + intersectionSize / 2.0);
            updateLane(southCars, southLight, centerY - intersectionSize / 2.0);
            updateLane(eastCars, eastLight, centerX - intersectionSize / 2.0);
            updateLane(westCars, westLight, centerX + intersectionSize / 2.0);

            // Remove cars that are off screen
            northCars.removeIf(c -> c.y < -50);
            southCars.removeIf(c -> c.y > 850);
            eastCars.removeIf(c -> c.x > 850);
            westCars.removeIf(c -> c.x < -50);
        }

        private void updateLane(List<Car> cars, TrafficLight light, double stopLine) {
            for (int i = 0; i < cars.size(); i++) {
                Car car = cars.get(i);
                double front = car.getFront();
                double targetVelocity = Math.abs(car.velocity);

                // Determine if car is before the stop line based on direction
                boolean beforeStopLine;
                if (car.direction == Direction.SOUTH || car.direction == Direction.EAST) {
                    beforeStopLine = front < stopLine;
                } else {
                    beforeStopLine = front > stopLine;
                }

                // Check car ahead to prevent overlapping
                if (i > 0) {
                    Car carAhead = cars.get(i - 1);
                    double gap = carAhead.getBack() - front;

                    if (gap < car.stopBuffer) {
                        targetVelocity = 0.0;
                    }
                }

                // Traffic light logic - only applies if BEFORE the stop line
                if (beforeStopLine) {
                    if (light.state == TrafficLight.State.RED) {
                        double distanceToStop;

                        if (car.direction == Direction.SOUTH || car.direction == Direction.EAST) {
                            distanceToStop = stopLine - front;
                        } else {
                            distanceToStop = front - stopLine;
                        }

                        if (distanceToStop < 50) {
                            targetVelocity = Math.min(targetVelocity, Math.abs(car.velocity) * 0.3);
                        }

                        if (distanceToStop < 5) {
                            targetVelocity = 0.0;
                        }
                    } else if (light.state == TrafficLight.State.YELLOW) {
                        double distanceToStop;

                        if (car.direction == Direction.SOUTH || car.direction == Direction.EAST) {
                            distanceToStop = stopLine - front;
                        } else {
                            distanceToStop = front - stopLine;
                        }

                        if (distanceToStop < 40) {
                            targetVelocity = Math.min(targetVelocity, Math.abs(car.velocity) * 0.5);
                        }
                    }
                }

                car.accelerateTo(targetVelocity);
                car.move();
            }
        }

        private void drawLight(Graphics2D g2d, int centerX, int centerY) {
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillRoundRect(centerX - 18, centerY - 36, 36, 72, 8, 8);

            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillOval(centerX - 12, centerY - 28, 24, 18);
            g2d.fillOval(centerX - 12, centerY - 6, 24, 18);
            g2d.fillOval(centerX - 12, centerY + 16, 24, 18);
        }

        private void drawLightState(Graphics2D g2d, TrafficLight light, int centerX, int centerY) {
            g2d.setColor(light.state == TrafficLight.State.RED ? Color.RED : Color.LIGHT_GRAY);
            g2d.fillOval(centerX - 12, centerY - 28, 24, 18);

            g2d.setColor(
                    light.state == TrafficLight.State.YELLOW ? Color.ORANGE : Color.LIGHT_GRAY);
            g2d.fillOval(centerX - 12, centerY - 6, 24, 18);

            g2d.setColor(light.state == TrafficLight.State.GREEN ? Color.GREEN : Color.LIGHT_GRAY);
            g2d.fillOval(centerX - 12, centerY + 16, 24, 18);

            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("SansSerif", Font.BOLD, 12));

            String remaining = String.valueOf(light.remainingSeconds);
            int stringWidth = g2d.getFontMetrics().stringWidth(remaining);

            g2d.drawString(remaining, centerX - stringWidth / 2, centerY + 46);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D g2d = (Graphics2D) graphics.create();
            g2d.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw roads
            g2d.setColor(Color.BLACK);

            // Vertical road
            g2d.fillRect(centerX - laneWidth, 0, laneWidth * 2, getHeight());

            // Horizontal road
            g2d.fillRect(0, centerY - laneWidth, getWidth(), laneWidth * 2);

            // Draw intersection
            g2d.setColor(new Color(40, 40, 40));
            g2d.fillRect(
                    centerX - intersectionSize / 2,
                    centerY - intersectionSize / 2,
                    intersectionSize,
                    intersectionSize);

            // Draw lane dividers
            g2d.setColor(Color.YELLOW);
            g2d.setStroke(
                    new BasicStroke(
                            2f,
                            BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_BEVEL,
                            0,
                            new float[] {10},
                            0));

            // Vertical divider
            g2d.drawLine(centerX, 0, centerX, centerY - intersectionSize / 2);
            g2d.drawLine(centerX, centerY + intersectionSize / 2, centerX, getHeight());

            // Horizontal divider
            g2d.drawLine(0, centerY, centerX - intersectionSize / 2, centerY);
            g2d.drawLine(centerX + intersectionSize / 2, centerY, getWidth(), centerY);

            // Draw stop lines (BEFORE the intersection)
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(4f));

            // South lane stop line (cars going UP/NORTH, approaching from BELOW)
            g2d.drawLine(
                    centerX - laneWidth,
                    centerY + intersectionSize / 2,
                    centerX,
                    centerY + intersectionSize / 2);

            // North lane stop line (cars going DOWN/SOUTH, approaching from ABOVE)
            g2d.drawLine(
                    centerX,
                    centerY - intersectionSize / 2,
                    centerX + laneWidth,
                    centerY - intersectionSize / 2);

            // West lane stop line (cars going RIGHT/EAST, approaching from LEFT)
            g2d.drawLine(
                    centerX - intersectionSize / 2,
                    centerY - laneWidth,
                    centerX - intersectionSize / 2,
                    centerY);

            // East lane stop line (cars going LEFT/WEST, approaching from RIGHT)
            g2d.drawLine(
                    centerX + intersectionSize / 2,
                    centerY,
                    centerX + intersectionSize / 2,
                    centerY + laneWidth);

            // Draw traffic lights
            drawLight(g2d, centerX + 50, centerY - intersectionSize / 2 - 80);
            drawLightState(g2d, southLight, centerX + 50, centerY - intersectionSize / 2 - 80);

            drawLight(g2d, centerX - 50, centerY + intersectionSize / 2 + 80);
            drawLightState(g2d, northLight, centerX - 50, centerY + intersectionSize / 2 + 80);

            drawLight(g2d, centerX + intersectionSize / 2 + 80, centerY - 50);
            drawLightState(g2d, eastLight, centerX + intersectionSize / 2 + 80, centerY - 50);

            drawLight(g2d, centerX - intersectionSize / 2 - 80, centerY + 50);
            drawLightState(g2d, westLight, centerX - intersectionSize / 2 - 80, centerY + 50);

            // Draw cars
            for (Car car : northCars) car.draw(g2d);
            for (Car car : southCars) car.draw(g2d);
            for (Car car : eastCars) car.draw(g2d);
            for (Car car : westCars) car.draw(g2d);

            g2d.dispose();
        }
    }

    enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    static class TrafficLight {
        enum State {
            GREEN,
            YELLOW,
            RED
        }

        State state;
        final int greenSeconds, yellowSeconds, redSeconds;
        int remainingSeconds;

        TrafficLight(int green, int yellow, int red) {
            greenSeconds = green;
            yellowSeconds = yellow;
            redSeconds = red;
            state = State.RED;
            remainingSeconds = red;
        }

        void update(int deltaTime) {
            remainingSeconds -= Math.max(1, deltaTime / 1000);

            if (remainingSeconds <= 0) {
                switch (state) {
                    case GREEN -> {
                        state = State.YELLOW;
                        remainingSeconds = yellowSeconds;
                    }
                    case YELLOW -> {
                        state = State.RED;
                        remainingSeconds = redSeconds;
                    }
                    case RED -> {
                        state = State.GREEN;
                        remainingSeconds = greenSeconds;
                    }
                }
            }
        }
    }

    static class Car {
        double x, y, velocity;
        double length;
        double width;
        double stopBuffer = 25.0;
        double currentVelocity;
        double accelerationRate = 0.08;
        Direction direction;
        Color color;

        Car(
                double x,
                double y,
                double velocity,
                double length,
                double width,
                Direction direction,
                Random rand) {
            this.x = x;
            this.y = y;
            this.velocity = velocity;
            this.length = length;
            this.width = width;
            this.currentVelocity = 0.0;
            this.direction = direction;

            // Random car colors
            Color[] colors = {
                new Color(255, 69, 58), // Red
                new Color(50, 173, 230), // Blue
                new Color(52, 199, 89), // Green
                new Color(255, 214, 10), // Yellow
                new Color(175, 82, 222), // Purple
                new Color(255, 159, 10), // Orange
                new Color(255, 255, 255), // White
                new Color(142, 142, 147), // Gray
                new Color(0, 122, 255) // Bright Blue
            };
            this.color = colors[rand.nextInt(colors.length)];
        }

        double getFront() {
            return switch (direction) {
                case NORTH -> y;
                case SOUTH -> y + length;
                case EAST -> x + length;
                case WEST -> x;
            };
        }

        double getBack() {
            return switch (direction) {
                case NORTH -> y + length;
                case SOUTH -> y;
                case EAST -> x;
                case WEST -> x + length;
            };
        }

        void accelerateTo(double target) {
            if (Math.abs(target - currentVelocity) < accelerationRate) {
                currentVelocity = target;
            } else if (currentVelocity < target) {
                currentVelocity += accelerationRate;
            } else {
                currentVelocity -= accelerationRate;
            }
        }

        void move() {
            switch (direction) {
                case NORTH -> y -= currentVelocity;
                case SOUTH -> y += currentVelocity;
                case EAST -> x += currentVelocity;
                case WEST -> x -= currentVelocity;
            }
        }

        void draw(Graphics2D g2d) {
            if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                // Car body
                g2d.setColor(color);
                g2d.fillRoundRect((int) x, (int) y, (int) width, (int) length, 4, 4);

                // Windows
                g2d.setColor(new Color(100, 150, 200, 180));
                g2d.fillRect((int) x + 2, (int) y + 3, (int) width - 4, (int) (length * 0.3));
                g2d.fillRect(
                        (int) x + 2,
                        (int) y + (int) (length * 0.55),
                        (int) width - 4,
                        (int) (length * 0.3));

                // Outline
                g2d.setColor(color.darker());
                g2d.setStroke(new BasicStroke(1.5f));
                g2d.drawRoundRect((int) x, (int) y, (int) width, (int) length, 4, 4);
            } else {
                // Car body
                g2d.setColor(color);
                g2d.fillRoundRect((int) x, (int) y, (int) length, (int) width, 4, 4);

                // Windows
                g2d.setColor(new Color(100, 150, 200, 180));
                g2d.fillRect((int) x + 3, (int) y + 2, (int) (length * 0.3), (int) width - 4);
                g2d.fillRect(
                        (int) x + (int) (length * 0.55),
                        (int) y + 2,
                        (int) (length * 0.3),
                        (int) width - 4);

                // Outline
                g2d.setColor(color.darker());
                g2d.setStroke(new BasicStroke(1.5f));
                g2d.drawRoundRect((int) x, (int) y, (int) length, (int) width, 4, 4);
            }
        }
    }
}
