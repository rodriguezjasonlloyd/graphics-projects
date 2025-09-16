package mp2rodriguez;

import java.awt.*;
import java.awt.geom.*;

public class MP2_Rodriguez extends Frame {
    private Shape makeCross(float ax, float ay) {
        final float AB = 100f, R = 40f, CD = 20f, EF = 100f;
        GeneralPath cross = new GeneralPath();

        cross.moveTo(ax, ay);
        cross.lineTo(ax, ay - AB);
        cross.quadTo(ax, ay - AB - R, ax + R, ay - AB - R);
        cross.lineTo(ax + R + CD, ay - AB - R);
        cross.quadTo(ax + R + CD + R, ay - AB - R, ax + R + CD + R, ay - AB);
        cross.lineTo(ax + R + CD + R, ay);

        cross.lineTo(ax + R + CD + R + EF, ay);
        cross.quadTo(ax + R + CD + R + EF + R, ay, ax + R + CD + R + EF + R, ay + R);
        cross.lineTo(ax + R + CD + R + EF + R, ay + R + CD);
        cross.quadTo(
                ax + R + CD + R + EF + R, ay + R + CD + R, ax + R + CD + R + EF, ay + R + CD + R);
        cross.lineTo(ax + R + CD + R, ay + AB);

        cross.lineTo(ax + R + CD + R, ay + AB + EF);
        cross.quadTo(ax + R + CD + R, ay + AB + EF + R, ax + R + CD, ay + AB + EF + R);
        cross.lineTo(ax + R, ay + AB + EF + R);
        cross.quadTo(ax, ay + AB + EF + R, ax, ay + AB + EF);
        cross.lineTo(ax, ay + AB);

        cross.lineTo(ax - EF, ay + AB);
        cross.quadTo(ax - EF - R, ay + AB, ax - EF - R, ay + AB - R);
        cross.lineTo(ax - EF - R, ay + AB - R - CD);
        cross.quadTo(ax - EF - R, ay + AB - R - CD - R, ax - EF, ay + AB - R - CD - R);
        cross.lineTo(ax, ay);
        cross.closePath();

        return cross;
    }

    private Shape makeTreeBody(float ax, float ay) {
        final float TREE_W = 300f, TREE_H = 450f, OFFSET = 20f;
        final float HALF_W = TREE_W / 2f, TIER_H = TREE_H / 3f;

        float cx = ax;
        float y1 = ay + TIER_H, y2 = ay + 2 * TIER_H, y3 = ay + TREE_H;
        float hw1 = HALF_W * (1f / 3f), hw2 = HALF_W * (2f / 3f), hw3 = HALF_W;

        GeneralPath tree = new GeneralPath();
        tree.moveTo(cx, ay);
        tree.lineTo(cx + hw1, y1);
        tree.lineTo(cx + hw1 - OFFSET, y1);
        tree.lineTo(cx + hw2, y2);
        tree.lineTo(cx + hw2 - OFFSET, y2);
        tree.lineTo(cx + hw3, y3);
        tree.lineTo(cx - hw3, y3);
        tree.lineTo(cx - hw2 + OFFSET, y2);
        tree.lineTo(cx - hw2, y2);
        tree.lineTo(cx - hw1 + OFFSET, y1);
        tree.lineTo(cx - hw1, y1);
        tree.closePath();

        return tree;
    }

    private Shape makeTrunk(float ax, float ay) {
        final float TREE_H = 450f;
        float y3 = ay + TREE_H;
        return new Rectangle2D.Float(ax - 15f, y3, 30f, 30f);
    }

    private Shape makeStar(float ax, float ay) {
        final float STAR_S = 30f;
        float sx = ax, sy = ay - 20f;
        GeneralPath star = new GeneralPath();
        star.moveTo(sx, sy - STAR_S / 2f);
        star.lineTo(sx + STAR_S / 2f, sy);
        star.lineTo(sx, sy + STAR_S / 2f);
        star.lineTo(sx - STAR_S / 2f, sy);
        star.closePath();
        return star;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2f));

        Shape cross = makeCross(250f, 280f);
        g2.setColor(Color.BLACK);
        g2.draw(cross);

        Shape treeBody = makeTreeBody(300f, 100f);
        Shape trunk = makeTrunk(300f, 100f);
        Shape star = makeStar(300f, 100f);

        g2.setColor(new Color(0, 180, 0));
        g2.fill(treeBody);
        g2.setColor(Color.GRAY);
        g2.fill(trunk);
        g2.setColor(Color.YELLOW);
        g2.fill(star);
        g2.setColor(Color.DARK_GRAY);
        g2.draw(treeBody);
        g2.draw(trunk);
        g2.draw(star);

        Area crossArea = new Area(cross);
        Area treeArea = new Area(treeBody);
        treeArea.add(new Area(star));

        Area union = new Area(crossArea);
        union.add(new Area(treeArea));

        Area inter = new Area(crossArea);
        inter.intersect(new Area(treeArea));

        Area xor = new Area(crossArea);
        xor.exclusiveOr(new Area(treeArea));

        Area crossMinusTree = new Area(crossArea);
        crossMinusTree.subtract(treeArea);

        Area treeMinusCross = new Area(treeArea);
        treeMinusCross.subtract(crossArea);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));
        g2.setColor(Color.BLUE);
        g2.fill(union);
        g2.setColor(Color.RED);
        g2.fill(inter);
        g2.setColor(Color.MAGENTA);
        g2.fill(xor);
        g2.setColor(new Color(60, 120, 220));
        g2.fill(crossMinusTree);
        g2.setColor(new Color(0, 200, 100));
        g2.fill(treeMinusCross);

        g2.setComposite(AlphaComposite.SrcOver);
    }

    public static void main(String[] args) {
        MP2_Rodriguez frame = new MP2_Rodriguez();
        frame.setTitle("MP2Rodriguez - Cross & Tree with Area Ops");
        frame.setSize(1000, 800);
        frame.setBackground(Color.WHITE);
        frame.setVisible(true);
    }
}
