package org.example;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.geom.*;
import java.util.Date;

public class Old extends Frame {

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke bs = new BasicStroke(4.0f);
        g2d.setStroke(bs);

        // stoplight
        g2d.setPaint(Color.GRAY);
        Rectangle2D.Double stoplight1 = new Rectangle2D.Double(200, 100, 100, 225);
        g2d.fill(stoplight1);
        Rectangle2D.Double stoplight2 = new Rectangle2D.Double(195, 95, 110, 235);
        g2d.draw(stoplight2);

        AffineTransform translate = new AffineTransform();
        translate.setToTranslation(340, 0); // quadrant 1
        g2d.fill(translate.createTransformedShape(stoplight1));
        g2d.draw(translate.createTransformedShape(stoplight2));

        translate.setToTranslation(340, 520); // quadrant 4
        g2d.fill(translate.createTransformedShape(stoplight1));
        g2d.draw(translate.createTransformedShape(stoplight2));

        translate.setToTranslation(0, 520); // quadrant 3
        g2d.fill(translate.createTransformedShape(stoplight1));
        g2d.draw(translate.createTransformedShape(stoplight2));

        g2d.setPaint(Color.RED);
        Ellipse2D.Double red_1 = new Ellipse2D.Double(220, 110, 60, 60);
        g2d.draw(red_1);
        Ellipse2D.Double red_2 = new Ellipse2D.Double(220, 630, 60, 60);
        g2d.draw(red_2);
        Ellipse2D.Double red_3 = new Ellipse2D.Double(560, 630, 60, 60);
        g2d.draw(red_3);
        Ellipse2D.Double red_4 = new Ellipse2D.Double(560, 110, 60, 60);
        g2d.draw(red_4);

        translate.setToTranslation(0, 520);
        g2d.draw(translate.createTransformedShape(red_2));

        g2d.setPaint(Color.YELLOW);
        Ellipse2D.Double yellow_1 = new Ellipse2D.Double(220, 180, 60, 60);
        g2d.draw(yellow_1);
        Ellipse2D.Double yellow_2 = new Ellipse2D.Double(220, 700, 60, 60);
        g2d.draw(yellow_2);
        Ellipse2D.Double yellow_3 = new Ellipse2D.Double(560, 700, 60, 60);
        g2d.draw(yellow_3);
        Ellipse2D.Double yellow_4 = new Ellipse2D.Double(560, 180, 60, 60);
        g2d.draw(yellow_4);

        translate.setToTranslation(0, 520);
        g2d.draw(translate.createTransformedShape(yellow_2));

        g2d.setPaint(Color.GREEN);
        Ellipse2D.Double green_1 = new Ellipse2D.Double(220, 250, 60, 60);
        g2d.draw(green_1);
        Ellipse2D.Double green_2 = new Ellipse2D.Double(220, 770, 60, 60);
        g2d.draw(green_2);
        Ellipse2D.Double green_3 = new Ellipse2D.Double(560, 770, 60, 60);
        g2d.draw(green_3);
        Ellipse2D.Double green_4 = new Ellipse2D.Double(560, 250, 60, 60);
        g2d.draw(green_4);

        //
        g2d.setPaint(Color.black);
        Line2D.Double lane_1 = new Line2D.Double(10, 400, 900, 400);
        g2d.draw(lane_1);
        Line2D.Double lane_2 = new Line2D.Double(10, 600, 900, 600);
        g2d.draw(lane_2);
        Line2D.Double lane_3 = new Line2D.Double(320, 400, 320, 20);
        g2d.draw(lane_3);
        Line2D.Double lane_4 = new Line2D.Double(520, 400, 520, 20);
        g2d.draw(lane_4);
        Line2D.Double lane_5 = new Line2D.Double(320, 600, 320, 1000);
        g2d.draw(lane_5);
        Line2D.Double lane_6 = new Line2D.Double(520, 600, 520, 1000);
        g2d.draw(lane_6);

        BasicStroke sb = new BasicStroke(6.0f);
        g2d.setStroke(sb);
        g2d.setPaint(Color.yellow);

        Line2D.Double yellow_lane1 = new Line2D.Double(50, 500, 150, 500);
        g2d.draw(yellow_lane1);
        Line2D.Double yellow_lane2 = new Line2D.Double(200, 500, 300, 500);
        g2d.draw(yellow_lane2);

        Line2D.Double yellow_lane4 = new Line2D.Double(540, 500, 640, 500);
        g2d.draw(yellow_lane4);
        Line2D.Double yellow_lane5 = new Line2D.Double(690, 500, 790, 500);
        g2d.draw(yellow_lane5);

        g2d.setPaint(Color.white);
        Line2D.Double blank_lane = new Line2D.Double(321, 400, 519, 400);
        g2d.draw(blank_lane);
        Line2D.Double blank_lane2 = new Line2D.Double(321, 600, 519, 600);
        g2d.draw(blank_lane2);

        GeneralPath car = new GeneralPath();
        car.moveTo(60, 120);
        car.lineTo(80, 120);
        car.quadTo(90, 140, 100, 120);
        car.lineTo(160, 120);
        car.quadTo(170, 140, 180, 120);
        car.lineTo(200, 120);
        car.curveTo(195, 100, 200, 80, 160, 80);
        car.lineTo(110, 80);
        car.lineTo(90, 100);
        car.lineTo(60, 100);
        car.lineTo(60, 120);

        g2d.setPaint(Color.blue);
        AffineTransform scaling = new AffineTransform();
        translate.setToTranslation(300, 450);
        scaling.setToScale(-1, 1);

        g2d.fill(translate.createTransformedShape(scaling.createTransformedShape(car)));
        g2d.setPaint(Color.red);
        g2d.fill(red_3);
        g2d.fill(red_4);
        g2d.setPaint(Color.blue);

        AffineTransform rotate = new AffineTransform();
        translate.setToTranslation(250, 600);
        rotate.setToRotation(Math.PI / 2);
        g2d.fill(
                translate.createTransformedShape(
                        scaling.createTransformedShape(rotate.createTransformedShape(car))));

        // quadrant 1
        int i = 600;
        g2d.setPaint(Color.GREEN);
        g2d.fill(green_1);
        for (int x = 0; x < 15; x++) {
            g2d.setPaint(Color.WHITE);
            translate.setToTranslation(i, 350);
            g2d.fill(translate.createTransformedShape((car)));
            i -= 80;
            g2d.setPaint(Color.GRAY);
            translate.setToTranslation(i, 350);
            g2d.fill(translate.createTransformedShape((car)));
            sustain(500);

            if (i == 200) {
                g2d.fill(green_1);
                g2d.setPaint(Color.YELLOW);
                g2d.fill(yellow_1);
            }
        }
        g2d.setPaint(Color.GRAY);
        g2d.fill(yellow_1);
        g2d.setPaint(Color.RED);
        g2d.fill(red_1);

        // quadrant 4
        int i2 = 300;
        g2d.setPaint(Color.GRAY);
        g2d.fill(red_3);
        g2d.setPaint(Color.green);
        g2d.fill(green_3);
        for (int x = 0; x < 15; x++) {
            g2d.setPaint(Color.WHITE);
            translate.setToTranslation(i2, 450);
            g2d.fill(translate.createTransformedShape(scaling.createTransformedShape(car)));
            i2 += 80;
            g2d.setPaint(Color.GRAY);
            translate.setToTranslation(i2, 450);
            g2d.fill(translate.createTransformedShape(scaling.createTransformedShape(car)));
            sustain(500);

            if (i2 == 500) {
                g2d.fill(green_3);
                g2d.setPaint(Color.YELLOW);
                g2d.fill(yellow_3);
            }
        }
        g2d.setPaint(Color.GRAY);
        g2d.fill(green_3);
        g2d.fill(yellow_3);
        g2d.setPaint(Color.RED);
        g2d.fill(red_3);

        g2d.setPaint(Color.GRAY);
        g2d.fill(red_4);

        // quadrant 1
        int i3 = 600;
        g2d.setPaint(Color.GREEN);
        g2d.fill(green_4);
        for (int x = 0; x < 15; x++) {
            g2d.setPaint(Color.WHITE);
            translate.setToTranslation(250, i3);
            g2d.fill(
                    translate.createTransformedShape(
                            scaling.createTransformedShape(rotate.createTransformedShape(car))));
            i3 -= 80;
            g2d.setPaint(Color.GRAY);
            translate.setToTranslation(250, i3);
            g2d.fill(
                    translate.createTransformedShape(
                            scaling.createTransformedShape(rotate.createTransformedShape(car))));
            sustain(500);

            if (i3 == 400) {
                g2d.fill(green_4);
                g2d.setPaint(Color.YELLOW);
                g2d.fill(yellow_4);
            }
        }
        g2d.setPaint(Color.GRAY);
        g2d.fill(yellow_4);
        g2d.setPaint(Color.RED);
        g2d.fill(red_4);
    }

    public void sustain(long t) {
        long finish = new Date().getTime() + t;
        while ((new Date()).getTime() < finish) {}
    }

    public static void main(String[] args) {
        MP_4WTL_Rodriguez traffic = new MP_4WTL_Rodriguez();
        traffic.setBackground(Color.WHITE);
        traffic.setTitle("Traffic Lights");
        traffic.setSize(1000, 1000);
        traffic.setVisible(true);

        traffic.addWindowListener(
                new java.awt.event.WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        traffic.dispose();
                    }
                });
    }
}
