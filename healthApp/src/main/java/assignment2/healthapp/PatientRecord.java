package assignment2.healthapp;

public class PatientRecord {
    public PatientRecord() {
        this.patientID = -1;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.phoneNumber = "";
        this.healthHistory = "";
        this.insuranceID = "";
    }

    public PatientRecord(int patientID, String firstName, String lastName,
                         String email, String phoneNumber, String healthHistory, String insuranceID) {
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.healthHistory = healthHistory;
        this.insuranceID = insuranceID;
    }

    PatientRecord createPatientRecord(int patientID, String firstName, String lastName,
                        String email, String phoneNumber, String healthHistory, String insuranceID) {
        return new PatientRecord(patientID, firstName, lastName, email, phoneNumber, healthHistory, insuranceID);
    }

    private int patientID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String healthHistory;
    private String insuranceID;
}
