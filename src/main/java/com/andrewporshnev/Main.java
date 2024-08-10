package com.andrewporshnev;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    private static String FONT = "src/main/resources/ArialRegular.ttf";

    public static void main(String[] args) throws IOException, DocumentException {
        if (args.length < 2) {
            throw new RuntimeException("Не указаны пути");
        }

        File file = new File(args[1]);
        if (!file.isDirectory()) {
            throw new RuntimeException("Укажите директорию для сохранения файла");
        }
        String outputPath = file.getAbsolutePath() + "/inventory-cards.pdf";

        List<String> dataLines = Files.readAllLines(Paths.get(args[0]));
        exportToPdf(dataLines, outputPath);



    }

    private static void exportToPdf(List<String> data, String path) throws DocumentException, IOException {
        Document document = new Document();
        document.setMargins(20, 20, 20, 20);

        BaseFont baseFont = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(baseFont, 12, Font.NORMAL);
        PdfWriter.getInstance(document, new FileOutputStream(path));

        document.open();
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        data.forEach(l -> addCard(table, l, font));
        document.add(table);
        document.close();
    }

    private static void addCard(PdfPTable table, String code, Font font) {
        PdfPTable innerTable = new PdfPTable(1);
        Phrase phrase = new Phrase("Ячейка", font);
        innerTable.addCell(phrase);
        innerTable.addCell("Cell2");
        table.addCell(innerTable);
    }
}