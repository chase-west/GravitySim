import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.util.Duration;

public class UpdateManager {
    private Timeline timeline;
    private Rectangle2D screenBounds;

    private double screenWidth;
    private double screenHeight;

    public UpdateManager(Duration interval) {
        // Initialize bounds
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
        System.out.println("Screen Width: " + screenWidth + ", Height: " + screenHeight);
    }

    // Update the screen bounds
    private void updateScreenBounds() {
        screenBounds = Screen.getPrimary().getVisualBounds();
        screenWidth = screenBounds.getWidth();
        screenHeight = screenBounds.getHeight();
    }

    // Getters for current screen dimensions
    public double getScreenWidth() {
        return screenWidth;
    }

    public double getScreenHeight() {
        return screenHeight;
    }
}
