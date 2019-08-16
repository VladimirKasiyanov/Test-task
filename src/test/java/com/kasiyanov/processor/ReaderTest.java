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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

public class ReaderTest {

    private final Path path = Paths.get("src", "test", "java", "com", "kasiyanov", "file", "sourceFile.txt");
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
    public void checkGetAllUsersFromFile() {
        Map<String, UserDto> allUsersFromFile = Reader.getInstance().getAllUsersFromFile(path);
        Assert.assertEquals(18, allUsersFromFile.size());
    }

    @Test
    public void checkFindUserByEmail() {
        String expectedOutputMassage = "Чтобы найти пользователя - Введите email пользователя\r\n";
        UserDto expectedUser = UserDto.builder()
                .name("Test10")
                .surname("Test10")
                .email("test10@tut.by")
                .roles(Arrays.asList("manager"))
                .phones(Arrays.asList("37529 2548723", "37544 6547893"))
                .build();

        UserDto actualUser = null;
        String InputValues = "test10@tut.by";

        provideInput(InputValues);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            actualUser = Reader.getInstance().findUserByEmail(reader, path);
            Assert.assertEquals(expectedOutputMassage, getOutput());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(expectedUser, actualUser);
    }
}