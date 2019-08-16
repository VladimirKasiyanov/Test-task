package com.kasiyanov.processor;

import com.kasiyanov.dto.UserDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Deleter {

    private static final Deleter INSTANCE = new Deleter();

    public UserDto deleteUserFromFile(BufferedReader reader, Path path) throws IOException {
        Map<String, UserDto> allUsersFromFile = Reader.getInstance().getAllUsersFromFile(path);
        UserDto currentUser = Reader.getInstance().findUserByEmail(reader, path);
        if (currentUser != null) {
            allUsersFromFile.remove(currentUser.getEmail());
            Writer.getInstance().writeAllUsersToFile(allUsersFromFile, path);
        }
        return currentUser;
    }

    public static Deleter getInstance() {
        return INSTANCE;
    }
}
