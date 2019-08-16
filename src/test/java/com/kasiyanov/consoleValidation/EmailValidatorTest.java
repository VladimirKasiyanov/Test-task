package com.kasiyanov.consoleValidation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class EmailValidatorTest {

    private final PrintStream systemOut = System.out;

    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private String getOutput() {
        return testOut.toString();
    }

    @After
    public void restoreSystemOutput() {
        System.setOut(systemOut);
    }

    @Test
    public void checkEmailValidationCorrectCase() {
        boolean expected = true;
        String line = "test@tut.by";
        boolean actual = EmailValidator.getInstance().isValid(line);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkEmailValidationLineInsteadOfEmail() {
        String expectedOutput = "Введён не корректный email - попробуйте ещё раз\r\n";
        String wrongEmail = "emailemailemail";
        EmailValidator.getInstance().isValid(wrongEmail);
        Assert.assertEquals(expectedOutput, getOutput());
    }

    @Test
    public void checkEmailValidationDomainWithoutDot() {
        String expectedOutput = "Введён не корректный email - попробуйте ещё раз\r\n";
        String wrongEmail = "email@email";
        EmailValidator.getInstance().isValid(wrongEmail);
        Assert.assertEquals(expectedOutput, getOutput());
    }

    @Test
    public void checkEmailValidationWithoutAt() {
        String expectedOutput = "Введён не корректный email - попробуйте ещё раз\r\n";
        String wrongEmail = "emailemail.com";
        EmailValidator.getInstance().isValid(wrongEmail);
        Assert.assertEquals(expectedOutput, getOutput());
    }
}