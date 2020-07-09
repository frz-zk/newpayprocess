/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.util.Arrays;
import java.util.List;

public class FileManager {


    private String path;


    public FileManager(String filePath) {

        path = filePath;

    }

    public static void create(String path) {

        File file = new File(path);
        file.getParentFile().mkdirs();

    }

    public static void write(String text, String path) throws IOException {

        List<String> contents = Arrays.asList(text);
        Files.write(Paths.get(path), contents,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE);
    }


}
