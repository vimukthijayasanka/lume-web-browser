package lk.ijse.dep13.lume.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainController {
    public AnchorPane root;
    public TextField txtAddress;
    public WebView wbDisplay;
    public Button btnAbout;

    private String url;

    public void initialize() {
        // initially URL set to http://google.com
        txtAddress.setText("http://google.com/");

        txtAddress.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) Platform.runLater(txtAddress::selectAll);
        });
    }

    public void txtAddressOnAction(ActionEvent actionEvent) {
        url = txtAddress.getText();
        if (url == null || url.isEmpty()) showAlert(Alert.AlertType.WARNING, "WARNING", "URL can't be empty");
        if (validateAddress(url)) {
            getInfoUrl(url);
        } else {
            showAlert(Alert.AlertType.ERROR, "ERROR", "Invalid URL");
        }
    }

    private void getInfoUrl(String url) {
        
    }

    private boolean validateAddress(String address) {
        if (url == null || url.isEmpty()) return false;
        if (url.startsWith("http://") || url.startsWith("https://")) return true;
        if (!url.contains("://") && url.contains(".")) return true;
        if (url.contains("://")) {
            int colonIndex = url.indexOf(":", url.indexOf("://") + 3);
            if (colonIndex > 0) return true;
            return false;
        }
        else return false;
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void btnAboutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.centerOnScreen();
        stage.resizableProperty().setValue(false);
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/scene/AboutScene.fxml")));
        stage.setScene(scene);
        stage.show();
    }
}
