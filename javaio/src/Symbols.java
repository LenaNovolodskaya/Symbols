import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Класс для подсчёта частоты появления каждого символа
 * @author Новолодская Елена
 */

public class Symbols {
    /**
     * Метод проходит по каждому символу строки и записывает данные в словарь.
     * Если символ уже есть, прибавляет 1 к значению.
     * Если символа ещё нет, записывает его со значением 1.
     * @param symbolsMap  словарь, в который записываются данные.
     * @param s строка, в которой необходимо посчитать частоту появления символов.
     * @return словарь с записью всех символов и их частотой появления.
     */
    public static Map countStr(Map<Character, Integer> symbolsMap, String s) {
        for (int i = 0; i < s.length(); i++) {
            char symbol = s.charAt(i);
            if (symbol != ' ') {
                symbolsMap.put(symbol,symbolsMap.getOrDefault(symbol,0)+1);
            }
        }
        return symbolsMap;
    }

    public static void main(String[] args) throws IOException {
        // чтение из файла input.txt
        File inputFile = new File("input.txt");
        Map<Character, Integer> symbolsMap = new HashMap<>();
        try(Scanner in = new Scanner(inputFile)) {
            while(in.hasNext()) {
                String str = in.nextLine();
                countStr(symbolsMap, str);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Ошибка открытия файла input.txt");
            return;
        }

        // сортировка по частоте появления символов
        List<Map.Entry<Character, Integer>> sortedSymbolsMap = new ArrayList<>(symbolsMap.entrySet());
        sortedSymbolsMap.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // запись в файл output.txt
        File outputFile = new File("output.txt");
        try(PrintWriter writer = new PrintWriter(outputFile)) {
            for (Map.Entry<Character,Integer> entry : sortedSymbolsMap) {
                writer.printf("'%c'=%d, ",entry.getKey(), entry.getValue());
            }
        }
        catch (IOException e) {
            System.out.println("Ошибка записи в файл output.txt");
            return;
        }
        System.out.println("Результат записан в файл output.txt");

    }
}