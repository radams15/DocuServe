package uk.co.therhys.docuserve.Api;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import uk.co.therhys.docuserve.Db.DocumentManager;

import java.io.IOException;

@RestController
public class RenderController {
    @Autowired
    private DocumentManager documentManager;
    private final HtmlRenderer renderer = HtmlRenderer.builder().build();
    private final Parser parser = Parser.builder().build();


    private String renderContent(String in){
        Node document = parser.parse(in);
        return renderer.render(document);
    }

    @GetMapping("/render")
    ResponseEntity<RenderedDocument> render(@RequestParam(name = "path") String path){
        try {
            String content = documentManager.read(path);

            String out = renderContent(content);

            return new ResponseEntity<>(
                    new RenderedDocument(out),
                    HttpStatus.OK
            );

        }catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity<>(
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
