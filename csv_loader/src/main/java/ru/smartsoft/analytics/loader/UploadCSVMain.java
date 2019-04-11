package ru.smartsoft.analytics.loader;

import ru.smartsoft.analytics.loader.db.DBService;
import ru.smartsoft.analytics.loader.db.DBServiceImpl;
import ru.smartsoft.analytics.loader.db.dataset.UserHistoryEntity;
import ru.smartsoft.analytics.loader.parse.CSVParserImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class UploadCSVMain {

    public static void main(String[] args) throws IOException {
        System.out.print("Filename to upload: ");
        String fileName = new Scanner(System.in).nextLine();

        if (fileName==null) {
            System.out.println("operation cancelled");
            return;
        }

        uploadCSV(fileName);
    }
    private static  void uploadCSV(String fileName) throws IOException {
        DBService dbService = new DBServiceImpl();
        List<UserHistoryEntity> recordsList = new CSVParserImpl().parseFile(fileName);
        dbService.insertBatch(recordsList);
        dbService.shutdown();
    }
}
