import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Body {
    private Circle circle;

    public Body() {
        // Drawing a Circle
        circle = new Circle();
        // Setting the properties of the circle
        circle.setCenterX(200.0f);
        circle.setCenterY(150.0f);
        circle.setRadius(100.0f);
        // Setting other properties
        circle.setFill(Color.DARKCYAN);
        circle.setStrokeWidth(8.0);
        circle.setStroke(Color.DARKSLATEGREY);
    }

    public Circle getCircle() {
        return circle;
    }
}
