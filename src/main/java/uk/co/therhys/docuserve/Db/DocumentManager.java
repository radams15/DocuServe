package uk.co.therhys.docuserve.Db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class DocumentManager {
    private String base;

    public DocumentManager(@Value("${docuserve.root}") String base){
        this.base = base;

        System.out.println("================== Base path: " + base + " ================== ");
    }

    private Path getFile(String file){
        return Paths.get(base, file).toAbsolutePath();
    }

    public boolean exists(String filePath){
        return getFile(filePath).toFile().exists();
    }

    public void write(String filePath, String content) throws IOException {
        Path path = getFile(filePath);

        Files.writeString(path, content);
    }

    public String read(String filePath) throws IOException {
        Path path = getFile(filePath);

        return Files.readString(path);
    }

    public Stream<Path> getAll() throws IOException {
        Path basePath = Paths.get(base);

        return Files.find(basePath, 999, (p, bfa) -> bfa.isRegularFile()).map(basePath::relativize);
    }
}
