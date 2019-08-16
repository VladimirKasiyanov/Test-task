package com.kasiyanov.parser;

import com.kasiyanov.dto.UserDto;
import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class ParameterCreatorImplUpdateProxy implements ParameterCreator {

    private final String CURRENT_USER_NAME = "Текущее имя пользователя - ";
    private final String CURRENT_USER_SURNAME = "Текущая фамилия пользователя - ";
    private final String CURRENT_USER_EMAIL = "Текущий email пользователя - ";
    private final String CURRENT_USER_ROLES = "Текущие роли пользователя - ";
    private final String CURRENT_USER_PHONES = "Текущие номера телефонов пользователя - ";

    private ParameterCreatorImpl parameterCreator;
    private UserDto currentUser;

    @Override
    public String createUserName(BufferedReader reader) throws IOException {
        System.out.println(CURRENT_USER_NAME + currentUser.getName());
        return parameterCreator.createUserName(reader);
    }

    @Override
    public String createUserSurname(BufferedReader reader) throws IOException {
        System.out.println(CURRENT_USER_SURNAME + currentUser.getSurname());
        return parameterCreator.createUserSurname(reader);
    }

    @Override
    public String createUserEmail(BufferedReader reader) throws IOException {
        System.out.println(CURRENT_USER_EMAIL + currentUser.getEmail());
        return parameterCreator.createUserEmail(reader);
    }

    @Override
    public List<String> createUserRoles(BufferedReader reader) throws IOException {
        System.out.println(CURRENT_USER_ROLES + currentUser.getRoles().toString());
        return parameterCreator.createUserRoles(reader);
    }

    @Override
    public List<String> createUserPhones(BufferedReader reader) throws IOException {
        System.out.println(CURRENT_USER_PHONES + currentUser.getPhones().toString());
        return parameterCreator.createUserPhones(reader);
    }
}
