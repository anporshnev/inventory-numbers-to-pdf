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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, DocumentException {
        if (args.length < 2) {
            throw new RuntimeException("Не указаны пути");
        }

        File file = new File(args[1]);
        if (!file.isDirectory()) {
            throw new RuntimeException("Не указана директория для сохранения файла");
        }
        String outputPath = file.getAbsolutePath() + "/inventory-cards.pdf";
        List<Device> data = getData(args[0]);

        if (data.isEmpty()) {
            System.out.println("Нет данных для экспорта");
            return;
        }

        System.out.println(data);
//        exportToPdf(dataLines, outputPath);



    }

    private static List<Device> getData(String path) throws IOException {
        List<Device> data = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        try (Workbook workbook = WorkbookFactory.create(new File(path))) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                String[] targetRowItems = new String[2];
                for (int i = 0; i < 2; i++) {
                    targetRowItems[i] = formatter.formatCellValue(row.getCell(i));
                }
                data.add(new Device(targetRowItems[0], targetRowItems[1]));
            }
        }
        return data;
    }

    private static void exportToPdf(List<String> data, String path) throws DocumentException, IOException {
        Document document = new Document();
        document.setMargins(20, 20, 20, 20);

        String FONT = "src/main/resources/ArialRegular.ttf";
        BaseFont baseFont = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(baseFont, 12, Font.NORMAL);
        PdfWriter.getInstance(document, new FileOutputStream(path));

        document.open();
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        data.forEach(l -> addCell(table, l, font));
        document.add(table);
        document.close();
    }

    private static void addCell(PdfPTable table, String code, Font font) {
        PdfPTable innerTable = new PdfPTable(1);
        Phrase phrase = new Phrase("Ячейка", font);
        innerTable.addCell(phrase);
        innerTable.addCell("Cell2");
        table.addCell(innerTable);
    }
}