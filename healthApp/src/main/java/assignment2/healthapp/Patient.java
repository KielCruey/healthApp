package assignment2.healthapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Patient extends User {
    // constructors
    public Patient() {
        super(); // user class

        firstName = "";
        lastName = "";
        email = "";
        phoneNumber = "";
        healthHistory = "";
        insuranceID = "";
    }

    public Patient(int userID, String password, int permission)
    {
        super(userID, password, permission); // user class

        firstName = "";
        lastName = "";
        email = "";
        phoneNumber = "";
        healthHistory = "";
        insuranceID = "";
    }

    public Patient(int userID, String password, int permission,
                   String firstName, String lastName, String email,
                   String phoneNumber, String healthHistory, String insuranceID)
    {
        super(userID, password, permission); // user class

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.healthHistory = healthHistory;
        this.insuranceID = insuranceID;
    }

    // ======= member functions =======
    private HealthApplication healthApp;

    public void setHealthApp(HealthApplication healthApp) { this.healthApp = healthApp; }
    public HealthApplication getHealthApp() { return this.healthApp; }

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String healthHistory;
    private String insuranceID;


 /*
    private Patient createPatient(int userID, String password, int permission, String firstName, String lastName, String email,
                          String phoneNumber, String healthHistory, String insuranceID) {
        return new Patient(createUserID(), password, permission, firstName, lastName, email, phoneNumber, healthHistory, insuranceID);
    }
*/


    private void viewCTScanResults() {

    }

    private void login() {

    }
}