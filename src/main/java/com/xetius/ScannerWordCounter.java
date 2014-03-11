package com.xetius;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ScannerWordCounter implements WordCounter{
    private static Logger logger = LoggerFactory.getLogger(ScannerWordCounter.class);

    public int count(String fileName) {
        int count = 0;
        try {
            count = processFile(fileName, count);
        } catch (FileNotFoundException e) {
            logger.error("Unable to count file : {}, {}", fileName, e);
        }

        return count;
    }

    int processFile(String fileName, int count) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(new FileInputStream(file));

        count = processStream(count, scanner);
        return count;
    }

    int processStream(int count, Scanner scanner) {
        while(scanner.hasNext()) {
            scanner.next();
            count ++;
        }
        return count;
    }
}
