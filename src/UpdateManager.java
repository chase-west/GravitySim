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
        calculateGravityBruteForce(bodyList);
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

    private void calculateGravityBruteForce(List<Body> bodyList) {
        for (int i = 0; i < bodyList.size(); i++) {

        }
    }


    public double getScreenHeight() {
        return screenHeight;
    }
}
