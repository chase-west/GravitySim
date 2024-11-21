import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.Random;

public class Body {
    private Circle circle;
    private float mass;
    private float xLocation;
    private float yLocation;
    private  float xVelocity;
    private float yVelocity;

    public static final double GRAVITATIONAL_CONSTANT = 6.67430e-11;

    public Body(double screenWidth, double screenHeight) {
        this.xLocation = randFloat(0, (float) (screenWidth - 10));
        this.yLocation = randFloat(0, (float) (screenHeight - 10));

        // Drawing a Circle
        circle = new Circle();
        circle.setCenterX(this.xLocation);
        circle.setCenterY(this.yLocation);
        circle.setRadius(10.0f);
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
        return this.mass;
    }

    public void setPosition(float xPos, float yPos) {
        this.xLocation = xPos;
        this.yLocation = yPos;
        circle.setCenterX(this.xLocation);
        circle.setCenterY(this.yLocation);
    }

    public void updatePosition() {
        this.xLocation += this.xVelocity;
        this.yLocation += this.yVelocity;

        // Update the circle's position
        circle.setCenterX(this.xLocation);
        circle.setCenterY(this.yLocation);
    }

    public float[] getVelocity() {
        return new float[] {this.xVelocity, this.yVelocity};
    }
    public void setVelocity(float xVelocity, float yVelocity) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }


    public void setMass(float mass) {
        this.mass = mass;
    }

    // Get the current location of the Body
    public float[] getLocation() {
        return new float[] {this.xLocation, this.yLocation};
    }
}
