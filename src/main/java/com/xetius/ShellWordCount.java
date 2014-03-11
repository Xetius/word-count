package com.xetius;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Scanner;

public class ShellWordCount implements WordCounter{
    private static Logger logger = LoggerFactory.getLogger(ShellWordCount.class);

    public int count(String filename) {
        String command = "cat " + filename + " | wc -w";
        String[] commands = {"/bin/sh", "-c", command};

        try {
            Process p = Runtime.getRuntime().exec(commands);
            p.waitFor();

            Scanner scanner = new Scanner(p.getInputStream());
            return scanner.nextInt();
        } catch (IOException e) {
            logger.error("Unable to read response : {}", e);
        } catch (InterruptedException e) {
            logger.error("Process interrupted : {}", e);
        }

        return 0;
    }
}
