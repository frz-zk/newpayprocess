/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileManager {

//    final static String paymentPath = "src/paymentFile/payment.txt";
//    final static String transactionPath = "src/paymentFile/transaction.txt";
//    final static String inventoryPath = "src/paymentFile/Inventory.txt";

    private String path;

    public FileManager() {
    }

    public FileManager(String filePath) {

        path = filePath;

    }

//    public void create() {
//
//        try {
//            File myObj = new File(path);
//            if (myObj.createNewFile()) {
//                System.out.println("File created: " + myObj.getName());
//            } else {
//                System.out.println("File already exists.");
//            }
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//    }


    public void append(String text) {
        try {
            Files.write(Paths.get(path), text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public void overrideFile(String text) throws IOException {
        File f = new File(path);
        FileWriter fr = new FileWriter(f, false);
        fr.write(text);
        fr.close();
    }

    public String[] openfile() throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader textreader = new BufferedReader(fr);

        int numberOfLines = readLines();

        String[] textData = new String[numberOfLines];
        int i;
        for (i = 0; i < numberOfLines; i++) {
            textData[i] = String.valueOf(textreader.readLine());
        }
        textreader.close();

        return textData;
    }

    int readLines() throws IOException {
        FileReader fileToRead = new FileReader(path);
        BufferedReader bf = new BufferedReader(fileToRead);
        String aLine;
        int numberOfLines = 0;
        while ((aLine = bf.readLine()) != null) {

            numberOfLines++;

        }
        bf.close();
        return numberOfLines;
    }

}
