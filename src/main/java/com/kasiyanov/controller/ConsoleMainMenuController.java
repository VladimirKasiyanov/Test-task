package com.kasiyanov.controller;

import com.kasiyanov.processor.Deleter;
import com.kasiyanov.processor.Reader;
import com.kasiyanov.processor.Saver;
import com.kasiyanov.processor.Updater;
import com.kasiyanov.dto.UserDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Scanner;

import static com.kasiyanov.controller.massage.MenuMassage.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConsoleMainMenuController {

    private static final ConsoleMainMenuController INSTANCE = new ConsoleMainMenuController();

    public void runConsoleReader(Path path) {
        try (Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            System.out.println(MENU_MASSAGE.getContent());
            while (scanner.hasNext()) {
                line = scanner.nextLine().trim();
                if (line.equals("1")) {
                    saveUser(reader, path);
                } else if (line.equals("2")) {
                    findUserByEmail(reader, path);
                } else if (line.equals("3")) {
                    updateUser(reader, path);
                } else if (line.equals("4")) {
                    deleteUser(reader, path);
                } else if (line.equalsIgnoreCase("Q")) {
                    System.out.println(WORK_FINISHED.getContent());
                    break;
                } else if (!(line.isEmpty())) {
                    System.out.println(ENTERED_INCORRECT_DATA.getContent());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveUser(BufferedReader reader, Path path) throws IOException {
        Saver.getInstance().saveUser(reader, path);
        System.out.println(USER_SAVED_SUCCESSFUL.getContent());
        System.out.println(MENU_MASSAGE.getContent());
    }

    private void findUserByEmail(BufferedReader reader, Path path) throws IOException {
        UserDto userByEmail = Reader.getInstance().findUserByEmail(reader, path);
        if (userByEmail != null) {
            System.out.println(userByEmail.toString());
            System.out.println(MENU_MASSAGE.getContent());
        } else {
            System.out.println(USER_NOT_FOUND_BY_EMAIL.getContent());
            System.out.println(MENU_MASSAGE.getContent());
        }
    }

    private void updateUser(BufferedReader reader, Path path) throws IOException {
        if (Updater.getInstance().updateUser(reader, path) != null) {
            System.out.println(USER_UPDATED_SUCCESSFUL.getContent());
            System.out.println(MENU_MASSAGE.getContent());
        } else {
            System.out.println(USER_NOT_FOUND_BY_EMAIL.getContent());
            System.out.println(MENU_MASSAGE.getContent());
        }
    }

    private void deleteUser(BufferedReader reader, Path path) throws IOException {
        if (Deleter.getInstance().deleteUserFromFile(reader, path) != null) {
            System.out.println(USER_DELETED_SUCCESSFUL.getContent());
            System.out.println(MENU_MASSAGE.getContent());
        } else {
            System.out.println(USER_NOT_FOUND_BY_EMAIL.getContent());
            System.out.println(MENU_MASSAGE.getContent());
        }
    }

    public static ConsoleMainMenuController getInstance() {
        return INSTANCE;
    }
}
