package service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;

public class FileManager {
    public static void write(String text, String path) throws IOException {
        List<String> contents = Collections.singletonList(text);

        Files.write(Paths.get(path), contents,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE);

    }


}

