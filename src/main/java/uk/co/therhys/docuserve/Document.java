package uk.co.therhys.docuserve;

import lombok.Data;

@Data
public class Document {
    private String id;
    private String content;
    private String path;

    public Document(String id, String content, String path){
        this.id = id;
        this.content = content;
        this.path = path;
    }
}
