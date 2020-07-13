package service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;

public class Mythread extends Thread {
    public String text;
    public String path;

    @Override
    public void run() {

        List<String> contents = Collections.singletonList(text);
        try {
            Files.write(Paths.get(path), contents,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setText(String text) {
        this.text = text;
    }

    public void setPath(String path) {
        this.path = path;
    }
}