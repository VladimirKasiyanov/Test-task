package com.kasiyanov.consoleReader;

import com.kasiyanov.consoleValidation.PhoneListValidator;
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

public class PhonesLineConsoleReaderTest {

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
    public void checkPhonesLineConsoleReader() {
        String expectedOutputMassage = "Введите от одного до трёх номеров телефонов пользователя в виде 375хх ххххххх через запятую\r\n";
        String expectedTrimmedLineWithParameters = "37533 1112233, 37544 5556677";

        PhonesLineConsoleReader phonesLineConsoleReader = new PhonesLineConsoleReader(PhoneListValidator.getInstance());
        String actualTrimmedLineWithParameters = null;
        String line = "37533 1112233, 37544 5556677";

        provideInput(line);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            actualTrimmedLineWithParameters = phonesLineConsoleReader.getTrimmedLineWithParameters(reader);
            Assert.assertEquals(expectedOutputMassage, getOutput());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(expectedTrimmedLineWithParameters, actualTrimmedLineWithParameters);
    }

    @Test
    public void checkPhonesLineConsoleReaderWithWrongAttempt() {
        String expectedOutputMassage = "Введите от одного до трёх номеров телефонов пользователя в виде 375хх ххххххх через запятую\r\n" +
                "Введено более трёх номеров телефонов, " +
                "введите от одного до трёх номеров телефонов пользователя в виде 375хх ххххххх через запятую\r\n";
        String expectedTrimmedLineWithParameters = "37533 1112233, 37544 5556677";

        PhonesLineConsoleReader phonesLineConsoleReader = new PhonesLineConsoleReader(PhoneListValidator.getInstance());
        String actualTrimmedLineWithParameters = null;
        String line = "37533 1112233, 37544 5556677, 37533 1112233, 37544 5556677\n" + "37533 1112233, 37544 5556677";

        provideInput(line);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            actualTrimmedLineWithParameters = phonesLineConsoleReader.getTrimmedLineWithParameters(reader);
            Assert.assertEquals(expectedOutputMassage, getOutput());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(expectedTrimmedLineWithParameters, actualTrimmedLineWithParameters);
    }

}