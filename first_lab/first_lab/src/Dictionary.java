import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Dictionary {
    private String fileName;
    private int type; // 1 - 4 латинские буквы, 2 - 5 цифр
    private Map<String, String> data = new LinkedHashMap<>();

    public Dictionary(String fileName, int type) {
        this.fileName = fileName;
        this.type = type;
        loadFromFile();
    }

    public void loadFromFile() {
        data.clear();

        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Ошибка создания файла: " + fileName);
            }
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    data.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + fileName);
        }
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                bw.write(entry.getKey() + "=" + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + fileName);
        }
    }

    public void showAll() {
        if (data.isEmpty()) {
            System.out.println("Словарь пуст.");
            return;
        }

        for (Map.Entry<String, String> entry : data.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public void add(String key, String value) {
        if (!isValidKey(key)) {
            if (type == 1) {
                System.out.println("Ошибка: ключ должен содержать ровно 4 латинские буквы.");
            } else {
                System.out.println("Ошибка: ключ должен содержать ровно 5 цифр.");
            }
            return;
        }

        data.put(key, value);
        saveToFile();
        System.out.println("Запись добавлена.");
    }

    public void find(String key) {
        if (data.containsKey(key)) {
            System.out.println("Найдено: " + key + " -> " + data.get(key));
        } else {
            System.out.println("Запись не найдена.");
        }
    }

    public void delete(String key) {
        if (data.containsKey(key)) {
            data.remove(key);
            saveToFile();
            System.out.println("Запись удалена.");
        } else {
            System.out.println("Запись не найдена.");
        }
    }

    private boolean isValidKey(String key) {
        if (type == 1) {
            return key.matches("[a-zA-Z]{4}");
        } else {
            return key.matches("\\d{5}");
        }
    }
}