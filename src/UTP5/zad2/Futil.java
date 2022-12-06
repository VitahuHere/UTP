package UTP5.zad2;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil implements FileVisitor<Path> {

    private final BufferedWriter writer;

    public Futil(String resultFileName) throws IOException {
        writer = Files.newBufferedWriter(Paths.get("./" + resultFileName), StandardCharsets.UTF_8);
    }

    public void close() throws IOException {
        writer.close();
    }

    public static void processDir(String dirName, String resultFileName) {
        try {
            Futil futil = new Futil(resultFileName);
            Files.walkFileTree(Paths.get(dirName), futil);
            futil.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Files.readAllLines(file, Charset.forName("cp1250")).forEach(line -> {
            try {
                writer.write(line);
                writer.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
