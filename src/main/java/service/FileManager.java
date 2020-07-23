package service;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public static String Readfile(String paht) {
        int count;
        StringBuilder output = new StringBuilder();
        Path filepathe = Paths.get(paht);

        try (SeekableByteChannel scan = Files.newByteChannel(filepathe)) {
            ByteBuffer mBuf = ByteBuffer.allocate(1000);
            do {
                count = scan.read(mBuf);
                if (count != -1) {
                    mBuf.rewind();
                    for (int i = 0; i < count; i++) {
                        output.append((char) mBuf.get());

                    }
                }
            }
            while (count != -1);
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}



