package uk.co.therhys.docuserve.Api;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.therhys.docuserve.Db.DocumentManager;
import uk.co.therhys.docuserve.Db.DocumentRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DocumentController {
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentManager documentManager;

    @GetMapping("/documents")
    public ResponseEntity<List<Document>> documents(){
        ArrayList<Document> out = new ArrayList<>();

        documentRepository.findAll()
                .forEach(
                    i ->
                        out.add(new Document(i.getId(), "", i.getPath()))
        );

        return new ResponseEntity<>(out, HttpStatus.OK);
    }

    @GetMapping("/document")
    public ResponseEntity<Document> document(@RequestParam(value="id") Integer id){
        uk.co.therhys.docuserve.Db.Document doc = documentRepository.findById(id).orElse(null);

        if(doc != null) {
            try {
                String content = documentManager.read(doc.getPath());

                Document out = new Document(doc.getId(), content, doc.getPath());

                return new ResponseEntity<>(out, HttpStatus.OK);
            }catch (IOException e){
                e.printStackTrace();

                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/document")
    public ResponseEntity<Document> postDocument(@RequestBody @NonNull Document document){
        System.out.println(document);

        uk.co.therhys.docuserve.Db.Document doc = new uk.co.therhys.docuserve.Db.Document(document.getId(), document.getPath());

        documentRepository.save(doc);

        try {
            documentManager.write(document.getPath(), document.getContent());
        }catch (IOException e){
            e.printStackTrace();

            return new ResponseEntity<>(document, HttpStatus.UNAUTHORIZED);
        }

        document.setId(doc.getId());

        return new ResponseEntity<>(document, HttpStatus.OK);
    }
}
