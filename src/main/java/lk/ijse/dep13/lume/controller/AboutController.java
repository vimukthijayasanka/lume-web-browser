package lk.ijse.dep13.lume.controller;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.net.URI;

public class AboutController {
    public void lblLicenseOnMouseClicked(MouseEvent mouseEvent) {
        new Thread(() -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/vimukthijayasanka/lume-web-browser/blob/main/license.txt"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    public void btnGitHubOnAction(ActionEvent actionEvent) {
        new Thread(() -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/vimukthijayasanka/lume-web-browser"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }
}
