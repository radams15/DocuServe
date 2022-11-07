package uk.co.therhys.docuserve;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DocumentController {

    @GetMapping("/document")
    public ResponseEntity<Document> document(@RequestParam(value="id") String id){
        return new ResponseEntity<>(new Document(
                id,
                "# Document\n\n## Subheading!",
                "/Documents/doc1.md"
        ), HttpStatus.OK);
    }

    @PostMapping("/document")
    public ResponseEntity<Document> postDocument(@RequestBody @NonNull Document document){
        System.out.println(document);

        return new ResponseEntity<>(document, HttpStatus.OK);
    }
}
