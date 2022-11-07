package uk.co.therhys.docuserve;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

@RestController
public class RenderController {
    private final HtmlRenderer renderer = HtmlRenderer.builder().build();
    private final Parser parser = Parser.builder().build();


    private String render(String in){
        Node document = parser.parse(in);
        return renderer.render(document);
    }

    @PostMapping("/render")
    ResponseEntity<RenderedDocument> render(@RequestBody @NonNull RenderRequest toRender){
        String out = render(toRender.getContent());

        return new ResponseEntity<>(
                new RenderedDocument(out),
                HttpStatus.OK
        );
    }
}
