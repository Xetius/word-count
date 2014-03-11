package com.xetius;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        WordCounter wordCount = new ScannerWordCounter();
        int count = wordCount.count(args[0]);
        System.out.println("Word Count : " + count);
    }
}
