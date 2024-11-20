import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.util.Random;


public class Body {
    private Circle circle;
    private float mass;
    public static final double GRAVITATIONAL_CONSTANT = 6.67430e-11;

    public Body() {

        // Drawing a Circle
        circle = new Circle();
        // Setting the properties of the circle
        circle.setCenterX(200.0f);
        circle.setCenterY(150.0f);
        circle.setRadius(10.0f);
        // Setting other properties
        circle.setFill(Color.DARKCYAN);
        circle.setStrokeWidth(8.0);
    }

    // Constructor to create body in respect to screen width and height
    public Body(double screenWidth, double screenHeight) {

        float randX = randFloat(0, (float) (screenWidth - 10));
        float randY = randFloat(0, (float) (screenHeight - 10));

        // Drawing a Circle
        circle = new Circle();
        // Setting the properties of the circle
        circle.setCenterX(randX);
        circle.setCenterY(randY);
        circle.setRadius(10.0f);
        // Setting other properties
        circle.setFill(Color.DARKCYAN);
        circle.setStrokeWidth(8.0);


    }

    public static float randFloat(float min, float max) {

        Random rand = new Random();

        return rand.nextFloat() * (max - min) + min;

    }

    public Circle getCircle() {
        return circle;
    }

    public float getMass() {
        return  this.mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }


}
