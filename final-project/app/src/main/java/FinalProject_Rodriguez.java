import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

public class FinalProject_Rodriguez extends JPanel {
    private double time = 0;
    private Timer timer;

    public FinalProject_Rodriguez() {
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.BLACK);

        timer =
                new Timer(
                        16,
                        event -> {
                            time += 0.016;
                            repaint();
                        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int groundY = 550; // Four Arms ground position

        // Phase 1: Watch appears and pulses (0-5s)
        if (time < 5) {
            drawOmnitrix(graphics2D, centerX, centerY, time);
        }
        // Phase 2: Dial shows different aliens (5-12s)
        else if (time < 12) {
            drawOmnitrix(graphics2D, centerX, centerY, 5);
            drawAlienSelection(graphics2D, centerX, centerY, time - 5);
        }
        // Phase 3: Selection slam (12-15s)
        else if (time < 15) {
            drawOmnitrix(graphics2D, centerX, centerY, 5);
            drawDialSlam(graphics2D, centerX, centerY, time - 12);
        }
        // Phase 4: Energy burst (15-22s)
        else if (time < 22) {
            drawEnergyBurst(graphics2D, centerX, centerY, time - 15);
        }
        // Phase 5: Transformation (22-28s)
        else if (time < 28) {
            drawTransformation(graphics2D, centerX, groundY, time - 22);
        }
        // Phase 6: Final alien pose (28-32s)
        else if (time < 32) {
            drawFinalAlien(graphics2D, centerX, groundY, time - 28);
        } else {
            time = 0;
        }
    }

    private void drawOmnitrix(Graphics2D graphics2D, int centerX, int centerY, double time) {
        // White border circle
        int whiteBorderSize = 300;
        int xWhiteBorder = centerX - whiteBorderSize / 2;
        int yWhiteBorder = centerY - whiteBorderSize / 2;
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillOval(xWhiteBorder, yWhiteBorder, whiteBorderSize, whiteBorderSize);

        // Grey circle
        int greySize = 280;
        int xGrey = centerX - greySize / 2;
        int yGrey = centerY - greySize / 2;
        graphics2D.setColor(Color.GRAY);
        graphics2D.fillOval(xGrey, yGrey, greySize, greySize);

        // Black center circle
        int blackSize = 200;
        int xBlack = centerX - blackSize / 2;
        int yBlack = centerY - blackSize / 2;
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillOval(xBlack, yBlack, blackSize, blackSize);

        // Green hourglass shape in center
        int shapeWidth = 80;
        int shapeHeight = 100;
        int xCenter = centerX - shapeWidth / 2;
        int yCenter = centerY - shapeHeight;

        // Pulsing green glow (only the green pulses)
        float alpha = (float) (0.3 + Math.sin(time * 4) * 0.2);
        graphics2D.setColor(new Color(0, 255, 0, (int) (alpha * 255)));
        graphics2D.fillOval(centerX - 120, centerY - 120, 240, 240);

        // Solid green hourglass
        graphics2D.setColor(new Color(173, 217, 110));
        int arcHeight = shapeHeight;
        graphics2D.fillArc(xCenter, yCenter + 5, shapeWidth, arcHeight, 0, -180);
        graphics2D.fillArc(xCenter, yCenter + shapeHeight - 5, shapeWidth, arcHeight, 0, 180);
    }

    private void drawAlienSelection(Graphics2D graphics2D, int centerX, int centerY, double time) {
        // Determine which alien to show (cycles through 4 aliens)
        int currentAlien = (int) (time / 1.75) % 4;

        // Fade transition between aliens
        double fadeProgress = (time % 1.75) / 1.75;
        float alpha = 255;
        if (fadeProgress < 0.1) {
            alpha = (float) (fadeProgress / 0.1) * 255;
        } else if (fadeProgress > 0.9) {
            alpha = (float) ((1 - fadeProgress) / 0.1) * 255;
        }

        // Draw current alien silhouette in center
        graphics2D.setColor(new Color(0, 255, 0, (int) alpha));
        drawAlienIcon(graphics2D, centerX, centerY, currentAlien);

        // Selection indicator arrow
        graphics2D.setColor(Color.GREEN);
        graphics2D.fillPolygon(
                new int[] {centerX, centerX - 20, centerX + 20},
                new int[] {centerY - 180, centerY - 160, centerY - 160},
                3);
    }

    private void drawAlienIcon(Graphics2D graphics2D, int x, int y, int alienType) {
        AffineTransform old = graphics2D.getTransform();
        graphics2D.translate(x, y);
        graphics2D.scale(4, 4);

        switch (alienType % 4) {
            case 0: // Four Arms - massive muscular alien
                // Head with mohawk fin
                graphics2D.fillOval(-8, -25, 16, 18);
                graphics2D.fillPolygon(new int[] {-3, 0, 3}, new int[] {-25, -32, -25}, 3);
                // Massive torso
                graphics2D.fillRect(-18, -8, 36, 28);
                // Top arms
                graphics2D.fillRect(-28, -6, 10, 5); // left shoulder
                graphics2D.fillRect(-35, -6, 7, 18); // left upper arm
                graphics2D.fillRect(-38, 12, 10, 8); // left forearm
                graphics2D.fillRect(18, -6, 10, 5); // right shoulder
                graphics2D.fillRect(28, -6, 7, 18); // right upper arm
                graphics2D.fillRect(28, 12, 10, 8); // right forearm
                // Bottom arms
                graphics2D.fillRect(-28, 8, 10, 5); // left shoulder
                graphics2D.fillRect(-35, 8, 7, 18); // left upper arm
                graphics2D.fillRect(-38, 26, 10, 8); // left forearm
                graphics2D.fillRect(18, 8, 10, 5); // right shoulder
                graphics2D.fillRect(28, 8, 7, 18); // right upper arm
                graphics2D.fillRect(28, 26, 10, 8); // right forearm
                break;
            case 1: // Heatblast - flame creature
                // Molten body
                graphics2D.fillOval(-12, -5, 24, 30);
                // Flame head
                graphics2D.fillPolygon(
                        new int[] {-8, -10, -5, 0, 5, 10, 8},
                        new int[] {-8, -15, -22, -25, -22, -15, -8},
                        7);
                // Inner flame detail
                graphics2D.fillPolygon(new int[] {-5, 0, 5}, new int[] {-10, -18, -10}, 3);
                // Flame arms
                graphics2D.fillPolygon(new int[] {-12, -20, -18}, new int[] {5, 8, 15}, 3);
                graphics2D.fillPolygon(new int[] {12, 20, 18}, new int[] {5, 8, 15}, 3);
                break;
            case 2: // XLR8 - speed demon
                // Helmet/visor head
                graphics2D.fillOval(-10, -22, 20, 16);
                graphics2D.fillRect(-8, -18, 16, 4); // visor
                // Body hunched forward
                graphics2D.fillPolygon(new int[] {-10, -8, 8, 10}, new int[] {-5, 12, 12, -5}, 4);
                // Wheel feet
                graphics2D.fillOval(-10, 18, 8, 8);
                graphics2D.fillOval(2, 18, 8, 8);
                break;
            case 3: // Diamond Head - crystal alien
                // Crystal head with spikes
                graphics2D.fillPolygon(new int[] {-8, 0, 8}, new int[] {-12, -28, -12}, 3);
                graphics2D.fillPolygon(new int[] {-12, -8, -4}, new int[] {-10, -18, -10}, 3);
                graphics2D.fillPolygon(new int[] {12, 8, 4}, new int[] {-10, -18, -10}, 3);
                // Torso crystals
                graphics2D.fillPolygon(
                        new int[] {-15, -10, 0, 10, 15, 10, 0, -10},
                        new int[] {-8, -5, -8, -5, -8, 20, 22, 20},
                        8);
                // Arm crystals
                graphics2D.fillPolygon(new int[] {-15, -22, -25, -20}, new int[] {0, 0, 12, 15}, 4);
                graphics2D.fillPolygon(new int[] {15, 22, 25, 20}, new int[] {0, 0, 12, 15}, 4);
                break;
        }
        graphics2D.setTransform(old);
    }

    private void drawDialSlam(Graphics2D graphics2D, int centerX, int centerY, double time) {
        // Scale down effect with bright flash
        double scale = 1 - (time / 3) * 0.3;

        AffineTransform old = graphics2D.getTransform();
        graphics2D.translate(centerX, centerY);
        graphics2D.scale(scale, scale);

        graphics2D.setColor(new Color(0, 255, 0));
        graphics2D.fillOval(-80, -80, 160, 160);

        // Bright white flash
        float alpha = (float) (1 - time / 3);
        graphics2D.setColor(new Color(255, 255, 255, (int) (alpha * 255)));
        graphics2D.fillOval(-100, -100, 200, 200);

        graphics2D.setTransform(old);
    }

    private void drawEnergyBurst(Graphics2D graphics2D, int centerX, int centerY, double time) {
        // Multiple expanding energy rings
        for (int i = 0; i < 6; i++) {
            double delay = i * 0.25;
            if (time > delay) {
                double localT = time - delay;
                double radius = localT * 100;
                float alpha = (float) Math.max(0, 1 - localT / 7);

                graphics2D.setColor(new Color(0, 255, 0, (int) (alpha * 200)));
                graphics2D.setStroke(new BasicStroke(10));
                graphics2D.drawOval(
                        (int) (centerX - radius),
                        (int) (centerY - radius),
                        (int) (radius * 2),
                        (int) (radius * 2));
            }
        }

        // Bright center flash
        float alpha = (float) Math.max(0, 1 - time / 7);
        graphics2D.setColor(new Color(255, 255, 255, (int) (alpha * 255)));
        graphics2D.fillOval(centerX - 60, centerY - 60, 120, 120);

        graphics2D.setColor(new Color(0, 255, 0, (int) (alpha * 255)));
        graphics2D.fillOval(centerX - 50, centerY - 50, 100, 100);
    }

    private void drawTransformation(Graphics2D graphics2D, int centerX, int centerY, double t) {
        // Draw background immediately
        drawActionBackground(graphics2D);

        // Quick green flash that fades out on top
        if (t < 0.5) {
            float alpha = (float) ((0.5 - t) / 0.5 * 255);
            graphics2D.setColor(new Color(0, 255, 0, (int) alpha));
            graphics2D.fillRect(0, 0, getWidth(), getHeight());
        }

        // Four Arms appears immediately
        drawFourArms(graphics2D, centerX, centerY, 0);
    }

    private void drawFinalAlien(Graphics2D graphics2D, int centerX, int centerY, double t) {
        // Draw background first
        drawActionBackground(graphics2D);

        // Action sequences - toy figure fighting style
        int sequence = (int) (t / 1.5) % 3;
        double seqProgress = (t % 1.5) / 1.5;

        AffineTransform old = graphics2D.getTransform();

        if (sequence == 0) {
            // Sequence 1: Jumping slam attack
            int jumpHeight = (int) (Math.sin(seqProgress * Math.PI) * -150);
            graphics2D.translate(centerX, centerY + jumpHeight);

            if (seqProgress > 0.7) {
                // Arms down for slam
                drawFourArms(graphics2D, 0, 0, 1);
            } else {
                // Arms up during jump
                drawFourArms(graphics2D, 0, 0, 2);
            }

        } else if (sequence == 1) {
            // Sequence 2: Spinning attack
            double spin = seqProgress * Math.PI * 4;
            graphics2D.translate(centerX, centerY);
            graphics2D.rotate(spin);
            drawFourArms(graphics2D, 0, 0, 3);

        } else {
            // Sequence 3: Side to side punching
            int sideMove = (int) (Math.sin(seqProgress * Math.PI * 4) * 60);
            graphics2D.translate(centerX + sideMove, centerY);

            if (Math.sin(seqProgress * Math.PI * 4) > 0) {
                drawFourArms(graphics2D, 0, 0, 4); // Left punch
            } else {
                drawFourArms(graphics2D, 0, 0, 5); // Right punch
            }
        }

        graphics2D.setTransform(old);
    }

    private void drawActionBackground(Graphics2D graphics2D) {
        // City skyline background
        graphics2D.setColor(new Color(30, 30, 60)); // Dark blue sky
        graphics2D.fillRect(0, 0, getWidth(), getHeight());

        // Buildings
        graphics2D.setColor(new Color(40, 40, 50));
        graphics2D.fillRect(50, 400, 100, 400);
        graphics2D.fillRect(200, 300, 120, 500);
        graphics2D.fillRect(400, 350, 80, 450);
        graphics2D.fillRect(550, 250, 150, 550);

        // Windows
        graphics2D.setColor(new Color(255, 255, 150, 180));
        for (int x = 60; x < 140; x += 20) {
            for (int y = 420; y < 780; y += 30) {
                graphics2D.fillRect(x, y, 10, 15);
            }
        }
        for (int x = 210; x < 310; x += 20) {
            for (int y = 320; y < 780; y += 30) {
                graphics2D.fillRect(x, y, 10, 15);
            }
        }
        for (int x = 410; x < 470; x += 20) {
            for (int y = 370; y < 780; y += 30) {
                graphics2D.fillRect(x, y, 10, 15);
            }
        }
        for (int x = 560; x < 690; x += 20) {
            for (int y = 270; y < 780; y += 30) {
                graphics2D.fillRect(x, y, 10, 15);
            }
        }

        // Ground
        graphics2D.setColor(new Color(60, 60, 70));
        graphics2D.fillRect(0, 700, getWidth(), 100);
    }

    private void drawFourArms(Graphics2D graphics2D, int centerX, int centerY, int pose) {
        AffineTransform old = graphics2D.getTransform();
        graphics2D.translate(centerX, centerY);

        // Body
        graphics2D.setColor(new Color(255, 100, 0));
        graphics2D.fillRect(-60, -20, 120, 140);

        // Head
        graphics2D.fillOval(-50, -110, 100, 100);

        // Arms based on pose
        switch (pose) {
            case 0: // Standing/idle
                graphics2D.fillRect(-85, -10, 25, 90);
                graphics2D.fillRect(-85, 50, 25, 70);
                graphics2D.fillRect(60, -10, 25, 90);
                graphics2D.fillRect(60, 50, 25, 70);
                break;
            case 1: // Arms down (slam)
                graphics2D.fillRect(-85, 40, 25, 80);
                graphics2D.fillRect(-85, 80, 25, 60);
                graphics2D.fillRect(60, 40, 25, 80);
                graphics2D.fillRect(60, 80, 25, 60);
                break;
            case 2: // Arms up (jumping)
                graphics2D.fillRect(-85, -80, 25, 80);
                graphics2D.fillRect(-85, -40, 25, 60);
                graphics2D.fillRect(60, -80, 25, 80);
                graphics2D.fillRect(60, -40, 25, 60);
                break;
            case 3: // Arms out (spinning)
                graphics2D.fillRect(-110, -10, 40, 25);
                graphics2D.fillRect(-130, 30, 60, 25);
                graphics2D.fillRect(70, -10, 40, 25);
                graphics2D.fillRect(70, 30, 60, 25);
                break;
            case 4: // Left punch
                graphics2D.fillRect(-120, -10, 60, 25);
                graphics2D.fillRect(-120, 30, 60, 25);
                graphics2D.fillRect(60, -10, 25, 90);
                graphics2D.fillRect(60, 50, 25, 70);
                break;
            case 5: // Right punch
                graphics2D.fillRect(-85, -10, 25, 90);
                graphics2D.fillRect(-85, 50, 25, 70);
                graphics2D.fillRect(60, -10, 60, 25);
                graphics2D.fillRect(60, 30, 60, 25);
                break;
        }

        // Legs
        graphics2D.fillRect(-45, 120, 35, 80);
        graphics2D.fillRect(10, 120, 35, 80);

        // Black details on body
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(-50, 0, 100, 15);
        graphics2D.fillRect(-50, 40, 100, 15);

        // Eyes
        graphics2D.setColor(new Color(0, 255, 0));
        graphics2D.fillOval(-30, -80, 20, 20);
        graphics2D.fillOval(10, -80, 20, 20);

        // Omnitrix on chest
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillOval(-30, 20, 60, 60);
        graphics2D.setColor(Color.GRAY);
        graphics2D.fillOval(-25, 25, 50, 50);
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillOval(-20, 30, 40, 40);
        graphics2D.setColor(new Color(0, 255, 0));
        graphics2D.fillOval(-15, 35, 30, 30);

        graphics2D.setTransform(old);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> {
                    JFrame frame = new JFrame("Rodriguez - Final Project (Ben 10)");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.add(new FinalProject_Rodriguez());
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                });
    }
}
