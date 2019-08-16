package com.kasiyanov.consoleReader;

import com.kasiyanov.consoleValidation.NameValidator;
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

public class NameConsoleReaderTest {

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
    public void checkNameConsoleReader() {
        String expectedOutputMassage = "Введите имя пользователя\r\n";
        String expectedTrimmedLineWithParameters = "Name";
        NameConsoleReader nameConsoleReader = new NameConsoleReader(NameValidator.getInstance());
        String actualTrimmedLineWithParameters = null;
        String line = "Name";
        provideInput(line);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            actualTrimmedLineWithParameters = nameConsoleReader.getTrimmedLineWithParameters(reader);
            Assert.assertEquals(expectedOutputMassage, getOutput());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(expectedTrimmedLineWithParameters, actualTrimmedLineWithParameters);
    }
}