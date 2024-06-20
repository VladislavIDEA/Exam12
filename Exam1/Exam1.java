import java.util.Scanner;

public class Exam1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение (например, \"Hello\" + \"World\", \"Hello\"  , 200 + 300): ");
        String expression = scanner.nextLine();

        try {
            String result = calculate(expression);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calculate(String expression) throws Exception {
        String[] parts = expression.split(" ");

        if (parts.length != 3) {
            throw new Exception("Некорректный формат выражения.");
        }

        String a = parts[0];
        String operation = parts[1];
        String b = parts[2];

        // Проверка на корректность ввода
        if (!a.matches("\"[a-zA-Z0-9]{1,10}\"")) {
            throw new Exception("Некорректный формат строки a.");
        }
        if (!b.matches("[\"a-zA-Z0-9]{1,10}\"")) {
            throw new Exception("Некорректный формат строки b.");
        }
        if (!operation.matches("[+\\-/]")) {
            throw new Exception("Некорректный формат операции.");
        }

        a = a.substring(1, a.length() - 1); // Удаление кавычек из строки a
        b = b.substring(1, b.length() - 1); // Удаление кавычек из строки b

        int multiplier = 1;// Инициализация
        int divider = 1;// Инициализация
        try {
            multiplier = Integer.parseInt(b);
            divider = Integer.parseInt(b);
            // Проверка диапазона чисел
            if (multiplier < 1 || multiplier > 10 || divider < 1 || divider > 10) {
                throw new Exception("Число должно быть в диапазоне от 1 до 10.");
            }
        } catch (NumberFormatException e) {
            // Если строка b не является числом, проверяем ее длину
            if (b.length() > 10) {
                throw new Exception("Строка b должна быть не длиннее 10 символов.");
            }
        }

        String result = "";
        switch (operation) {
            case "+":
                result = a + b; // Сложение строк
                break;
            case "-":
                if (a.contains(b)) {
                    result = a.replace(b, "");
                } else {
                    result = a;
                }
                break;
            case "":
                result = a.repeat(multiplier); // Умножение строки на число
                break;
            case "/":
                if (divider > 0 && divider <= a.length()) {
                    result = a.substring(0, divider);
                } else {
                    throw new Exception("Некорректный формат делителя.");
                }
                break;
            default:
                throw new Exception("Неподдерживаемая операция.");
        }

        if (result.length() > 40) {
            result = result.substring(0, 40) + "...";
        }

        return result;
    }
}



