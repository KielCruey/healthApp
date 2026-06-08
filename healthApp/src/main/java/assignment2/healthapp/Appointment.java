package assignment2.healthapp;

public class Appointment {
    Appointment(int appointmentID, int patientID) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.appointmentDate = new Date();
    }
    Appointment(int appointmentID, int patientID, int day, int month, int year) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.appointmentDate = new Date(day, month, year);
    }

    private int appointmentID;
    private int patientID;
    private Date appointmentDate;
}