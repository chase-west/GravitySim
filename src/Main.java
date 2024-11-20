import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.util.Duration;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {

        // Ensure you get correct screen width and height after stage is shown
        double screenWidth = 1000;  // Set to a fixed value for now (or dynamically)
        double screenHeight = 1000; // Set to a fixed value for now (or dynamically)

        // Create body object(s)
        Body physicsBody = new Body(screenWidth, screenHeight);
        physicsBody.setMass(100);

        // Add the circle to the scene graph
        Group root = new Group(physicsBody.getCircle());
        Scene scene = new Scene(root, screenWidth, screenHeight);

        primaryStage.setTitle("Gravity explorer");
        primaryStage.setScene(scene);

        // Stop updates when the window closes
        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Window is closing. Stopping updates...");
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    }
}
