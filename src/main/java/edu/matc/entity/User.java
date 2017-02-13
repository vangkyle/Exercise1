package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * A class to represent a user.
 *
 * @author pwaite
 */
@Entity
@Table(name = "users")
public class User {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private String userid;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;


    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param firstName the first name
     * @param lastName the last name
     * @param dateOfBirth the date of birth
     */
    public User(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }


    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets userid.
     *
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * Sets userid.
     *
     * @param userid the userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }


    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int calculateAge() {
        LocalDate now = LocalDate.now();
        long age = ChronoUnit.YEARS.between(dateOfBirth, now);
        return (int)age;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userid='" + userid + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}