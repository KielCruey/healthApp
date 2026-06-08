package assignment2.healthapp;

public class Doctor extends User {
    // constructors
    public Doctor() {
        super(); // user class
    }

    public Doctor(int userID, String password, int permission) {
        super(userID, password, permission); // user class
    }
}