package assignment2.healthapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Objects;

public class LoginController {
    public LoginController() { }

    // changing scene variables
    private Stage stage;
    private Scene scene;
    private Parent root;

    private HealthApplication healthApp;
    public void setHealthApp(HealthApplication healthApp) { this.healthApp = healthApp; }

    // ui -- login page components
    @FXML private Button submitButton;
    @FXML private TextField userIDTextbox;
    @FXML private TextField passwordTextbox;

    @FXML void onSubmitButtonClick() {
        SubmitButtonListener();
    }

    public void SubmitButtonListener() {
        int uiUserID = Integer.parseInt(userIDTextbox.getText());
        String uiPassword = passwordTextbox.getText();

        for(int i = 0; i < healthApp.getLoginModel().getUserLoginList().size(); i++) {
            User tempUser = healthApp.getLoginModel().getUserLoginList().get(i);

            int userID = tempUser.getUserID();
            String password = tempUser.getPassword();

            // user is found and can login
            if((userID == uiUserID) && Objects.equals(password, uiPassword)) {
                healthApp.setCurrentLoginUser(tempUser);
                healthApp.showAppOverview();

                break; // if found, exit loop
            }
        }
    }
}
