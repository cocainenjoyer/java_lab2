import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FrequencyDictionary {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя входного файла: ");
        String inputFileName = scanner.nextLine();

        File inputFile = new File(inputFileName);
        if (!inputFile.exists() || !inputFile.isFile()) {
            System.out.println("Файл не существует или это не файл.");
            return;
        }

        System.out.print("Введите имя выходного файла: ");
        String outputFileName = scanner.nextLine();

        Map<Character, Integer> frequencyMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            int character;
            while ((character = reader.read()) != -1) {
                char ch = (char) character;
                if (Character.isLetter(ch) && (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z')) {
                    frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
            System.out.println("Результаты записаны в файл " + outputFileName);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
