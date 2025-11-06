import java.awt.event.*;

public class CSEGraphics {
    // public static void main(String[] args) {
    //     StringAndGeometry stringAndGeometry = new StringAndGeometry();
    //     stringAndGeometry.setBackground(Color.LIGHT_GRAY);
    //     stringAndGeometry.setTitle("Drawing String in Java");
    //     stringAndGeometry.setSize(900, 900);
    //     stringAndGeometry.setForeground(Color.BLUE);
    //     stringAndGeometry.setVisible(true);
    //
    //     stringAndGeometry.addWindowListener(
    //             new WindowAdapter() {
    //                 public void windowClosing(WindowEvent we) {
    //                     System.exit(0);
    //                 }
    //             });
    // }

    public static void main(String[] args) {
        int height = 700;

        Transformation transformation = new Transformation(height);
        transformation.setTitle("Transformation Demo - Rodriguez");
        transformation.setSize(700, height);
        transformation.setVisible(true);
        transformation.setLocationRelativeTo(null);

        transformation.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent event) {
                        System.exit(0);
                    }
                });
    }
}
