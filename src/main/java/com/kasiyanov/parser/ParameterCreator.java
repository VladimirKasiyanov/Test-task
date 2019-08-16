package com.kasiyanov.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface ParameterCreator {

    String createUserName(BufferedReader reader) throws IOException;

    String createUserSurname(BufferedReader reader) throws IOException;

    String createUserEmail(BufferedReader reader) throws IOException;

    List<String> createUserRoles(BufferedReader reader) throws IOException;

    List<String> createUserPhones(BufferedReader reader) throws IOException;
}
