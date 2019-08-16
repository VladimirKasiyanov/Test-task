package com.kasiyanov;

import com.kasiyanov.controller.ConsoleMainMenuController;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Runner {

    public static void main(String[] args) {
        Path path = Paths.get("src", "main", "java", "com", "kasiyanov", "file", "text.txt");
        ConsoleMainMenuController.getInstance().runConsoleReader(path);
    }
}
