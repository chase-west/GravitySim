import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UpdateManager {
    private Timeline timeline;
    private double screenWidth;
    private double screenHeight;
    private Stage primaryStage; // Reference to the primary stage (window)

    public UpdateManager(Duration interval, Stage primaryStage) {
        this.primaryStage = primaryStage;

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
        //System.out.println("Screen Width: " + screenWidth + ", Height: " + screenHeight);
    }

    // Update the screen bounds based on the primaryStage size
    private void updateScreenBounds() {
        screenWidth = primaryStage.getWidth();
        screenHeight = primaryStage.getHeight();
    }

    // Getters for current screen dimensions
    public double getScreenWidth() {
        return screenWidth;
    }

    public double getScreenHeight() {
        return screenHeight;
    }
}
