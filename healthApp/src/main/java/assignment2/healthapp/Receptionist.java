package assignment2.healthapp;

public class Receptionist extends User {
    // constructors
    public Receptionist() {
        super(); // user class
    }

    public Receptionist(int userID, String password, int permission) {
        super(userID, password, permission); // user class
    }

    PatientRecord pRecord;
    public void setPatientRecord(PatientRecord pRecord) { this.pRecord = pRecord; };
    public PatientRecord getPatientRecord() { return pRecord; };

    // uml functions
    private void scheduleExam() {

    }
}