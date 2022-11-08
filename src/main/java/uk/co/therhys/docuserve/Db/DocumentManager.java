package uk.co.therhys.docuserve.Db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class DocumentManager {
    private String base;

    public DocumentManager(@Value("${docuserve.root}") String base){
        this.base = base;

        System.out.println("================== Base: " + base + " ================== ");
    }

    private Path getFile(String file){
        Path path = Paths.get(base, file).toAbsolutePath();

        return path;
    }

    private boolean exists(String filePath){
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
}
