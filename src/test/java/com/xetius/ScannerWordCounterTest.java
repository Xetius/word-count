package com.xetius;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class ScannerWordCounterTest {

    private ScannerWordCounter wordCounter;

    @Before
    public void setUp() {
        wordCounter = new ScannerWordCounter();
    }

    @Test
    public void testProcessStreamForNormalFile() {
        runTestOnStream("/examples/normal.txt", 9);
    }

    @Test
    public void testProcessStreamForFileWithNumbers() {
        runTestOnStream("/examples/numbers.txt", 15);
    }

    @Test
    public void testProcessStreamForFileFilledWithWhiteSpace() {
        runTestOnStream("/examples/whitespace.txt", 0);
    }

    @Test
    public void testProcessStreamForEmptyFile() {
        runTestOnStream("/examples/empty.txt", 0);
    }

    @Test(expected = NullPointerException.class)
    public void testWithNonExistentFile() {
        runTestOnStream("/thisFileDoesNotExist.txt", 0);
    }

    @Test(expected = NullPointerException.class)
    public void testWithNullFileName() {
        runTestOnStream(null, 0);
    }

    private void runTestOnStream(String fileName, int expectedCount) {
        int count = 0;
        Scanner scanner = getScanner(fileName);

        count = wordCounter.processStream(count, scanner);
        Assert.assertEquals(expectedCount, count);
    }

    private Scanner getScanner(String fileName) {
        try {
            URL url = this.getClass().getResource(fileName);
            File file = new File(url.getFile());
            return new Scanner(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
