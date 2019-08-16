package com.kasiyanov.processor;

import com.kasiyanov.dto.UserDto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

public class WriterTest {

    private final Path sourcePath = Paths.get("src", "test", "java", "com", "kasiyanov", "file", "sourceFile.txt");
    private final Path destinationPath = Paths.get("src", "test", "java", "com", "kasiyanov", "file", "resultFile.txt");
    private final InputStream systemIn = System.in;

    private ByteArrayInputStream testIn;

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @After
    public void restoreSystemOutput() {
        System.setIn(systemIn);
        try {
            Files.deleteIfExists(destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkWriteUserToFile() {
        UserDto actualUser = null;
        UserDto newUser = UserDto.builder()
                .name("Test")
                .surname("Test")
                .email("test@tut.by")
                .roles(Arrays.asList("buyer", "manager"))
                .phones(Arrays.asList("37533 1112233", "37544 5556677"))
                .build();

        Writer.getInstance().writeUserToFile(newUser, destinationPath);

        String InputValues = "test@tut.by";
        provideInput(InputValues);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            actualUser = Reader.getInstance().findUserByEmail(reader, destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int resultUsersCount = Reader.getInstance().getAllUsersFromFile(destinationPath).size();

        Assert.assertTrue(resultUsersCount > 0);
        Assert.assertEquals(newUser, actualUser);
    }

    @Test
    public void checkWriteAllUserToFile() {
        Map<String, UserDto> allUsersFromFile = Reader.getInstance().getAllUsersFromFile(sourcePath);
        Writer.getInstance().writeAllUsersToFile(allUsersFromFile, destinationPath);

        int sourcePathUsersCount = Reader.getInstance().getAllUsersFromFile(sourcePath).size();
        int destinationPathUsersCount = Reader.getInstance().getAllUsersFromFile(destinationPath).size();
        Assert.assertEquals(sourcePathUsersCount, destinationPathUsersCount);
    }
}