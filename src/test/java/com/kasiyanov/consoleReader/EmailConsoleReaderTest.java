package com.kasiyanov.consoleReader;

import com.kasiyanov.consoleValidation.EmailValidator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class EmailConsoleReaderTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @After
    public void restoreSystemOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void checkEmailConsoleReader() {
        String expectedOutputMassage = "Введите email пользователя\r\n";
        String expectedTrimmedLineWithParameters = "test@tut.by";

        EmailConsoleReader emailConsoleReader = new EmailConsoleReader(EmailValidator.getInstance());
        String actualTrimmedLineWithParameters = null;
        String line = "test@tut.by";

        provideInput(line);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            actualTrimmedLineWithParameters = emailConsoleReader.getTrimmedLineWithParameters(reader);
            Assert.assertEquals(expectedOutputMassage, getOutput());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(expectedTrimmedLineWithParameters, actualTrimmedLineWithParameters);
    }

    @Test
    public void checkEmailConsoleReaderWithWrongAttempt() {
        String expectedOutputMassage = "Введите email пользователя\r\nВведён не корректный email - попробуйте ещё раз\r\n";
        String expectedTrimmedLineWithParameters = "test@tut.by";

        EmailConsoleReader emailConsoleReader = new EmailConsoleReader(EmailValidator.getInstance());
        String actualTrimmedLineWithParameters = null;
        String line = "test\n" + "test@tut.by";

        provideInput(line);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            actualTrimmedLineWithParameters = emailConsoleReader.getTrimmedLineWithParameters(reader);
            Assert.assertEquals(expectedOutputMassage, getOutput());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(expectedTrimmedLineWithParameters, actualTrimmedLineWithParameters);
    }
}