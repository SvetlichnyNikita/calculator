package org.example;

import java.util.Scanner;

public class Calculator {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Введите первое число: ");
        double result = readNumber();

        while (true) {
            System.out.print("Введите операцию (+, -, *, /, C - сброс, S - выход): ");
            char operation = scanner.next().charAt(0);

            if (operation == 's' || operation == 'S') {
                System.out.println("Выход.");
                break;
            }

            if (operation == 'c' || operation == 'C') {
                result = 0;
                System.out.println("Результат сброшен: " + result);
                continue;
            }

            if (!isSupported(operation)) {
                System.out.println("Неизвестная операция: " + operation);
                continue;
            }

            System.out.print("Введите второе число: ");
            double operand = readNumber();

            if (operation == '/' && operand == 0) {
                System.out.println("Ошибка: деление на ноль. Результат не изменился: " + result);
                continue;
            }

            result = calculate(result, operation, operand);
            System.out.println("Результат: " + result);
        }

        scanner.close();
    }

    private static double calculate(double first, char operation, double second) {
        switch (operation) {
            case '+':
                return first + second;
            case '-':
                return first - second;
            case '*':
                return first * second;
            case '/':
                return first / second;
            default:
                throw new IllegalArgumentException("Операция не поддерживается: " + operation);
        }
    }

    private static boolean isSupported(char operation) {
        return operation == '+' || operation == '-' || operation == '*' || operation == '/';
    }

    private static double readNumber() {
        while (true) {
            String input = scanner.next().replace(',', '.');
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Это не число, повторите ввод: ");
            }
        }
    }
}
