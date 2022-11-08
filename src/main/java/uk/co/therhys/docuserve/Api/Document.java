package uk.co.therhys.docuserve.Api;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class Document {
    private Integer id;
    private String content;

    private String path;

    public Document(Integer id, String content, String path){
        this.id = id;
        this.content = content;
        this.path = path;
    }
}
