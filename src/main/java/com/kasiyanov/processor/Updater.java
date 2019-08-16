package com.kasiyanov.processor;

import com.kasiyanov.dto.UserDto;
import com.kasiyanov.parser.ParameterCreator;
import com.kasiyanov.parser.ParameterCreatorImpl;
import com.kasiyanov.parser.ParameterCreatorImplUpdateProxy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Updater {

    private static final Updater INSTANCE = new Updater();

    public UserDto updateUser(BufferedReader reader, Path path) throws IOException {
        Map<String, UserDto> allUsersFromFile = Reader.getInstance().getAllUsersFromFile(path);
        UserDto currentUser = Reader.getInstance().findUserByEmail(reader, path);
        if (currentUser != null) {
            ParameterCreator parameterCreator = new ParameterCreatorImplUpdateProxy(ParameterCreatorImpl.getInstance(), currentUser);
            UserDto updatedUser = Creator.getInstance().createUserDto(reader, parameterCreator);
            allUsersFromFile.remove(currentUser.getEmail());
            allUsersFromFile.put(updatedUser.getEmail(), updatedUser);
            Writer.getInstance().writeAllUsersToFile(allUsersFromFile, path);
        }
        return currentUser;
    }

    public static Updater getInstance() {
        return INSTANCE;
    }
}
