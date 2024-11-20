import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create body object(s)
        Body physicsBody = new Body();

        // Add the circle to the scene graph
        Group root = new Group(physicsBody.getCircle());
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Gravity explorer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
