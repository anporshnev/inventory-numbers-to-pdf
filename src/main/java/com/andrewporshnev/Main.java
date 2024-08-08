package com.andrewporshnev;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
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
    public static void main(String[] args) throws IOException, DocumentException {
        if (args.length < 2) {
            throw new RuntimeException("Не указаны пути");
        }

        List<String> dataLines = Files.readAllLines(Paths.get(args[0]));

        File file = new File(args[1]);
        if (!file.isDirectory()) {
            throw new RuntimeException("Укажите директорию для сохранения файла");
        }

        String outputPath = file.getAbsolutePath() + "/inventory-cards.pdf";
        Document document = new Document();
        document.setMargins(20, 20, 20, 20);
        PdfWriter.getInstance(document, new FileOutputStream(outputPath));
        document.open();

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        dataLines.forEach(l -> addCard(table, l));

        document.add(table);
        document.close();
    }

    private static void addCard(PdfPTable table, String code) {
        PdfPTable innerTable = new PdfPTable(1);
        innerTable.addCell("Cell1");
        innerTable.addCell("Cell2");
        table.addCell(innerTable);




//        PdfPCell cell = new PdfPCell(new Phrase(code));
//        cell.setPadding(10);
//        table.addCell(cell);
    }
}