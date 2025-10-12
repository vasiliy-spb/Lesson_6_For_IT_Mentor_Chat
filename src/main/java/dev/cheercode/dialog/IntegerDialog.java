package dev.cheercode.dialog;

import java.util.List;
import java.util.Scanner;

public class IntegerDialog implements Dialog<Integer> {

    private String title;
    private String error;
    private List<Integer> numbers;
    private Scanner scanner;

    public IntegerDialog(String title, String error, List<Integer> numbers) {
        this.title = title;
        this.error = error;
        this.numbers = numbers;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Integer input() {
        System.out.println(title);

        while (true) {
            String input = scanner.nextLine();

            try {
                int number = Integer.parseInt(input);
                if (numbers.contains(number)) {
                    return number;
                }
            } catch (NumberFormatException ignore) {
            }

            System.out.println(error);
        }
    }
}
