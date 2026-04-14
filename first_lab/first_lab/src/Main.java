import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Dictionary dict1 = new Dictionary("dict1.txt", 1);
        Dictionary dict2 = new Dictionary("dict2.txt", 2);

        while (true) {
            System.out.println("\nМЕНЮ");
            System.out.println("1 - Показать оба словаря");
            System.out.println("2 - Работать со словарём 1 (4 латинские буквы)");
            System.out.println("3 - Работать со словарём 2 (5 цифр)");
            System.out.println("0 - Выход");
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                break;
            }

            if (choice == 1) {
                System.out.println("\nСловарь 1:");
                dict1.showAll();

                System.out.println("\nСловарь 2:");
                dict2.showAll();
            } else if (choice == 2) {
                workWithDictionary(scanner, dict1);
            } else if (choice == 3) {
                workWithDictionary(scanner, dict2);
            } else {
                System.out.println("Неверный выбор.");
            }
        }

        scanner.close();
    }

    public static void workWithDictionary(Scanner scanner, Dictionary dict) {
        while (true) {
            System.out.println("\n1 - Показать содержимое");
            System.out.println("2 - Добавить запись");
            System.out.println("3 - Найти запись по ключу");
            System.out.println("4 - Удалить запись по ключу");
            System.out.println("0 - Назад");
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                break;
            }

            if (choice == 1) {
                dict.showAll();
            } else if (choice == 2) {
                System.out.print("Введите ключ: ");
                String key = scanner.nextLine();

                System.out.print("Введите перевод: ");
                String value = scanner.nextLine();

                dict.add(key, value);
            } else if (choice == 3) {
                System.out.print("Введите ключ: ");
                String key = scanner.nextLine();

                dict.find(key);
            } else if (choice == 4) {
                System.out.print("Введите ключ: ");
                String key = scanner.nextLine();

                dict.delete(key);
            } else {
                System.out.println("Неверный выбор.");
            }
        }
    }
}