package lk.ijse.dep13.lume;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scene/MainScene.fxml"))));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lume Web");
        primaryStage.show();
    }
}
