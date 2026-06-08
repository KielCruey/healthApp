package assignment2.healthapp;

public class Globals {
    private static Globals instance;

    private int gUserID;
    private int gAppointmentID;

    private Globals() {
        this.gUserID = 4;
        this.gAppointmentID = 0;
    }

    public static Globals getInstance() {
        // checks if class instance exists already
        if (instance == null) instance = new Globals();
        return instance;
    }

    public int getNewUserID() {
        gUserID++;
        return gUserID;
    }

    public int getNewAppointmentID() {
        gAppointmentID++;
        return gAppointmentID;
    }
}