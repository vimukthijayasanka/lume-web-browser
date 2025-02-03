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
import java.util.Objects;

public class MainController {
    public AnchorPane root;
    public TextField txtAddress;
    public WebView wbDisplay;
    public Button btnAbout;

    private String protocol;
    private String path;

    public void initialize() {
        // initially URL set to http://google.com
        txtAddress.setText("http://google.com/");

        txtAddress.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) Platform.runLater(txtAddress::selectAll);
        });
    }

    public void txtAddressOnAction(ActionEvent actionEvent) {
        String url = txtAddress.getText();
        if (url == null || url.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "WARNING", "URL can't be empty");
            return;
        }
        if (validateAddress(url)) {
            getInfoUrl(url);
        } else {
            showAlert(Alert.AlertType.ERROR, "ERROR", "Invalid URL");
        }
    }

    private void getInfoUrl(String url) {
        if (url.contains("://")) {
            protocol = url.substring(0, url.indexOf("://"));
            url = url.substring(url.indexOf("://") + 3);
        }

        if (url.contains("/")) {
            path = url.substring(url.indexOf("/"));
            url = url.substring(0, url.indexOf("/"));
        }

        String host;
        String port;

        if (url.contains(":")) {
            host = url.substring(0, url.indexOf(":"));
            port = url.substring(url.indexOf(":") + 1);

        } else {
            host = url;
            port = protocol.equals("https") ? "443" : "80";
        }

        if (!host.startsWith("www.") && !host.contains("localhost")) {
            host = "www." + host;
        }

        System.out.printf("""
                Protocol: %s
                Host: %s
                Port: %s
                Path: %s
                %n""", protocol, host, port, path);
    }

    private boolean validateAddress(String address) {
        if (address == null || address.isEmpty()) return false;
        if (address.contains("http://")) return true;
        if (!address.contains("://") && address.contains(".")) return true;
        if (address.contains("://")) {
            int colonIndex = address.indexOf(":", address.indexOf("://") + 3);
            return colonIndex > 0;
        }
        return false;
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
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scene/AboutScene.fxml"))));
        stage.setScene(scene);
        stage.show();
    }
}
