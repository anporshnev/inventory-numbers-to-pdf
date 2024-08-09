package com.andrewporshnev;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    private static String FONT = "src/main/resources/ArialRegular.ttf";

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new RuntimeException("Не указаны пути");
        }

        List<String> dataLines = Files.readAllLines(Paths.get(args[0]));

        File file = new File(args[1]);
        if (!file.isDirectory()) {
            throw new RuntimeException("Укажите директорию для сохранения файла");
        }

        String outputPath = file.getAbsolutePath() + "/inventory-stickers";

    }
}