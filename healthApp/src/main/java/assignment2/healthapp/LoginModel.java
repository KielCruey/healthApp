package assignment2.healthapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoginModel {
    public LoginModel() {
        this.userLoginList = FXCollections.observableArrayList();
        this.appointmentList = FXCollections.observableArrayList();
        this.ctTestList = FXCollections.observableArrayList();

        // system default users
        userLoginList.add(new Patient(1, "1", EnumRole.Role.patient.ordinal()));
        userLoginList.add(new Doctor(2,"2", EnumRole.Role.doctor.ordinal()));
        userLoginList.add(new Receptionist(3, "3", EnumRole.Role.receptionist.ordinal()));
        userLoginList.add(new Technician(4, "4", EnumRole.Role.technician.ordinal()));

        ctTestList.add(new CTTest(1, 2, 3, 4, 5, 6, 100));
    }

    // variables
    private ObservableList<User> userLoginList;
    private ObservableList<Appointment> appointmentList;
    private ObservableList<CTTest> ctTestList;

    // getters/setters
    public ObservableList<User> getUserLoginList() { return this.userLoginList; }
    public ObservableList<Appointment> getAppointmentList() { return this.appointmentList; }
    public ObservableList<CTTest> getCTTestList() { return this.ctTestList; }
}