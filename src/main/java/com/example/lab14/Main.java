package com.example.lab14;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== ЛАБОРАТОРНАЯ РАБОТА №14 (Вариант 3) ===");
        System.out.println("Если вы хотите ввести числа вручную с клавиатуры, запустите с аргументом --interactive.");
        System.out.println("По умолчанию используется демонстрационный поток ввода (имитация клавиатуры), чтобы все 5 заданий выполнились сразу.\n");

        boolean interactive = args.length > 0 && "--interactive".equalsIgnoreCase(args[0]);

        String simulatedInput =
                "10 20 30 -5 15 0 25 5 40 60\n" +
                "3.5 4.2 5.0 2.8 6.1 4.0 3.9 5.5 7.2 4.8\n";

        Scanner scanner = interactive
                ? new Scanner(System.in)
                : new Scanner(new ByteArrayInputStream(simulatedInput.getBytes(StandardCharsets.UTF_8)));
        scanner.useLocale(Locale.US);

        File dataDir = new File("data_lab14");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        task1(dataDir);
        System.out.println("\n--------------------------------------------\n");
        task2(dataDir, scanner, interactive);
        System.out.println("\n--------------------------------------------\n");
        task3(dataDir);
        System.out.println("\n--------------------------------------------\n");
        task4(dataDir, scanner, interactive);
        System.out.println("\n--------------------------------------------\n");
        task5(dataDir);

        scanner.close();
    }

    /**
     * 1. Создать файл с информацией о своей специальности.
     * Вывести данные об этом файле. Вывести информацию из файла.
     */
    private static void task1(File dir) {
        System.out.println("--- Задание 1: Информация о специальности ---");
        File specFile = new File(dir, "specialty.txt");

        String info = "Специальность: 5-04-0612-02 «Разработка и сопровождение программного обеспечения информационных систем»\n" +
                      "Дисциплина: Основы кроссплатформенного программирования\n" +
                      "Квалификация: Техник-программист";

        // Запись в файл
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(specFile), StandardCharsets.UTF_8))) {
            writer.write(info);
        } catch (IOException e) {
            System.err.println("Ошибка записи файла: " + e.getMessage());
            return;
        }

        // Вывод данных о файле (методы класса File)
        System.out.println("Данные о файле:");
        System.out.println("  Имя файла: " + specFile.getName());
        System.out.println("  Путь: " + specFile.getPath());
        System.out.println("  Абсолютный путь: " + specFile.getAbsolutePath());
        System.out.println("  Родительский каталог: " + specFile.getParent());
        System.out.println("  Размер файла: " + specFile.length() + " байт");
        System.out.println("  Последняя модификация: " + new Date(specFile.lastModified()));
        System.out.println("  Существует: " + specFile.exists());
        System.out.println("  Доступен для чтения: " + specFile.canRead());
        System.out.println("  Доступен для записи: " + specFile.canWrite());
        System.out.println("  Это директория: " + specFile.isDirectory());
        System.out.println("  Это обычный файл: " + specFile.isFile());

        // Вывод информации из файла
        System.out.println("\nСодержимое файла:");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(specFile), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("  " + line);
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    /**
     * 2. Ввести с клавиатуры в файл 'f' 10 целых чисел.
     * Вывести их на экран и найти сумму всех чисел.
     */
    private static void task2(File dir, Scanner scanner, boolean interactive) {
        System.out.println("--- Задание 2: 10 целых чисел и их сумма ---");
        File f = new File(dir, "f_task2.txt");

        if (interactive) {
            System.out.println("Введите 10 целых чисел через пробел или Enter:");
        } else {
            System.out.println("Ввод 10 целых чисел (автоматический поток): 10 20 30 -5 15 0 25 5 40 60");
        }

        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8))) {
            for (int i = 0; i < 10; i++) {
                int num = scanner.nextInt();
                writer.println(num);
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл f: " + e.getMessage());
            return;
        }

        int sum = 0;
        System.out.print("Считанные из файла 'f' числа: ");
        try (Scanner fileScanner = new Scanner(f, "UTF-8")) {
            while (fileScanner.hasNextInt()) {
                int num = fileScanner.nextInt();
                System.out.print(num + " ");
                sum += num;
            }
            System.out.println();
            System.out.println("Сумма всех чисел: " + sum);
        } catch (IOException e) {
            System.err.println("Ошибка чтения из файла f: " + e.getMessage());
        }
    }

    /**
     * 3. Создать текстовый файл 'f' со случайными целыми числами.
     * Записать в файл 'g' числа, большие 50, а в файл 'h' – не превышающие 50.
     */
    private static void task3(File dir) {
        System.out.println("--- Задание 3: Фильтрация случайных чисел (f -> g и h) ---");
        File f = new File(dir, "f_task3.txt");
        File g = new File(dir, "g_task3.txt");
        File h = new File(dir, "h_task3.txt");

        Random random = new Random(42);
        try (PrintWriter writerF = new PrintWriter(new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8))) {
            for (int i = 0; i < 15; i++) {
                int randomNum = random.nextInt(100) + 1; // от 1 до 100
                writerF.println(randomNum);
            }
        } catch (IOException e) {
            System.err.println("Ошибка создания файла f: " + e.getMessage());
            return;
        }

        try (Scanner readerF = new Scanner(f, "UTF-8");
             PrintWriter writerG = new PrintWriter(new OutputStreamWriter(new FileOutputStream(g), StandardCharsets.UTF_8));
             PrintWriter writerH = new PrintWriter(new OutputStreamWriter(new FileOutputStream(h), StandardCharsets.UTF_8))) {

            while (readerF.hasNextInt()) {
                int num = readerF.nextInt();
                if (num > 50) {
                    writerG.println(num);
                } else {
                    writerH.println(num);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при распределении чисел: " + e.getMessage());
            return;
        }

        System.out.println("Числа в исходном файле 'f': " + readNumbersInline(f));
        System.out.println("Числа в файле 'g' (> 50):   " + readNumbersInline(g));
        System.out.println("Числа в файле 'h' (<= 50):  " + readNumbersInline(h));
    }

    /**
     * 4. Ввести с клавиатуры в файл 10 вещественных значений скорости ветра.
     * Считать данные и вывести среднюю скорость.
     */
    private static void task4(File dir, Scanner scanner, boolean interactive) {
        System.out.println("--- Задание 4: Скорость ветра (10 вещественных чисел) ---");
        File windFile = new File(dir, "wind_speed.txt");

        if (interactive) {
            System.out.println("Введите 10 вещественных значений скорости ветра:");
        } else {
            System.out.println("Ввод 10 значений скорости ветра: 3.5 4.2 5.0 2.8 6.1 4.0 3.9 5.5 7.2 4.8");
        }

        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(windFile), StandardCharsets.UTF_8))) {
            for (int i = 0; i < 10; i++) {
                double speed = scanner.nextDouble();
                writer.println(speed);
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи файла скорости ветра: " + e.getMessage());
            return;
        }

        double sum = 0.0;
        int count = 0;
        System.out.print("Считанные значения скорости ветра: ");
        try (Scanner fileScanner = new Scanner(windFile, "UTF-8")) {
            fileScanner.useLocale(Locale.US);
            while (fileScanner.hasNextDouble()) {
                double val = fileScanner.nextDouble();
                System.out.print(val + " ");
                sum += val;
                count++;
            }
            System.out.println();
            double avg = count > 0 ? sum / count : 0.0;
            System.out.printf(Locale.US, "Средняя скорость ветра: %.2f м/с%n", avg);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла скорости ветра: " + e.getMessage());
        }
    }

    /**
     * 5. Создать текстовый файл 'f' с 10 целыми числами.
     * Вывести количество чисел, равных нулю.
     */
    private static void task5(File dir) {
        System.out.println("--- Задание 5: Количество чисел, равных нулю ---");
        File f = new File(dir, "f_task5.txt");
        int[] sampleNumbers = {12, 0, -7, 0, 45, 0, 8, 19, 0, -3};

        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8))) {
            for (int num : sampleNumbers) {
                writer.println(num);
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи файла f: " + e.getMessage());
            return;
        }

        int zeroCount = 0;
        try (Scanner fileScanner = new Scanner(f, "UTF-8")) {
            while (fileScanner.hasNextInt()) {
                if (fileScanner.nextInt() == 0) {
                    zeroCount++;
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла f: " + e.getMessage());
        }

        System.out.println("Числа в файле 'f': " + readNumbersInline(f));
        System.out.println("Количество чисел, равных нулю: " + zeroCount);
    }

    private static String readNumbersInline(File file) {
        StringBuilder sb = new StringBuilder();
        try (Scanner sc = new Scanner(file, "UTF-8")) {
            while (sc.hasNext()) {
                sb.append(sc.next()).append(" ");
            }
        } catch (IOException e) {
            return "[Ошибка чтения]";
        }
        return sb.toString().trim();
    }
}
