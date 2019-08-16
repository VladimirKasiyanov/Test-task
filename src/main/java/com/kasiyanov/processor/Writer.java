package com.kasiyanov.processor;

import com.kasiyanov.dto.UserDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static java.nio.file.StandardOpenOption.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Writer {

    private static final Writer INSTANCE = new Writer();

    public void writeUserToFile(UserDto newUser, Path path) {
        try {
            Files.write(path, (newUser.toString() + System.lineSeparator()).getBytes(), CREATE, APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeAllUsersToFile(Map<String, UserDto> allUsersFromFile, Path path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
            allUsersFromFile.values().forEach(it -> {
                try {
                    writer.append(it.toString()).append(System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Writer getInstance() {
        return INSTANCE;
    }
}
