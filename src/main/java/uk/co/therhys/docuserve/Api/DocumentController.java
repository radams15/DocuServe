package uk.co.therhys.docuserve.Api;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.therhys.docuserve.Db.DocumentManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DocumentController {
    @Autowired
    private DocumentManager documentManager;

    @GetMapping("/documents")
    public ResponseEntity<List<Document>> documents(){
        try {
            List<Document> out = documentManager.getAll()
                    .map(
                            (Path i) ->
                                    new Document("", i.toString())
                    ).collect(Collectors.toList());

            return new ResponseEntity<>(out, HttpStatus.OK);
        }catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/document")
    public ResponseEntity<Document> document(@RequestParam(value="path") String path){
        if(documentManager.exists(path)) {
            try {
                String content = documentManager.read(path);

                Document out = new Document(content, path);

                return new ResponseEntity<>(out, HttpStatus.OK);
            }catch (IOException e){
                e.printStackTrace();

                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @PostMapping("/document")
    public ResponseEntity<Document> postDocument(@RequestBody @NonNull Document document){
        System.out.println(document);

        try {
            documentManager.write(document.getPath(), document.getContent());
        }catch (IOException e){
            e.printStackTrace();

            return new ResponseEntity<>(document, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(document, HttpStatus.OK);
    }
}
