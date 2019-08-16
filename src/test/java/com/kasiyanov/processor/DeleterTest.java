package com.kasiyanov.processor;

import com.kasiyanov.dto.UserDto;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleterTest {

    private final Path sourcePath = Paths.get("src", "test", "java", "com", "kasiyanov", "file", "sourceFile.txt");
    private final Path destinationPath = Paths.get("src", "test", "java", "com", "kasiyanov", "file", "resultFile.txt");
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        try {
            Files.copy(sourcePath, destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        try {
            Files.deleteIfExists(destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkDeleteUserFromFile() {
        String expectedOutputMassage = "Чтобы найти пользователя - Введите email пользователя\r\n" +
                "Чтобы найти пользователя - Введите email пользователя\r\n";

        UserDto actualUser = null;
        int startUsersCount = Reader.getInstance().getAllUsersFromFile(destinationPath).size();
        String InputValues = "test10@tut.by\n"+ "test10@tut.by\n";

        provideInput(InputValues);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Deleter.getInstance().deleteUserFromFile(reader, destinationPath);
            actualUser = Reader.getInstance().findUserByEmail(reader, destinationPath);
            Assert.assertEquals(expectedOutputMassage, getOutput());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int resultUsersCount = Reader.getInstance().getAllUsersFromFile(destinationPath).size();

        Assert.assertNull(actualUser);
        Assert.assertEquals((resultUsersCount + 1), startUsersCount);
    }
}