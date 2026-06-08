package assignment2.healthapp;

public class Date {
    // constructors
    public Date() {
        this.day = 0;
        this.month = 0;
        this.year = 0;
    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // uml functions
    private int day;
    private int month;
    private int year;
}