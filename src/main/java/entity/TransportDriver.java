package entity;

import java.util.Date;

public class TransportDriver {
    private long driverId;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private short drivingExperience;

    public TransportDriver() {
    }

    public TransportDriver(long driverId, String firstName, String lastName, Date birthDate, short drivingExperience) {
        this.driverId = driverId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.drivingExperience = drivingExperience;
    }

    public long getDriverId() {
        return driverId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public short getDrivingExperience() {
        return drivingExperience;
    }

    @Override
    public String toString() {
        return "\n---------------------------------------------------------------------------"
                + "\n" + driverId + ". " + firstName + " " + lastName + "; birthDate = " + birthDate
                + "; drivingExperience = " + drivingExperience + " years";
    }

    public String toOutputInFile() {
        return driverId +
               ", " + firstName +
               ", " + lastName +
               ", " + birthDate +
               ", " + drivingExperience + ",\n";
    }
}
