package dev.cheercode.dialog;

import java.util.List;
import java.util.Scanner;

public class StringDialog implements Dialog<String> {
    private final String title;
    private final String error;
    private final List<String> keys;
    private final Scanner scanner;

    public StringDialog(String title, String error, List<String> keys) {
        this.title = title;
        this.error = error;
        this.keys = keys;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String input() {
        System.out.println(title);

        while (true) {
            String input = scanner.nextLine();

            for (String key : keys) {
                if (input.equalsIgnoreCase(key)) {
                    return input;
                }
            }

            System.out.println(error);
        }
    }
}
