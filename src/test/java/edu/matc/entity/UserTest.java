package edu.matc.entity;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

/**
 * Created by student on 1/26/17.
 */
public class UserTest {
    User user;

    @Before
    public void setUp() {
        user = new User("Test", "User", getLocalDate("08-01-2000"));
    }

    @Test
    public void calculateAge() throws Exception {
        int age = user.calculateAge();
        // Goal is to make sure the calculateAge() method works
        assertEquals("Age calculation failed", 17, age);

    }

    private LocalDate getLocalDate(String inputDate) {
        DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(inputDate, DATE_FORMAT);
    }

}