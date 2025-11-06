## Project Description

This project is a graphical application built in **Java** that renders a complex **vector-based portrait**. The portrait is constructed from numerous distinct geometric shapes, each defined by a `GeneralPath` object using precise coordinate points and **Bezier curves**.

The core design principle involves splitting the entire image into many small, color-filled shapes, referred to as "nodes" (`paintShapeNode_0_0_1`). This approach was deliberately chosen to circumvent the **Java Virtual Machine's (JVM) method size limit** (65,535 bytes), which could be reached if the entire vector graphic was contained within a single large drawing method. The shapes are defined relative to a local $(0, 0)$ origin and then positioned and rendered onto the canvas using **affine transformations** (`AffineTransform`).

## How to Run

This is a standard Java AWT application made with NetBeans so it should be tested from there.

## Libraries Used

The project relies solely on the **standard Java libraries** for graphical user interface and 2D rendering.

## Brief Design Notes

### Vector Graphics Focus

The entire image is defined using **vector paths** (`GeneralPath`) consisting of move-to and **cubic Bezier curve** commands (`curveTo`). This ensures that the rendered image is scalable without loss of quality.

### Node-Based Rendering

To manage the complexity of the large graphic and adhere to technical constraints (the JVM method size limit), the portrait is modularized:

1.  Each small visual component is defined in a separate private method (e.g., `paintShapeNode_0_0_X`), where it is drawn relative to a $(0, 0)$ origin.
2.  The main rendering method (`paintPortrait`) calls each node method sequentially. Before each call, it applies an **`AffineTransform`** to the `Graphics2D` context to position the shape correctly on the final canvas.
    _Example:_
    ```java
    graphics.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 340.17578125f, 19.29296875f));
    paintShapeNode_0_0_1(graphics);
    ```
    This approach separates the _definition_ of a shape from its _placement_.

### Color Definition

Colors are specified directly within the drawing methods using `java.awt.Color` with explicit Red, Green, Blue, and Alpha (RGBA) values, allowing for precise color control.

### Screenshot compared to original

![Portrait Output](screenshot.png)
