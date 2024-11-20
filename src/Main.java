import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Group;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create body object(s)
        Body physicsBody = new Body(100, 200);
        physicsBody.setMass(100);
        System.out.println(physicsBody.getMass());

        // Add the circle to the scene graph
        Group root = new Group(physicsBody.getCircle());
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Gravity explorer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        System.out.println(screenBounds.getHeight());
        System.out.println(screenBounds.getWidth());
    }
}
