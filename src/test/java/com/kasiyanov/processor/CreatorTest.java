package com.kasiyanov.processor;

import com.kasiyanov.dto.UserDto;
import com.kasiyanov.parser.ParameterCreator;
import com.kasiyanov.parser.ParameterCreatorImpl;
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
import java.util.Arrays;

public class CreatorTest {

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
    public void checkUserCreation() {
        String expectedOutputMassage = "Введите имя пользователя\r\n" +
                "Введите фамилию пользователя\r\n" +
                "Введите email пользователя\r\n" +
                "Введите от одной до трёх ролей пользователя через запятую\r\n" +
                "Введите от одного до трёх номеров телефонов пользователя в виде 375хх ххххххх через запятую\r\n";
        UserDto expectedUser = UserDto.builder()
                .name("Test")
                .surname("Test")
                .email("test@tut.by")
                .roles(Arrays.asList("buyer", "manager"))
                .phones(Arrays.asList("37533 1112233", "37544 5556677"))
                .build();

        ParameterCreator parameterCreator = ParameterCreatorImpl.getInstance();
        UserDto actualUser = null;
        String InputValues = "Test\n" + "Test\n" + "test@tut.by\n"+ "buyer, manager\n"+ "37533 1112233, 37544 5556677\n";

        provideInput(InputValues);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            actualUser = Creator.getInstance().createUserDto(reader, parameterCreator);
            Assert.assertEquals(expectedOutputMassage, getOutput());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(expectedUser, actualUser);
    }
}