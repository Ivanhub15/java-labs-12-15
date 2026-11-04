package com.example.lab15;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ЛАБОРАТОРНАЯ РАБОТА №15: Работа с Git и GitHub ===\n");
        System.out.println("Проверка истории веток и merge-коммитов в репозитории Lab_15:\n");

        runGitCommand("git", "status", "-s");
        System.out.println("--- Список веток (git branch -a) ---");
        runGitCommand("git", "branch", "-a");
        System.out.println("\n--- Граф истории коммитов с merge-коммитами (git log --oneline --graph --all) ---");
        runGitCommand("git", "log", "--oneline", "--graph", "--all");
    }

    private static void runGitCommand(String... command) {
        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.directory(new File("."));
            pb.redirectErrorStream(true);
            Process process = pb.start();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
            process.waitFor();
        } catch (Exception e) {
            System.out.println("Не удалось вызвать git: " + e.getMessage());
        }
    }
}
