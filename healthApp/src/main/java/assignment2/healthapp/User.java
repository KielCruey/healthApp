package assignment2.healthapp;

public class User {
    public User() {
        this.userID = -1;
        this.password = "";
        this.role = -1;
    }

    public User(int userID, String password, int role) {
        this.userID = userID;
        this.password = password;
        this.role = role;
    }

    private int userID;
    private String password;
    private int role;

    public int getUserID() { return userID; }
    public String getPassword() { return password; }
    public int getRole() { return role; }
}