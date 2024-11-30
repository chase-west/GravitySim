import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private int bodyCount = 0;

    @Override
    public void start(Stage primaryStage) {
        double screenWidth = 1000;  // Set to a fixed value for now
        double screenHeight = 1000; // Set to a fixed value for now
        List<Body> bodyList = new ArrayList<>();

        // Create body object(s)
        Body physicsBody = new Body(screenWidth, screenHeight);
        physicsBody.setMass(100);
        physicsBody.setPosition(100, 100);
        physicsBody.setVelocity(2, 2);
        bodyList.add(physicsBody);

        Pane root = new Pane(physicsBody.getCircle());

        // Button to add a physics body
        Button addBody = new Button("Click to add a physics body");
        addBody.setOnAction(event -> {

            bodyCount++; //Increment count for console output (and maybe something else??)

            // Create a new body and add it to the scene
            Body newBody = new Body(screenWidth, screenHeight);
            newBody.setMass(50);
            newBody.setPosition((float) (Math.random() * screenWidth), (float) (Math.random() * screenHeight));
            newBody.setVelocity((float) (Math.random() * 4 - 2), (float) (Math.random() * 4 - 2));
            bodyList.add(newBody);

            // Add the circle to the root
            root.getChildren().add(newBody.getCircle());
            System.out.println("Added a new physics body! Body: " + bodyCount);
        });

        // Layout for button and scene
        VBox buttonLayout = new VBox(10, addBody);
        buttonLayout.setAlignment(Pos.TOP_CENTER);

        // Add the circle to the scene graph
        StackPane stackPane = new StackPane(root, buttonLayout);
        Scene scene = new Scene(stackPane, screenWidth, screenHeight);

        // Setup stage
        primaryStage.setTitle("Gravity Explorer");
        primaryStage.setScene(scene);

        // Start update manager
        UpdateManager updateManager = new UpdateManager(Duration.millis(16), primaryStage, bodyList);
        updateManager.start();

        // Stop updates when the window closes
        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Window is closing. Stopping updates...");
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
