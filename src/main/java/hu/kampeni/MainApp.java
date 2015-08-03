package hu.kampeni;

import hu.kampeni.components.CustomButton;
import hu.kampeni.view.MainScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class MainApp extends Application {

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));

        StackPane b = new StackPane();
//        Scene scene = new Scene(root);
        Scene scene = new MainScene();
//        Scene scene = new Scene(b);
        stage.setWidth(800);
        stage.setHeight(600);
        scene.getStylesheets().add("/styles/Styles.css");
        CustomButton cb = new CustomButton("fasz");
        b.getChildren().add(cb);

        stage.setTitle("Slide puzzle");
        stage.setScene(scene);
        stage.show();
    }

}
