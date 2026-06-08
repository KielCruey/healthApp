package assignment2.healthapp;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class AppController {
    public AppController() {

    }

    private HealthApplication healthApp;
    public void setHealthApp(HealthApplication healthApp) { this.healthApp = healthApp; }

    // =========== helper function(s) ===========
    private boolean checkValidUser(TextField textField) {
        for(int i = 0; i < healthApp.getLoginModel().getUserLoginList().size(); i++) {
            User tempUser = healthApp.getLoginModel().getUserLoginList().get(i);
            int userID = tempUser.getUserID();

            // user is found and can login
            if((userID == Integer.parseInt(textField.getText()))) {
                healthApp.setCurrentLoginUser(tempUser);
                return true;
            }
        }

        return false;
    }

    // main pane
    @FXML public TabPane appTabPane;

    // FXML
    @FXML public Tab scheduleTab;
    @FXML public Tab patientInformationTab;
    @FXML public Tab ctScanTab;
    @FXML public Tab viewResultsTab;
    @FXML public Tab logoutTab;

    // scheduleTab
    @FXML public TextField generateAppointmentIDTextbox;
    String newAppointmentID;
    @FXML public TextField patientIDTextbox;
    @FXML public TextField appointmentMonthTextbox;
    @FXML public TextField appointmentDayTextbox;
    @FXML public TextField appointmentYearTextbox;

    @FXML public Button generateAppointmentIDButton;
    @FXML public Button scheduleAppointmentButton;

    @FXML public TextArea confirmScheduleTextArea;

    private void createUser() {
        // add patient to database
        healthApp.addNewAppointmentToDatabase(Integer.parseInt(newAppointmentID),
                Integer.parseInt(patientIDTextbox.getText()),
                Integer.parseInt(appointmentDayTextbox.getText()),
                Integer.parseInt(appointmentMonthTextbox.getText()),
                Integer.parseInt(appointmentYearTextbox.getText()));
    }

    @FXML void onGenerateAppointmentIDClick() {
        confirmScheduleTextArea.clear();

        // disable ui
        generateAppointmentIDButton.setDisable(true);
        generateAppointmentIDTextbox.setDisable(true);

        newAppointmentID = String.valueOf(healthApp.createNewAppointmentID());
        generateAppointmentIDTextbox.setText(newAppointmentID);

        System.out.println("GenerateAppointmentIDClick");
    }

    @FXML void onScheduleAppointmentButtonClick() {
        // checks if valid patient ID
        if(!checkValidUser(patientIDTextbox)) {
            confirmScheduleTextArea.setText("Denied Scheduled Appointment");
            return;
        }
        else {
            createUser();
            clearScheduleTab();
            confirmScheduleTextArea.setText("Scheduled Appointment");
        }

        System.out.println("ScheduleAppointmentButton");
    }

    private void clearScheduleTab() {
        generateAppointmentIDTextbox.setDisable(false);
        scheduleAppointmentButton.setDisable(false);
        generateAppointmentIDTextbox.setDisable(false);

        generateAppointmentIDTextbox.clear();
        patientIDTextbox.clear();
        appointmentMonthTextbox.clear();
        appointmentDayTextbox.clear();
        appointmentYearTextbox.clear();
    }

    // patientInformationTab
    @FXML public TextField newPatientIDTextbox;
    String newPatientID;
    @FXML public TextField firstNameTextbox;
    @FXML public TextField lastNameTextbox;
    @FXML public TextField emailTextbox;
    @FXML public TextField phoneNumberTextbox;
    @FXML public TextField healthHistoryTextbox;
    @FXML public TextField insuranceIDTextbox;

    @FXML public Button generateNewPatientIDButton;
    @FXML public Button generatePatientFileButton;

    private void clearPatientInformationTab() {
        newPatientIDTextbox.clear();
        firstNameTextbox.clear();
        lastNameTextbox.clear();
        emailTextbox.clear();
        phoneNumberTextbox.clear();
        healthHistoryTextbox.clear();
        insuranceIDTextbox.clear();

        newPatientIDTextbox.setDisable(false);
        generateNewPatientIDButton.setDisable(false);
        generatePatientFileButton.setDisable(false);
    }

    @FXML void onGenerateNewPatientIDButtonClick() {
        // disable ui
        generateNewPatientIDButton.setDisable(true);
        newPatientIDTextbox.setDisable(true);

        newPatientID = String.valueOf(healthApp.createNewUserID());
        newPatientIDTextbox.setText(newPatientID);

        System.out.println("GenerateNewPatientIDButton");
    }

    @FXML void onGeneratePatientFileButtonClick() {
        // disable button
        generatePatientFileButton.setDisable(true);

        // patient to database
        healthApp.addNewPatientToDatabase(Integer.parseInt(newPatientID), firstNameTextbox.getText(), lastNameTextbox.getText(),
                emailTextbox.getText(), phoneNumberTextbox.getText(),
                healthHistoryTextbox.getText(), insuranceIDTextbox.getText());

        // create .txt file
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Save Patient File");
        String fileName = newPatientIDTextbox.getText() + "_PatientInfo";
        fileChooser.setInitialFileName(fileName);
        fileChooser.setInitialDirectory(new File("C:/cse-460"));

        File txtFile = fileChooser.showSaveDialog(healthApp.getStage());

        String fileContent = newPatientIDTextbox.getText() + " " +
                            firstNameTextbox.getText() + " " +
                            lastNameTextbox.getText() + " " +
                            emailTextbox.getText() + " " +
                            phoneNumberTextbox.getText() + " " +
                            healthHistoryTextbox.getText() + " " +
                            insuranceIDTextbox.getText();

        if (txtFile != null) {
            try {
                // Write text area content to the chosen file
                Files.writeString(txtFile.toPath(), fileContent);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // clear tab data
        clearPatientInformationTab();

        System.out.println("GeneratePatientFileButton");
    }

    // ctScanTab
    @FXML public TextField patientIDCTScanTabTextBox;
    @FXML public TextField lmScoreTextbox;
    @FXML public TextField ladScoreTextbox;
    @FXML public TextField lcxScoreTextbox;
    @FXML public TextField rcaScoreTextbox;
    @FXML public TextField pdaScoreTextbox;
    @FXML public TextField totalScoreTextbox;

    @FXML public Button checkPatientIDButton;
    @FXML public Button generateReportButton;

    @FXML public TextArea confirmResultsCTScanTextArea;

    void clearCTScanTab() {
        patientIDCTScanTabTextBox.clear();
        lmScoreTextbox.clear();
        ladScoreTextbox.clear();
        lcxScoreTextbox.clear();
        rcaScoreTextbox.clear();
        pdaScoreTextbox.clear();
        totalScoreTextbox.clear();

        checkPatientIDButton.setDisable(false);
        generateReportButton.setDisable(false);
    }

    @FXML void onCheckPatientIDButtonClick() {
        // checks patientID data
        if(!checkValidUser(patientIDCTScanTabTextBox)) {
            confirmResultsCTScanTextArea.setText("Not a Valid User");
        }
        else {
            checkPatientIDButton.setDisable(true);
            patientIDCTScanTabTextBox.setDisable(true);
            confirmResultsCTScanTextArea.setText("Valid User");
        }

        System.out.println("CheckPatientIDButton");
    }

    @FXML void onGenerateReportButtonClick() {
        generateReportButton.setDisable(true);

        // patient to database
        healthApp.addNewCTScanToDatabase(
            Integer.parseInt(patientIDCTScanTabTextBox.getText()),
            Integer.parseInt(lmScoreTextbox.getText()),
            Integer.parseInt(ladScoreTextbox.getText()),
            Integer.parseInt(lcxScoreTextbox.getText()),
            Integer.parseInt(rcaScoreTextbox.getText()),
            Integer.parseInt(pdaScoreTextbox.getText()),
            Integer.parseInt(totalScoreTextbox.getText())
        );

        // create .txt file
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Save Patient File");
        String fileName = patientIDCTScanTabTextBox.getText() + "_CTResults";
        fileChooser.setInitialFileName(fileName);
        fileChooser.setInitialDirectory(new File("C:/cse-460"));

        File txtFile = fileChooser.showSaveDialog(healthApp.getStage());

        String fileContent = patientIDCTScanTabTextBox.getText() + " " +
                                lmScoreTextbox.getText() + " " +
                                ladScoreTextbox.getText() + " " +
                                lcxScoreTextbox.getText() + " " +
                                rcaScoreTextbox.getText() + " " +
                                pdaScoreTextbox.getText() + " " +
                                totalScoreTextbox.getText();

        if (txtFile != null) {
            try {
                // Write text area content to the chosen file
                Files.writeString(txtFile.toPath(), fileContent);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // clear tab data
        clearCTScanTab();

        System.out.println("GenerateReportButton");
    }

    // viewResultsTab
    @FXML public TextField patientViewResultsTextbox;

    @FXML public TextField lmScoreTextbox_ViewResults;
    @FXML public TextField ladScoreTextbox_ViewResults;
    @FXML public TextField lcxScoreTextbox_ViewResults;
    @FXML public TextField rcaScoreTextbox_ViewResults;
    @FXML public TextField pdaScoreTextbox_ViewResults;
    @FXML public TextField totalScoreTextbox_ViewResults;

    @FXML public Button fetchPatientDataButton;
    @FXML public TextArea uiResultsCTScanTextArea;

    void fillViewResults() {
        for(int i = 0; i < healthApp.getLoginModel().getCTTestList().size(); i++) {
            CTTest tempCTTest = healthApp.getLoginModel().getCTTestList().get(i);
            int patientID = tempCTTest.getPatientID();

            // user is found and can login
            if((patientID == Integer.parseInt(patientViewResultsTextbox.getText()))) {
                lmScoreTextbox_ViewResults.setText(Integer.toString(tempCTTest.getLMScore()));
                ladScoreTextbox_ViewResults.setText(Integer.toString(tempCTTest.getLADScore()));
                lcxScoreTextbox_ViewResults.setText(Integer.toString(tempCTTest.getLCXScore()));
                rcaScoreTextbox_ViewResults.setText(Integer.toString(tempCTTest.getRCAScore()));
                pdaScoreTextbox_ViewResults.setText(Integer.toString(tempCTTest.getPDAScore()));
                totalScoreTextbox_ViewResults.setText(Integer.toString(tempCTTest.getTotalCACScore()));
            }
        }
    }

    @FXML void onFetchPatientDataButtonClick() {
        if(!checkValidUser(patientViewResultsTextbox)) {
            uiResultsCTScanTextArea.setText("Not a Valid User");
        }
        else {
            uiResultsCTScanTextArea.setText("CT Scan Results Populated");
            fillViewResults();
        }

        System.out.println("FetchPatientDataButton");
    }

    // logout
    @FXML public Button logoutButton;

    @FXML void onLogoutButtonClick() {
        logoutButtonClickListener();
    }

    void logoutButtonClickListener() {
        healthApp.showLoginOverview();
        System.out.println("LogoutButton");
    }

    // accessResultsTab
    @FXML public TextField patientIDAccessResultsTextbox;
    @FXML public TextField totalAgatstonScoreTextbox;
    @FXML public TextField accessResultsTextbox;

    @FXML public Button fetchPatientDataAccessResultsButton;
    @FXML public Button emailPatientButton;

    @FXML public TextArea accessResultsTextArea;

    @FXML void onFetchPatientDataAccessResultsButtonClick() {
        if(!checkValidUser(patientIDAccessResultsTextbox)) {
            accessResultsTextArea.setText("Not a Valid User");
            return;
        }
        else {
            accessResultsTextArea.setText("Valid User");
        }

        for(int i = 0; i < healthApp.getLoginModel().getCTTestList().size(); i++) {
            CTTest tempCTTest = healthApp.getLoginModel().getCTTestList().get(i);
            int patientID = tempCTTest.getPatientID();

            // user is found and can login
            if((patientID == Integer.parseInt(patientIDAccessResultsTextbox.getText()))) {
                int totalScore = tempCTTest.getLMScore() + tempCTTest.getLMScore() +
                        tempCTTest.getLCXScore() + tempCTTest.getRCAScore() + tempCTTest.getPDAScore();
                int averageScore = totalScore / 5;

                // populate "Total Agatson CAC Score"
                totalAgatstonScoreTextbox.setText(String.valueOf(averageScore));

                // populate "Risk Results"
                if(averageScore > 100) accessResultsTextbox.setText("High Risk");
                else if(averageScore > 10) accessResultsTextbox.setText("Low Risk");
                else accessResultsTextbox.setText("No Risk");
            }
        }

        // ui disable
        patientIDAccessResultsTextbox.setDisable(true);

        System.out.println("FetchPatientDataAccessResultsButton");
    }

    @FXML void onEmailPatientButtonClick() {
        accessResultsTextArea.setText("Emailed Patient");
        System.out.println("emailPatientButton");
    }
}
