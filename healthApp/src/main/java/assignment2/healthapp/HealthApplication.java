package assignment2.healthapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class HealthApplication extends Application {
    public HealthApplication() {
        globals = Globals.getInstance();
        loginModel = new LoginModel();
    }

    public static Globals globals;
    public Globals grabGlobals() { return Globals.getInstance(); }

    // ui -- window/stage/scene
    private Stage stage; // app windows
    private BorderPane rootLayout ;
    private Scene scene;

    public Stage getStage() { return this.stage; }

    public BorderPane getRootLayout() { return rootLayout; }

    // model-view-controller
    private User currentLoginUser;
    private LoginController loginController;
    private AppController appController;

    private LoginModel loginModel;

    // getters/setters
    public void setCurrentLoginUser(User currentLoginUser) { this.currentLoginUser = currentLoginUser; }

    public User getCurrentLoginUser() { return this.currentLoginUser; }
    public LoginController getLoginController() { return this.loginController; }
    public LoginModel getLoginModel() { return this.loginModel; }

    // functions
    public void setStage(Stage stage) { this.stage = stage; }
    public void setScene(Scene scene) { this.scene = scene; }
    public void setLoginController(LoginController loginController) { this.loginController = loginController; }
    public void setLoginModel(LoginModel loginModel) { this.loginModel = loginModel; }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.stage.setTitle("Heart Health Imaging and Recording System");

        showRootOverview();
        showLoginOverview();
    }

    // ========== database functions ==========
    // newPatientID -- from ui
    public void addNewPatientToDatabase(int newPatientID, String firstName, String lastName,
                                        String email, String phoneNumber,
                                        String healthHistory, String insuranceID) {
        getLoginModel().getUserLoginList().add(new Patient(newPatientID, "password",
                                                EnumRole.Role.patient.ordinal(),
                                                firstName, lastName,
                                                email, phoneNumber,
                                                healthHistory, insuranceID));
    }

    public void addNewAppointmentToDatabase(int newAppointmentID, int patientID, int day, int month, int year) {
        getLoginModel().getAppointmentList().add(new Appointment(newAppointmentID, patientID, day, month, year));
    }

    public void addNewCTScanToDatabase(int patientID, int LMScore, int LADScore,
                                        int LCXScore, int RCAScore, int PDAScore, int totalScore)
    {
        getLoginModel().getCTTestList().add(new CTTest(patientID, LMScore, LADScore, LCXScore, RCAScore, PDAScore, totalScore));
    }

    // ========== helper functions ==========
    public int createNewUserID() {
        return this.grabGlobals().getNewUserID();
    }

    public int createNewAppointmentID() {
        return this.grabGlobals().getNewAppointmentID();
    }

    private void removeTabFromUI(String fxIDName) {
        String idString = fxIDName;
        appController.appTabPane.getTabs().removeIf(tab -> idString.equals(tab.getId()));
    }

    private void checkUserType() {
        if(this.currentLoginUser.getRole() == EnumRole.Role.patient.ordinal()) {
            removeTabFromUI("scheduleTab");
            removeTabFromUI("patientInformationTab");
            removeTabFromUI("ctScanTab");
            removeTabFromUI("accessResultsTab");
        }
        else if(this.currentLoginUser.getRole() == EnumRole.Role.doctor.ordinal()) {
            removeTabFromUI("scheduleTab");
            removeTabFromUI("patientInformationTab");
            removeTabFromUI("ctScanTab");
        }
        else if(this.currentLoginUser.getRole() == EnumRole.Role.receptionist.ordinal()) {
            removeTabFromUI("ctScanTab");
            removeTabFromUI("viewResultsTab");
            removeTabFromUI("accessResultsTab");
        }
        else if(this.currentLoginUser.getRole() == EnumRole.Role.technician.ordinal()) {
            removeTabFromUI("scheduleTab");
            removeTabFromUI("patientInformationTab");
            removeTabFromUI("viewResultsTab");
            removeTabFromUI("accessResultsTab");
        }
    }

    // ========== ui overview renderings ==========
    public void showRootOverview() {
        try {
            // getting fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HealthApplication.class.getResource("root-view.fxml"));
            rootLayout = (BorderPane) loader.load();

            // painting outline
            scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showLoginOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HealthApplication.class.getResource("login-view.fxml"));
            AnchorPane loginOverview = (AnchorPane) loader.load();

            // establishing a controller for that ui
            loginController = loader.getController();
            loginController.setHealthApp(this);

            rootLayout.setCenter(loginOverview);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showAppOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HealthApplication.class.getResource("app-view.fxml"));
            AnchorPane appOverview = (AnchorPane) loader.load();

            // establishing a controller for that ui
            appController = loader.getController();
            appController.setHealthApp(this);

            // checking what login user it is -- changes ui
            checkUserType();

            rootLayout.setCenter(appOverview);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
