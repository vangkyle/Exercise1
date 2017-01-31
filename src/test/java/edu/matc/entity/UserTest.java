package edu.matc.entity;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

/**
 * Created by student on 1/26/17.
 */
public class UserTest {
    User user;

    @Test
    public void calculateAge() throws Exception {
        LocalDate myDate = getLocalDate();

        user = new User("Test", "User", "6", myDate);

        int age = user.calculateAge();
        // Goal is to make sure the calculateAge() method works
        assertEquals("Age calculation failed", 17, age);

    }

    private LocalDate getLocalDate() {
        DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse("08-01-2000", DATE_FORMAT);
    }

}