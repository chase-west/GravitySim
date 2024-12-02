import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.List;


public class UpdateManager {
    private Timeline timeline;
    private double screenWidth;
    private double screenHeight;
    private Stage primaryStage; // Reference to the primary stage (window)
    private List<Body> bodyList;
    double deltaTime = 0.016 * 100000;

    public UpdateManager(Duration interval, Stage primaryStage, List<Body> bodyList) {
        this.primaryStage = primaryStage;
        this.bodyList = bodyList;

        // Initialize with the current window size
        updateScreenBounds();

        // Create a timeline that calls the update method
        timeline = new Timeline(
                new KeyFrame(interval, event -> handleUpdates())
        );
        timeline.setCycleCount(Timeline.INDEFINITE); // Run indefinitely
    }

    // Start the timeline
    public void start() {
        timeline.play();
    }

    // Stop the timeline
    public void stop() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    // Main handler for periodic updates
    private void handleUpdates() {
        updateScreenBounds();
        calculateGravityBruteForce(bodyList, deltaTime);
        updateBodies();



        // checkBounds(body);;
        //System.out.println("Screen Width: " + screenWidth + ", Height: " + screenHeight);
    }

    // Update the screen bounds based on the primaryStage size
    private void updateScreenBounds() {
        screenWidth = primaryStage.getWidth();
        screenHeight = primaryStage.getHeight();
    }

    private void checkBounds(Body body) {
        float[] location = body.getLocation();
        float[] velocity = body.getVelocity();

        // Reverse velocity if the body hits a screen edge
        if (location[0] <= 0 || location[0] >= screenWidth) {
            body.setVelocity(-velocity[0], velocity[1]);
        }
        if (location[1] <= 0 || location[1] >= screenHeight) {
            body.setVelocity(velocity[0], -velocity[1]);
        }
    }

    private void updateBodies() {
        for (Body body : bodyList) {
            body.updatePosition();
            checkBounds(body);
        }
    }

    // Getters for current screen dimensions
    public double getScreenWidth() {
        return screenWidth;
    }

    private void calculateGravityBruteForce(List<Body> bodyList, double deltaTime) {
        double GRAVITATIONAL_CONSTANT = 6.67430e-11; // Actual gravitational constant
        // Conversion factor (1 pixel = 1,000,000 meters)
        double DISTANCE_SCALE = 1_000_000.0;

        for (int i = 0; i < bodyList.size(); i++) {
            for (int j = 0; j < bodyList.size(); j++) {
                if (i == j) continue;

                Body body1 = bodyList.get(i);
                Body body2 = bodyList.get(j);

                float[] location1 = body1.getLocation();
                float[] location2 = body2.getLocation();

                // Convert locations to meters
                double x1 = location1[0] * DISTANCE_SCALE;
                double y1 = location1[1] * DISTANCE_SCALE;
                double x2 = location2[0] * DISTANCE_SCALE;
                double y2 = location2[1] * DISTANCE_SCALE;

                double distanceX = (x2 - x1);
                double distanceY = (y2 - y1);

                double totalDistance = Math.hypot(distanceX, distanceY);

                // Prevent division by zero and extreme forces
                if (totalDistance < 1) continue;

                // Calculate gravitational force
                double force = (GRAVITATIONAL_CONSTANT * body1.getMass() * body2.getMass())
                        / (totalDistance * totalDistance);

                // Calculate force components
                double forceX = force * (distanceX / totalDistance);
                double forceY = force * (distanceY / totalDistance);

                // Calculate accelerations
                double accelerationX1 = forceX / body1.getMass();
                double accelerationY1 = forceY / body1.getMass();

                // Update velocities
                float[] velocity1 = body1.getVelocity();
                float updatedXVelocity1 = velocity1[0] + (float) (accelerationX1 * deltaTime);
                float updatedYVelocity1 = velocity1[1] + (float) (accelerationY1 * deltaTime);

                body1.setVelocity(updatedXVelocity1, updatedYVelocity1);
            }
        }
    }


    public double getScreenHeight() {
        return screenHeight;
    }
}
